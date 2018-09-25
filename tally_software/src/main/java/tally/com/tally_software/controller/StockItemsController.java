package tally.com.tally_software.controller;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tally.com.tally_software.model.Company;
import tally.com.tally_software.model.StockGroups;
import tally.com.tally_software.model.StockItems;
import tally.com.tally_software.model.Unit;
import tally.com.tally_software.repository.StockItemsRepository;

import tally.com.tally_software.service.CompanyService;
import tally.com.tally_software.service.StockGroupService;
import tally.com.tally_software.service.StockItemsService;
import tally.com.tally_software.service.UnitService;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/navbar")
public class StockItemsController {


    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    StockItemsRepository stockItemsRepository;

    @Autowired
    StockItemsService stockItemsService;

    @Autowired
    CompanyService companyService;

    @Autowired
    StockGroupService stockGroupService;

    @Autowired
    UnitService unitService;


    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @RequestMapping(path = "/{companyId}/itemsList", method = RequestMethod.GET)
    public ModelAndView getAllItem(@PathVariable("companyId") int companyId, Authentication authentication, HttpServletRequest request, Unit unit) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("companyId", companyId);
        modelAndView.addObject("companyList", companyService.getCompanyList().stream().filter(u->u.getCompany_id() == companyId).collect(Collectors.toList()));
        Principal principal = request.getUserPrincipal();

        List<StockItems> stockItems = new ArrayList<>();


        modelAndView.setViewName("/items/itemsList");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String created_by = authentication.getAuthorities().toString();

        List<StockItems> itemsList = stockItemsService.getAllItems().stream().filter(u ->u.getCompany().getCompany_id()==companyId &&  u.getCreated_by().equals(auth.getName())).collect(Collectors.toList());
//        for (StockItems stockItems1: itemsList){
//            System.out.println(stockItems1.getItemName());
//        }


        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        modelAndView.addObject("itemsList", itemsList);


        return modelAndView;
    }

    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @GetMapping("/{companyId}/createItem")
    public ModelAndView createItem(Unit unit, Authentication authentication, @PathVariable(value = "companyId") int companyId) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String created_by = authentication.getAuthorities().toString();
        modelAndView.addObject("companyId", companyId);
        modelAndView.addObject("companyList", companyService.getCompanyList().stream().filter(u->u.getCompany_id() == companyId).collect(Collectors.toList()));
        List<StockGroups> stockGroupsList = stockGroupService.getStockGroupsList().stream().filter(u ->u.getCompany().getCompany_id()==companyId && u.getCreated_by().equals(auth.getName())).collect(Collectors.toList());
        List<Unit> unitList = unitService.getAllUnit().stream().filter(u ->u.getCompany().getCompany_id()==companyId && u.getCreated_by().equals(auth.getName())).collect(Collectors.toList());

        modelAndView.addObject("stockGroupsList", stockGroupsList);
        modelAndView.addObject("unitList", unitList);
//        System.out.println("companyID : " + companyId);

        modelAndView.setViewName("items/createItem");
        modelAndView.addObject("item", new StockItems());
        return modelAndView;
    }


    @PreAuthorize("hasAnyRole( 'ROLE_USER')")
    @RequestMapping(path = "/{companyId}/item/save", method = RequestMethod.POST)
    public ModelAndView saveItem(RedirectAttributes redirectAttributes, Authentication authentication,
                                 HttpServletRequest request, @Valid @ModelAttribute("item") StockItems stockItems,
                                 BindingResult result, @PathVariable int companyId
    ) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("companyId", companyId);


//        if (result.hasErrors()) {
//            logger.info("Validation Error");
//            modelAndView.setViewName("/items/createItem");
//            modelAndView.addObject("item", stockItems);
//            return modelAndView;
//        }


        Company company = companyService.getCompany(companyId).get();
        stockItems.setCreated_time(new Date());
        Principal principal = request.getUserPrincipal();
        stockItems.setCreated_by(principal.getName());
        stockItems.setCompany(company);
        stockItemsService.save(stockItems);
        redirectAttributes.addFlashAttribute("message", "Successfully create a Item. X");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");


        modelAndView.setViewName("redirect:/navbar/" + companyId + "/createItem");
        return modelAndView;
    }

    @PreAuthorize("hasAnyRole( 'ROLE_USER')")
//    @GetMapping("/{companyId}/group/edit/{id}")
    @RequestMapping(path = "/{companyId}/item/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editItem(@PathVariable int id, @PathVariable int companyId) {
        Optional<StockItems> stockItems = stockItemsService.getOne(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("companyId", companyId);
        modelAndView.addObject("companyList", companyService.getCompanyList().stream().filter(u->u.getCompany_id() == companyId).collect(Collectors.toList()));
        modelAndView.addObject("stockGroupsList", stockGroupService.getStockGroupsList());
        modelAndView.addObject("unitList", unitService.getAllUnit().stream().filter(u->u.getCompany().getCompany_id()==companyId).collect(Collectors.toList()));

        modelAndView.setViewName("items/updateItem");


        modelAndView.addObject("item", stockItems.get());
        return modelAndView;
    }

    @PreAuthorize("hasAnyRole( 'ROLE_USER')")
    @PostMapping("/{companyId}/updateItem/{id}")
    public ModelAndView updateItem(HttpServletRequest request, @Valid @ModelAttribute("item") StockItems stockItems, BindingResult result, @PathVariable int id, @PathVariable int companyId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("companyId", companyId);
        modelAndView.addObject("stockGroupsList", stockGroupService.getStockGroupsList());
        modelAndView.addObject("unitList", unitService.getAllUnit());

        if (result.hasErrors()) {
            logger.info("Validation errors while submitting form.");
            modelAndView.setViewName("unit/updateUnit");
            modelAndView.addObject("item", stockItems);
            return modelAndView;
        }
        Optional<StockItems> stockItems1 = stockItemsService.getOne(id);

        stockItems.setId(id);
        stockItems.setCreated_time(new Date());
        Principal principal = request.getUserPrincipal();
        stockItems.setCreated_by(principal.getName());
        stockItemsService.save(stockItems);
        modelAndView.setViewName("redirect:/navbar/" + companyId + "/itemsList");
        return modelAndView;
    }


    @PreAuthorize("hasAnyRole( 'ROLE_USER')")
    @RequestMapping(path = "/{companyId}/item/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteItem(@PathVariable int id, @PathVariable int companyId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("companyId", companyId);
        stockItemsRepository.deleteById(id);
        return new ModelAndView("redirect:/navbar/" + companyId + "/itemsList");
    }

//    @RequestMapping(value = "/{companyId}/download", method = RequestMethod.GET)
//    public StreamingResponseBody getSteamingFile(HttpServletResponse response, @PathVariable int companyId) throws IOException {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("companyId", companyId);
//        response.setContentType("text/html;charset=UTF-8");
//        response.setHeader("Content-Disposition", "attachment; filename=\"itemsList.pdf\"");
//        InputStream inputStream = new FileInputStream(new File("C:\\Users\\Nazmul\\Downloads\\tally_software\\src\\main\\resources\\templates\\items\\itemsList.html"));
//
//        return outputStream -> {
//            int nRead;
//            byte[] data = new byte[1024];
//            while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
//                outputStream.write(data, 0, nRead);
//            }
//            inputStream.close();
//        };
//    }

    @RequestMapping(path = "/{companyId/download}", method = RequestMethod.GET)
    public String downloadFile(@PathVariable int companyId, Authentication authentication) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/navbar/" + companyId + "/itemsList");
        modelAndView.addObject("companyId", companyId);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        try {
            String fileName = "C:\\Users\\Nazmul\\Downloads\\itemsList.pdf";
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();
            List<StockItems> itemsList = stockItemsService.getAllItems().stream().filter(u -> u.getCreated_by().equals(auth.getName())).collect(Collectors.toList());
            for (StockItems stockItems1 : itemsList) {
                System.out.println(stockItems1.getItemName());
                Paragraph paragraph = new Paragraph(stockItems1.getItemName() + ". " + stockItems1.getStockGroups() + ". " + stockItems1.getTotal());
                document.add(paragraph);
                document.add(new Paragraph(" "));

            }


            document.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }


}

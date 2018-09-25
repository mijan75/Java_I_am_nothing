package tally.com.tally_software.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tally.com.tally_software.model.Company;
import tally.com.tally_software.model.StockGroups;
import tally.com.tally_software.repository.StockGroupsRepository;
import tally.com.tally_software.service.CompanyService;
import tally.com.tally_software.service.StockGroupService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/navbar")
public class StockGroupsController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    StockGroupsRepository stockGroupsRepository;

    @Autowired
    StockGroupService stockGroupService;

    @Autowired
    CompanyService companyService;


    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @RequestMapping(path = "/{companyId}/stockList", method = RequestMethod.GET)
    public ModelAndView getAllStockGroups(@PathVariable int companyId, Authentication authentication, HttpServletRequest request, StockGroups stockGroups) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("companyId", companyId);
        modelAndView.addObject("companyList", companyService.getCompanyList().stream().filter(u->u.getCompany_id() == companyId).collect(Collectors.toList()));
        Principal principal = request.getUserPrincipal();

        modelAndView.setViewName("/inventory/stockList");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String created_by = authentication.getAuthorities().toString();

        List<StockGroups> stockGroupsList = stockGroupService.getStockGroupsList().stream().filter(u ->u.getCompany().getCompany_id()==companyId && u.getCreated_by().equals(auth.getName())).collect(Collectors.toList());


        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        modelAndView.addObject("stockGroupsList", stockGroupsList);


        return modelAndView;
    }

    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @GetMapping("/{companyId}/createStockGroups")
    public ModelAndView CreateStock(StockGroups stockGroups, Authentication authentication, @PathVariable(value = "companyId") int companyId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("companyId", companyId);
        modelAndView.addObject("companyList", companyService.getCompanyList().stream().filter(u->u.getCompany_id() == companyId).collect(Collectors.toList()));
//        System.out.println("companyID : " + companyId);

        modelAndView.setViewName("/inventory/createStockGroups");
        modelAndView.addObject("stock", new StockGroups());
        return modelAndView;
    }


    @PreAuthorize("hasAnyRole( 'ROLE_USER')")
    @RequestMapping(path = "/{companyId}/stock/save", method = RequestMethod.POST)
    public ModelAndView saveStock(RedirectAttributes redirectAttributes, Authentication authentication,
                                  HttpServletRequest request, @Valid @ModelAttribute("stockGroups") StockGroups stockGroups,
                                  BindingResult result, @PathVariable int companyId
    ) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("companyId", companyId);


        if (result.hasErrors()) {
            logger.info("Validation Error");
            modelAndView.setViewName("/inventory/createStockGroups");
            modelAndView.addObject("stockGroups", stockGroups);
            return modelAndView;
        }


        Company company = companyService.getCompany(companyId).get();
        stockGroups.setCreated_time(new Date());
        Principal principal = request.getUserPrincipal();
        stockGroups.setCreated_by(principal.getName());
//        ledger.setGroups(groupService.getGroups(group).get());
        stockGroups.setCompany(company);
        stockGroupService.save(stockGroups);
        redirectAttributes.addFlashAttribute("message", "Successfully create a Stock Group. X");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");


        modelAndView.setViewName("redirect:/navbar/" + companyId + "/createStockGroups");
        return modelAndView;
    }

    @PreAuthorize("hasAnyRole( 'ROLE_USER')")
//    @GetMapping("/{companyId}/group/edit/{id}")
    @RequestMapping(path = "/{companyId}/stock/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editStock(@PathVariable int id, @PathVariable int companyId) {
        Optional<StockGroups> groups = stockGroupService.getStockGroup(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("companyList", companyService.getCompanyList().stream().filter(u->u.getCompany_id() == companyId).collect(Collectors.toList()));
        modelAndView.addObject("companyId", companyId);

        modelAndView.setViewName("inventory/updateStock");


        modelAndView.addObject("stock", groups.get());
        return modelAndView;
    }

    @PreAuthorize("hasAnyRole( 'ROLE_USER')")
    @PostMapping("/{companyId}/updateStock/{id}")
    public ModelAndView updateStock(HttpServletRequest request, @Valid @ModelAttribute("stock") StockGroups stockGroups, BindingResult result, @PathVariable int id, @PathVariable int companyId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("companyId", companyId);

        if (result.hasErrors()) {
            logger.info("Validation errors while submitting form.");
            modelAndView.setViewName("inventory/updateStock");
            modelAndView.addObject("stock", stockGroups);
            return modelAndView;
        }
        Optional<StockGroups> stockGroups1 = stockGroupService.getStockGroup(id);

        stockGroups.setId(id);
        stockGroups.setCreated_time(new Date());
        Principal principal = request.getUserPrincipal();
        stockGroups.setCreated_by(principal.getName());
        stockGroupService.update(stockGroups);
        modelAndView.setViewName("redirect:/navbar/" + companyId + "/stockList");
        return modelAndView;
    }


    @PreAuthorize("hasAnyRole( 'ROLE_USER')")
    @RequestMapping(path = "/{companyId}/stock/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteStock(@PathVariable int id, @PathVariable int companyId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("companyId", companyId);
        stockGroupsRepository.deleteById(id);
        return new ModelAndView("redirect:/navbar/" + companyId + "/stockList");
    }
}

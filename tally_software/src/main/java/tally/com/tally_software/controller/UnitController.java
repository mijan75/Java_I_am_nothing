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
import tally.com.tally_software.model.Unit;
import tally.com.tally_software.repository.StockGroupsRepository;
import tally.com.tally_software.repository.UnitRepository;
import tally.com.tally_software.service.CompanyService;
import tally.com.tally_software.service.StockGroupService;
import tally.com.tally_software.service.UnitService;

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
public class UnitController {


    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UnitRepository unitRepository;

    @Autowired
    UnitService unitService;

    @Autowired
    CompanyService companyService;


    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @RequestMapping(path = "/{companyId}/unitList", method = RequestMethod.GET)
    public ModelAndView getAllUnit(@PathVariable int companyId, Authentication authentication, HttpServletRequest request, Unit unit) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("companyId", companyId);
        modelAndView.addObject("companyList", companyService.getCompanyList().stream().filter(u->u.getCompany_id() == companyId).collect(Collectors.toList()));
        Principal principal = request.getUserPrincipal();

        modelAndView.setViewName("/unit/unitList");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String created_by = authentication.getAuthorities().toString();

        List<Unit> unitList = unitService.getAllUnit().stream().filter(u -> u.getCompany().getCompany_id()==companyId && u.getCreated_by().equals(auth.getName())).collect(Collectors.toList());


        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        modelAndView.addObject("unitList", unitList);


        return modelAndView;
    }

    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @GetMapping("/{companyId}/createUnit")
    public ModelAndView createUnit(Unit unit, Authentication authentication, @PathVariable(value = "companyId") int companyId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("companyId", companyId);
//        System.out.println("companyID : " + companyId);
        modelAndView.addObject("companyList", companyService.getCompanyList().stream().filter(u->u.getCompany_id() == companyId).collect(Collectors.toList()));

        modelAndView.setViewName("/unit/createUnit");
        modelAndView.addObject("unit", new Unit());
        return modelAndView;
    }


    @PreAuthorize("hasAnyRole( 'ROLE_USER')")
    @RequestMapping(path = "/{companyId}/unit/save", method = RequestMethod.POST)
    public ModelAndView saveStock(RedirectAttributes redirectAttributes, Authentication authentication,
                                  HttpServletRequest request, @Valid @ModelAttribute("unit") Unit unit,
                                  BindingResult result, @PathVariable int companyId
    ) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("companyId", companyId);


        if (result.hasErrors()) {
            logger.info("Validation Error");
            modelAndView.setViewName("/unit/createUnit");
            modelAndView.addObject("unit", unit);
            return modelAndView;
        }


        Company company = companyService.getCompany(companyId).get();
        unit.setCreated_time(new Date());
        Principal principal = request.getUserPrincipal();
        unit.setCreated_by(principal.getName());
        unit.setCompany(company);
        unitService.save(unit);
        redirectAttributes.addFlashAttribute("message", "Successfully create a Unit. X");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");


        modelAndView.setViewName("redirect:/navbar/" + companyId + "/createUnit");
        return modelAndView;
    }

    @PreAuthorize("hasAnyRole( 'ROLE_USER')")
//    @GetMapping("/{companyId}/group/edit/{id}")
    @RequestMapping(path = "/{companyId}/unit/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editStock(@PathVariable int id, @PathVariable int companyId) {
        Optional<Unit> unit = unitService.getOneUnit(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("companyId", companyId);
        modelAndView.addObject("companyList", companyService.getCompanyList().stream().filter(u->u.getCompany_id() == companyId).collect(Collectors.toList()));

        modelAndView.setViewName("unit/updateUnit");


        modelAndView.addObject("unit", unit.get());
        return modelAndView;
    }

    @PreAuthorize("hasAnyRole( 'ROLE_USER')")
    @PostMapping("/{companyId}/updateUnit/{id}")
    public ModelAndView updateStock(HttpServletRequest request, @Valid @ModelAttribute("unit") Unit unit, BindingResult result, @PathVariable int id, @PathVariable int companyId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("companyId", companyId);

        if (result.hasErrors()) {
            logger.info("Validation errors while submitting form.");
            modelAndView.setViewName("unit/updateUnit");
            modelAndView.addObject("unit", unit);
            return modelAndView;
        }
        Optional<Unit> unit1 = unitService.getOneUnit(id);

        unit.setId(id);
        unit.setCreated_time(new Date());
        Principal principal = request.getUserPrincipal();
        unit.setCreated_by(principal.getName());
        unitService.save(unit);
        modelAndView.setViewName("redirect:/navbar/" + companyId + "/unitList");
        return modelAndView;
    }


    @PreAuthorize("hasAnyRole( 'ROLE_USER')")
    @RequestMapping(path = "/{companyId}/unit/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteStock(@PathVariable int id, @PathVariable int companyId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("companyId", companyId);
        unitRepository.deleteById(id);
        return new ModelAndView("redirect:/navbar/" + companyId + "/unitList");
    }
}

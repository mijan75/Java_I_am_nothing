package tally.com.tally_software.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tally.com.tally_software.model.*;
import tally.com.tally_software.repository.LedgerRepository;
import tally.com.tally_software.service.CompanyService;
import tally.com.tally_software.service.DevGroupService;
import tally.com.tally_software.service.GroupService;
import tally.com.tally_software.service.LedgerService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/navbar")
public class LedgerController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private GroupService groupService;
    @Autowired
    private DevGroupService devGroupService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private LedgerService ledgerService;

    @Autowired
    private LedgerRepository ledgerRepository;

//    @GetMapping("/{id}/ledger")
//    public ModelAndView ledger( @PathVariable(value = "id") int id){
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("companyId", id);
//        modelAndView.addObject("groupList", groupService.getAllGroups());
//        modelAndView.setViewName("ledger/ledgerLists");
//
////        System.out.println(ledgerService.getAllLedger().get(0).getCompany());
//
//        List<Ledger> ledgerList = ledgerService.getAllLedger().stream().filter(u -> u.getCompany().getCompany_id() == id).collect(Collectors.toList());
//
//
////
//        modelAndView.addObject("ledgerList", ledgerList);
//        return modelAndView;
//    }

    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @GetMapping("/{companyId}/ledger/lists")
    public ModelAndView ledgerLists(@PathVariable(value = "companyId") int companyId, Authentication authentication, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        Principal principal = request.getUserPrincipal();
        modelAndView.addObject("companyId", companyId);
        modelAndView.setViewName("ledger/ledgerLists");
        System.out.println(companyId);

        List<Ledger> ledgerList = ledgerService.getAllLedger().stream().filter(u -> u.getCompany().getCompany_id() == companyId && u.getCompany().getUsername().equals(principal.getName())).collect(Collectors.toList());

        modelAndView.addObject("ledgerList", ledgerList);
        return modelAndView;

    }

    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @GetMapping("/{id}/ledger/createLedger")
    public ModelAndView createCompany(Ledger ledger, Authentication authentication, HttpServletRequest request, @PathVariable(value = "id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        Principal principal = request.getUserPrincipal();
        modelAndView.addObject("companyId", id);
        modelAndView.addObject("groupList", devGroupService.getAllDevGroup());
//       modelAndView.setViewName("ledger/ledgerLists");
        modelAndView.setViewName("/ledger/createLedger");


        modelAndView.addObject("ledger", new Ledger());
        return modelAndView;
    }


    @PostMapping("/{id}/ledger/save")
    public ModelAndView ledgeProcessr(RedirectAttributes redirectAttributes, Authentication authentication,
                                      HttpServletRequest request, @Valid @ModelAttribute("ledger") Ledger ledger,
                                      BindingResult result, Model model, @RequestParam(value = "companyId") int companyId,
                                      String ledgerName) {


        ModelAndView modelAndView = new ModelAndView();
        if (result.hasErrors()) {
            logger.info("Validation Error");
            modelAndView.setViewName("/ledger/createLedger");
            modelAndView.addObject("ledger", ledger);
            return modelAndView;
        }

        Ledger ledgers = new Ledger();
        Company company = companyService.getCompany(companyId).get();



//        ledger.setGroups(groupService.getGroups(group).get());
        ledger.setCompany(company);
        ledgerService.save(ledger);

        DevGroup devGroup = ledger.getDevGroup();
        devGroup.getLedgers().add(ledger);
        devGroupService.save(devGroup);

        redirectAttributes.addFlashAttribute("message", "Successfully create a Ledger. X");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        /*model.addAttribute("companyId", id);
        model.addAttribute("groupList", groupService.getAllGroups());*/


//        ledgers.setLedger_name(ledgerName);
//        ledger.setEmail("nazmul@mail.com");


//        company.setLedgers(Arrays.asList(ledger));

        modelAndView.setViewName("redirect:/navbar/" + companyId + "/ledger/lists");
//        companyService.createCompany(company);
        return modelAndView;
//        return "redirect:/navbar/"+ companyId +"/ledger";
    }


    @PreAuthorize("hasAnyRole( 'ROLE_USER')")
//    @GetMapping("/{companyId}/ledger/edit/{id}")
    @RequestMapping(path = "/{companyId}/ledger/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editLedger(@PathVariable int id, @PathVariable int companyId) {
        Optional<Ledger> ledgers = ledgerService.getLedger(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("companyId", companyId);
        modelAndView.addObject("groupList", devGroupService.getAllDevGroup());
        modelAndView.setViewName("ledger/updateLedger");

        System.out.println("List Company id:" + companyId);


        modelAndView.addObject("ledger", ledgers.get());
        return modelAndView;
    }

    @PreAuthorize("hasAnyRole( 'ROLE_USER')")
//    @PostMapping("/{companyId}/ledger/updateLedger/{id}")
    @RequestMapping(path = "/{companyId}/ledger/updateLedger/{id}", method = RequestMethod.POST)
    public ModelAndView updateLedger(HttpServletRequest request, @PathVariable int companyId, @Valid @ModelAttribute("ledger") Ledger ledger, BindingResult result, @PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("companyId", companyId);
        System.out.println("companyId : " + companyId);
        System.out.println("ledgerId :" + id);
        if (result.hasErrors()) {
            logger.info("Validation errors while submitting form.");
            modelAndView.setViewName("ledger/updateLedger");
            modelAndView.addObject("ledger", ledger);
            return modelAndView;
        }
        Optional<Ledger> ledgers = ledgerService.getLedger(id);
//        Company company = companyService.getCompany(companyId).get();


//        Company company = companyService.getCompany(companyId).get();
//        company.setCompany_id(companyId);

//        ledger.setGroups(groupService.getGroups(group).get());
//        ledger.setCompany(company);
        ledgerService.save(ledger);
        modelAndView.setViewName("redirect:/navbar/" + companyId + "/ledger/lists");
//        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }

    @PreAuthorize("hasAnyRole( 'ROLE_USER')")
//        @GetMapping("/{id}/ledger/delete/{id}")
    @RequestMapping(path = "/{companyId}/ledger/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteUser(@PathVariable int id, @PathVariable int companyId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("companyId", companyId);
        ledgerRepository.deleteById(id);
        modelAndView.setViewName("redirect:/navbar/" + companyId + "/ledger/lists");
        return modelAndView;
    }

}

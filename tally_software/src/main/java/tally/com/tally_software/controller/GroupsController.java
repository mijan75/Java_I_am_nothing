package tally.com.tally_software.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tally.com.tally_software.model.*;
import tally.com.tally_software.repository.GroupRepository;
import tally.com.tally_software.service.CompanyService;
import tally.com.tally_software.service.DevGroupService;
import tally.com.tally_software.service.GroupService;
import tally.com.tally_software.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/navbar")
public class GroupsController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    GroupService groupService;

    @Autowired
    CompanyService companyService;

    @Autowired
    DevGroupService devGroupService;


    @Autowired
    GroupRepository groupRepository;

    @Autowired
    UserService userService;

    public int cmpaid;

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "error/access-denied";
    }


    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @RequestMapping(path = "/{companyId}/yourgrouplists", method = RequestMethod.GET)
    public ModelAndView getAllYourGroups(Model model, @PathVariable(value = "companyId") int companyId, Authentication authentication, HttpServletRequest request, Groups groups) {
//        model.addAttribute("companyId", companyId);
        Principal principal = request.getUserPrincipal();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("companyId", companyId);
        modelAndView.setViewName("/groups/yourgrouplists");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String created_by = authentication.getAuthorities().toString();

        List<Groups> groupList = groupService.getAllGroups().stream().filter(u -> u.getCompany().getCompany_id() == companyId).collect(Collectors.toList());


        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        modelAndView.addObject("grouplist", groupList);


        return modelAndView;
    }

    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @RequestMapping(path = "/{companyId}/grouplists", method = RequestMethod.GET)
    public ModelAndView getAllGroups(Model model, @PathVariable int companyId, Authentication authentication, HttpServletRequest request, Groups groups) {
        model.addAttribute("companyId", companyId);
        Principal principal = request.getUserPrincipal();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/groups/grouplists");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String created_by = authentication.getAuthorities().toString();

        List<DevGroup> devGroupList = devGroupService.getAllDevGroup();


        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        modelAndView.addObject("devgrouplist", devGroupList);


        return modelAndView;
    }

    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @GetMapping("/{companyId}/createGroup")
    public ModelAndView CreateGroup(Groups groups, Authentication authentication, @PathVariable(value = "companyId") int companyId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("companyId", companyId);
//        System.out.println("companyID : " + companyId);
        modelAndView.addObject("devGroupList", devGroupService.getAllDevGroup());
        modelAndView.setViewName("/groups/createGroup");
        modelAndView.addObject("group", new Groups());
        return modelAndView;
    }

//    @PreAuthorize("hasAnyRole( 'ROLE_USER')")
////    @PostMapping("/{companyId}/groups/saveGroup")
//    @RequestMapping(path = "/{companyId}/groups/saveGroup", method = RequestMethod.POST)
//    public ModelAndView saveGroup(RedirectAttributes redirectAttributes, @PathVariable int companyId, Authentication authentication, HttpServletRequest request, @Valid @ModelAttribute("group") Groups groups, BindingResult result){
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("companyId", companyId);
//        if(result.hasErrors()){
//            logger.info("Validation Error");
//            modelAndView.setViewName("/groups/createGroup");
//            modelAndView.addObject("group", groups);
//            return modelAndView;
//        }
//        System.out.println("company Id: "+companyId);
//
//        groups.setCreated_time(new Date());
//        Principal principal = request.getUserPrincipal();
//        groups.setCreated_by(principal.getName());
//        Company company = companyService.getCompany(companyId).get();
//        groups.setCompany(company);
//        groupService.saveGroups(groups);
//        redirectAttributes.addFlashAttribute("message", "Successfully create a new group. X");
//        redirectAttributes.addFlashAttribute("alertClass", "alert-success");
//        modelAndView.setViewName("redirect:/navbar/"+companyId+"/groups/createGroup");
//        return modelAndView;
//    }

    @PreAuthorize("hasAnyRole( 'ROLE_USER')")
//    @PostMapping("/{companyId}/groups/save")
    @RequestMapping(path = "/{companyId}/groups/save", method = RequestMethod.POST)
    public ModelAndView saveGroup(RedirectAttributes redirectAttributes, Authentication authentication,
                                  HttpServletRequest request, @Valid @ModelAttribute("group") Groups groups,
                                  BindingResult result, @PathVariable int companyId
    ) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("companyId", companyId);


        System.out.println("companyID : " + companyId);
        if (result.hasErrors()) {
            logger.info("Validation Error");
            modelAndView.setViewName("/groups/createGroup");
            modelAndView.addObject("groups", groups);
            return modelAndView;
        }

        Groups groups1 = new Groups();
        Company company = companyService.getCompany(companyId).get();
        groups.setCreated_time(new Date());
        Principal principal = request.getUserPrincipal();
        groups.setCreated_by(principal.getName());
//        ledger.setGroups(groupService.getGroups(group).get());
        groups.setCompany(company);
        groupService.saveGroups(groups);
        redirectAttributes.addFlashAttribute("message", "Successfully create a Group. X");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        /*model.addAttribute("companyId", id);
        model.addAttribute("groupList", groupService.getAllGroups());*/


//        ledgers.setLedger_name(ledgerName);
//        ledger.setEmail("nazmul@mail.com");


//        company.setLedgers(Arrays.asList(ledger));

        modelAndView.setViewName("redirect:/navbar/" + companyId + "/yourgrouplists");
//        companyService.createCompany(company);
        return modelAndView;
//        return "redirect:/navbar/"+ companyId +"/ledger";
    }


    @PreAuthorize("hasAnyRole( 'ROLE_USER')")
//    @GetMapping("/{companyId}/group/edit/{id}")
    @RequestMapping(path = "/{companyId}/group/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editUser(@PathVariable int id, @PathVariable int companyId) {
        Optional<Groups> groups = groupService.getGroups(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("companyId", companyId);
        System.out.println("Update CompanyId:" + companyId);
        modelAndView.addObject("devGroupList", devGroupService.getAllDevGroup());
        modelAndView.setViewName("groups/updateGroup");


        modelAndView.addObject("group", groups.get());
        return modelAndView;
    }


    @PreAuthorize("hasAnyRole( 'ROLE_USER')")
    @PostMapping("/{companyId}/updateGroup/{id}")
    public ModelAndView updateUser(HttpServletRequest request, @Valid @ModelAttribute("group") Groups groups, BindingResult result, @PathVariable int id, @PathVariable int companyId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("companyId", companyId);

        if (result.hasErrors()) {
            logger.info("Validation errors while submitting form.");
            modelAndView.setViewName("groups/updateGroup");
            modelAndView.addObject("group", groups);
            return modelAndView;
        }
        Optional<Groups> group = groupService.getGroups(id);

        groups.setGroupId(id);
        groups.setCreated_time(new Date());
        Principal principal = request.getUserPrincipal();
        groups.setCreated_by(principal.getName());
        groupService.updateGroups(groups);
        modelAndView.setViewName("redirect:/navbar/" + companyId + "/yourgrouplists");
        return modelAndView;
    }


    @PreAuthorize("hasAnyRole( 'ROLE_USER')")
    @RequestMapping(path = "/{companyId}/group/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteUser(@PathVariable int id, @PathVariable int companyId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("companyId", companyId);
        groupRepository.deleteById(id);
        return new ModelAndView("redirect:/navbar/" + companyId + "/yourgrouplists");
    }


    @PreAuthorize("hasAnyRole( 'ROLE_USER')")
    @RequestMapping(value = "/{companyId}", method = RequestMethod.GET)
    public ModelAndView navbar(Model model, @PathVariable(value = "companyId") int companyId, HttpServletRequest request, Authentication authentication, User user, Company company) {
        ModelAndView modelAndView = new ModelAndView();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();


//        System.out.println(companyList.contains(id));
//        System.out.println(id);


        model.addAttribute("companyId", companyId);


        if (companyService.getCompany(companyId).isPresent() && auth.getName().equals(companyService.getCompany(companyId).get().getUsername())) {
            List<Company> companyList1 = companyService.getCompanyList().stream().filter(u -> u.getCompany_id() == companyId).collect(Collectors.toList());
            modelAndView.addObject("companyList1", companyList1);
            modelAndView.setViewName("/navbar");
        } else {

            modelAndView.setViewName("error/access-denied");

        }


        return modelAndView;
    }

}

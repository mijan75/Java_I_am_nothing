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
import tally.com.tally_software.model.Company;
import tally.com.tally_software.model.DevGroup;
import tally.com.tally_software.model.User;
import tally.com.tally_software.repository.DevGroupRepository;
import tally.com.tally_software.service.DevGroupService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class DevGroupController {

    private static final Logger logger = LoggerFactory.getLogger(CompanyController.class);

    @Autowired
    DevGroupService devGroupService;

    @Autowired
    DevGroupRepository devGroupRepository;

    @PreAuthorize("hasAnyRole('ROLE_DEVELOPER')")
    @GetMapping("dev/grouplists")
    public ModelAndView getAllGroup(Authentication authentication, HttpServletRequest request, DevGroup devGroup) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("devGroup/groupLists");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String created_by = authentication.getAuthorities().toString();

        List<DevGroup> devGroupList = devGroupService.getAllDevGroup();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        modelAndView.addObject("devGroupList", devGroupList);
        return modelAndView;
    }


    @PreAuthorize("hasAnyRole('ROLE_DEVELOPER')")
    @GetMapping("dev/create")
    public ModelAndView createPage(DevGroup devGroup) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        modelAndView.addObject("devGroup", devGroup);

        modelAndView.setViewName("/devGroup/create");
        modelAndView.addObject("devGroup", new DevGroup());
        return modelAndView;
    }


    @PreAuthorize("hasAnyRole('ROLE_DEVELOPER')")
    @PostMapping("/save/devGroup")
    public ModelAndView saveCompany(HttpServletRequest request, Authentication authentication, @Valid @ModelAttribute("devGroup") DevGroup devGroup, BindingResult result, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView();

        if (result.hasErrors()) {
            logger.info("Validation Error");
            modelAndView.setViewName("/devGroup/create");
            modelAndView.addObject("devGroup", devGroup);
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//            List<User> userList = userService.getAll().stream().filter(u -> u.getCreated_by().equals(auth.getName())).collect(Collectors.toList());
//
//            model.addAttribute("users", userList);
            return modelAndView;
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String created_by = authentication.getAuthorities().toString();
        devGroup.setCreated_time(new Date());
        Principal principal = request.getUserPrincipal();
        devGroup.setCreated_by(principal.getName());


        redirectAttributes.addFlashAttribute("message", "Successfully create a new Group. X");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        devGroupService.save(devGroup);
        modelAndView.setViewName("redirect:/dev/grouplists");
        return modelAndView;

    }

    @PreAuthorize("hasAnyRole('ROLE_DEVELOPER')")
    @GetMapping("devGroup/edit/{id}")
    public ModelAndView editGroup(@PathVariable int id, Authentication authentication, HttpServletRequest request) {
        Optional<DevGroup> devGroup = devGroupService.getDevGroup(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("devGroup/groupUpdate");
        Principal principal = request.getUserPrincipal();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        List<User> userList = userService.getAll().stream().filter(u -> u.getCreated_by().equals(auth.getName())).collect(Collectors.toList());
//        model.addAttribute("users", userList);
        modelAndView.addObject("devGroup", devGroup.get());
        return modelAndView;
    }


    @PreAuthorize("hasAnyRole('ROLE_DEVELOPER')")
    @PostMapping("dev/updateGroup/{id}")
    public ModelAndView updateGroup(HttpServletRequest request, Authentication authentication, @Valid @ModelAttribute("devGroup") DevGroup devGroup, BindingResult result, @PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView();

        if (result.hasErrors()) {
            logger.info("Validation errors while submitting form.");
            modelAndView.setViewName("devGroup/groupUpdate");
            modelAndView.addObject("devGroup", devGroup);
            return modelAndView;
        }

        Optional<DevGroup> devGroup1 = devGroupService.getDevGroup(id);

        devGroup.setCreated_time(new Date());
        Principal principal = request.getUserPrincipal();
        devGroup.setCreated_by(principal.getName());
        devGroup.setG_id(id);
        devGroupService.save(devGroup);
        modelAndView.setViewName("redirect:/dev/grouplists");
        return modelAndView;
    }


    @PreAuthorize("hasAnyRole('ROLE_DEVELOPER')")
    @GetMapping("/devGroup/delete/{id}")
    public ModelAndView deleteGroup(@PathVariable int id) {
        devGroupRepository.deleteById(id);
        return new ModelAndView("redirect:/dev/grouplists");
    }


}

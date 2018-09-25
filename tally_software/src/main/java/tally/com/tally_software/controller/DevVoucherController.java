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
import tally.com.tally_software.model.DevGroup;
import tally.com.tally_software.model.DevVoucher;
import tally.com.tally_software.repository.DevVoucherRepository;
import tally.com.tally_software.service.DevVoucherService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class DevVoucherController {

    private static final Logger logger = LoggerFactory.getLogger(CompanyController.class);

    @Autowired
    DevVoucherRepository devVoucherRepository;

    @Autowired
    DevVoucherService devVoucherService;


    @PreAuthorize("hasAnyRole('ROLE_DEVELOPER')")
    @GetMapping("/voucher/voucherlist")
    public ModelAndView getAllGroup(Authentication authentication, HttpServletRequest request, DevVoucher devVoucher) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("devVoucher/voucherlist");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String created_by = authentication.getAuthorities().toString();

        List<DevVoucher> devVoucherList = devVoucherService.devVoucherList();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        modelAndView.addObject("devVoucherList", devVoucherList);
        return modelAndView;
    }


    @PreAuthorize("hasAnyRole('ROLE_DEVELOPER')")
    @GetMapping("devVoucher/createVoucher")
    public ModelAndView createPage(DevVoucher devVoucher) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        modelAndView.addObject("devVoucher", devVoucher);

        modelAndView.setViewName("/devVoucher/createVoucher");
        modelAndView.addObject("devVoucher", new DevVoucher());
        return modelAndView;
    }


    @PreAuthorize("hasAnyRole('ROLE_DEVELOPER')")
    @PostMapping("/save/devVoucher")
    public ModelAndView saveCompany(HttpServletRequest request, Authentication authentication, @Valid @ModelAttribute("devVoucher") DevVoucher devVoucher, BindingResult result, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView();

        if (result.hasErrors()) {
            logger.info("Validation Error");
            modelAndView.setViewName("/devVoucher/createVoucher");
            modelAndView.addObject("devVoucher", devVoucher);
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//            List<User> userList = userService.getAll().stream().filter(u -> u.getCreated_by().equals(auth.getName())).collect(Collectors.toList());
//
//            model.addAttribute("users", userList);
            return modelAndView;
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String created_by = authentication.getAuthorities().toString();
        devVoucher.setCreated_time(new Date());
        Principal principal = request.getUserPrincipal();
        devVoucher.setCreated_by(principal.getName());


        redirectAttributes.addFlashAttribute("message", "Successfully create a new Group. X");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        devVoucherService.save(devVoucher);
        modelAndView.setViewName("redirect:/voucher/voucherlist");
        return modelAndView;

    }

    @PreAuthorize("hasAnyRole('ROLE_DEVELOPER')")
    @GetMapping("devVoucher/edit/{id}")
    public ModelAndView editGroup(@PathVariable int id, Authentication authentication, HttpServletRequest request) {
        Optional<DevVoucher> devVoucher = devVoucherService.getDevVoucher(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("devVoucher/updateVoucher");
        Principal principal = request.getUserPrincipal();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        List<User> userList = userService.getAll().stream().filter(u -> u.getCreated_by().equals(auth.getName())).collect(Collectors.toList());
//        model.addAttribute("users", userList);
        modelAndView.addObject("devVoucher", devVoucher.get());
        return modelAndView;
    }


    @PreAuthorize("hasAnyRole('ROLE_DEVELOPER')")
    @PostMapping("devVoucher/updateVoucher/{id}")
    public ModelAndView updateGroup(HttpServletRequest request, Authentication authentication, @Valid @ModelAttribute("devVoucher") DevVoucher devVoucher, BindingResult result, @PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView();

        if (result.hasErrors()) {
            logger.info("Validation errors while submitting form.");
            modelAndView.setViewName("devVoucher/updateVoucher");
            modelAndView.addObject("devVoucher", devVoucher);
            return modelAndView;
        }

        Optional<DevVoucher> devVoucher1 = devVoucherService.getDevVoucher(id);

        devVoucher.setCreated_time(new Date());
        Principal principal = request.getUserPrincipal();
        devVoucher.setCreated_by(principal.getName());
        devVoucher.setV_id(id);
        devVoucherService.save(devVoucher);
        modelAndView.setViewName("redirect:/voucher/voucherlists");
        return modelAndView;
    }


    @PreAuthorize("hasAnyRole('ROLE_DEVELOPER')")
    @GetMapping("/devVoucher/delete/{id}")
    public ModelAndView deleteGroup(@PathVariable int id) {
        devVoucherRepository.deleteById(id);
        return new ModelAndView("redirect:/voucher/voucherlist");
    }

}

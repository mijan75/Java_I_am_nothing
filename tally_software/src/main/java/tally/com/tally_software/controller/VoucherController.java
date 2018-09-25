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
import tally.com.tally_software.model.DevVoucher;
import tally.com.tally_software.model.Groups;
import tally.com.tally_software.model.Voucher;
import tally.com.tally_software.repository.VoucherRepository;
import tally.com.tally_software.service.CompanyService;
import tally.com.tally_software.service.DevVoucherService;
import tally.com.tally_software.service.VoucherService;

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
public class VoucherController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);


    @Autowired
    VoucherRepository voucherRepository;

    @Autowired
    VoucherService voucherService;

    @Autowired
    DevVoucherService devVoucherService;

    @Autowired
    CompanyService companyService;


//    @GetMapping("/access-denied")
//    public String accessDenied() {
//        return "error/access-denied";
//    }

    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @RequestMapping(path = "/{companyId}/yourvoucherlists", method = RequestMethod.GET)
    public ModelAndView getAllYourVoucher(@PathVariable(value = "companyId") int companyId, Authentication authentication, HttpServletRequest request, Voucher voucher) {
        Principal principal = request.getUserPrincipal();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("companyId", companyId);
        modelAndView.setViewName("voucher/yourvoucherlist");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String created_by = authentication.getAuthorities().toString();

        List<Voucher> voucherList = voucherService.getAllVoucher().stream().filter(u -> u.getCompany().getCompany_id() == companyId).collect(Collectors.toList());


        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        modelAndView.addObject("voucherList", voucherList);


        return modelAndView;
    }


    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @GetMapping("/{companyId}/voucher/list")
    public ModelAndView getAllVoucher(@PathVariable int companyId, Authentication authentication) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("companyId", companyId);
        modelAndView.setViewName("voucher/voucherlist");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String created_by = authentication.getAuthorities().toString();

        List<DevVoucher> devVoucherList = devVoucherService.devVoucherList();


        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        modelAndView.addObject("devVoucherList", devVoucherList);


        return modelAndView;


    }


    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @GetMapping("/{companyId}/createVoucher")
    public ModelAndView CreateVoucher(Voucher voucher, Authentication authentication, @PathVariable(value = "companyId") int companyId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("companyId", companyId);
//        System.out.println("companyID : " + companyId);
        modelAndView.addObject("devVoucherList", devVoucherService.devVoucherList());
        modelAndView.setViewName("voucher/createVoucher");
        modelAndView.addObject("group", new Voucher());
        return modelAndView;
    }


    @PreAuthorize("hasAnyRole( 'ROLE_USER')")
    @RequestMapping(path = "/{companyId}/voucher/save", method = RequestMethod.POST)
    public ModelAndView saveVoucher(RedirectAttributes redirectAttributes, Authentication authentication,
                                    HttpServletRequest request, @Valid @ModelAttribute("voucher") Voucher voucher,
                                    BindingResult result, @PathVariable int companyId
    ) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("companyId", companyId);


        if (result.hasErrors()) {
            logger.info("Validation Error");
            modelAndView.setViewName("voucher/createVoucher");
            modelAndView.addObject("voucher", voucher);
            return modelAndView;
        }


        Company company = companyService.getCompany(companyId).get();
        voucher.setCreated_time(new Date());
        Principal principal = request.getUserPrincipal();
        voucher.setCreated_by(principal.getName());
//        ledger.setGroups(groupService.getGroups(group).get());
        voucher.setCompany(company);
        voucherService.save(voucher);
        redirectAttributes.addFlashAttribute("message", "Successfully create a Voucher. X");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");


        modelAndView.setViewName("redirect:/navbar/" + companyId + "/yourvoucherlists");

        return modelAndView;
    }

    @PreAuthorize("hasAnyRole( 'ROLE_USER')")
//    @GetMapping("/{companyId}/group/edit/{id}")
    @RequestMapping(path = "/{companyId}/voucher/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editVoucher(@PathVariable int id, @PathVariable int companyId) {
        Optional<Voucher> voucher = voucherService.getOne(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("companyId", companyId);

        modelAndView.addObject("devVoucherList", devVoucherService.devVoucherList());
        modelAndView.setViewName("voucher/updateVoucher");


        modelAndView.addObject("voucher", voucher.get());
        return modelAndView;
    }


    @PreAuthorize("hasAnyRole( 'ROLE_USER')")
    @PostMapping("/{companyId}/updateVoucher/{id}")
    public ModelAndView updateVoucher(HttpServletRequest request, @Valid @ModelAttribute("voucher") Voucher voucher, BindingResult result, @PathVariable int id, @PathVariable int companyId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("companyId", companyId);

        if (result.hasErrors()) {
            logger.info("Validation errors while submitting form.");
            modelAndView.setViewName("voucher/updateVoucher");
            modelAndView.addObject("voucher", voucher);
            return modelAndView;
        }
        Optional<Voucher> voucher1 = voucherService.getOne(id);

        voucher.setId(id);
        voucher.setCreated_time(new Date());
        Principal principal = request.getUserPrincipal();
        voucher.setCreated_by(principal.getName());
        voucherService.update(voucher);
        modelAndView.setViewName("redirect:/navbar/" + companyId + "/yourvoucherlists");
        return modelAndView;
    }


    @PreAuthorize("hasAnyRole( 'ROLE_USER')")
//    @GetMapping("/{companyId}/group/delete/{id}")
    @RequestMapping(path = "/{companyId}/voucher/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteVoucher(@PathVariable int id, @PathVariable int companyId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("companyId", companyId);
        voucherRepository.deleteById(id);
        return new ModelAndView("redirect:/navbar/" + companyId + "/yourvoucherlists");
    }
}

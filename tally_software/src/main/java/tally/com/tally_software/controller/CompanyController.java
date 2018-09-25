package tally.com.tally_software.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tally.com.tally_software.model.Company;
import tally.com.tally_software.model.User;
import tally.com.tally_software.repository.CompanyRepository;
import tally.com.tally_software.service.CompanyService;
import tally.com.tally_software.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class CompanyController {

    private static final Logger logger = LoggerFactory.getLogger(CompanyController.class);

    @Autowired
    private CompanyService companyService;

    @Autowired
    private CompanyRepository companyRepository;


    @Autowired
    UserService userService;

    public String handleIndex() {
        return "index";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping(value = "/createCompany", method = RequestMethod.GET, produces = MediaType.ALL_VALUE)
    public ModelAndView createCompany(Company company, Model model, Authentication authentication) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        modelAndView.addObject("company", company);
        List<User> userList = userService.getAll().stream().filter(u -> u.getCreated_by().equals(auth.getName())).collect(Collectors.toList());
//        for(User users : userList) {
//            System.out.println(user.getName());
//        }
        model.addAttribute("users", userList);
        modelAndView.setViewName("/company/createCompany");
        modelAndView.addObject("company", new Company());
        return modelAndView;
    }



    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping(value = "/save/company", method = RequestMethod.POST)
    public ModelAndView saveCompany( @RequestParam("file") MultipartFile file, @RequestParam("company_name") String company_name, @RequestParam("address") String address, @RequestParam("phone_number") String phone_number,
                                    @RequestParam("c_email") String c_email, @RequestParam("startdate") String startdate, @RequestParam("enddate") String enddate, @RequestParam("username") String username,
                                    HttpServletRequest request, Authentication authentication,  RedirectAttributes redirectAttributes) throws Exception{
        ModelAndView modelAndView = new ModelAndView();






        Company company1 = new Company();

        company1.setCompany_name(company_name);
        company1.setAddress(address);
        company1.setC_email(c_email);
        company1.setEnddate(enddate);
        company1.setStartdate(startdate);
        company1.setUsername(username);
        company1.setPhone_number(phone_number);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String created_by = authentication.getAuthorities().toString();
        company1.setCreated_time(new Date());
        Principal principal = request.getUserPrincipal();
        company1.setCreated_by(principal.getName());
        if(!file.isEmpty()) {
            company1.setFile(file.getBytes());
        }






        redirectAttributes.addFlashAttribute("message", "Successfully create a new company. X");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        companyService.createCompany(company1);
        modelAndView.setViewName("redirect:/company/lists");
        return modelAndView;

    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @RequestMapping(value = "/company/lists" , method = RequestMethod.GET , produces = MediaType.ALL_VALUE)
    public ModelAndView getAllCompany(Authentication authentication, HttpServletRequest request, Company company) {
        Principal principal = request.getUserPrincipal();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/company/companylists");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.IMAGE_PNG);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String created_by = authentication.getAuthorities().toString();
        System.out.println(auth.getName());


        List<Company> companyList = companyService.getCompanyList().stream().filter(u -> u.getUsername().equals(auth.getName()) || u.getCreated_by().equals(auth.getName())).collect(Collectors.toList());
//        List<Company> companyList = companyService.getCompanyList().stream().filter(u ->  u.getCreated_by().equals(principal.getName())).collect(Collectors.toList());


        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        modelAndView.addObject("companyList", companyList);
        return modelAndView;

    }
    @RequestMapping(value = "/company/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable(value = "id") int id) {
        byte[] image = companyRepository.getOne(id).getFile();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.IMAGE_PNG);

//        System.out.println(httpHeaders);

        return new ResponseEntity<byte[]>(image, httpHeaders, HttpStatus.OK);
    }
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
//    @GetMapping("/company/distribute/{id}")
//    public ModelAndView companydistribution(@PathVariable int id, Authentication authentication, HttpServletRequest request){
//        ModelAndView modelAndView = new ModelAndView();
//        Optional<Company> company  = companyService.getCompany(id);
//
//        modelAndView.setViewName("company/cDistribute");
//
//
//        modelAndView.addObject("company", company.get());
//        return modelAndView;
//
//
//    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/company/edit/{id}")
    public ModelAndView editCompany(Model model, @PathVariable int id, Authentication authentication, HttpServletRequest request) {
        Optional<Company> company = companyService.getCompany(id);
        byte[] image = companyRepository.getOne(id).getFile();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.IMAGE_PNG);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("company/updateCompany");
        Principal principal = request.getUserPrincipal();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<User> userList = userService.getAll().stream().filter(u -> u.getCreated_by().equals(auth.getName())).collect(Collectors.toList());
        model.addAttribute("users", userList);
        modelAndView.addObject("company", company.get());
        return modelAndView;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping(value = "user/updateCompany/{id}", method=RequestMethod.POST, produces = MediaType.ALL_VALUE)
    public ModelAndView updateCompany(@RequestParam("file") MultipartFile file, @RequestParam("company_name") String company_name, @RequestParam("address") String address, @RequestParam("phone_number") String phone_number,
                                      @RequestParam("c_email") String c_email, @RequestParam("startdate") String startdate, @RequestParam("enddate") String enddate, @RequestParam("username") String username,
                                      HttpServletRequest request, Authentication authentication,  @PathVariable int id)throws Exception {

        ModelAndView modelAndView = new ModelAndView();

//        if (result.hasErrors()) {
//            logger.info("Validation errors while submitting form.");
//            modelAndView.setViewName("company/updateCompany");
//            modelAndView.addObject("company", company);
//            return modelAndView;
//        }

        Optional<Company> companies = companyService.getCompany(id);


        Company company1 = new Company();

        company1.setCompany_name(company_name);
        company1.setAddress(address);
        company1.setC_email(c_email);
        company1.setEnddate(enddate);
        company1.setStartdate(startdate);
        company1.setUsername(username);
        company1.setPhone_number(phone_number);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String created_by = authentication.getAuthorities().toString();
        company1.setCreated_time(new Date());
        Principal principal = request.getUserPrincipal();
        company1.setCreated_by(principal.getName());
//        if(!file.isEmpty()) {
            company1.setFile(file.getBytes());
//        }

        company1.setCompany_id(id);
//
        companyRepository.save(company1);
        modelAndView.setViewName("redirect:/company/lists");
        return modelAndView;
    }


}

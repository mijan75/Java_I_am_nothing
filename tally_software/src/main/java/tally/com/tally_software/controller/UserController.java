package tally.com.tally_software.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tally.com.tally_software.config.SecurityUtility;
import tally.com.tally_software.domain.Role;
import tally.com.tally_software.domain.UserRole;
import tally.com.tally_software.model.Company;
import tally.com.tally_software.model.Feedback;
import tally.com.tally_software.model.User;
import tally.com.tally_software.model.UserComment;
import tally.com.tally_software.repository.CompanyRepository;
import tally.com.tally_software.repository.FeedbackRepository;
import tally.com.tally_software.repository.RoleRepository;
import tally.com.tally_software.repository.UserRepository;
import tally.com.tally_software.service.*;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;


@Controller
@EnableAsync
@RequestMapping("/")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private NotificationService notificationService;


    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private FeedbackService feedbackService;


    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    CommentService commentService;


    public String handleIndex() {
        return "index";
    }

    @PreAuthorize("!isAuthenticated()")
    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

//    @PreAuthorize("!isAuthenticated()")
//    @GetMapping("/registration")
//    public String registration(Model model){
//        return "registration";
//    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "error/access-denied";
    }


    @GetMapping("/")
    public String welcome(Model model) {
        model.addAttribute("commentList", commentService.getAllComment());

        return "home";
    }


    @PreAuthorize("hasAnyRole('ROLE_DEVELOPER', 'ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/profile")
    public String profile(Model model) {
        return "user/profile";
    }


    @PostMapping("/")
    public ModelAndView userFeadback(@Valid @ModelAttribute("feedback") Feedback feedback, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView();
        feedback.setCreated_time(new Date());
        redirectAttributes.addFlashAttribute("message", "Failed X");
        redirectAttributes.addFlashAttribute("alertClass", "alert-danger");

        modelAndView.addObject("feedback", feedback);
        feedbackService.saveFeedback(feedback);
        redirectAttributes.addFlashAttribute("message", "Success! After some time check your email. X");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        modelAndView.setViewName("redirect:/");

        return modelAndView;


    }

    @PostMapping("/comment")
    public ModelAndView userComment(@Valid @ModelAttribute("comment") UserComment comment, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView();
        comment.setCreated_time(new Date());
        redirectAttributes.addFlashAttribute("message", "Failed X");
        redirectAttributes.addFlashAttribute("alertClass", "alert-danger");

        modelAndView.addObject("comment", comment);
        commentService.saveComment(comment);
        redirectAttributes.addFlashAttribute("message", "Success! Thank you so much. X");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        modelAndView.setViewName("redirect:/");

        return modelAndView;


    }



//    @GetMapping("/comment/lists")
//    public ModelAndView commentList(Feedback feedback) {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("user/feedback");
//        List<Feedback> feedbackList = feedbackService.getFeedbackList();
//        modelAndView.addObject("feedbackList", feedbackList);
//        return modelAndView;
//    }

    @GetMapping("/feedback/lists")
    public ModelAndView feedbackList(Feedback feedback) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/feedback");
        List<Feedback> feedbackList = feedbackService.getFeedbackList();
        modelAndView.addObject("feedbackList", feedbackList);
        return modelAndView;
    }

    @GetMapping("/feedback/delete/{id}")
    public ModelAndView deletefeedback(@PathVariable int id) {
        feedbackRepository.deleteById(id);
        return new ModelAndView("redirect:/feedback/lists");
    }

    @PreAuthorize("hasAnyRole('ROLE_DEVELOPER', 'ROLE_ADMIN')")
    @GetMapping("/create/{id}")
    public ModelAndView userCreatePage(@PathVariable int id, Authentication authentication) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/user/create");

        if(id != 0){
            Optional<Feedback> feedback = feedbackService.getFeedback(id);



            User user = new User();
            user.setName(feedback.get().getName());
            user.setEmail(feedback.get().getEmail());

            modelAndView.addObject("user", user);
        }else{
            modelAndView.addObject("user", new User());
        }
        return modelAndView;
    }


    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_DEVELOPER')")
    @PostMapping("/user/save")
    public ModelAndView userCreate(HttpServletRequest request, @Valid @ModelAttribute("user") User user, BindingResult result, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView();
        String userRole = user.getRole();
        Optional<Role> role = roleRepository.findByRole(userRole);
        Set<UserRole> userRoles = new HashSet<>();

        if (result.hasErrors()) {
            logger.info("Validation Error");
            modelAndView.setViewName("/user/create");
            modelAndView.addObject("user", user);
            return modelAndView;
        }


        if (role.isPresent()) {
            userRoles.add(new UserRole(user, role.get()));
        } else {
            if (userRole.equals("ROLE_DEVELOPER")) {
                Role newRole = new Role();
                newRole.setRoleId(1);
                newRole.setRole("ROLE_DEVELOPER");
                userRoles.add(new UserRole(user, newRole));

            } else if (userRole.equals("ROLE_ADMIN")) {
                Role newRole = new Role();
                newRole.setRoleId(2);
                newRole.setRole("ROLE_ADMIN");
                userRoles.add(new UserRole(user, newRole));
            } else if (userRole.equals("ROLE_USER")) {
                Role newRole = new Role();
                newRole.setRoleId(3);
                newRole.setRole("ROLE_USER");
                userRoles.add(new UserRole(user, newRole));
            }
        }


        user.setCreated_time(new Date());
        Principal principal = request.getUserPrincipal();
        user.setCreated_by(principal.getName());
        String temp = user.getPassword();
        String password = user.getPassword();
        user.setPassword(SecurityUtility.passwordEncoder().encode(password));

        if (userService.getUserByUsername(user.getUsername()) != null) {
            redirectAttributes.addFlashAttribute("message", "Failed! Already this Username taken! X");

            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
            modelAndView.setViewName("redirect:/create");
            return modelAndView;
        } else if (userService.getUserByEmail(user.getEmail()) != null) {
            redirectAttributes.addFlashAttribute("message", "Failed! Already this Email taken! X");

            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
            modelAndView.setViewName("redirect:/create");
            return modelAndView;
        }
        userService.createUser(user, userRoles);
        User user1 = new User();

        try {
            user1.setPassword(temp);
            user1.setName(user.getName());
            user1.setEmail(user.getEmail());
            user1.setUsername(user.getUsername());
            notificationService.sendNotification(user1);
        } catch (MailException ex) {
            logger.info("Error Sending Mail " + ex.getMessage());
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_DEVELOPER"))){
            Feedback feedback1 = feedbackService.getFeedBackById(user.getEmail());
            Company company = new Company();
            company.setCompany_name(feedback1.getCellNumber());
            company.setC_email(user.getEmail());
            company.setUsername(user.getUsername());
            company.setCreated_by(user.getUsername());
            companyService.createCompany(company);
        }else{
            System.out.println("Company is not creating");
        }





        userRoles.clear();
        modelAndView.setViewName("redirect:/user/lists");
        return modelAndView;


    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_DEVELOPER')")
    @GetMapping("/user/lists")
    public ModelAndView getAllUsers(Authentication authentication, HttpServletRequest request, User users) {
        Principal principal = request.getUserPrincipal();
        System.out.println(principal);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/user/userlist");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String created_by = authentication.getAuthorities().toString();
        List<User> userList = userService.getAll().stream().filter(u -> u.getCreated_by().equals(auth.getName()) && u.getCreated_by() != auth.getName()).collect(Collectors.toList());
//                .stream().
//                filter(u->u.getRole().equals("ROLE_SUPER_ADMIN") && u.getRole().equals("ROLE_ADMIN")).collect(Collectors.toList());

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//        String dateString = formatter.format(userList.get(0).getCreated_time());

//        try {
//            Date date = formatter.parse(dateString);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }





        System.out.println(auth.getName());

        String roleCheck = authentication.getAuthorities().toString();

        for (int i = 0; i < userList.size(); i++) {
            Set<UserRole> userRoleSet = userList.get(i).getUserRoles();

            for (UserRole ur : userRoleSet) {
                if (!roleCheck.equals("[ROLE_DEVELOPER]")) {
                    if (ur.getRole().getRole().equals("ROLE_DEVELOPER")) {
                        userList.remove(userList.get(i));
                    }
                }

            }
        }


        modelAndView.addObject("userslist", userList);

        return modelAndView;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_DEVELOPER', 'ROLE_USER')")
    @GetMapping("/user/edit/{id}")
    public ModelAndView editUser(@PathVariable int id) {
        Optional<User> user = userService.getUser(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/update");


        modelAndView.addObject("user", user.get());
        return modelAndView;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_DEVELOPER', 'ROLE_USER')")
    @PostMapping("user/update/{id}")
    public ModelAndView updateUser(HttpServletRequest request, @Valid @ModelAttribute("user") User user, BindingResult result, @PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView();
        String userRole = user.getRole();
//        Set<UserRole> userRoles = new HashSet<>();
        if (result.hasErrors()) {
            logger.info("Validation errors while submitting form.");
            modelAndView.setViewName("user/update");
            modelAndView.addObject("user", user);
            return modelAndView;
        }
        Optional<User> users = userService.getUser(id);
        user.setPassword(users.get().getPassword());
        user.setId(id);
        user.setCreated_time(new Date());
//        Principal principal = request.getUserPrincipal();
        user.setCreated_by(user.getUsername());
        userService.updateUser(user);
        modelAndView.setViewName("redirect:/profile");
        return modelAndView;
    }


    @GetMapping("/user/delete/{id}")
    public ModelAndView deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
        return new ModelAndView("redirect:/user/lists");
    }

//    @GetMapping("/forgotPass")
//    public String forgotPassword(Model model) {
//        return "forgot-password";
//    }
//
//
//    @PostMapping("/forgotPass")
//    public ModelAndView updatePassword(@PathVariable int id, HttpServletRequest request , @Valid @ModelAttribute("password") User user,  RedirectAttributes  redirectAttributes){
//       ModelAndView modelAndView = new ModelAndView();
//
//
//        if ( userService.getUserByUsername(user.getUsername()) == null){
//            redirectAttributes.addFlashAttribute("message", "Failed! Your username  is not matched! X" );
//
//            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
////           modelAndView.addObject("user");
//            modelAndView.setViewName("redirect:/forgotPass");
//            return  modelAndView;
//        }
//
//
//
//
//
//
//        modelAndView.setViewName("redirect:/newPassword");
//        return modelAndView;
//
//
//    }
//
//    @GetMapping("/newPassword")
//    public String newPassword(Model model) {
//        return "reset-password";
//    }
//
//    public ModelAndView newPassword(HttpServletRequest request, RedirectAttributes redirectAttributes){
//        ModelAndView modelAndView = new ModelAndView();
//
//        return modelAndView;
//    }


}




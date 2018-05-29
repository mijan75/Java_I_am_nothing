package com.example.BillPaymentAdminPanel.controller;

import com.example.BillPaymentAdminPanel.config.SecurityUtility;
import com.example.BillPaymentAdminPanel.domain.Role;
import com.example.BillPaymentAdminPanel.domain.UserRole;
import com.example.BillPaymentAdminPanel.model.BillInfo;
import com.example.BillPaymentAdminPanel.model.StakeHolder;
import com.example.BillPaymentAdminPanel.model.TempUser;
import com.example.BillPaymentAdminPanel.model.Users;
import com.example.BillPaymentAdminPanel.repository.ReportRepository;
import com.example.BillPaymentAdminPanel.repository.RoleRepository;
import com.example.BillPaymentAdminPanel.service.StakeHolderService;
import com.example.BillPaymentAdminPanel.service.UserService;
import org.hibernate.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private StakeHolderService stakeHolderService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ReportRepository reportRepository;

    @GetMapping
    public String handleIndex() {
        return "index";
    }

    @PreAuthorize("!isAuthenticated()")
    @GetMapping("/login")
    public String login(Model model){
        return "login";
    }


    @GetMapping("/access-denied")
    public String accessDenied() {
        return "error/access-denied";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPER_ADMIN')")
    @GetMapping("/user/create")
    public ModelAndView createPage(HttpServletRequest httpServletRequest, Authentication authentication){

        Principal principal = httpServletRequest.getUserPrincipal();
        String name = principal.getName();

        String roleCheck = authentication.getAuthorities().toString();

        int stakeholderId = 0;

        Users user = userService.getUserByUsername(name);
        if(user.getStakeHolder() != null){
            stakeholderId = user.getStakeHolder().getId();
        }


        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/create");
        modelAndView.addObject("user", new Users());

        //Before
        //List<StakeHolder> stakeHolderList = stakeHolderService.getAll().stream().filter(s -> s.getStatus() == 1).collect(Collectors.toList());

        //After
        List<StakeHolder> stakeHolderList = stakeHolderService.activeStakeHolder(1);

        List<StakeHolder> mainStakeHolderList = new ArrayList<>();
        for(StakeHolder s: stakeHolderList){
            if(s.getId() == stakeholderId){
                mainStakeHolderList.add(s);
            }
        }

        if(roleCheck.equals("[ROLE_ADMIN]")){
            modelAndView.addObject("stake", stakeHolderList);
        }
        else{
            modelAndView.addObject("stake", mainStakeHolderList);
        }

        return modelAndView;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPER_ADMIN')")
    @PostMapping("/user/save")
    public ModelAndView saveUser(HttpServletRequest request , @Valid @ModelAttribute("user") Users user, BindingResult result){

        ModelAndView modelAndView = new ModelAndView();

        if(result.hasErrors()){
            logger.info("Validation Error");
            modelAndView.setViewName("user/create");
            modelAndView.addObject("user", user);
            return modelAndView;
        }

        int stakeholderId = user.getTransientId();
        String userRole = user.getRole();
        user.setStatus(1);
        Optional<StakeHolder> stakeHolder = stakeHolderService.findStakeHolder(stakeholderId);

        if(stakeHolder.isPresent()){
            user.setStakeHolder(stakeHolder.get());
        }

        Optional<Role> role = roleRepository.findByRole(userRole);
        Set<UserRole> userRoles = new HashSet<>();

        if(role.isPresent()){
            userRoles.add(new UserRole(user, role.get()));
        }else {
            if(userRole.equals("ROLE_SUPER_ADMIN")){
                Role newRole = new Role();
                newRole.setRoleId(1);
                newRole.setRole("ROLE_SUPER_ADMIN");
                userRoles.add(new UserRole(user, newRole));
            }
            else if(userRole.equals("ROLE_ADMIN")){
                Role newRole = new Role();
                newRole.setRoleId(2);
                newRole.setRole("ROLE_ADMIN");
                userRoles.add(new UserRole(user, newRole));
            }
            else if(userRole.equals("ROLE_SUPER_ADMIN")){
                Role newRole = new Role();
                newRole.setRoleId(3);
                newRole.setRole("ROLE_SUPER_ADMIN");
                userRoles.add(new UserRole(user, newRole));
            }
            else if(userRole.equals("ROLE_ADMIN_REPORT")){
                Role newRole = new Role();
                newRole.setRoleId(4);
                newRole.setRole("ROLE_ADMIN_REPORT");
                userRoles.add(new UserRole(user, newRole));
            }
            else if(userRole.equals("ROLE_STAKEHOLDER_API")){
                Role newRole = new Role();
                newRole.setRoleId(5);
                newRole.setRole("ROLE_STAKEHOLDER_API");
                userRoles.add(new UserRole(user, newRole));
            }
            else if(userRole.equals("ROLE_STAKEHOLDER_REPORT")){
                Role newRole = new Role();
                newRole.setRoleId(6);
                newRole.setRole("ROLE_STAKEHOLDER_REPORT");
                userRoles.add(new UserRole(user, newRole));
            }


        }

        user.setCreated_time(new Date());
        Principal principal = request.getUserPrincipal();
        user.setCreated_by(principal.getName());

        String password = user.getPassword();
        user.setPassword(SecurityUtility.passwordEncoder().encode(password));

        userService.createUser(user, userRoles);
        userRoles.clear();
        modelAndView.setViewName("redirect:/user/lists");
        return  modelAndView;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPER_ADMIN')")
    @GetMapping("/user/lists")
    public ModelAndView getAll(Authentication authentication){

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/userlist");

        //Before
        //List<Users> usersList = userService.getAll().stream().filter(u -> u.getStatus() == 1).collect(Collectors.toList());

        //After
        List<Users> usersList = userService.activeUsers(1);

        String roleCheck = authentication.getAuthorities().toString();

        for(int i=0; i<usersList.size(); i++){
            Set<UserRole> userRoleSet = usersList.get(i).getUserRoles();

            for(UserRole ur: userRoleSet){
                if(!roleCheck.equals("[ROLE_SUPER_ADMIN]")){
                    if(ur.getRole().getRole().equals("ROLE_SUPER_ADMIN")){
                        usersList.remove(usersList.get(i));
                    }
                }

            }
        }

        modelAndView.addObject("users", usersList);
        return modelAndView;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPER_ADMIN')")
    @GetMapping("/user/edit/{id}")
    public ModelAndView editStakeHolder(@PathVariable int id){

        Optional<Users> user = userService.getUser(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/update");

        //Before
        //List<StakeHolder> stakeHolderList = stakeHolderService.getAll().stream().filter(s -> s.getStatus() == 1).collect(Collectors.toList());

        //After
        List<StakeHolder> stakeHolderList = stakeHolderService.activeStakeHolder(1);
        modelAndView.addObject("stake", stakeHolderList);


        if (user.get().getStakeHolder() != null){
            user.get().setTransientId(user.get().getStakeHolder().getId());
        }

        Set<UserRole> roleSet = user.get().getUserRoles();
        for(UserRole ur: roleSet){
            user.get().setRole(ur.getRole().getRole());
        }
        modelAndView.addObject("user", user.get());
        return modelAndView;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPER_ADMIN')")
    @PostMapping("/user/edit/{id}")
    public ModelAndView updateStakeHolder(HttpServletRequest request, @Valid @ModelAttribute("user") Users user, BindingResult result, @PathVariable int id){

        ModelAndView modelAndView = new ModelAndView();
        if(result.hasErrors()){
            logger.info("Validation errors while submitting form.");
            modelAndView.setViewName("user/update");
            modelAndView.addObject("user", user);
            return modelAndView;
        }

        int stakeholderId = user.getTransientId();
        String userRole = user.getRole();

        Optional<StakeHolder> stakeHolder = stakeHolderService.findStakeHolder(stakeholderId);
        if(stakeHolder.isPresent()){
            user.setStakeHolder(stakeHolder.get());
        }

        Set<UserRole> userRoles = new HashSet<>();

        Principal principal = request.getUserPrincipal();
        user.setUpdated_by(principal.getName());
        Optional<Users> users = userService.getUser(id);
        user.setPassword(users.get().getPassword());
        user.setUpdated_time(new Date());
        user.setId(id);

        Optional<Users> usersOptional = userService.getUser(id);
        user.setCreated_by(usersOptional.get().getCreated_by());
        user.setCreated_time(usersOptional.get().getCreated_time());

        userService.updateUser(user, userRoles);
        return new ModelAndView("redirect:/user/lists");
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPER_ADMIN')")
    @GetMapping("/user/delete/{id}")
    public ModelAndView deleteStakeHolder(@PathVariable int id){

        Optional<Users> user = userService.getUser(id);
        user.get().setStatus(0);
        userService.statusUpdate(user.get(), user.get().getUserRoles());
        return new ModelAndView("redirect:/user/lists");
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMIN_REPORT')")
    @GetMapping("/user/report")
    public ModelAndView report(Model model){
    
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("reportList", new ArrayList<>());
        model.addAttribute("standardDate", new Date());
        modelAndView.addObject("tempUser", new TempUser());
        modelAndView.addObject("stake", stakeHolderService.activeStakeHolder(1));
        modelAndView.setViewName("user/report");
        return modelAndView;
    }

    @PreAuthorize("hasAnyRole('ROLE_STAKEHOLDER_REPORT')")
    @GetMapping("/user/report/stake")
    public ModelAndView stakeReport(HttpServletRequest request){

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("reportList", new ArrayList<>());
        modelAndView.addObject("tempUser", new TempUser());
        modelAndView.setViewName("user/reportstake");
        return modelAndView;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPER_ADMIN')")
    @GetMapping("/user/inactivelists")
    public ModelAndView inactiveUserLists(Authentication authentication){

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/inactivelist");
        String roleCheck = authentication.getAuthorities().toString();

        //Before
        //List<Users> usersList = userService.getAll().stream().filter(s -> s.getStatus() == 0).collect(Collectors.toList());

        //After
        List<Users> usersList = userService.activeUsers(0);

        for(int i=0; i<usersList.size(); i++){
            Set<UserRole> userRoleSet = usersList.get(i).getUserRoles();

            for(UserRole ur: userRoleSet){

                if(!roleCheck.equals("[ROLE_SUPER_ADMIN]")){
                    if(ur.getRole().getRole().equals("ROLE_SUPER_ADMIN")){
                        usersList.remove(usersList.get(i));
                    }
                }
            }
        }
        modelAndView.addObject("users", usersList);
        return modelAndView;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPER_ADMIN')")
    @GetMapping("/user/active/{id}")
    public ModelAndView activeUser(@PathVariable int id){

        ModelAndView modelAndView = new ModelAndView();
        Optional<Users> user = userService.getUser(id);
        user.get().setStatus(1);
        userService.statusUpdate(user.get(), user.get().getUserRoles());
        return new ModelAndView("redirect:/user/inactivelists");
    }

    @PersistenceContext
    public EntityManager entityManager;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADMIN_REPORT')")
    @PostMapping("/user/serchBycustomernumber")
    public ModelAndView searchById(@Valid @ModelAttribute("tempUser") TempUser tempUser, BindingResult result){

        ModelAndView modelAndView = new ModelAndView();
        ArrayList<BillInfo> billInfoArrayList = new ArrayList<>();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT * FROM bill_info WHERE");

        if(tempUser.getCustomerId() != 0){
            stringBuilder.append(" customer_number=" + tempUser.getCustomerId());
        }

        if(tempUser.getBillNumber() != 0){
            if(tempUser.getCustomerId() != 0)
                stringBuilder.append(" AND bill_number=" + tempUser.getBillNumber());
            else stringBuilder.append(" bill_number=" + tempUser.getBillNumber());
        }


        if(tempUser.getStakeholderId() != 0){
            if(tempUser.getBillNumber() != 0 || tempUser.getCustomerId() != 0)
                stringBuilder.append(" AND stake_id="+ tempUser.getStakeholderId());
            else stringBuilder.append(" stake_id="+ tempUser.getStakeholderId());
        }

        if(tempUser.getStatusId() != 0){
            if(tempUser.getCustomerId() !=0 || tempUser.getStakeholderId() != 0 || tempUser.getBillNumber() != 0)
                stringBuilder.append(" AND bill_status="+ tempUser.getStatusId());
            else stringBuilder.append(" bill_status="+ tempUser.getStatusId());
        }

        if(tempUser.getAmountTo() != 0 && tempUser.getAmountForm() != 0){
            if(tempUser.getCustomerId() !=0 || tempUser.getStakeholderId() != 0 || tempUser.getStatusId() != 0 || tempUser.getBillNumber() !=0 )
                stringBuilder.append(" AND paid_amount BETWEEN "+ tempUser.getAmountForm() + " AND " + tempUser.getAmountTo());
            else stringBuilder.append(" paid_amount BETWEEN "+ tempUser.getAmountForm() + " AND " + tempUser.getAmountTo());
        }

        Date fristDate = null;
        Date lastDate = null;

        if(!tempUser.getStardDate().equals("") && !tempUser.getEndDate().equals("")){
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");


            String startDate = tempUser.getStardDate();
            char[] temp = startDate.toCharArray();
            if(!tempUser.getStardDate().equals(""))
                temp[10] = ' ';
            startDate = String.valueOf(temp);

            String endDate = tempUser.getEndDate();
            char[] temp1 = endDate.toCharArray();
            if(!tempUser.getEndDate().equals(""))
                temp1[10] = ' ';
            endDate = String.valueOf(temp1);



            try {
                fristDate = format.parse(startDate);
                lastDate = format.parse(endDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if(tempUser.getCustomerId() != 0 || tempUser.getBillNumber() != 0 || tempUser.getStatusId() != 0 || tempUser.getStakeholderId() != 0 || (tempUser.getAmountTo() != 0 && tempUser.getAmountForm() != 0))
                stringBuilder.append(" AND pay_date BETWEEN " + "?1" + " AND "+ "?2 ");
            else stringBuilder.append(" pay_date BETWEEN " + "?1" + " AND " + "?2 ");

        }

        stringBuilder.append(";");
        if(tempUser.getCustomerId() != 0 || tempUser.getStakeholderId() != 0 || tempUser.getStatusId() != 0 || tempUser.getBillNumber() != 0 || (tempUser.getAmountTo() != 0 && tempUser.getAmountForm() != 0) || !tempUser.getStardDate().equals("") || !tempUser.getEndDate().equals("")){
            Query q = entityManager.createNativeQuery(new String(stringBuilder), BillInfo.class);
            if(fristDate != null && lastDate != null)
                q.setParameter(1, fristDate, TemporalType.TIMESTAMP).setParameter(2, lastDate, TemporalType.DATE);
            billInfoArrayList = (ArrayList<BillInfo>) q.getResultList();
        }

        modelAndView.addObject("reportList", billInfoArrayList);
        modelAndView.addObject("tempUser", new TempUser());
        modelAndView.addObject("stake", stakeHolderService.activeStakeHolder(1));
        modelAndView.setViewName("user/report");
        return modelAndView;
    }

    @PostMapping("/user/searchByName/stake")
    public ModelAndView getReport(@Valid @ModelAttribute("tempUser") TempUser tempUser, BindingResult bindingResult, HttpServletRequest request ){

        ModelAndView modelAndView = new ModelAndView();
        ArrayList<BillInfo> billInfoArrayList = new ArrayList<>();
        Principal principal = request.getUserPrincipal();
        String name = principal.getName();
        Users users = userService.getUserByUsername(name);
        billInfoArrayList = (ArrayList<BillInfo>) reportRepository.findByStakeHolderId(users.getStakeHolder().getId());



        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT * FROM bill_info WHERE");

        if(tempUser.getCustomerId() != 0){
            stringBuilder.append(" customer_number=" + tempUser.getCustomerId());
        }

        if(tempUser.getBillNumber() != 0){
            if(tempUser.getCustomerId() != 0)
                stringBuilder.append(" AND bill_number=" + tempUser.getBillNumber());
            else stringBuilder.append(" bill_number=" + tempUser.getBillNumber());
        }

        if(tempUser.getStatusId() != 0){
            if(tempUser.getCustomerId() !=0 || tempUser.getStakeholderId() != 0 || tempUser.getBillNumber() != 0)
                stringBuilder.append(" AND bill_status="+ tempUser.getStatusId());
            else stringBuilder.append(" bill_status="+ tempUser.getStatusId());
        }

        if(tempUser.getAmountTo() != 0 && tempUser.getAmountForm() != 0){
            if(tempUser.getCustomerId() !=0 || tempUser.getStakeholderId() != 0 || tempUser.getStatusId() != 0 || tempUser.getBillNumber() !=0 )
                stringBuilder.append(" AND paid_amount BETWEEN "+ tempUser.getAmountForm() + " AND " + tempUser.getAmountTo());
            else stringBuilder.append(" paid_amount BETWEEN "+ tempUser.getAmountForm() + " AND " + tempUser.getAmountTo());
        }

        Date fristDate = null;
        Date lastDate = null;

        if(!tempUser.getStardDate().equals("") && !tempUser.getEndDate().equals("")){
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");


            String startDate = tempUser.getStardDate();
            char[] temp = startDate.toCharArray();
            if(!tempUser.getStardDate().equals(""))
                temp[10] = ' ';
            startDate = String.valueOf(temp);

            String endDate = tempUser.getEndDate();
            char[] temp1 = endDate.toCharArray();
            if(!tempUser.getEndDate().equals(""))
                temp1[10] = ' ';
            endDate = String.valueOf(temp1);



            try {
                fristDate = format.parse(startDate);
                lastDate = format.parse(endDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if(tempUser.getCustomerId() != 0 || tempUser.getBillNumber() != 0 || tempUser.getStatusId() != 0 || tempUser.getStakeholderId() != 0 || (tempUser.getAmountTo() != 0 && tempUser.getAmountForm() != 0))
                stringBuilder.append(" AND pay_date BETWEEN " + "?1" + " AND "+ "?2 ");
            else stringBuilder.append(" pay_date BETWEEN " + "?1" + " AND " + "?2 ");

        }
        stringBuilder.append(" AND stake_id="+users.getStakeHolder().getId());
        stringBuilder.append(";");
        if(tempUser.getCustomerId() != 0 || tempUser.getStakeholderId() != 0 || tempUser.getStatusId() != 0 || tempUser.getBillNumber() != 0 || (tempUser.getAmountTo() != 0 && tempUser.getAmountForm() != 0) || !tempUser.getStardDate().equals("") || !tempUser.getEndDate().equals("")){
            Query q = entityManager.createNativeQuery(new String(stringBuilder), BillInfo.class);
            if(fristDate != null && lastDate != null)
                q.setParameter(1, fristDate, TemporalType.TIMESTAMP).setParameter(2, lastDate, TemporalType.DATE);
            billInfoArrayList = (ArrayList<BillInfo>) q.getResultList();
        }

        modelAndView.setViewName("user/reportstake");
        modelAndView.addObject("tempUser", new TempUser());
        modelAndView.addObject("reportList", billInfoArrayList);
        return modelAndView;

    }
}



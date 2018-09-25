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
import org.thymeleaf.expression.Lists;
import tally.com.tally_software.model.*;
import tally.com.tally_software.service.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.sound.midi.Soundbank;
import javax.validation.Valid;
import javax.validation.constraints.Null;
import java.io.IOException;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/navbar/{companyId}")
public class AccountVoucherController {

    private static final Logger logger = LoggerFactory.getLogger(CompanyController.class);

    @Autowired
    private CompanyService companyService;

    @Autowired
    AccountingVoucherService accountingVoucherService;

    @Autowired
    DevVoucherService devVoucherService;

    @Autowired
    LedgerService ledgerService;

    @Autowired
    StockItemsService stockItemsService;

    @Autowired
    UnitService unitService;

    @Autowired
    DevGroupService devGroupService;


    @RequestMapping(path = "/createJournal")
    public ModelAndView createPage(ModelAndView modelAndView, Authentication authentication, HttpServletRequest request, AccountingVoucher accountingVoucher, @PathVariable int companyId) {

        Principal principal = request.getUserPrincipal();
        modelAndView.addObject("companyId", companyId);
        List<DevVoucher> devVoucherList = devVoucherService.devVoucherList().stream().filter(u -> u.getUnique_id() == 100).collect(Collectors.toList());
        List<Ledger> ledgerListContra = ledgerService.getAllLedger().stream().filter(u ->u.getCompany().getCompany_id()==companyId && u.getDevGroup().getUniqueGId() == 1 || u.getDevGroup().getUniqueGId() == 5).collect(Collectors.toList());
        List<Ledger> ledgerListPayment = ledgerService.getAllLedger();
        List<Ledger> ledgerListJournal = ledgerService.getAllLedger().stream().filter(u ->u.getCompany().getCompany_id()==companyId && u.getDevGroup().getUniqueGId() == 2 || u.getDevGroup().getUniqueGId() == 3
                || u.getDevGroup().getUniqueGId() == 4 || u.getDevGroup().getUniqueGId() == 0).collect(Collectors.toList());
        List<Ledger> ledgerListReceipt = ledgerService.getAllLedger().stream().filter(u ->u.getCompany().getCompany_id()==companyId && u.getDevGroup().getUniqueGId() != 1 && u.getDevGroup().getUniqueGId() != 5).collect(Collectors.toList());

        List<Ledger> ledgerListPurchase = ledgerService.getAllLedger().stream().filter(u ->u.getCompany().getCompany_id()==companyId && u.getDevGroup().getUniqueGId() != 0 && u.getDevGroup().getUniqueGId() != 4 && u.getDevGroup().getUniqueGId() != 3).collect(Collectors.toList());
        List<Ledger> ledgerListSales = ledgerService.getAllLedger().stream().filter(u ->u.getCompany().getCompany_id()==companyId && u.getDevGroup().getUniqueGId() != 0 && u.getDevGroup().getUniqueGId() != 3 && u.getDevGroup().getUniqueGId() != 4).collect(Collectors.toList());


        List<StockItems> stockItemsList = stockItemsService.getAllItems().stream().filter(u ->u.getCompany().getCompany_id()==companyId && u.getCompany().getCompany_id() == companyId).collect(Collectors.toList());
        List<Unit> unitList = unitService.getAllUnit().stream().filter(u -> u.getCompany().getCompany_id() == companyId).collect(Collectors.toList());
        List<Company> companyList = companyService.getCompanyList().stream().filter(u -> u.getCompany_id() == companyId).collect(Collectors.toList());

        List<Ledger> ledgerListPurchase1 = ledgerService.getAllLedger().stream().filter(u ->u.getCompany().getCompany_id()==companyId && u.getDevGroup().getUniqueGId() != 2 && u.getDevGroup().getUniqueGId() != 1
                && u.getDevGroup().getUniqueGId() != 4 && u.getDevGroup().getUniqueGId() != 0 && u.getDevGroup().getUniqueGId() != 5).collect(Collectors.toList());
        List<Ledger> ledgerListSales1 = ledgerService.getAllLedger().stream().filter(u ->u.getCompany().getCompany_id()==companyId && u.getDevGroup().getUniqueGId() != 2 && u.getDevGroup().getUniqueGId() != 1
                && u.getDevGroup().getUniqueGId() != 3 && u.getDevGroup().getUniqueGId() != 0 && u.getDevGroup().getUniqueGId() != 5).collect(Collectors.toList());

        modelAndView.addObject("ledgerListContra", ledgerListContra);
        modelAndView.addObject("ledgerListContra1", ledgerListContra);
        modelAndView.addObject("ledgerListPayment", ledgerListPayment);
        modelAndView.addObject("ledgerListJournal", ledgerListJournal);
        modelAndView.addObject("ledgerListPurchase", ledgerListPurchase);
        modelAndView.addObject("ledgerListSales", ledgerListSales);
        modelAndView.addObject("ledgerListReceipt", ledgerListReceipt);
        modelAndView.addObject("ledgerListPurchase1", ledgerListPurchase1);
        modelAndView.addObject("ledgerListSales1", ledgerListSales1);
//        System.out.println(accountingVoucherService.getCreditLedger());

        modelAndView.addObject("devVoucherList", devVoucherList);
        modelAndView.addObject("stockItemsList", stockItemsList);
        modelAndView.addObject("unitList", unitList);
        modelAndView.addObject("companyList", companyList);
        modelAndView.setViewName("/journal/createJournal");
        modelAndView.addObject("journal", new AccountingVoucher());
        return modelAndView;
    }

    @PostMapping("/journal/save")
    public ModelAndView ledgeProcessr(RedirectAttributes redirectAttributes, Authentication authentication,
                                      HttpServletRequest request, @Valid @ModelAttribute("journal") AccountingVoucher accountingVoucher,
                                      BindingResult result, @RequestParam(value = "companyId") int companyId
    ) {


        ModelAndView modelAndView = new ModelAndView();
//        if(result.hasErrors()){
//            logger.info("Validation Error");
//            modelAndView.setViewName("/journal/createJournal");
//            modelAndView.addObject("journal", accountingVoucher);
//            return modelAndView;
//        }


        Company company = companyService.getCompany(companyId).get();


//        ledger.setGroups(groupService.getGroups(group).get());
        accountingVoucher.setCompany(company);



        Principal principal = request.getUserPrincipal();
        accountingVoucher.setCreated_by(principal.getName());



        accountingVoucherService.save(accountingVoucher);

        Ledger ledger = accountingVoucher.getDebitLedger();
        ledger = accountingVoucher.getCreditLedger();
        ledger.getAccountingVoucher().add(accountingVoucher);
        ledgerService.save(ledger);




        System.out.println("Journal :" + accountingVoucher);
        redirectAttributes.addFlashAttribute("message", "Successfully create a Journal. X");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");


        modelAndView.setViewName("redirect:/navbar/" + companyId + "/createJournal");

        return modelAndView;
    }


    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @GetMapping("/groupSummary")
    public ModelAndView ledgerLists(@PathVariable(value = "companyId") int companyId, Authentication authentication, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        Principal principal = request.getUserPrincipal();
        modelAndView.addObject("companyId", companyId);
        modelAndView.setViewName("journal/groupSummary");
        List<Company> companyList = companyService.getCompanyList().stream().filter(u -> u.getCompany_id() == companyId).collect(Collectors.toList());
        modelAndView.addObject("companyList", companyList);


        double sum = 0.0;
        List<AccountingVoucher> list = accountingVoucherService.getAll().stream().filter(u -> u.getCompany().getCompany_id() == companyId).collect(Collectors.toList());
//
//        Map<String, Double> another =  list.stream().collect(Collectors.groupingBy(AccountingVoucher::getDebitId, Collectors.summingDouble(AccountingVoucher::getDebit)));
//        Map<String, Double> another1 =  list.stream().collect(Collectors.groupingBy(AccountingVoucher::getCreditId, Collectors.summingDouble(AccountingVoucher::getCredit)));
//
//        System.out.println(another);
//        System.out.println(another1);


//      List<Double> voucheList = new ArrayList<Double>(another.values());
//
//        System.out.println(list);
//        companyid = companyId;
//        System.out.println(companyid);


        if (authentication.getName().equals(companyService.getCompany(companyId).get().getUsername())) {

            List<AccountingVoucher> accountingVoucherList = accountingVoucherService.getDebitLedger(companyId);
            accountingVoucherList.addAll(accountingVoucherService.getCreditLedger(companyId));
//            accountingVoucherList.addAll(accountingVoucherService.getDebitLedger());

            for (AccountingVoucher accountingVoucher : accountingVoucherList) {
                accountingVoucherList.forEach(d -> {
                    if (accountingVoucher.getDebitId() != null && d.getCreditId() != null) {

                        if (accountingVoucher.getDebitId().equals(d.getCreditId()) && accountingVoucher.getDebitId() != d.getCreditId()) {
                            accountingVoucher.setCreditId(d.getCreditId());
                            accountingVoucher.setCredit(d.getCredit());
                            if (accountingVoucher.getDebit() > accountingVoucher.getCredit() == true) {
                                accountingVoucher.setDebit(accountingVoucher.getDebit() - accountingVoucher.getCredit());
                                accountingVoucher.setCredit(0);
                            } else {
                                accountingVoucher.setCredit(accountingVoucher.getCredit() - accountingVoucher.getDebit());
                                accountingVoucher.setDebit(0);
                            }
                        }
                    }
                });
            }


            accountingVoucherList.forEach(s -> {
                if (s.getDebitId() == null && s.getCreditId() != null) {

                    s.setDebitId(s.getCreditId());


                }
            });

            for (int i = 0; i < accountingVoucherList.size(); i++) {
                if (accountingVoucherList.get(i).getDebitId() != null) {
                    for (int j = i + 1; j < accountingVoucherList.size(); j++) {
                        if (accountingVoucherList.get(i).getDebitId().equals(accountingVoucherList.get(j).getDebitId())) {
                            accountingVoucherList.remove(j);

                            j--;

                        }
                    }
                }
            }

            double debitSum = 0.0;
            double creditSum = 0.0;
            debitSum = accountingVoucherList.stream().mapToDouble(a -> a.getDebit()).sum();
            creditSum = accountingVoucherList.stream().mapToDouble(a -> a.getCredit()).sum();





            modelAndView.addObject("debitSum", debitSum);
            modelAndView.addObject("creditSum", creditSum);


            modelAndView.addObject("accountingVoucherList", accountingVoucherList);
        } else {
            modelAndView.setViewName("error/access-denied");
        }


//        modelAndView.addObject("voucheList",voucheList);

        return modelAndView;

    }

    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @GetMapping("/dayBook")
    public ModelAndView dayBook(@PathVariable(value = "companyId") int companyId, Authentication authentication, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        Principal principal = request.getUserPrincipal();
        List<Company> companyList = companyService.getCompanyList().stream().filter(u -> u.getCompany_id() == companyId).collect(Collectors.toList());
        modelAndView.addObject("companyList", companyList);
        modelAndView.addObject("companyId", companyId);
        modelAndView.setViewName("journal/journalList");
        System.out.println(companyId);



        if (authentication.getName().equals(companyService.getCompany(companyId).get().getUsername())) {

            List<AccountingVoucher> accountingVouchers = accountingVoucherService.getAll().stream().filter(u -> u.getCompany().getCompany_id() == companyId).collect(Collectors.toList());

            Map<String, Double> voucherMap =  new HashMap<>();
            accountingVouchers.stream().map(g -> g.getDebitId()).distinct().forEach(t -> {
                double temp = 0;
                for (AccountingVoucher a: accountingVouchers) {
                    if(t.equals(a.getDebitId())) {
                        temp += a.getDebit();
                    }
                }
                voucherMap.put(t, temp);

            });

            Map<String, Double> voucherMap1 =  new HashMap<>();
            accountingVouchers.stream().map(g -> g.getCreditId()).distinct().forEach(t -> {
                double temp = 0;
                for (AccountingVoucher a: accountingVouchers) {
                    if(t.equals(a.getCreditId())) {
                        temp += a.getCredit();
                    }
                }
                voucherMap1.put(t, temp);

            });


//            Voucher type wise

            Map<String, Double> voucherTypeMap =  new HashMap<>();
            accountingVouchers.stream().map(g -> g.getVoucherName()).distinct().forEach(t -> {
                double temp = 0;
                double temp1 = 0;
                for (AccountingVoucher a: accountingVouchers) {
                    if(t.equals(a.getVoucherName())) {
                        temp += a.getDebit();
                        temp1 += a.getCredit();
                    }
                }
                voucherTypeMap.put(t, temp);

            });


            double debitSum = 0.0;
            double creditSum = 0.0;
            double voucherSum = 0.0;
            debitSum = accountingVouchers.stream().mapToDouble(a -> a.getDebit()).sum();
            creditSum = accountingVouchers.stream().mapToDouble(a -> a.getCredit()).sum();
            voucherSum = accountingVouchers.stream().mapToDouble(a -> a.getDebit()).sum();





            modelAndView.addObject("debitSum", debitSum);
            modelAndView.addObject("creditSum", creditSum);
            modelAndView.addObject("voucherSum", voucherSum);

            modelAndView.addObject("voucherMap", voucherMap);
            modelAndView.addObject("voucherMap1", voucherMap1);
            modelAndView.addObject("voucherTypeMap", voucherTypeMap);

            modelAndView.addObject("accountingVoucherList", accountingVouchers);

        } else {
            modelAndView.setViewName("error/access-denied");
        }
        return modelAndView;

    }

    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @GetMapping("/incomeStatement")
    public ModelAndView incomeStatement(@PathVariable(value = "companyId") int companyId, Authentication authentication, HttpServletRequest request) throws ServletException, IOException {
        ModelAndView modelAndView = new ModelAndView();
        Principal principal = request.getUserPrincipal();
        modelAndView.addObject("companyId", companyId);
        modelAndView.setViewName("journal/incomeStatement");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        Map<String, Double> another =  list.stream().collect(Collectors.groupingBy(AccountingVoucher::getDebitId, Collectors.summingDouble(AccountingVoucher::getDebit)));
        List<Company> companyList = companyService.getCompanyList().stream().filter(u -> u.getCompany_id() == companyId).collect(Collectors.toList());
        modelAndView.addObject("companyList", companyList);

        if (companyService.getCompany(companyId).isPresent() && auth.getName().equals(companyService.getCompany(companyId).get().getUsername())) {

            List<AccountingVoucher> expense = accountingVoucherService.getAll().stream().filter(u->u.getCompany().getCompany_id() == companyId && u.getDebitLedger().getDevGroup().getKeyId() == 10).collect(Collectors.toList());
            List<AccountingVoucher> income = accountingVoucherService.getAll().stream().filter(u->u.getCompany().getCompany_id() == companyId && u.getCreditLedger().getDevGroup().getKeyId() == 20).collect(Collectors.toList());

//          income.stream().map(AccountingVoucher::getDebit).mapToDouble(Double::doubleValue).sum();

//            Expense

            Map<String, Double> expenseVoucherSumMap =  new HashMap<>();
            expense.stream().map(g -> g.getDebitId()).distinct().forEach(t -> {
                double temp = 0;
                for (AccountingVoucher a: expense) {
                    if(t.equals(a.getDebitId())) {
                        temp += a.getDebit();
                    }
                }
                expenseVoucherSumMap.put(t, temp);

            });

//            Income

            Map<String, Double> incomeVoucherSumMap =  new HashMap<>();
            income.stream().map(g -> g.getCreditId()).distinct().forEach(t -> {
                double temp = 0;
                for (AccountingVoucher a: income) {
                    if(t.equals(a.getCreditId())) {
                        temp += a.getCredit();
                    }
                }
                incomeVoucherSumMap.put(t, temp);

            });


           /* expense.stream().distinct().forEach(System.out::println);
            expense.stream().collect(Collectors.toSet()).forEach(System.out::println);*/


//
//            expenseVoucherSumMap.entrySet().forEach(System.out::println);


            double debitSum = 0.0;
            double creditSum = 0.0;
            debitSum = expense.stream().mapToDouble(a -> a.getDebit()).sum();
            creditSum = income.stream().mapToDouble(a -> a.getCredit()).sum();

            if (debitSum > creditSum){
               double netLoss =  debitSum  - creditSum;
               modelAndView.addObject("netLoss", netLoss);
            }else {
                double netProfit = creditSum - debitSum;
                modelAndView.addObject("netProfit", netProfit);

            }


            modelAndView.addObject("debitSum", debitSum);
            modelAndView.addObject("creditSum", creditSum);
            modelAndView.addObject("incomeVoucherSumMap", incomeVoucherSumMap);
            modelAndView.addObject("expenseVoucherSumMap", expenseVoucherSumMap);
        } else {
            modelAndView.setViewName("error/access-denied");
        }


        return modelAndView;

    }


    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @GetMapping("/balanceSheet")
    public ModelAndView balaceSheet(@PathVariable(value = "companyId") int companyId, Authentication authentication, HttpServletRequest request){

        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        modelAndView.addObject("companyId", companyId);
        modelAndView.setViewName("journal/balanceSheet");

        List<Company> companyList = companyService.getCompanyList().stream().filter(u -> u.getCompany_id() == companyId).collect(Collectors.toList());
        modelAndView.addObject("companyList", companyList);

        if (companyService.getCompany(companyId).isPresent() && auth.getName().equals(companyService.getCompany(companyId).get().getUsername())) {
            List<AccountingVoucher> assets = accountingVoucherService.getAll().stream().filter(u->u.getCompany().getCompany_id() == companyId &&  u.getDebitLedger().getDevGroup().getKeyId() == 40).collect(Collectors.toList());
            List<AccountingVoucher> liability = accountingVoucherService.getAll().stream().filter(u->u.getCompany().getCompany_id() == companyId && u.getCreditLedger().getDevGroup().getKeyId() == 30).collect(Collectors.toList());

            Map<String, Double> assetBalaceSumMap =  new HashMap<>();
            assets.stream().map(g -> g.getDebitId()).distinct().forEach(t -> {
                double temp = 0;
                for (AccountingVoucher a: assets) {
                    if(t.equals(a.getDebitId())) {
                        temp += a.getDebit();
                    }
                }
                assetBalaceSumMap.put(t, temp);

            });


            Map<String, Double> liabilityBalaceSumMap =  new HashMap<>();
            liability.stream().map(g -> g.getCreditId()).distinct().forEach(t -> {
                double temp = 0;
                for (AccountingVoucher a: liability) {
                    if(t.equals(a.getCreditId())) {
                        temp += a.getCredit();
                    }
                }
                liabilityBalaceSumMap.put(t, temp);

            });


            modelAndView.addObject("assetBalaceSumMap", assetBalaceSumMap);
            modelAndView.addObject("liabilityBalaceSumMap", liabilityBalaceSumMap);


            double debitSum = 0.0;
            double creditSum = 0.0;
            debitSum = assets.stream().mapToDouble(a -> a.getDebit()).sum();
            creditSum = liability.stream().mapToDouble(a -> a.getCredit()).sum();


                modelAndView.addObject("debitSum", debitSum);

                modelAndView.addObject("creditSum", creditSum);


        }else {
            modelAndView.setViewName("error/access-denied");
        }

            return modelAndView;
    }


}

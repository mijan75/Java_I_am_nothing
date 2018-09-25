package tally.com.tally_software.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import tally.com.tally_software.model.AccountingVoucher;
import tally.com.tally_software.repository.AccountingVoucherRepo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class AccountingVoucherService {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    AccountingVoucherRepo accountingVoucherRepo;

    @Autowired
    LedgerService ledgerService;

    public List<AccountingVoucher> getAll(){
        return accountingVoucherRepo.findAllByOrderByIdAsc();
    }

    public void save(AccountingVoucher accountingVoucher){
        accountingVoucherRepo.save(accountingVoucher);
    }
    public Optional<AccountingVoucher> getOne(int id){
        return  accountingVoucherRepo.findById(id);
    }



//    public  List<AccountingVoucher> getCreditLedger(){
//        Query query = entityManager.createNamedQuery("SELECT credit_ledger_ledger_id, SUM(credit) FROM accounting_voucher GROUP BY credit_ledger_ledger_id", AccountingVoucher.class);
//
//        return query.getResultList();
//
//    }

    public List<AccountingVoucher> getCreditLedger(int id){

        return   jdbcTemplate.query("SELECT  credit_id, SUM(credit) FROM accounting_voucher  WHERE company_company_id = '"+id+"' GROUP BY  credit_id", (resultSet, i) -> {
            return accountingVoucherD(resultSet);
        });
    }

    private AccountingVoucher accountingVoucherD(ResultSet resultSet) throws SQLException {
        AccountingVoucher accountingVoucher = new AccountingVoucher();
        accountingVoucher.setCredit(resultSet.getInt("SUM(credit)"));
        accountingVoucher.setCreditId(resultSet.getString("credit_id"));
        return accountingVoucher;
    }

    public List<AccountingVoucher> getDebitLedger(int id){
        return jdbcTemplate.query("SELECT debit_id, SUM(debit) FROM accounting_voucher WHERE company_company_id = '"+id+"'  GROUP BY debit_id", (resultSet, i)->{
            return accountingVoucherC(resultSet) ;
        });
    }

    private AccountingVoucher accountingVoucherC(ResultSet resultSet) throws SQLException {
        AccountingVoucher accountingVoucher = new AccountingVoucher();
        accountingVoucher.setDebit(resultSet.getInt("SUM(debit)"));
        accountingVoucher.setDebitId(resultSet.getString("debit_id"));
        return accountingVoucher;
    }

//    public List<AccountingVoucher> getDebit(int id){
//        return jdbcTemplate.query("SELECT debit_id, SUM(debit) FROM accounting_voucher WHERE debit_id = 'Purchase A/c' && company_company_id = '"+id+"'  GROUP BY debit_id", (resultSet, i)->{
//            return accountingVoucherC(resultSet) ;
//        });
//    }
//
//    private AccountingVoucher income(ResultSet resultSet) throws SQLException {
//        AccountingVoucher accountingVoucher = new AccountingVoucher();
//        accountingVoucher.setDebit(resultSet.getInt("SUM(debit)"));
//        accountingVoucher.setDebitId(resultSet.getString("debit_id"));
//        return accountingVoucher;
//    }






}

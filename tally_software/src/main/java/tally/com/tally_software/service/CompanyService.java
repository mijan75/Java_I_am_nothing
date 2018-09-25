package tally.com.tally_software.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tally.com.tally_software.model.Company;
import tally.com.tally_software.model.Ledger;
import tally.com.tally_software.model.User;
import tally.com.tally_software.repository.CompanyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    private static final Logger LOG =  LoggerFactory.getLogger(CompanyService.class);

    @Autowired
    CompanyRepository companyRepository;

    public List<Company> getCompanyList(){
       return companyRepository.findAll();
    }

    public Optional<Company> getCompany(int id){
        return companyRepository.findById(id);
    }

    public void createCompany(Company company){

         companyRepository.save(company);
    }

    public List<Company> getCompanyByUsername(String username) {return  companyRepository.findByUsername(username);}


//    public Company getCompanyByName(String company_name){
//        return companyRepository.findByCompany_Name(company_name);
//    }

    public void updateCompany(Company company){
        companyRepository.save(company);
    }

    public void deleteUser(Company company){
        companyRepository.delete(company);
    }

    public List<Ledger> getAllLedger(int companyId) {
        return companyRepository.getOne(companyId).getLedgers();
    }

}

package tally.com.tally_software.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tally.com.tally_software.model.Ledger;
import tally.com.tally_software.repository.CompanyRepository;
import tally.com.tally_software.repository.LedgerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LedgerService {

    @Autowired
    LedgerRepository ledgerRepository;

    @Autowired
    CompanyRepository companyRepository;





    public List<Ledger> getAllLedger(){
        return ledgerRepository.findAllByOrderByLedgerNameAsc();
    }

    public Ledger save(Ledger ledger){
        return ledgerRepository.save(ledger);
    }

    public Ledger update(Ledger ledger){
        return ledgerRepository.save(ledger);
    }

    public Optional<Ledger> getLedger(int id){
        return ledgerRepository.findById(id);
    }


    public void deleteGroup(Ledger ledger){
        ledgerRepository.delete(ledger);
    }

}

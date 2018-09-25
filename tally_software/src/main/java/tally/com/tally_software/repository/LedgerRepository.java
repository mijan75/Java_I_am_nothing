package tally.com.tally_software.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tally.com.tally_software.model.Ledger;

import java.util.List;

@Repository
public interface LedgerRepository  extends JpaRepository<Ledger, Integer> {
    public List<Ledger> findAllByOrderByLedgerNameAsc();
}

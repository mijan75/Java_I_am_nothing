package tally.com.tally_software.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tally.com.tally_software.model.AccountingVoucher;

import java.util.List;


@Repository
public interface AccountingVoucherRepo extends JpaRepository<AccountingVoucher, Integer> {

    public List<AccountingVoucher> findAllByOrderByIdAsc();



}

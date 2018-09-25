package tally.com.tally_software.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import tally.com.tally_software.model.DevVoucher;
import tally.com.tally_software.model.Groups;

import java.util.List;

@Repository
public interface DevVoucherRepository extends JpaRepository<DevVoucher, Integer> {
      public List<DevVoucher> findAllByOrderByVoucherNameAsc ();

}

package tally.com.tally_software.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.authentication.jaas.JaasPasswordCallbackHandler;
import org.springframework.stereotype.Repository;
import tally.com.tally_software.model.StockGroups;

import java.util.List;

@Repository
public interface StockGroupsRepository extends JpaRepository<StockGroups, Integer> {

    public List<StockGroups> findAllByOrderByStockGroupNameAsc();
}

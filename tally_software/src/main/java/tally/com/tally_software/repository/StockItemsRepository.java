package tally.com.tally_software.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tally.com.tally_software.model.StockItems;

import java.util.List;

@Repository
public interface StockItemsRepository extends JpaRepository<StockItems, Integer> {

    public List<StockItems> findAllByOrderByItemNameAsc();
}


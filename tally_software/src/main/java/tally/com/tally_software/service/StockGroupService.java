package tally.com.tally_software.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tally.com.tally_software.model.StockGroups;
import tally.com.tally_software.repository.StockGroupsRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StockGroupService {

    @Autowired
    StockGroupsRepository stockGroupsRepository;

    public List<StockGroups> getStockGroupsList(){
        return stockGroupsRepository.findAllByOrderByStockGroupNameAsc();
    }

    public Optional<StockGroups> getStockGroup(int id){
        return stockGroupsRepository.findById(id);
    }

    public StockGroups save(StockGroups stockGroups){

        return stockGroupsRepository.save(stockGroups);
    }

    public void update(StockGroups stockGroups){
        stockGroupsRepository.save(stockGroups);
    }

    public void delete(StockGroups stockGroups){
        stockGroupsRepository.delete(stockGroups);
    }



}

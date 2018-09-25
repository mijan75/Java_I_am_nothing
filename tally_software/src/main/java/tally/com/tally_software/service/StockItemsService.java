package tally.com.tally_software.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tally.com.tally_software.model.StockItems;
import tally.com.tally_software.repository.StockItemsRepository;

import java.util.List;
import java.util.Optional;

@Service
public  class  StockItemsService {

    @Autowired
    StockItemsRepository stockItemsRepository;


    public List<StockItems> getAllItems(){
        return stockItemsRepository.findAllByOrderByItemNameAsc();
    }

    public void save(StockItems stockItems){
        stockItemsRepository.save(stockItems);
    }

    public Optional<StockItems>getOne(int id){
        return stockItemsRepository.findById(id);
    }

}

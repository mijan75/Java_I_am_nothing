package tally.com.tally_software.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tally.com.tally_software.model.Unit;
import tally.com.tally_software.repository.UnitRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UnitService {

    @Autowired
    UnitRepository unitRepository;

    public List<Unit> getAllUnit(){
        return unitRepository.findAllByOrderBySymbolNameAsc();
    }

    public Unit save(Unit unit){
        return unitRepository.save(unit);
    }

    public Optional<Unit> getOneUnit(int id){
        return unitRepository.findById(id);
    }
}

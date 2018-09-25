package tally.com.tally_software.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tally.com.tally_software.model.DevGroup;
import tally.com.tally_software.repository.DevGroupRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DevGroupService {

    @Autowired
    private DevGroupRepository devGroupRepository;


    public List<DevGroup> getAllDevGroup(){
        return devGroupRepository.findAllByOrderByGroupNameAsc();
    }

    public DevGroup save(DevGroup devGroup){
        return devGroupRepository.save(devGroup);
    }

    public DevGroup update(DevGroup devGroup){
        return devGroupRepository.save(devGroup);
    }

    public Optional<DevGroup> getDevGroup(int id){
        return devGroupRepository.findById(id);
    }
}

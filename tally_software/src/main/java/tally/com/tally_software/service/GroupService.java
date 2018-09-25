package tally.com.tally_software.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tally.com.tally_software.model.Groups;
import tally.com.tally_software.repository.GroupRepository;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService {

    @Autowired
    GroupRepository groupRepository;

    public List<Groups> getAllGroups(){
        return groupRepository.findAllByOrderByGroupNameAsc();
    }

    public Optional<Groups> getGroups(int id){
        return groupRepository.findById(id);
    }

    public Groups saveGroups(Groups groups){
        return groupRepository.save(groups);
    }

    public Groups updateGroups(Groups groups){
        return groupRepository.save(groups);
    }

    public void deleteGroup(Groups groups){
        groupRepository.delete(groups);
    }
}

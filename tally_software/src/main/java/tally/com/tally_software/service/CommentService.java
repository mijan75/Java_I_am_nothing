package tally.com.tally_software.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tally.com.tally_software.model.UserComment;
import tally.com.tally_software.repository.CommentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;


    public List<UserComment> getAllComment(){
        return commentRepository.findAll();
    }


    public void saveComment(UserComment userComment){
        commentRepository.save(userComment);
    }

    public Optional<UserComment> getOne(int id){
        return commentRepository.findById(id);
    }
}

package tally.com.tally_software.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tally.com.tally_software.model.Feedback;
import tally.com.tally_software.model.User;
import tally.com.tally_software.repository.FeedbackRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackService {

    @Autowired
    FeedbackRepository feedbackRepository;

    public List<Feedback> getFeedbackList() {
        return feedbackRepository.findAll();
    }

    public Optional getFeedback(int id) {
        return feedbackRepository.findById(id);
    }

    public Feedback getFeedBackById(String email){ return feedbackRepository.findByEmail(email);}

    public Feedback saveFeedback(Feedback feedback){
      Feedback feed =  feedbackRepository.save(feedback);
      return feed;
    }

}

package tally.com.tally_software.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tally.com.tally_software.model.Feedback;

@Repository
public interface FeedbackRepository  extends JpaRepository<Feedback, Integer> {
    public Feedback findByEmail(String emil);
}

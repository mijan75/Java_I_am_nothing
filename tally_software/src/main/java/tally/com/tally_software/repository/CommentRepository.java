package tally.com.tally_software.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tally.com.tally_software.model.UserComment;

@Repository
public interface CommentRepository extends JpaRepository<UserComment, Integer> {
}

package tally.com.tally_software.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Jpa21Utils;
import org.springframework.stereotype.Repository;
import tally.com.tally_software.model.Unit;

import java.util.List;

@Repository
public interface UnitRepository extends JpaRepository<Unit, Integer> {
    public List<Unit> findAllByOrderBySymbolNameAsc();
}

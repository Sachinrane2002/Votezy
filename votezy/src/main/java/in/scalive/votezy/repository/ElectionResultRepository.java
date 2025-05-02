package in.scalive.votezy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.scalive.votezy.entity.electionResult;

@Repository
public interface ElectionResultRepository extends JpaRepository<electionResult, Long> {
	Optional<electionResult> findByElectionName(String electionName);
}

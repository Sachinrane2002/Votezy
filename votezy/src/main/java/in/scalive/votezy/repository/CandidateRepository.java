package in.scalive.votezy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.scalive.votezy.entity.candidate;

@Repository
public interface CandidateRepository extends JpaRepository<candidate, Long> {

	List<candidate> findAllByOrderByVoteCountDesc();

}

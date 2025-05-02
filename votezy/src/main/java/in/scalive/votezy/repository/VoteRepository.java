package in.scalive.votezy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.scalive.votezy.entity.vote;

@Repository
public interface VoteRepository extends JpaRepository<vote, Long> {

}

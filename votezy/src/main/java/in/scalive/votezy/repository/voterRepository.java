package in.scalive.votezy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.scalive.votezy.entity.voter;

@Repository
public interface voterRepository extends JpaRepository<voter, Long> {
	boolean existsByEmail(String email);
  }

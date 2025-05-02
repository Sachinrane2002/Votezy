package in.scalive.votezy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.scalive.votezy.entity.candidate;
import in.scalive.votezy.entity.vote;
import in.scalive.votezy.entity.voter;
import in.scalive.votezy.exception.ResourceNotFoundException;
import in.scalive.votezy.exception.VoteNotAllowedException;
import in.scalive.votezy.repository.CandidateRepository;
import in.scalive.votezy.repository.VoteRepository;
import in.scalive.votezy.repository.voterRepository;
import jakarta.transaction.Transactional;

@Service
public class VotingService {
	private VoteRepository voteRepository;
	private CandidateRepository candidateRepository;
	private voterRepository voterRepository;

	@Autowired
	public VotingService(VoteRepository voteRepository, CandidateRepository candidateRepository,
			in.scalive.votezy.repository.voterRepository voterRepository) {
		this.voteRepository = voteRepository;
		this.candidateRepository = candidateRepository;
		this.voterRepository = voterRepository;
	}

	@Transactional
	public vote castVote(Long voterId, Long candidateId) {
		if (!voterRepository.existsById(voterId)) {
			throw new ResourceNotFoundException("Voter not found with ID: " + voterId);
		}
		if (!candidateRepository.existsById(candidateId)) {
			throw new ResourceNotFoundException("Candidate not found with ID: " + candidateId);
		}

		voter vtr = voterRepository.findById(voterId).get();
		if (vtr.isHasVoted()) {
			throw new VoteNotAllowedException("Voter Id :" + voterId + " has already casted vote");
		}
		candidate cdt = candidateRepository.findById(candidateId).get();
		vote vote = new vote();
		vote.setVoter(vtr);
		vote.setCandidate(cdt);
		// voteRepository.save(vote);

		cdt.setVoteCount(cdt.getVoteCount() + 1);
		candidateRepository.save(cdt);
		vtr.setVote(vote);
		vtr.setHasVoted(true);
		voterRepository.save(vtr);
		return vote;
	}

	public List<vote> getAllVotes() {
		return voteRepository.findAll();
	}

}

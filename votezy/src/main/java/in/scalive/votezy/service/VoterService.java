package in.scalive.votezy.service;

import java.util.DuplicateFormatFlagsException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.scalive.votezy.entity.candidate;
import in.scalive.votezy.entity.vote;
import in.scalive.votezy.entity.voter;
import in.scalive.votezy.exception.ResourceNotFoundException;
import in.scalive.votezy.repository.CandidateRepository;
import in.scalive.votezy.repository.voterRepository;
import jakarta.transaction.Transactional;

@Service
public class VoterService {
	private voterRepository voterRepository;
	private CandidateRepository candidateRepository;

	@Autowired
	public VoterService(in.scalive.votezy.repository.voterRepository voterRepository,
			CandidateRepository candidateRepository) {
		this.voterRepository = voterRepository;
		this.candidateRepository = candidateRepository;
	}

	public voter registerVoter(voter voter) {
		if (voterRepository.existsByEmail(voter.getEmail())) {
			throw new DuplicateFormatFlagsException("Voter with email " + voter.getEmail() + " already exists");
		}
		return voterRepository.save(voter);
	}

	public List<voter> getAllVoters() {
		return voterRepository.findAll();
	}

	public voter getVoterById(Long id) {
		voter vtr = voterRepository.findById(id).orElse(null);
		if (vtr == null) {
			throw new ResourceNotFoundException("voter with id: " + id + " not found!");
		}
		return vtr;
	}

	public voter updateVoter(Long id, voter updateVoter) {
		voter voter = voterRepository.findById(id).orElse(null);
		if (voter == null) {
			throw new ResourceNotFoundException("voter with id:" + id + " not found!");
		}
		if (updateVoter.getName() != null) {
			voter.setName(updateVoter.getName());
		}
		if (updateVoter.getEmail() != null) {
			voter.setEmail(updateVoter.getEmail());
		}

		return voterRepository.save(voter);
	}

	@Transactional
	public void deletVoter(Long id) {
		voter voter = voterRepository.findById(id).orElse(null);
		if (voter == null) {
			throw new ResourceNotFoundException("Cannot delete voter with id :" + id + " as it doesn not exist");
		}

		vote vote = voter.getVote();
		if (vote != null) {
			candidate candidate = vote.getCandidate();
			candidate.setVoteCount(candidate.getVoteCount() - 1);
			candidateRepository.save(candidate);
		}
		voterRepository.delete(voter);
	}
}

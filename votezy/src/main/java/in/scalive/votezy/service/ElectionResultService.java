package in.scalive.votezy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.scalive.votezy.entity.candidate;
import in.scalive.votezy.entity.electionResult;
import in.scalive.votezy.exception.ResourceNotFoundException;
import in.scalive.votezy.repository.CandidateRepository;
import in.scalive.votezy.repository.ElectionResultRepository;
import in.scalive.votezy.repository.voterRepository;

@Service
public class ElectionResultService {
	private CandidateRepository candidateRepository;
	private ElectionResultRepository electionResultRepository;
	private voterRepository voterRepository;

	@Autowired
	public ElectionResultService(CandidateRepository candidateRepository,
			ElectionResultRepository electionResultRepository,
			in.scalive.votezy.repository.voterRepository voterRepository) {
		this.candidateRepository = candidateRepository;
		this.electionResultRepository = electionResultRepository;
		this.voterRepository = voterRepository;
	}

	public electionResult declareElectionResult(String electionName) {
		Optional<electionResult> existingResult = this.electionResultRepository.findByElectionName(electionName);
		if (existingResult.isPresent()) {
			return existingResult.get();
		}

		if (voterRepository.count() == 0) {
			throw new IllegalStateException("Cannot declare the result as no votes have been");
		}

		List<candidate> allCandidates = candidateRepository.findAllByOrderByVoteCountDesc();
		if (allCandidates.isEmpty()) {
			throw new ResourceNotFoundException("No candidates available");
		}

		candidate winner = allCandidates.get(0);
		int totalVotes = 0;
		for (candidate candidate : allCandidates) {
			totalVotes += candidate.getVoteCount();
		}

		electionResult result = new electionResult();
		result.setElectionName(electionName);
		result.setWinner(winner);
		result.setTotalVotes(totalVotes);
		return electionResultRepository.save(result);
	}
	
	public List<electionResult> getAllResults(){
		return electionResultRepository.findAll();
	}
	
}
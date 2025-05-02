package in.scalive.votezy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.scalive.votezy.entity.candidate;
import in.scalive.votezy.entity.vote;
import in.scalive.votezy.exception.ResourceNotFoundException;
import in.scalive.votezy.repository.CandidateRepository;

@Service
public class CandidateService {
	public CandidateRepository candidateRepo;

	@Autowired
	public CandidateService(CandidateRepository candidateRepo) {
		this.candidateRepo = candidateRepo;
	}

	public candidate addCandidate(candidate cdt) {
		return candidateRepo.save(cdt);
	}

	public List<candidate> getAllCandidates() {
		return candidateRepo.findAll();
	}

	public candidate getCandidateById(Long id) {
		candidate cdt = candidateRepo.findById(id).orElse(null);
		if (cdt == null) {
			throw new ResourceNotFoundException("Candidate with id:" + id + " not found");
		}
		return cdt;
	}

	public candidate updateCandidate(Long id, candidate updateCandidate) {
		candidate cdt = getCandidateById(id);
		if (updateCandidate.getName() != null) {
			cdt.setName(updateCandidate.getName());
		}
		if (updateCandidate.getParty() != null) {
			cdt.setParty(updateCandidate.getParty());
		}
		return candidateRepo.save(cdt);
	}

	public void deleteCandidate(Long id) {
		candidate cdt = getCandidateById(id);
		List<vote> votes = cdt.getVotes();
		for (vote v : votes) {
			v.setCandidate(null);
		}
		cdt.getVotes().clear();
		candidateRepo.delete(cdt);
	}

}

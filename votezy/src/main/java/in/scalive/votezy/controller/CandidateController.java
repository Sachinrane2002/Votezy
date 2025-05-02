package in.scalive.votezy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.scalive.votezy.entity.candidate;
import in.scalive.votezy.service.CandidateService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/candidates")
@CrossOrigin
public class CandidateController {
	private CandidateService cdtService;

	@Autowired
	public CandidateController(CandidateService cdtService) {
		this.cdtService = cdtService;
	}

	@PostMapping("/add")
	public ResponseEntity<candidate> addCandidate(@RequestBody @Valid candidate cdt) {
		candidate saveCandidate = cdtService.addCandidate(cdt);
		return new ResponseEntity<candidate>(saveCandidate, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<candidate>> getAllCandidates() {
		List<candidate> cdtList = this.cdtService.getAllCandidates();
		return new ResponseEntity<List<candidate>>(cdtList, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<candidate> getCandidateById(@PathVariable Long id) {
		candidate cdt = this.cdtService.getCandidateById(id);
		return new ResponseEntity<candidate>(cdt, HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<candidate> updateCandidate(@PathVariable Long id, @RequestBody candidate cdt) {
		candidate updateCandidate = cdtService.updateCandidate(id, cdt);
		return new ResponseEntity<candidate>(updateCandidate, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteCanditate(@PathVariable Long id) {
		cdtService.deleteCandidate(id);
		return new ResponseEntity<String>("Candidate With ID:" + id + " delete Succefully!", HttpStatus.OK);
	}
}

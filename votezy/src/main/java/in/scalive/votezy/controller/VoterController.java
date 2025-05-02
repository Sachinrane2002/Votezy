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

import in.scalive.votezy.entity.voter;
import in.scalive.votezy.service.VoterService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/voters")
@CrossOrigin
public class VoterController {
	private VoterService voterService;

	@Autowired
	public VoterController(VoterService voterService) {
		this.voterService = voterService;
	}

	@PostMapping("/register")
	public ResponseEntity<voter> registerVoter(@RequestBody @Valid voter voter) {
		voter saveVoter = voterService.registerVoter(voter);
		return new ResponseEntity<>(saveVoter, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<voter> getVoterById(@PathVariable Long id) {
		voter vtr = voterService.getVoterById(id);
		return new ResponseEntity<>(vtr, HttpStatus.OK);
	}

	@GetMapping()
	public ResponseEntity<List<voter>> getAllVoters() {
		List<voter> listVoters = voterService.getAllVoters();
		return new ResponseEntity<>(listVoters, HttpStatus.OK);
	}
    @PutMapping("/update/{id}")
	public ResponseEntity<voter> updateVoter(@PathVariable Long id,@RequestBody voter voter){
		voter vtr = voterService.updateVoter(id, voter);
		return new ResponseEntity<>(vtr,HttpStatus.OK);
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteVoter(@PathVariable Long id){
		voterService.deletVoter(id);
		return new ResponseEntity<>("Voter with id: "+id+" deleted",HttpStatus.OK);
	}
}
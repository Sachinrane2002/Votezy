package in.scalive.votezy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.scalive.votezy.dto.VoteRequestDto;
import in.scalive.votezy.dto.VoteResponseDto;
import in.scalive.votezy.entity.vote;
import in.scalive.votezy.service.VotingService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/votes")
@CrossOrigin
public class VotingController {
	public VotingService votingService;

	@Autowired
	public VotingController(VotingService votingService) {
		this.votingService = votingService;
	}

	@PostMapping("/cast")
	public ResponseEntity<VoteResponseDto> castVote(@RequestBody @Valid VoteRequestDto voteRequestDto) {
		vote vt = votingService.castVote(voteRequestDto.getVoterId(), voteRequestDto.getCandidateId());
		VoteResponseDto voteResponse = new VoteResponseDto("Vote Casted Successfully!", true,
				voteRequestDto.getVoterId(), voteRequestDto.getCandidateId());
		return new ResponseEntity<>(voteResponse, HttpStatus.CREATED);
	}
    @GetMapping
	public ResponseEntity<List<vote>> getAllVotes() {
		List<vote> voteList = votingService.getAllVotes();
		return new ResponseEntity<List<vote>>(voteList, HttpStatus.OK);
	}
}

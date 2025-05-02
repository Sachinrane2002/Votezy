package in.scalive.votezy.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class VoteRequestDto {
	@NotNull(message = "Voter Id is required!")
	private Long voterId;
	@NotNull(message = "Candidate Id is required!")
	private Long candidateId;
}

package in.scalive.votezy.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
@Table(name = " election_result")
public class electionResult {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotBlank(message = "Name is required")
	private String electionName;
	private int totalVotes;
	@OneToOne
	@JoinColumn(name = "winner_id")
	@JsonIgnore
	private candidate winner;

	@JsonProperty("winnerId")
	public Long getwinnerId() {
		return winner != null ? winner.getId() : null;
	}
}

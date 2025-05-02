package in.scalive.votezy.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class vote {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@OneToOne
	@JoinColumn(name = "voter_id", unique = true)
	@JsonIgnore
	private voter voter;
	@ManyToOne
	@JoinColumn(name = "candidate_id")
	@JsonIgnore
	private candidate candidate;

	@JsonProperty("voterId")
	public Long getVoterId() {
		return voter != null ? voter.getId() : null;
	}

	@JsonProperty("candidateId")
	public Long getCandidateId() {
		return candidate != null ? candidate.getId() : null;
	}
}

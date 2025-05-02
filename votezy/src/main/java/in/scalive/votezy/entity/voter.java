package in.scalive.votezy.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class voter {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
    @NotBlank(message="Name is required")
	private String name;
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid Email Formate")
	private String email;
	private boolean hasVoted = false;
	@OneToOne(mappedBy = "voter",cascade = CascadeType.ALL)
	@JsonIgnore
	private vote vote;
}

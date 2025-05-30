package com.backend.utility;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeamWithoutId {

	@NotBlank(message = "Team name cannot be blank")
	private String teamName;

	@NotBlank(message = "Game name cannot be blank")
	private String gameName;

	@NotBlank(message = "Email ID cannot be blank")
	private String emailId;

}

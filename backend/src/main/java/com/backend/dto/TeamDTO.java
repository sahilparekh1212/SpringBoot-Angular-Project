package com.backend.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeamDTO {

	@NotNull(message = "Team ID cannot be null")
	private Long id;

	@NotBlank(message = "Team name cannot be blank")
	private String teamName;

	@NotBlank(message = "Game name cannot be blank")
	private String gameName;

	@NotBlank(message = "Email ID cannot be blank")
	private String emailId;

}

package com.vac.main.controller.request;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CredentialRequest {

	@Pattern(regexp = "[0-9A-Za-z_]*", message = "User names may contain letters, numbers and underscores")
	private String userName;

}

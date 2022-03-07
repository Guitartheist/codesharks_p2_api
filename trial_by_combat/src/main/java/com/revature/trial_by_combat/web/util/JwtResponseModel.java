package com.revature.trial_by_combat.web.util;

import java.io.Serializable;

public class JwtResponseModel implements Serializable {
	/**
	*
	*/
	private static final long serialVersionUID = 1L;
	private final String token;

	public JwtResponseModel(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}
}

package com.revature.trial_by_combat.web.servlets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.trial_by_combat.web.util.JwtRequestModel;
import com.revature.trial_by_combat.web.util.JwtResponseModel;
import com.revature.trial_by_combat.web.util.JwtUserDetailsService;
import com.revature.trial_by_combat.web.util.TokenManager;

/**
 * 
 * REST endpoint for user authentication
 *
 * @author Philip Wentworth
 * @version 1.0
 * @since 1.0
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthServlet {
	
	/**
	 * Implements org.springframework.security.core.userdetails.UserDetailsService;
	 * Uses method loadUserByUsername() 
	 */
	@Autowired
	private JwtUserDetailsService userDetailsService;

	/**
	 * Authenticates user based on user name and password
	 */
	@Autowired
	private AuthenticationManager authenticationManager;
	
	/**
	 * Generates the JWT user token
	 */
	@Autowired
	private TokenManager tokenManager;

	/**
	 * Responds to client requesting authentication by generating a JWT token and returning the token to the client
	 * @param request Holds username and password from client request.
	 * @return Status OK and JWT token.
	 * @throws Exception "USER_DIABLED" and "INVALID_CREDENTIALS".
	 */
	@PostMapping("/login")
	public ResponseEntity<JwtResponseModel> createToken(@RequestBody JwtRequestModel request) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
		final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
		final String jwtToken = tokenManager.generateJwtToken(userDetails);
		return ResponseEntity.ok(new JwtResponseModel(jwtToken));
	}
}

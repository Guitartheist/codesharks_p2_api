package com.revature.trial_by_combat.web.util;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.revature.trial_by_combat.models.Player;
import com.revature.trial_by_combat.services.PlayerService;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	private final PlayerService playerService;

	public JwtUserDetailsService(PlayerService playerService) {
		super();
		this.playerService = playerService;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Player> player = playerService.findPlayerByUsername(username);
		if (player.isPresent()) {
			return new User(player.get().getUsername(), player.get().getPassword(), new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}
}

package com.revature.trial_by_combat.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.trial_by_combat.daos.PlayerDAO;
import com.revature.trial_by_combat.models.Player;

@Service
public class PlayerService {
	private final PlayerDAO playerDAO;
	
	@Autowired
	public PlayerService(PlayerDAO playerDAO) {
		this.playerDAO = playerDAO;
	}
	
	@Transactional
	public Player registerNewPlayer(Player player) {
		boolean usernameAvailable = playerDAO.findPlayerByUsername(player.getUsername()).isEmpty();
		boolean emailAvailable = playerDAO.findPlayerByEmail(player.getEmail()).isEmpty();
		
		if (usernameAvailable && emailAvailable) {
			String password = player.getPassword();
			PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String hashedPassword = passwordEncoder.encode(password);
			player.setPassword(hashedPassword);
			
			Player persistedPlayer = playerDAO.save(player);
			return persistedPlayer;
		}
		
		return null;
	}
	
	public Iterable<Player> findAllPlayers() {
		return playerDAO.findAll();
	}
	
	public Optional<Player> findPlayerById(int id) {
		return playerDAO.findById(id);
	}
	
	public Optional<Player> findPlayerByUsername(String username) {
		return playerDAO.findPlayerByUsername(username);
	}
	
	public Player findPlayerByUsernameAndPassword(String username, String password) {
		return playerDAO.findPlayerByUsernameAndPassword(username, password);
	}
	
	@Transactional
	public Player updatePlayer(Player player) {
		if (player.getPassword() == null)
			player.setPassword( playerDAO.findById(player.getId()).get().getPassword() );
		else {
			String password = player.getPassword();
			PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String hashedPassword = passwordEncoder.encode(password);
			player.setPassword(hashedPassword);
		}
		return playerDAO.save(player);
	}
	
	@Transactional
	public void deletePlayer(int id) {
		playerDAO.deleteById(id);
	}
}

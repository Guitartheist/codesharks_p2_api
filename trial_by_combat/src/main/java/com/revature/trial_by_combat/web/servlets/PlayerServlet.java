package com.revature.trial_by_combat.web.servlets;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.revature.trial_by_combat.models.Player;
import com.revature.trial_by_combat.services.PlayerService;
import com.revature.trial_by_combat.web.util.TokenManager;

@RestController
@RequestMapping("/player")
public class PlayerServlet {
	private final PlayerService playerService;
	private final TokenManager tokenManager;
	
	@Autowired
	public PlayerServlet(PlayerService playerService, TokenManager tokenManager) {
		this.playerService = playerService;
		this.tokenManager = tokenManager;
	}
	
	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Player createPlayer(@RequestBody Player player) {
		return playerService.registerNewPlayer(player);
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Player findPlayerById(@RequestParam int id) {
		return playerService.findPlayerById(id).get();
	}
	
	@GetMapping("/all")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Iterable<Player> findAllPlayers() {
		return playerService.findAllPlayers();
	}
	
	@GetMapping("/me")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public String getUsernameFromToken(HttpServletRequest request) {
		return tokenManager.getUsernameFromRequest(request);
	}
	
	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Player updatePlayer(@RequestBody Player player) {
		return playerService.updatePlayer(player);
	}
	
	@DeleteMapping
	@ResponseStatus(HttpStatus.OK)
	public void deletePlayer(@RequestParam int id) {
		playerService.deletePlayer(id);
	}
}

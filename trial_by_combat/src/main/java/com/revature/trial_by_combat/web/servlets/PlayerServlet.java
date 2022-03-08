package com.revature.trial_by_combat.web.servlets;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
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

@RestController
@RequestMapping("/player")
public class PlayerServlet {
	private final PlayerService playerService;
	
	@Autowired
	public PlayerServlet(PlayerService playerService) {
		this.playerService = playerService;
	}
	
	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Player createPlayer(@RequestBody @Valid Player player) {
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
	public Player getUsernameFromToken(Authentication auth) {
		return playerService.findPlayerByUsername( auth.getName() ).get();
	}
	
	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Player updatePlayer(@RequestBody @Valid Player player, Authentication auth) {
		if (playerService.findPlayerById(player.getId()).get().getUsername().equals(auth.getName()))
			return playerService.updatePlayer(player);
		else
			return null;
	}
	
	@DeleteMapping
	@ResponseStatus(HttpStatus.OK)
	public void deletePlayer(@RequestParam int id, Authentication auth) {
		if (playerService.findPlayerById(id).get().getUsername().equals(auth.getName()))
			playerService.deletePlayer(id);
	}
}

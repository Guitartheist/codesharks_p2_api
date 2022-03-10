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

/**
 * 
 * REST endpoint for calls to player database
 * 
 * @author Philip Wentworth
 * @version 1.0
 * @since 1.0
 * 
 */
@RestController
@RequestMapping("/player")
public class PlayerServlet {
	
	/**
	 *  Calls the Player Data Access Object (extends CrudRepository)
	 */
	private final PlayerService playerService;
	
	/**
	 * Database access dependency injection.
	 * PlayerService Dependency Injection.
	 * @param playerService calls the Player Data Access Object to interact with database.
	 */
	@Autowired
	public PlayerServlet(PlayerService playerService) {
		this.playerService = playerService;
	}
	
	/**
	 * Creates a Player in the database.
	 * @param player Player object to be persisted in database.
	 * @return Returns a copy of the persisted object.
	 */
	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Player createPlayer(@RequestBody @Valid Player player) {
		return playerService.registerNewPlayer(player);
	}
	
	/**
	 * Retrieves player by supplied id.
	 * @param id Id of Player to be retrieved from the database.
	 * @return A copy of the Player.
	 */
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Player findPlayerById(@RequestParam int id) {
		return playerService.findPlayerById(id).get();
	}
	
	/**
	 * Retrieves all players from the database. (Mapped to ./player/all).
	 * @return An Iterable interface that returns the players retrieved from the database
	 */
	@GetMapping("/all")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Iterable<Player> findAllPlayers() {
		return playerService.findAllPlayers();
	}
	
	/**
	 * Retrieves player by username from JWT auth token. (Mapped to ./player/me).
	 * @param auth JWT user token
	 * @return A player object
	 */
	@GetMapping("/me")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Player getUsernameFromToken(Authentication auth) {
		return playerService.findPlayerByUsername( auth.getName() ).get();
	}
	
	/**
	 * Updates the player's information in the database.
	 * @param player The player object to updated in the database
	 * @param auth JWT user token
	 * @return A copy of the updated armor object
	 */
	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Player updatePlayer(@RequestBody @Valid Player player, Authentication auth) {
		if (playerService.findPlayerById(player.getId()).get().getUsername().equals(auth.getName()))
			return playerService.updatePlayer(player);
		else
			return null;
	}
	
	/**
	 * Deletes the player with the supplied id from the database
	 * @param id The id of the player to be deleted
	 * @param auth JWT auth token
	 */
	@DeleteMapping
	@ResponseStatus(HttpStatus.OK)
	public void deletePlayer(@RequestParam int id, Authentication auth) {
		if (playerService.findPlayerById(id).get().getUsername().equals(auth.getName()))
			playerService.deletePlayer(id);
	}
}

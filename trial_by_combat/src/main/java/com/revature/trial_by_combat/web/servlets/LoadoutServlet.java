package com.revature.trial_by_combat.web.servlets;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.revature.trial_by_combat.models.Loadout;
import com.revature.trial_by_combat.services.PlayerService;
import com.revature.trial_by_combat.services.LoadoutService;

/**
 * 
 * REST endpoint for calls to loadout database
 *
 * @author Joshua Evans
 * @version 1.0
 * @since 1.0
 * 
 */
@RestController
@RequestMapping("/loadout")
public class LoadoutServlet {
	/**
	 *  Calls the Loadout Data Access Object (extends CrudRepository)
	 */
	private final LoadoutService loadoutService;
	
	/**
	 *  Calls the Player Data Access Object (extends CrudRepository)
	 */
	private final PlayerService playerService;

	/**
	 * Database access dependency injection.
	 * LoadoutService and PlayerService dependency injection
	 * @param loadoutService calls the Loadout Data Access Object to interact with database.
	 * @param playerService calls the Player Data Access Object to interact with database.
	 */
	@Autowired
	public LoadoutServlet(LoadoutService loadoutService, PlayerService playerService) {
		this.loadoutService = loadoutService;
		this.playerService = playerService;
	}

	/**
	 * Create a loadout (a set of items an avatar uses)in the database.
	 * @param loadout Loadout object to be persisted to the database.
	 * @param auth JWT user token
	 * @return A copy of the loadout.
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Loadout createLoadout(@RequestBody Loadout loadout, Authentication auth) {
		loadout.setPlayer( playerService.findPlayerByUsername(auth.getName()).get());
		return loadoutService.createNewLoadout(loadout);
	}
	
	/**
	 * Retrieves all loudouts from the database. (Mapped to ./loadout/all).
	 * @return An Iterable interface that returns the loadouts retrieved from the database
	 */	
	@GetMapping("/all")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Iterable<Loadout> findAllLoadouts() {
		return loadoutService.findAllLoadouts();
	}
	
	/**
	 * Retrieves loadouts belonging to player, by player id. (Mapped to ./loadout/player).
	 * @param id Id of player
	 * @return An Iterable interface that returns the player's loadouts from the database.
	 */
	@GetMapping("/player")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Iterable<Loadout> findLoadoutByPlayerId(@RequestParam int id) {
		return loadoutService.findLoadoutsByPlayerId(id);
	}
	
	/**
	 * Retrieves loadout by supplied id.
	 * @param id Id of loadout to be retrieved from the database.
	 * @return A copy of the loadout.
	 */
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Loadout findLoadoutById(@RequestParam int id ) {
		return loadoutService.findLoadoutById(id).get();
	}
	
	
	/**
	 * Updates the supplied loaodut's information in the database.
	 * Explicitly sets Loadout player and name data in order to avoid duplicates with different ids in the database.
	 * @param loadout The loadout object to updated in the datbase
	 * @return A copy of the updated loadout object
	 */
	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Loadout updateLoadout(@RequestBody Loadout loadout) {
		Loadout loadoutToUpdate = loadoutService.findLoadoutById(loadout.getId()).get();
		loadoutToUpdate.setPlayer( playerService.findPlayerById(loadout.getPlayer().getId()).get() );
		loadoutToUpdate.setName(loadout.getName());
		loadoutToUpdate.setDescription(loadout.getDescription());
		
		return loadoutService.updateLoadout(loadoutToUpdate);
	}
	
	/**
	 * Deletes the loadout with the supplied id from the database
	 * @param id The id of the loadout to be deleted
	 */
	@DeleteMapping
	@ResponseStatus(HttpStatus.OK)
	public void deleteLoadout(@RequestParam int id) {
		loadoutService.deleteLoadoutById(id);
	}
}




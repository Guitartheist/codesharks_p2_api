package com.revature.trial_by_combat.web.servlets;

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

import com.revature.trial_by_combat.models.Avatar;
import com.revature.trial_by_combat.services.AvatarService;
import com.revature.trial_by_combat.services.PlayerService;

/**
 * 
 * REST endpoint for calls to Avatar database
 *
 * @author Philip Wentworth
 * @version 1.0
 * @since 1.0
 *
 */
@RestController
@RequestMapping("/avatar")
public class AvatarServlet {
	/**
	 *  Calls the Avatar Data Access Object (extends CrudRepository)
	 */
	private final AvatarService avatarService;
	/**
	 *  Calls the Player Data Access Object (extends CrudRepository)
	 */
	private final PlayerService playerService;
	
	/**
	 * Database access dependency injection.
	 * AvatarService, PlayerService Dependency Injection.
	 * @param avatarService calls the Avatar Data Access Object to interact with database.
	 * @param playerService calls the Player Data Access Object to interact with database.
	 */
	@Autowired
	public AvatarServlet(AvatarService avatarService, PlayerService playerService) {
		this.avatarService = avatarService;
		this.playerService = playerService;
	}
	
	/**
	 * Creates an Avatar in the database.
	 * @param avatar Avatar object to be persisted in database.
	 * @param auth JWT user token.
	 * @return Returns a copy of the persisted object.
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Avatar createAvatar(@RequestBody Avatar avatar, Authentication auth) {
		avatar.setPlayer( playerService.findPlayerByUsername( auth.getName() ).get() );
		return avatarService.createNewAvatar(avatar);
	}
	
	/**
	 * Retrieves Avatar by supplied id.
	 * @param id Id of Avatar to be retrieved from the database.
	 * @return A copy of the Avatar.
	 */
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Avatar findAvatarById(@RequestParam int id) {
		return avatarService.findAvatarById(id).get();
	}
	
	/**
	 * Retrieves Avatars by supplied Player id. (Mapped to ./avatar/player).
	 * @param auth JWT auth token.
	 * @return An Iterable interface that returns the Avatars retrieved from the database.
	 */
	@GetMapping("/player")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Iterable<Avatar> findAvatarsByPlayerId(Authentication auth) {
		return avatarService.findAvatarsByPlayerId( playerService.findPlayerByUsername(auth.getName()).get().getId() );
	}
	
	/**
	 * Retrieves all avatars from the database. (Mapped to ./avatar/all).
	 * @return An Iterable interface that returns the avatars retrieved from the database
	 */
	@GetMapping("/all")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Iterable<Avatar> findAllAvatars() {
		return avatarService.findAllAvatars();
	}
	
	/**
	 * Updates the avatar's information in the database.
	 * @param avatar The avatar object to updated in the database
	 * @return A copy of the updated avatar object
	 */
	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Avatar updateAvatar(@RequestBody Avatar avatar) {
		avatar.setPlayer( playerService.findPlayerById(avatar.getPlayer().getId()).get() );
		return avatarService.updateAvatar(avatar);
	}
	
	/**
	 * Deletes the avatar with the supplied id from the database
	 * @param id The id of the avatar item to be deleted
	 */
	@DeleteMapping
	@ResponseStatus(HttpStatus.OK)
	public void deleteAvatar(@RequestParam int id) {
		avatarService.deleteAvatar(id);
	}
}

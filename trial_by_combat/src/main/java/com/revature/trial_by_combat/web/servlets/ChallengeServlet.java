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
import com.revature.trial_by_combat.models.Challenge;
import com.revature.trial_by_combat.services.ChallengeService;
import com.revature.trial_by_combat.services.AvatarService;

/**
 * 
 * REST endpoint for calls to challenge database
 *
 * @author Philip Wentworth
 * @version 1.0
 * @since 1.0
 *
 */
@RestController
@RequestMapping("/challenge")
public class ChallengeServlet {
	/**
	 *  Calls the Challenge Data Access Object (extends CrudRepository)
	 */
	private final ChallengeService challengeService;
	/**
	 *  Calls the Avatar Data Access Object (extends CrudRepository)
	 */
	private final AvatarService avatarService;

	/**
	 * Database access dependency injection.
	 * ChallengeService, AvatarService dependency injection.
	 * @param challengeService calls the Challenge Data Access Object to interact with database.
	 * @param avatarService calls the Avatar Service Data Access Object to interact with database.
	 */
	@Autowired
	public ChallengeServlet(ChallengeService challengeService, AvatarService avatarService) {
		this.challengeService = challengeService;
		this.avatarService = avatarService;
	}

	/**
	 * Creates a Challenge in the database.
	 * @param challenge Challenge object to be persisted in database.
	 * @return Returns a copy of the persisted object.
	 * @throws Exception "No challenge found to save to database.".
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Challenge createChallenge(@RequestBody Challenge challenge) throws Exception {
		challenge.setAvatar(avatarService.findAvatarById(challenge.getAvatar().getId()).get());
		if (challenge.getChallenger() != null)
			challenge.setChallenger(avatarService.findAvatarById(challenge.getChallenger().getId()).get());
		else
			challenge.setChallenger(null);
		return challengeService.createNewChallenge(challenge);
	}
	
	/**
	 * Retrieves Challenge by supplied id.
	 * @param id Id of Challenge to be retrieved from the database.
	 * @return A copy of the Challenge.
	 */
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Challenge findChallengeById(@RequestParam int id) {
		return challengeService.findChallengeById(id);
	}
	
	/**
	 * Retrieves all challenges from the database. (Mapped to ./challenge/all).
	 * @return An Iterable interface that returns the challenges retrieved from the database
	 */
	@GetMapping("/all")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Iterable<Challenge> findAllChallenges() {
		return challengeService.findAllChallenges();
	}

	/**
	 * Updates the challenge's information in the database.
	 * @param challenge The challenge object to updated in the database
	 * @return A copy of the updated challenge object
	 */
	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Challenge updateChallenge(@RequestBody Challenge challenge) {
		Avatar avatar = avatarService.findAvatarById(challenge.getAvatar().getId()).get();
		Avatar challenger = avatarService.findAvatarById(challenge.getChallenger().getId()).get();
		challenge = challengeService.findChallengeById(challenge.getId());
		challenge.setAvatar(avatar);
		challenge.setChallenger(challenger);
		return challengeService.updateChallenge(challenge);
	}

	/**
	 * Deletes the challenge with the supplied id from the database
	 * @param id The id of the challenge to be deleted
	 */
	@DeleteMapping
	@ResponseStatus(HttpStatus.OK)
	public void deleteChallengeById(@RequestParam int id) {
		challengeService.deleteChallengeById(id);
	}
}
package com.revature.trial_by_combat.web.servlets;

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

import com.revature.trial_by_combat.models.ChallengeAction;
import com.revature.trial_by_combat.services.ChallengeActionService;
import com.revature.trial_by_combat.services.ChallengeService;

/**
 * 
 * REST endpoint for calls to challenge action database
 *
 * @author Philip Wentworth
 * @version 1.0
 * @since 1.0
 *
 */
@RestController
@RequestMapping("/challenge_action")
public class ChallengeActionServlet {
	/**
	 *  Calls the Challenge Action Data Access Object (extends CrudRepository)
	 */
	private final ChallengeActionService challengeActionService;
	/**
	 *  Calls the Challenge Service Data Access Object (extends CrudRepository)
	 */
	private final ChallengeService challengeService;
	
	/**
	 * Database access dependency injection.
	 * ChallengeActionService, ChallengeService dependency injection.
	 * @param challengeActionService calls the Challenge Action Data Access Object to interact with database.
	 * @param challengeService calls the Challenge Service Data Access Object to interact with database.
	 */
	@Autowired
	public ChallengeActionServlet(ChallengeActionService challengeActionService, ChallengeService challengeService) {
		this.challengeActionService = challengeActionService;
		this.challengeService = challengeService;
	}
	
	/**
	 * Creates a Challenge Action in the database.
	 * @param challengeAction Challenge Action object to be persisted in database.
	 * @return Returns a copy of the persisted object.
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ChallengeAction addChallengeAction(@RequestBody ChallengeAction challengeAction) {
		challengeAction.setChallenge( challengeService.findChallengeById( challengeAction.getChallenge().getId() ) );
		return challengeActionService.addChallengeAction(challengeAction);
	}
	
	/**
	 * Retrieves Challenge Action by supplied id.
	 * @param id Id of Challenge Action to be retrieved from the database.
	 * @return A copy of the Challenge Action.
	 */
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ChallengeAction findChallengeActionById(@RequestParam int id) {
		
		return challengeActionService.findChallengeActionById(id).get();
	}
	
	/**
	 * Retrieves Challenge Actions by supplied Challenge id. (Mapped to ./challenge_action/challenge).
	 * @param id Id of Challenge to be used to retrieve Challenge Actions from the database.
	 * @return An Iterable interface that returns the Challenges Actions retrieved from the database.
	 */
	@GetMapping("/challenge")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Iterable<ChallengeAction> findByChallengeId(@RequestParam int id) {
		return challengeActionService.findChallengeActionsByChallengeId(id);
	}
	
	/**
	 * Retrieves all challenge actions from the database. (Mapped to ./challenge_action/all).
	 * @return An Iterable interface that returns the challenge actions retrieved from the database
	 */
	@GetMapping("/all")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Iterable<ChallengeAction> findByChallengeId() {
		return challengeActionService.findAllChallengeActions();
	}
	
	/**
	 * Updates the challenge action's information in the database.
	 * @param challengeAction The challenge action object to updated in the database
	 * @return A copy of the updated challenge action object
	 */
	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ChallengeAction updateChallengeAction(@RequestBody ChallengeAction challengeAction) {
		challengeAction.setChallenge( challengeService.findChallengeById( challengeAction.getChallenge().getId() ) );
		return challengeActionService.updateChallengeAction(challengeAction);
	}
	
	/**
	 * Deletes the challenge action with the supplied id from the database
	 * @param id The id of the challenge action item to be deleted
	 */
	@DeleteMapping
	@ResponseStatus(HttpStatus.OK)
	public void deleteChallengeAction(@RequestParam int id) {
		challengeActionService.deleteChallengeAction(id);
	}
}

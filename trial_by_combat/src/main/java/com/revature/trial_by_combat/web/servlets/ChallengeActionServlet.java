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

@RestController
@RequestMapping("/challenge_action")
public class ChallengeActionServlet {
	private final ChallengeActionService challengeActionService;
	private final ChallengeService challengeService;
	
	@Autowired
	public ChallengeActionServlet(ChallengeActionService challengeActionService, ChallengeService challengeService) {
		this.challengeActionService = challengeActionService;
		this.challengeService = challengeService;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ChallengeAction addChallengeAction(@RequestBody ChallengeAction challengeAction) {
		challengeAction.setChallenge( challengeService.findChallengeById( challengeAction.getChallenge().getId() ).get() );
		return challengeActionService.updateChallengeAction(challengeAction);
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ChallengeAction findChallengeActionById(@RequestParam int id) {
		
		return challengeActionService.findChallengeActionById(id).get();
	}
	
	@GetMapping("/challenge")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Iterable<ChallengeAction> findByChallengeId(@RequestParam int id) {
		return challengeActionService.findChallengeActionsByChallengeId(id);
	}
	
	@GetMapping("/all")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Iterable<ChallengeAction> findByChallengeId() {
		return challengeActionService.findAllChallengeActions();
	}
	
	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ChallengeAction updateChallengeAction(@RequestBody ChallengeAction challengeAction) {
		challengeAction.setChallenge( challengeService.findChallengeById( challengeAction.getChallenge().getId() ).get() );
		return challengeActionService.updateChallengeAction(challengeAction);
	}
	
	@DeleteMapping
	@ResponseStatus(HttpStatus.OK)
	public void deleteChallengeAction(@RequestParam int id) {
		challengeActionService.deleteChallengeAction(id);
	}
}

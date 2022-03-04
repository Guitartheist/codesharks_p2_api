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

import com.revature.trial_by_combat.models.Challenge;
import com.revature.trial_by_combat.services.ChallengeService;
import com.revature.trial_by_combat.services.AvatarService;

@RestController
@RequestMapping("/challenge")
public class ChallengeServlet {
	private final ChallengeService challengeService;
	private final AvatarService avatarService;
	
	@Autowired
	public ChallengeServlet(ChallengeService challengeService, AvatarService avatarService) {
		this.challengeService = challengeService;
		this.avatarService = avatarService;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Challenge createChallenge(@RequestBody Challenge challenge) throws Exception {
		challenge.setAvatar( avatarService.findAvatarById(challenge.getAvatar().getId()).get());
        challenge.setChallenger( avatarService.findAvatarById(challenge.getChallenger().getId()).get());
		return challengeService.createNewChallenge(challenge);
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Challenge findChallengeById(@RequestParam int id) {
		return challengeService.findChallengeById(id).get();
	}
	
	@GetMapping("/avatar")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Iterable<Challenge> findAllChallengesByAvatarId(@RequestParam int id) {
		return challengeService.findAllChallengesByAvatarId(id);
	}

    @GetMapping("/challenger")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Iterable<Challenge> findAllChallengesByChallengerId(@RequestParam int id) {
		return challengeService.findAllChallengesByChallengerId(id);
    }
	
	@GetMapping("/all")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Iterable<Challenge> findAllChallenges() {
		return challengeService.findAllChallenges();
	}
	
	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Challenge updateChallenge(@RequestBody Challenge challenge) {
		challenge.setAvatar(avatarService.findAvatarById(challenge.getAvatar().getId()).get());
        challenge.setChallenger(avatarService.findAvatarById(challenge.getAvatar().getId()).get());
		return challengeService.updateChallenge(challenge);
	}
	
	@DeleteMapping
	@ResponseStatus(HttpStatus.OK)
	public void deleteChallengeById(@RequestParam int id) {
		challengeService.deleteChallengeById(id);
	}
}
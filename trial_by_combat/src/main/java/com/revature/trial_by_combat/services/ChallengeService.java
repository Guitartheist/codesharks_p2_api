package com.revature.trial_by_combat.services;

import java.util.Optional;

import javax.transaction.Transactional;
import com.revature.trial_by_combat.daos.ChallengeDAO;
import com.revature.trial_by_combat.models.Avatar;
import com.revature.trial_by_combat.models.Challenge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChallengeService {

	private final ChallengeDAO challengeDAO;

	@Autowired
	public ChallengeService(ChallengeDAO challengeDAO) {
		this.challengeDAO = challengeDAO;
	}

	@Transactional
	public Challenge createNewChallenge(Challenge challenge) throws Exception {

		if (challenge.getChallenger() != null)
			authenticateChallenge(challenge.getAvatar(), challenge.getChallenger());

		Challenge persistedChallenge = challengeDAO.save(challenge);
		return persistedChallenge;
	}

	@Transactional
	public Iterable<Challenge> findAllChallenges() {
		return challengeDAO.findAll();
	}

	@Transactional
	public Challenge findChallengeById(int id) {
		return challengeDAO.findChallengeById(id);
	}

	@Transactional
	public Challenge updateChallenge(Challenge challenge) {
		return challengeDAO.save(challenge);
	}

	@Transactional
	public void deleteChallengeById(int id) {
		challengeDAO.deleteById(id);
	}

	@Transactional
	public boolean authenticateChallenge(Avatar avatar, Avatar challenger) throws Exception {

		if (avatar.equals(challenger)) {
			throw new Exception("An Avatar can't fight against themselves!");
		}
		return true;
	}

}
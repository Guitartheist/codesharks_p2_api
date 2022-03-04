package com.revature.trial_by_combat.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.trial_by_combat.daos.ChallengeActionDAO;
import com.revature.trial_by_combat.models.ChallengeAction;

@Service
public class ChallengeActionService {
	private final ChallengeActionDAO challengeActionDAO;
	
	@Autowired
	public ChallengeActionService(ChallengeActionDAO challengeActionDAO) {
		this.challengeActionDAO = challengeActionDAO;
	}
	
	@Transactional
	public ChallengeAction addChallengeAction(ChallengeAction challengeAction) {
		challengeAction.setTimestamp(LocalDateTime.now ());
		return challengeActionDAO.save(challengeAction);
	}
	
	public Iterable<ChallengeAction> findAllChallengeActions() {
		return challengeActionDAO.findAll();
	}
	
	public Iterable<ChallengeAction> findChallengeActionsByChallengeId(int id) {
		return challengeActionDAO.findByChallengeId(id);
	}
	
	public Optional<ChallengeAction> findChallengeActionById(int id) {
		return challengeActionDAO.findById(id);
	}
	
	@Transactional
	public ChallengeAction updateChallengeAction(ChallengeAction challengeAction) {
		challengeAction.setTimestamp( challengeActionDAO.findById(challengeAction.getId()).get().getTimestamp() );
		return challengeActionDAO.save(challengeAction);
	}
	
	@Transactional
	public void deleteChallengeAction(int id) {
		challengeActionDAO.deleteById(id);
	}
}

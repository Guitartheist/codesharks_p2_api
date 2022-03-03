package com.revature.trial_by_combat.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.trial_by_combat.daos.ChallengeActionDAO;
import com.revature.trial_by_combat.models.ChallengeAction;

@ExtendWith(MockitoExtension.class)
public class ChallengeActionServiceTestSuite {
	@Mock
	private ChallengeActionDAO challengeActionDAO;

	ChallengeActionService sut;
	
	ChallengeAction challengeAction;
	
	@BeforeEach
	public void testPrep() {
		sut = new ChallengeActionService(challengeActionDAO);
		challengeAction = new ChallengeAction();
	}
	
	@Test
	public void test_ChallengeAction_addChallengeAction() {
		sut.addChallengeAction(challengeAction);
	}
	@Test
	public void test_ChallengeAction_findAllChallengeActions() {
		sut.findAllChallengeActions();
	}
	@Test
	public void test_ChallengeAction_findChallengeActionsByChallengeId() {
		sut.findChallengeActionById(1);
	}
	@Test
	public void test_ChallengeAction_findChallengeActionById() {
		sut.findChallengeActionsByChallengeId(1);
	}
	@Test
	public void test_ChallengeAction_updateChallengeAction() {
		sut.updateChallengeAction(challengeAction);
	}
	@Test
	public void test_ChallengeAction_deleteChallengeAction() {
		sut.deleteChallengeAction(1);
	}
}

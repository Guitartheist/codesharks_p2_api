package com.revature.trial_by_combat.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.trial_by_combat.daos.HealingPotionDAO;
import com.revature.trial_by_combat.models.HealingPotion;

@ExtendWith(MockitoExtension.class)
public class HealingPotionServiceTestSuite {

    @Mock
	private HealingPotionDAO healingPotionDAO;
	
	HealingPotionService sut;
	
	HealingPotion healingPotion;
	
	@BeforeEach
	public void testPrep() {
        sut = new HealingPotionService(healingPotionDAO);
        healingPotion = new HealingPotion();

    }

    @Test
	public void test_HealingPotionService_registerNewHealingPotion() {
		sut.registerNewHealingPotion(healingPotion);
	}
	
	@Test
	public void test_HealingPotionService_findAllHealingPotions() {
		sut.findAllHealingPotions();
	}

	
	@Test
	public void test_HealingPotionService_updateHealingPotion() {
		sut.updateHealingPotion(healingPotion);
	}
	
	
	// @Test
	// public void test_HealingPotionService_findHealingPotionById() {
	// 	sut.findHealingPotionById();
	// }
	
	// @Test
	// public void test_HealingPotionService_deleteHealingPotion() {
	// 	sut.deleteHealingPotionById();
	// }
    
}

package com.revature.trial_by_combat.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.trial_by_combat.daos.HealingPotionDAO;
import com.revature.trial_by_combat.daos.WeaponDAO;
import com.revature.trial_by_combat.models.HealingPotion;
import com.revature.trial_by_combat.models.Weapon;

@ExtendWith(MockitoExtension.class)
public class HealingPotionServiceTestSuite {

	@Mock
	private HealingPotionDAO healingpotionDAO;

	HealingPotionService sut;

	HealingPotion healingPotion;

	HealingPotion healingPotion2;

	@BeforeEach
	public void testPrep() {
		sut = new HealingPotionService(healingpotionDAO);
		healingPotion = new HealingPotion();
		healingPotion2 = new HealingPotion();

	}

	@Test
	public void test_HealingPotionService_registerNewHealingPotion() throws Exception {

//		when(healingpotionDAO.findHealingPotionByItemName(healingPotion.getItemname())).thenReturn(Optional.of(healingPotion));
//		Exception thrown = assertThrows(Exception.class, () -> sut.registerNewHealingPotion(healingPotion),
//				"The healing potion name selected is already in use.");
//		assertTrue(thrown.getMessage().contains("The healing potion name selected is already in use."));

		when(healingpotionDAO.findHealingPotionByItemName(healingPotion2.getItemname())).thenReturn(Optional.empty());
		when(healingpotionDAO.save(healingPotion2)).thenReturn(healingPotion2);
		assertNotNull(sut.registerNewHealingPotion(healingPotion2));

	}

	@Test
	public void test_HealingPotionService_findAllHealingPotions() {

		List<HealingPotion> mockList = new ArrayList<HealingPotion>();
		mockList.add(healingPotion);
		Iterable<HealingPotion> iterableMock = mockList;
		when(healingpotionDAO.findAll()).thenReturn(iterableMock);
		Iterable<HealingPotion> healingPotionList = sut.findAllHealingPotions();
		assertNotNull(healingPotionList);

	}

	@Test
	public void test_HealingPotionService_findHealingPotionById() {
		Optional<HealingPotion> mockHealingPotion = Optional.of(healingPotion);
		when(healingpotionDAO.findById(1)).thenReturn(mockHealingPotion);
		assertTrue(sut.findHealingPotionById(1).isPresent());
	}

	@Test
	public void test_HealingPotionService_updateHealingPotion() {
		when(healingpotionDAO.save(healingPotion)).thenReturn(healingPotion);
		assertNotNull(sut.updateHealingPotion(healingPotion));
	}

	@Test
	public void test_HealingPotionService_deleteHealingPotion() {
		sut.deleteHealingPotion(1);
	}

	@Test
	public void test_HealingPotionService_findHealingPotionByItemName() {
		when(healingpotionDAO.findHealingPotionByItemName("valid")).thenReturn(Optional.of(healingPotion));
		assertNotNull(sut.findHealingPotionByItemname("valid"));
	}

	@Test
	public void testHealingPotionService_findRandomHealingPotionByPrice() {
		List<HealingPotion> mockList = new ArrayList<HealingPotion>();
		mockList.add(healingPotion);
		when(healingpotionDAO.findHealingPotionsByPrice(50)).thenReturn(mockList);
		HealingPotion budgetHealingPotion = sut.findRandomHealingPotionByPrice(50);
		assertNotNull(budgetHealingPotion);
	}

}

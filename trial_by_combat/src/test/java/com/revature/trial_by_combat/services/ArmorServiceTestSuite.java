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

import com.revature.trial_by_combat.daos.ArmorDAO;
import com.revature.trial_by_combat.daos.WeaponDAO;
import com.revature.trial_by_combat.models.Armor;
import com.revature.trial_by_combat.models.Weapon;

@ExtendWith(MockitoExtension.class)
public class ArmorServiceTestSuite {

	@Mock
	private ArmorDAO armorDAO;

	ArmorServices sut;

	Armor armor;

	Armor armor2;

	@BeforeEach
	public void testPrep() {
		sut = new ArmorServices(armorDAO);
		armor = new Armor();
		armor2 = new Armor();

	}

	@Test
	public void test_ArmorService_registerNewArmor() throws Exception {

//		when(armorDAO.findArmorByItemName(armor.getItemname())).thenReturn(Optional.of(armor));
//		Exception thrown = assertThrows(Exception.class, () -> sut.registerNewArmor(armor),
//				"The armor name selected is already in use.");
//		assertTrue(thrown.getMessage().contains("The armor name selected is already in use."));

		when(armorDAO.findArmorByItemName(armor2.getItemname())).thenReturn(Optional.empty());
		when(armorDAO.save(armor2)).thenReturn(armor2);
		assertNotNull(sut.registerNewArmor(armor2));
		
	}

	@Test
	public void test_WeaponService_findAllArmor() {

		List<Armor> mockList = new ArrayList<Armor>();
		mockList.add(armor);
		Iterable<Armor> iterableMock = mockList;
		when(armorDAO.findAll()).thenReturn(iterableMock);
		Iterable<Armor> armorList = sut.findAllArmor();
		assertNotNull(armorList);

	}

	@Test
	public void test_ArmorService_findWeaponById() {
		Optional<Armor> mockArmor = Optional.of(armor);
		when(armorDAO.findById(1)).thenReturn(mockArmor);
		assertTrue(sut.findArmorById(1).isPresent());
	}

	@Test
	public void test_ArmorService_updateArmor() {
		when(armorDAO.save(armor)).thenReturn(armor);
		assertNotNull(sut.updateArmor(armor));
	}

	@Test
	public void test_ArmorService_deleteArmor() {
		sut.deleteArmorById(1);
	}

	@Test
	public void test_ArmorService_findArmorByItemName() {
		when(armorDAO.findArmorByItemName("valid")).thenReturn(Optional.of(armor));
		assertNotNull(sut.findArmorByItemName("valid"));
	}
	
	@Test public void testArmorService_findRandomArmorByPrice() {
		List<Armor> mockList = new ArrayList<Armor>();
		mockList.add(armor);
		when(armorDAO.findArmorsByPrice(50)).thenReturn(mockList);
		Armor budgetArmor = sut.findRandomArmorByPrice(50);
		assertNotNull(budgetArmor);
	}
}

package com.revature.trial_by_combat.services;

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
	
	@BeforeEach
	public void testPrep() {
        sut = new ArmorServices(armorDAO);
        armor = new Armor();

    }

    @Test
	public void test_ArmorService_registerNewArmor() {
		sut.registerNewArmor(armor);
	}
	
	@Test
	public void test_WeaponService_findAllArmor() {
		sut.findAllArmor();
	}
	
	/*
	@Test
	public void test_ArmorService_findArmorById() {
		sut.findArmorById();
	}
	*/
	
	@Test
	public void test_ArmorService_updateArmor() {
		sut.updateArmor(armor);
	}
	
	/*
	 @Test
	 public void test_ArmorService_deleteArmor() {
	 	sut.deleteArmorById();
	 }
	 */
	
}

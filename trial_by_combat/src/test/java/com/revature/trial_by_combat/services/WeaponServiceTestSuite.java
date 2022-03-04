package com.revature.trial_by_combat.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.trial_by_combat.daos.WeaponDAO;
import com.revature.trial_by_combat.models.Weapon;

@ExtendWith(MockitoExtension.class)
public class WeaponServiceTestSuite {

    @Mock
	private WeaponDAO weaponDAO;
	
	WeaponService sut;
	
	Weapon weapon;
	
	@BeforeEach
	public void testPrep() {
        sut = new WeaponService(weaponDAO);
        weapon = new Weapon();

    }

    @Test
	public void test_WeaponService_registerNewWeapon() {
		sut.registerNewWeapon(weapon);
	}
	
	@Test
	public void test_WeaponService_findAllWeapons() {
		sut.findAllWeapons();
	}
	
	// @Test
	// public void test_WeaponService_findWeaponById() {
	// 	sut.findWeaponById();
	// }
	
	@Test
	public void test_WeaponService_updateWeapon() {
		sut.updateWeapon(weapon);
	}
	
	// @Test
	// public void test_WeaponService_deleteWeapon() {
	// 	sut.deleteWeaponById();
	// }
    
}

package com.revature.trial_by_combat.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.revature.trial_by_combat.daos.WeaponDAO;
import com.revature.trial_by_combat.models.Challenge;
import com.revature.trial_by_combat.models.Weapon;

@ExtendWith(MockitoExtension.class)
public class WeaponServiceTestSuite {

	@Mock
	private WeaponDAO weaponDAO;

	WeaponService sut;

	Weapon weapon;

	Weapon weapon2;

	@BeforeEach
	public void testPrep() {
		sut = new WeaponService(weaponDAO);
		weapon = new Weapon();
		weapon2 = new Weapon();

	}

	@Test
	public void test_WeaponService_registerNewWeapon() throws Exception {

		when(weaponDAO.findWeaponByItemName(weapon.getItemname())).thenReturn(Optional.of(weapon));
		Exception thrown = assertThrows(Exception.class, () -> sut.registerNewWeapon(weapon),
				"The weapon name selected is already in use.");
		assertTrue(thrown.getMessage().contains("The weapon name selected is already in use."));

		when(weaponDAO.findWeaponByItemName(weapon2.getItemname())).thenReturn(Optional.empty());
		when(weaponDAO.save(weapon2)).thenReturn(weapon2);
		assertNotNull(sut.registerNewWeapon(weapon2));

	}

	@Test
	public void test_WeaponService_findAllWeapons() {

		List<Weapon> mockList = new ArrayList<Weapon>();
		mockList.add(weapon);
		Iterable<Weapon> iterableMock = mockList;
		when(weaponDAO.findAll()).thenReturn(iterableMock);
		Iterable<Weapon> weaponList = sut.findAllWeapons();
		assertNotNull(weaponList);

	}

	@Test
	public void test_WeaponService_findWeaponById() {
		Optional<Weapon> mockWeapon = Optional.of(weapon);
		when(weaponDAO.findById(1)).thenReturn(mockWeapon);
		assertTrue(sut.findWeaponById(1).isPresent());
	}

	@Test
	public void test_WeaponService_updateWeapon() {
		when(weaponDAO.save(weapon)).thenReturn(weapon);
		assertNotNull(sut.updateWeapon(weapon));
	}

	@Test
	public void test_WeaponService_deleteWeapon() {
		sut.deleteWeaponById(1);
	}

	@Test
	public void test_WeaponService_findWeaponByItemName() {

		when(weaponDAO.findWeaponByItemName("valid")).thenReturn(Optional.of(weapon));
		assertNotNull(sut.findWeaponByItemName("valid"));
	}

}

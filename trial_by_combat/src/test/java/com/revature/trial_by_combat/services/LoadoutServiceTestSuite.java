package com.revature.trial_by_combat.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.trial_by_combat.daos.LoadoutDAO;
import com.revature.trial_by_combat.models.Loadout;

@ExtendWith(MockitoExtension.class)
public class LoadoutServiceTestSuite {
	@Mock
	private LoadoutDAO loadoutDAO;
	
	LoadoutService sut;
	
	Loadout loadout;
	
	@BeforeEach
	public void testPrep() {
		sut = new LoadoutService(loadoutDAO);
		loadout = new Loadout();
		
		loadout.setName("TestSuite_name");
		loadout.setDescription("TestSuite_desc");
		
	}

	@Test
	public void test_LoadoutService_createNewLoadout()
	{
		sut.createNewLoadout(loadout);
	}
	
	@Test
	public void test_LoadoutService_findAllLoadouts()
	{
		sut.findAllLoadouts();
	}
	
	@Test
	public void test_LoadoutService_findLoadoutById()
	{
		sut.findLoadoutById(1);
	}
	
	@Test
	public void test_LoadoutService_findLoadoutByPlayerId()
	{
		sut.findLoadoutsByPlayerId(1);
	}
	
	@Test
	public void test_LoadoutService_updateLoadout()
	{
		sut.updateLoadout(loadout);
	}
	
	@Test
	public void test_LoadoutService_deleteLoadout()
	{
		sut.deleteLoadoutById(1);
	}
}

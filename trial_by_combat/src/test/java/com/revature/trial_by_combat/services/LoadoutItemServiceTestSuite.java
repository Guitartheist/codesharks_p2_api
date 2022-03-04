package com.revature.trial_by_combat.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.trial_by_combat.daos.LoadoutItemDAO;
import com.revature.trial_by_combat.models.LoadoutItem;

@ExtendWith(MockitoExtension.class)
public class LoadoutItemServiceTestSuite {
	@Mock
	LoadoutItemDAO loadoutItemDAO;
	
	LoadoutItemService sut;
	
	LoadoutItem loadoutItem;
	
	@BeforeEach
	void test_prep() {
		sut = new LoadoutItemService(loadoutItemDAO);
	}

//Create
	@Test
	public void test_LoadoutItemService_createNewLoadoutItem()
	{
		sut.createNewLoadoutItem(loadoutItem);
	}

//Read
	@Test
	public void test_LoadoutService_findAllLoadoutItems()
	{
		sut.findAllLoadoutItems();
	}
	@Test
	public void test_LoadoutService_findLoadoutItemById()
	{
		sut.findLoadoutItemById(1);
	}
	@Test
	public void test_LoadoutService_findLoadoutItemByLoadoutId()
	{
		sut.findLoadoutsByLoadoutId(1);
	}
	
//Update
	@Test
	public void test_LoadoutService_updateLoadoutItem()
	{
		sut.updateLoadoutItem(loadoutItem);
	}

//Delete
	@Test
	public void test_LoadoutService_deleteLoadoutItemById()
	{
		sut.deleteLoadoutItemById(1);
	}
}

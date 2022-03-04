package com.revature.trial_by_combat.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.trial_by_combat.daos.AvatarItemDAO;
import com.revature.trial_by_combat.models.AvatarItem;

@ExtendWith(MockitoExtension.class)
public class AvatarItemServiceTestSuite {
	@Mock
	private AvatarItemDAO avatarItemsDAO;

	AvatarItemService sut;

	AvatarItem avatarItem;
	
	@BeforeEach
	public void testPrep() {
		sut = new AvatarItemService(avatarItemsDAO);
		avatarItem = new AvatarItem();
	}

	@Test
	public void test_AvatarItemService_createNewAvatarItem() {
		sut.createNewAvatarItem(avatarItem);
	}

	@Test
	public void test_AvatarItemService_findAllAvatarItems() {
		sut.findAllAvatarItems();
	}

	@Test
	public void test_AvatarItemService_findAvatarItemsById() {
		sut.findAvatarItemsById(1);
	}

	@Test
	public void test_AvatarItemService_findAvatarItemsByAvatarId() {
		sut.findAvatarItemsByAvatarId(1);
	}

	@Test
	public void test_AvatarItemService_updateAvatarItems() {
		sut.updateAvatarItems(avatarItem);
	}

	@Test
	public void test_AvatarItemService_deleteAvatarItems() {
		sut.deleteAvatarItems(1);
	}
}

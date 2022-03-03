package com.revature.trial_by_combat.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.trial_by_combat.daos.AvatarItemsDAO;

@ExtendWith(MockitoExtension.class)
public class AvatarItemServiceTestSuite {
	@Mock
	private AvatarItemsDAO avatarItemsDAO;
	
	AvatarItemService sut;
	
	@BeforeEach
	void testPrep() {
		sut = new AvatarItemService(avatarItemsDAO);
	}
	
	@Test
	void test_AvatarItemService_createNewAvatarItem() {
		
	}
}

package com.revature.trial_by_combat.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.trial_by_combat.daos.AvatarDAO;
import com.revature.trial_by_combat.models.Avatar;

@ExtendWith(MockitoExtension.class)
public class AvatarServiceTestSuite {
	@Mock
	private AvatarDAO avatarDAO;
	
	AvatarService sut;
	
	Avatar avatar;
	
	@BeforeEach
	public void testPrep() {
		sut = new AvatarService(avatarDAO);
		avatar = new Avatar();
		avatar.setAvatarName("Trogdor");
		avatar.rollStatsAndGold();
	}

	@Test
	public void test_AvatarService_createNewAvatar()
	{
		sut.createNewAvatar(avatar);
	}
	
	@Test
	public void test_AvatarService_findAllAvatars()
	{
		sut.findAllAvatars();
	}
	
	@Test
	public void test_AvatarService_findAvatarById()
	{
		sut.findAvatarById(1);
	}
	
	@Test
	public void test_AvatarService_findAvatarByPlayerId()
	{
		sut.findAvatarsByPlayerId(1);
	}
	
	@Test
	public void test_AvatarService_updateAvatar()
	{
		sut.updateAvatar(avatar);
	}
	
	@Test
	public void test_AvatarService_deleteAvatar()
	{
		sut.deleteAvatar(1);
	}
}

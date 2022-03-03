package com.revature.trial_by_combat.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.trial_by_combat.daos.PlayerDAO;
import com.revature.trial_by_combat.models.Player;

@ExtendWith(MockitoExtension.class)
public class PlayerServiceTestSuite {
	@Mock
	private PlayerDAO playerDAO;
	
	PlayerService sut;
	
	Player player;
	
	@BeforeEach
	public void testPrep() {
		sut = new PlayerService(playerDAO);
		player = new Player();
		player.setId(1);
		player.setEmail("email@address.com");
		player.setUsername("username");
		player.setPassword("password");
	}
	
	@Test
	public void test_PlayerService_registerNewPlayer() {
		sut.registerNewPlayer(player);
	}
	
	@Test
	public void test_PlayerService_findAllPlayers() {
		sut.findAllPlayers();
	}
	
	@Test
	public void test_PlayerService_findPlayerById() {
		sut.findPlayerById(1);
	}
	
	@Test
	public void test_PlayerService_updatePlayer() {
		sut.updatePlayer(player);
	}
	
	@Test
	public void test_PlayerService_deletePlayer() {
		sut.deletePlayer(1);
	}
}

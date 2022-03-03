package com.revature.trial_by_combat.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.trial_by_combat.daos.AvatarDAO;
import com.revature.trial_by_combat.models.Avatar;

@Service
public class AvatarService {
	private final AvatarDAO avatarDAO;
	
	@Autowired
	public AvatarService(AvatarDAO avatarDAO) {
		this.avatarDAO = avatarDAO;
	}
	
	@Transactional
	public Avatar createNewAvatar(Avatar avatar) {
		avatar.rollStatsAndGold();
		return avatarDAO.save(avatar);
	}
	
	public Iterable<Avatar> findAllAvatars() {
		return avatarDAO.findAll();
	}
	
	public Optional<Avatar> findAvatarById(int id) {
		return avatarDAO.findById(id);
	}
	
	public Iterable<Avatar> findAvatarsByPlayerId(int id) {
		return avatarDAO.findAllAvatarsByPlayerId(id);
	}
	
	@Transactional
	public Avatar updateAvatar(Avatar avatar) {
		return avatarDAO.save(avatar);
	}
	
	@Transactional
	public void deleteAvatar(int id) {
		avatarDAO.deleteById(id);
	}
}

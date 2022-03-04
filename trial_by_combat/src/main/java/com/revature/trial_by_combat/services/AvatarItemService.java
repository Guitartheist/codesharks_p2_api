package com.revature.trial_by_combat.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.trial_by_combat.daos.AvatarItemDAO;
import com.revature.trial_by_combat.models.AvatarItem;

@Service
public class AvatarItemService {
	private final AvatarItemDAO avatarItemsDAO;
	
	@Autowired
	public AvatarItemService(AvatarItemDAO avatarItemsDAO) {
		this.avatarItemsDAO = avatarItemsDAO;
	}
	
	@Transactional
	public AvatarItem createNewAvatarItem(AvatarItem avatarItems) {
		return avatarItemsDAO.save(avatarItems);
	}
	
	public Iterable<AvatarItem> findAllAvatarItems() {
		return avatarItemsDAO.findAll();
	}
	
	public Optional<AvatarItem> findAvatarItemsById(int id) {
		return avatarItemsDAO.findById(id);
	}
	
	public Iterable<AvatarItem> findAvatarItemsByAvatarId(int id) {
		return avatarItemsDAO.findAvatarItemsByAvatarId(id);
	}
	
	@Transactional
	public AvatarItem updateAvatarItems(AvatarItem avatarItems) {
		return avatarItemsDAO.save(avatarItems);
	}
	
	@Transactional
	public void deleteAvatarItems(int id) {
		avatarItemsDAO.deleteById(id);
	}
}

package com.revature.trial_by_combat.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.trial_by_combat.daos.AvatarItemsDAO;
import com.revature.trial_by_combat.models.AvatarItems;

@Service
public class AvatarItemService {
	private final AvatarItemsDAO avatarItemsDAO;
	
	@Autowired
	public AvatarItemService(AvatarItemsDAO avatarItemsDAO) {
		this.avatarItemsDAO = avatarItemsDAO;
	}
	
	@Transactional
	public AvatarItems createNewAvatarItem(AvatarItems avatarItems) {
		return avatarItemsDAO.save(avatarItems);
	}
	
	public Iterable<AvatarItems> findAllAvatarItems() {
		return avatarItemsDAO.findAll();
	}
	
	public Optional<AvatarItems> findAvatarItemsById(int id) {
		return avatarItemsDAO.findById(id);
	}
	
	public Iterable<AvatarItems> findAvatarItemsByAvatarId(int id) {
		return avatarItemsDAO.findAvatarItemsByAvatarId(id);
	}
	
	@Transactional
	public AvatarItems updateAvatarItems(AvatarItems avatarItems) {
		return avatarItemsDAO.save(avatarItems);
	}
	
	@Transactional
	public void deleteAvatarItems(int id) {
		avatarItemsDAO.deleteById(id);
	}
}

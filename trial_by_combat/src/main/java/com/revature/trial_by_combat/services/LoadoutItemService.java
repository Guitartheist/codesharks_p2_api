package com.revature.trial_by_combat.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.trial_by_combat.daos.LoadoutItemDAO;
import com.revature.trial_by_combat.models.LoadoutItem;

@Service
public class LoadoutItemService {
	
	private final LoadoutItemDAO loadoutItemDAO;
	
	@Autowired
	public LoadoutItemService(LoadoutItemDAO loadoutItemDAO) {
		this.loadoutItemDAO = loadoutItemDAO;
	}

//Create
	public LoadoutItem createNewLoadoutItem(LoadoutItem loadoutItem) {
		return loadoutItemDAO.save(loadoutItem);
	}
//Read
	public Iterable<LoadoutItem> findAllLoadoutItems() {
		return loadoutItemDAO.findAll();
	}
	
	public Optional<LoadoutItem> findLoadoutItemById(int id) {
		 return loadoutItemDAO.findById(id);
	}

	public Iterable<LoadoutItem> findLoadoutsByLoadoutId(int id) {
		return loadoutItemDAO.findLoadoutItemsByLoadoutId(id);
	}
//Update
	@Transactional
	public LoadoutItem updateLoadoutItem(LoadoutItem loadoutItem) {
		return loadoutItemDAO.save(loadoutItem);
	}
//Delete
	@Transactional
	public void deleteLoadoutItemById(int id) {
		loadoutItemDAO.deleteById(id);
	}
	
	
	
}

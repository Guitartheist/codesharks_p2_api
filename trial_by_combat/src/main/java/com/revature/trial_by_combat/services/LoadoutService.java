package com.revature.trial_by_combat.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.trial_by_combat.daos.LoadoutDAO;
import com.revature.trial_by_combat.models.Loadout;

@Service
public class LoadoutService {

	private final LoadoutDAO loadoutDAO;
	
	@Autowired
	public LoadoutService(LoadoutDAO loadoutDAO) {
		this.loadoutDAO = loadoutDAO;
	}

//Create	
	@Transactional
	public Loadout createNewLoadout(Loadout loadout) {
			return loadoutDAO.save(loadout);
	}

//Read
	public Iterable<Loadout> findAllLoadouts() {
		return loadoutDAO.findAll();
	}
	
	public Iterable<Loadout> findLoadoutsByPlayerId(int id) {
		return loadoutDAO.findAllLoadoutsByPlayerId(id);
	}

	public Optional<Loadout> findLoadoutById(int id) {
		return loadoutDAO.findById(id);
	}
	
//Update - Don't call this, everything in loadout item is immutable
	@Transactional
	public Loadout updateLoadout(Loadout loadout) {
		return loadoutDAO.save(loadout);
	}

//Delete
	@Transactional
	public void deleteLoadoutById(int id) {
		loadoutDAO.deleteById(id);
	}
}

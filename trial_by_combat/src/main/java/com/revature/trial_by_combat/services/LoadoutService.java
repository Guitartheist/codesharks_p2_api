package com.revature.trial_by_combat.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.trial_by_combat.daos.LoadoutDAO;
import com.revature.trial_by_combat.models.Loadout;
import com.revature.trial_by_combat.models.Player;

@Service
public class LoadoutService {

	private final LoadoutDAO loadoutDAO;
	
	@Autowired
	public LoadoutService(LoadoutDAO loadoutDAO) {
		this.loadoutDAO = loadoutDAO;
	}
	
	@Transactional
	public Loadout createNewLoadout(Loadout loadout) {
			return loadoutDAO.save(loadout);
	}
	
	public Iterable<Loadout> findAllLoadouts() {
		return loadoutDAO.findAll();
	}
	
	public Optional<Loadout> findLoadoutById(int id) {
		return loadoutDAO.findById(id);
	}
	
	public Iterable<Loadout> findLoadoutsByPlayerId(int id) {
		return loadoutDAO.findAllLoadoutsByPlayerId(id);
	}
	
	@Transactional
	public Loadout updateLoadout(Loadout loadout) {
		return loadoutDAO.save(loadout);
	}
	
	@Transactional
	public void deleteLoadout(int id) {
		loadoutDAO.deleteById(id);
	}
}

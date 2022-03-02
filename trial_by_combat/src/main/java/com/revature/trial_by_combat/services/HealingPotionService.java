package com.revature.trial_by_combat.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.trial_by_combat.daos.HealingPotionDAO;
import com.revature.trial_by_combat.models.HealingPotion;

@Service
public class HealingPotionService {
	private final HealingPotionDAO healingPotionDAO;
	
	@Autowired
	public HealingPotionService(HealingPotionDAO healingPotionDAO) {
		this.healingPotionDAO = healingPotionDAO;
	}
	
	@Transactional
	public HealingPotion registerNewHealingPotion(HealingPotion healingPotion) {
		boolean itemnameAvailable = healingPotionDAO.findHealingPotionByItemname(healingPotion.getItemname()).isEmpty();
		
		if (itemnameAvailable) {
			HealingPotion persistedHealingPotion = healingPotionDAO.save(healingPotion);
			return persistedHealingPotion;
		}
		
		return null;
	}
	
	public Iterable<HealingPotion> findAllHealingPotions() {
		return healingPotionDAO.findAll();
	}
	
	public Optional<HealingPotion> findHealingPotionById(int id) {
		return healingPotionDAO.findById(id);
	}
	
	public HealingPotion updateHealingPotion(HealingPotion healingPotion) {
		return healingPotionDAO.save(healingPotion);
	}
	
	public void deleteHealingPotion(int id) {
		healingPotionDAO.deleteById(id);
	}
}

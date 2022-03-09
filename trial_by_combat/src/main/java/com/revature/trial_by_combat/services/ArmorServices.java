package com.revature.trial_by_combat.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.trial_by_combat.daos.ArmorDAO;
import com.revature.trial_by_combat.models.Armor;
import com.revature.trial_by_combat.models.Weapon;

@Service
public class ArmorServices {
	private final ArmorDAO armorDAO;

	@Autowired
	public ArmorServices(ArmorDAO armorDAO) {
		this.armorDAO = armorDAO;
	}

	@Transactional
	public Armor registerNewArmor(Armor armor) {
		boolean armorNameAvailable = armorDAO.findArmorByItemName(armor.getItemname()).isPresent();

		if (armorNameAvailable) {
			return null;
		}

		Armor persistedArmor = armorDAO.save(armor);
		return persistedArmor;
	}

	@Transactional
	public Iterable<Armor> findAllArmor() {
		return armorDAO.findAll();
	}

	@Transactional
	public Optional<Armor> findArmorById(int id) {
		return armorDAO.findById(id);
	}

	public Optional<Armor> findArmorByItemName(String itemname) {
		return armorDAO.findArmorByItemName(itemname);
	}

	@Transactional
	public Armor updateArmor(Armor armor) {
		return armorDAO.save(armor);
	}

	@Transactional
	public void deleteArmorById(int id) {
		armorDAO.deleteById(id);
	}
	
	@Transactional
	public Armor findRandomArmorByPrice(int budget) {
		List<Armor> armors = armorDAO.findArmorsByPrice(budget);
		return armors.get((int) (Math.random() * armors.size()));
		//return armors.get(1);
	}
}

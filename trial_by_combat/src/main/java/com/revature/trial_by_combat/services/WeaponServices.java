package com.revature.trial_by_combat.services;

import java.util.Optional;

import javax.transaction.Transactional;
import com.revature.trial_by_combat.daos.WeaponDAO;
import com.revature.trial_by_combat.models.Weapon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeaponServices {

	private final WeaponDAO weaponDAO;
	
	@Autowired
	public WeaponServices(WeaponDAO weaponDAO) {
		this.weaponDAO = weaponDAO;
	}
	
	@Transactional
	public Weapon registerNewWeapon(Weapon weapon){	

		boolean weaponNameAvailable = weaponDAO.findWeaponByItemName(weapon.getItemname()).isPresent();

		if(weaponNameAvailable) {
            return null;
		}

        Weapon persistedWeapon = weaponDAO.save(weapon);
        return persistedWeapon;	
	}

	@Transactional
	public Iterable<Weapon> findAllWeapons() {
		return weaponDAO.findAll();
	}
	
	@Transactional
	public Optional<Weapon> findWeaponById(int id) {
		return weaponDAO.findById(id);
    }

	public Optional<Weapon> findWeaponByItemName(String itemname) {
		return weaponDAO.findWeaponByItemName(itemname);
	}
	
	@Transactional
	public Weapon updateWeapon(Weapon weapon) {
        return weaponDAO.save(weapon);   
    }

    @Transactional
	public void deleteWeaponById(int id) {
        weaponDAO.deleteById(id);
    }	

    // @Transactional
	// public Weapon authenticateWeapon() {
		
	// }
	
}
    


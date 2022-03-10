package com.revature.trial_by_combat.web.servlets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import com.revature.trial_by_combat.models.Weapon;
import com.revature.trial_by_combat.services.WeaponService;

/**
 * 
 * REST endpoint for calls to weapon database
 *
 * @author Michael Bronzo
 * @version 1.0
 * @since 1.0
 * 
 */
@RestController
@RequestMapping("/weapon")
public class WeaponServlet {

	/**
	 *  Calls the Weapon Data Access Object (extends CrudRepository)
	 */
	private final WeaponService weaponService;

	/**
	 * Database access dependency injection.
	 * WeaponService Dependency Injection.
	 * @param weaponService calls the Weapon Data Access Object to interact with database.
	 */
	@Autowired
	public WeaponServlet(WeaponService weaponService) {
		this.weaponService = weaponService;
	}

	/**
	 * Creates a weapon in the database.
	 * @param weapon Weapon object to be persisted in database.
	 * @return Returns a copy of the persisted object.
	 * @throws Exception "The weapon name selected is already in use.".
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Weapon createWeapon(@RequestBody Weapon weapon) throws Exception {
		weapon.setPrice(weapon.getDamageDie() + (weapon.getDamageBonus() * 10));
		return weaponService.registerNewWeapon(weapon);
	}

	/**
	 * Retrieves weapon by supplied id.
	 * @param id Id of weapon to be retrieved from the database.
	 * @return A copy of the weapon.
	 */
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Weapon findWeaponById(@RequestParam int id) {
		return weaponService.findWeaponById(id).get();
	}
	
	/**
	 * Retrieves weapon by supplied name. (Mapped to ./weapon/itemname).
	 * @param itemname Name of weapon to be retrieved from the database.
	 * @return A copy of the weapon.
	 */
	@GetMapping("/itemname")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Optional<Weapon> findWeaponByItemName(@RequestParam String itemname) {
		return weaponService.findWeaponByItemName(itemname);
	}

	/**
	 * Retrieves all weapons from the database. (Mapped to ./weapon/all).
	 * @return An Iterable interface that returns the weapons retrieved from the database
	 */
	@GetMapping("/all")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Iterable<Weapon> findAllWeapons() {
		return weaponService.findAllWeapons();
	}

	/**
	 * Updates the supplied weapon's information in the database.
	 * Sets weapon price based on the weapon's damage.
	 * @param weapon The weapon object to updated in the database
	 * @return A copy of the updated weapon object
	 */
	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Weapon updateWeapon(@RequestBody Weapon weapon) {
		weapon.setPrice(weapon.getDamageDie() + (weapon.getDamageBonus() * 10));
		return weaponService.updateWeapon(weapon);
	}

	/**
	 * Deletes the weapon with the supplied id from the database
	 * @param id The id of the weapon to be deleted
	 */
	@DeleteMapping
	@ResponseStatus(HttpStatus.OK)
	public void deleteWeapon(@RequestParam int id) {
		weaponService.deleteWeaponById(id);
	}

}

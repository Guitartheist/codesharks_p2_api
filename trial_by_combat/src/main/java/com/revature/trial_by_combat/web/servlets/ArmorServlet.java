package com.revature.trial_by_combat.web.servlets;

import java.util.Optional;

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

import com.revature.trial_by_combat.models.Armor;
import com.revature.trial_by_combat.models.Weapon;
import com.revature.trial_by_combat.services.ArmorServices;

/**
 * 
 * REST endpoint for calls to armor database
 *
 * @author Gurman Singh
 * @version 1.0
 * @since 1.0
 *
 */
@RestController
@RequestMapping("/armor")
public class ArmorServlet {
	/**
	 *  Calls the Armor Data Access Object (extends CrudRepository)
	 */
	private final ArmorServices armorService;

	/**
	 * Data access dependency injection.
	 * ArmorService dependency injection
	 * @param armorService calls the Armor Data Access Object to interact with database.
	 */
	@Autowired
	public ArmorServlet(ArmorServices armorService) {
		this.armorService = armorService;
	}
	
	/**
	 * Creates a piece of armor in the database.
	 * Sets armor price based on the armor's damage reduction.
	 * @param armor Armor object to be persisted in database.
	 * @return Returns a copy of the persisted object.
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Armor createArmor(@RequestBody Armor armor) {
		armor.setPrice(armor.getArmorClass() * armor.getArmorClass() * 10 + armor.getDamageReduction() * 10);
		return armorService.registerNewArmor(armor);
	}
	
	/**
	 * Retrieves armor by supplied id.
	 * @param id Id of armor to be retrieved from the database.
	 * @return A copy of the armor.
	 */
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Armor findArmorById(@RequestParam int id) {
		return armorService.findArmorById(id).get();
	}

	/**
	 * Retrieves armor by supplied name. (Mapped to ./armor/itemname).
	 * @param itemname Name of armor to be retrieved from the database.
	 * @return A copy of the armor.
	 */
    @GetMapping("/itemname")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Optional<Armor> findArmorByItemName(@RequestParam String itemname) {
		return armorService.findArmorByItemName(itemname);
    }
	
    /**
	 * Retrieves all armor from the database. (Mapped to ./armor/all).
	 * @return An Iterable interface that returns the armor pieces retrieved from the database
	 */
	@GetMapping("/all")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Iterable<Armor> findAllArmor() {
		return armorService.findAllArmor();
	}
	
	@GetMapping("/randarmor")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Armor findRandomWeapon(@RequestParam int budget) {
		return armorService.findRandomArmorByPrice(budget);
	}
	
	/**
	 * Updates the supplied armor's information in the database.
	 * Sets armor price based on the armor's damage reduction.
	 * @param armor The armor object to updated in the database
	 * @return A copy of the updated armor object
	 */
	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Armor updateArmor(@RequestBody Armor armor) {
		armor.setPrice(armor.getArmorClass() * armor.getArmorClass() * 10 + armor.getDamageReduction() * 10);
		return armorService.updateArmor(armor);
	}
	
	/**
	 * Deletes the armor with the supplied id from the database
	 * @param id The id of the armor to be deleted
	 */
	@DeleteMapping
	@ResponseStatus(HttpStatus.OK)
	public void deleteArmor(@RequestParam int id) {
		armorService.deleteArmorById(id);
	}
}

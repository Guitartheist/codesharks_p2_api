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

import com.revature.trial_by_combat.models.HealingPotion;
import com.revature.trial_by_combat.models.Weapon;
import com.revature.trial_by_combat.services.HealingPotionService;

/**
 * 
 * REST endpoint for calls to healing potion database
 *
 * @author Tracy Bodine
 * @version 1.0
 * @since 1.0
 * 
 */
@RestController
@RequestMapping("/healing_potion")
public class HealingPotionServlet {
	/**
	 *  Calls the Healing Potion Data Access Object (extends CrudRepository)
	 */
	private final HealingPotionService healingPotionService;
	
	/**
	 * Database access dependency injection.
	 * Healing Potion dependency injection.
	 * @param healingPotionService calls the Challenge Action Item Data Access Object to interact with database.
	 */
	@Autowired
	public HealingPotionServlet(HealingPotionService healingPotionService) {
		this.healingPotionService = healingPotionService;
	}
	
	/**
	 * Creates an Healing Potion in the database.
	 * @param healingPotion Healing Potion object to be persisted in database.
	 * @return Returns a copy of the persisted object.
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public HealingPotion createHealingPotion(@RequestBody HealingPotion healingPotion) {
		healingPotion.setPrice(healingPotion.getHealingDie() + (healingPotion.getHealingBonus() * 10));
		return healingPotionService.registerNewHealingPotion(healingPotion);
	}
	
	/**
	 * Retrieves Healing Potion by supplied id.
	 * @param id Id of Healing Potion Item to be retrieved from the database.
	 * @return A copy of the Healing Potion.
	 */
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Optional<HealingPotion> findHealingPotionById(@RequestParam int id) {
		return healingPotionService.findHealingPotionById(id);
	}
	
	/**
	 * Retrieves Healing Potion by supplied name.
	 * @param itemname Name of Healing Potion Item to be retrieved from the database.
	 * @return A copy of the Healing Potion.
	 */
    @GetMapping("/itemname")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Optional<HealingPotion> findHealingPotionByItemName(@RequestParam String itemname) {
		return healingPotionService.findHealingPotionByItemname(itemname);
    }
	
    /**
	 * Retrieves all healing potions from the database. (Mapped to ./healing_potion/all).
	 * @return An Iterable interface that returns the healing potions retrieved from the database
	 */
	@GetMapping("/all")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Iterable<HealingPotion> findAllHealingPotions() {
		return healingPotionService.findAllHealingPotions();
	}
	
	@GetMapping("/randhealingpotion")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public HealingPotion findRandomHealingPotion(@RequestParam int budget) {
		return healingPotionService.findRandomHealingPotionByPrice(budget);
	}
	
	/**
	 * Updates the healing potion's information in the database.
	 * Sets the price based on the healing bonus and healing die.
	 * @param healingPotion The healing potion object to updated in the database
	 * @return A copy of the updated healing potion object
	 */
	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public HealingPotion updateHealingPotion(@RequestBody HealingPotion healingPotion) {
		healingPotion.setPrice(healingPotion.getHealingDie() + (healingPotion.getHealingBonus() * 10));
		return healingPotionService.updateHealingPotion(healingPotion);
	}
	
	/**
	 * Deletes the healing potion with the supplied id from the database
	 * @param id The id of the healing potion to be deleted
	 */
	@DeleteMapping
	@ResponseStatus(HttpStatus.OK)
	public void deleteHealingPotion(@RequestParam int id) {
		healingPotionService.deleteHealingPotion(id);
	}
	
}


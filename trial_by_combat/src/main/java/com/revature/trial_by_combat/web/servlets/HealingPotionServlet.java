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

@RestController
@RequestMapping("/healing_potion")
public class HealingPotionServlet {
	
	private final HealingPotionService healingPotionService;
	
	@Autowired
	public HealingPotionServlet(HealingPotionService healingPotionService) {
		this.healingPotionService = healingPotionService;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public HealingPotion createHealingPotion(@RequestBody HealingPotion healingPotion) {
		healingPotion.setPrice(healingPotion.getHealingDie() + (healingPotion.getHealingBonus() * 10));
		return healingPotionService.registerNewHealingPotion(healingPotion);
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Optional<HealingPotion> findHealingPotionById(@RequestParam int id) {
		return healingPotionService.findHealingPotionById(id);
	}
	
    @GetMapping("/itemname")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Optional<HealingPotion> findHealingPotionByItemName(@RequestParam String itemname) {
		return healingPotionService.findHealingPotionByItemname(itemname);
    }
	
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
	
	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public HealingPotion updateHealingPotion(@RequestBody HealingPotion healingPotion) {
		healingPotion.setPrice(healingPotion.getHealingDie() + (healingPotion.getHealingBonus() * 10));
		return healingPotionService.updateHealingPotion(healingPotion);
	}
	
	@DeleteMapping
	@ResponseStatus(HttpStatus.OK)
	public void deleteHealingPotion(@RequestParam int id) {
		healingPotionService.deleteHealingPotion(id);
	}
	
}


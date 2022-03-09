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
import com.revature.trial_by_combat.services.ArmorServices;

@RestController
@RequestMapping("/armor")
public class ArmorServlet {
	private final ArmorServices armorService;
	
	@Autowired
	public ArmorServlet(ArmorServices armorService) {
		this.armorService = armorService;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Armor createArmor(@RequestBody Armor armor) {
		armor.setPrice(armor.getArmorClass() * armor.getArmorClass() * 10 + armor.getDamageReduction() * 10);
		return armorService.registerNewArmor(armor);
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Armor findArmorById(@RequestParam int id) {
		return armorService.findArmorById(id).get();
	}

    @GetMapping("/itemname")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Optional<Armor> findArmorByItemName(@RequestParam String itemname) {
		return armorService.findArmorByItemName(itemname);
    }
	
	@GetMapping("/all")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Iterable<Armor> findAllArmor() {
		return armorService.findAllArmor();
	}
	
	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Armor updateArmor(@RequestBody Armor armor) {
		armor.setPrice(armor.getArmorClass() * armor.getArmorClass() * 10 + armor.getDamageReduction() * 10);
		return armorService.updateArmor(armor);
	}
	
	@DeleteMapping
	@ResponseStatus(HttpStatus.OK)
	public void deleteArmor(@RequestParam int id) {
		armorService.deleteArmorById(id);
	}
}

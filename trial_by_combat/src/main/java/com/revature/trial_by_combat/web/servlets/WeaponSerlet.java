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
import com.revature.trial_by_combat.services.WeaponServices;

@RestController
@RequestMapping("/weapon")
public class WeaponSerlet {

    private final WeaponServices weaponService;
	
	@Autowired
	public WeaponServlet(WeaponService weaponService) {
		this.weaponService = weaponService;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Weapon createWeapon(@RequestBody Weapon weapon) {
		return weaponService.registerNewWeapon(weapon);
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Weapon findWeaponById(@RequestParam int id) {
		return weaponService.findWeaponById(id).get();
	}

    @GetMapping("/itemname")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Optional<Weapon> findWeaponByItemName(@RequestParam String itemname) {
		return weaponService.findWeaponByItemName(itemname);
    }
	
	@GetMapping("/all")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Iterable<Weapon> findAllWeapons() {
		return weaponService.findAllWeapons();
	}
	
	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Weapon updateWeapon(@RequestBody Weapon weapon) {
		return weaponService.updateWeapon(weapon);
	}
	
	@DeleteMapping
	@ResponseStatus(HttpStatus.OK)
	public void deleteWeapon(@RequestParam int id) {
		weaponService.deleteWeaponById(id);
	}
    
}

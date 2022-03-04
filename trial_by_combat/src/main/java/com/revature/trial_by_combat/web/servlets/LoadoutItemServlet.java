package com.revature.trial_by_combat.web.servlets;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.revature.trial_by_combat.services.LoadoutItemService;
import com.revature.trial_by_combat.services.LoadoutService;
import com.revature.trial_by_combat.services.ItemService;
import com.revature.trial_by_combat.models.LoadoutItem;



@RestController
@RequestMapping("/loadout_item")
public class LoadoutItemServlet {
	private final LoadoutItemService loadoutItemService;
	private final LoadoutService loadoutService;
	private final ItemService itemService;
	
	@Autowired
	public LoadoutItemServlet(LoadoutItemService loadoutItemService, LoadoutService loadoutService, ItemService itemService) {
		this.loadoutItemService = loadoutItemService;
		this.loadoutService = loadoutService;
		this.itemService = itemService;
	}

/*** Create ***/
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public LoadoutItem createLoadoutItem(@RequestBody LoadoutItem loadoutItem) {
		loadoutItem.setItem(itemService.findItemById(loadoutItem.getItem().getId() ));
		loadoutItem.setLoadout(loadoutService.findLoadoutById(loadoutItem.getLoadout().getId()).get());
		return loadoutItemService.createNewLoadoutItem(loadoutItem);
	}
	
/*** Read ***/
	@GetMapping("/all")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Iterable<LoadoutItem> getAllLoadoutItems() {
		return loadoutItemService.findAllLoadoutItems();
	}
		
	@GetMapping("/loadout")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Iterable<LoadoutItem> findLoadoutsByLoadoutId(int id) {
		return loadoutItemService.findLoadoutsByLoadoutId(id);
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public LoadoutItem findLoadoutItemById(int id) {
		return loadoutItemService.findLoadoutItemById(id).get();
	}
	
/*** Update ***/
	// everything in update item is immutable
	
/*** Delete ***/
	@DeleteMapping
	@ResponseStatus(HttpStatus.OK)
	public void deleteLoadoutItemById(@RequestParam int id) {
		loadoutItemService.deleteLoadoutItemById(id);
	}
}

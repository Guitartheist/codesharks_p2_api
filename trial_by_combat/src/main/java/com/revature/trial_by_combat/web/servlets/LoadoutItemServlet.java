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


/**
 * 
 * REST endpoint for calls to loadout item database
 *
 * @author Joshua Evans
 * @version 1.0
 * @since 1.0
 * 
 */
@RestController
@RequestMapping("/loadout_item")
public class LoadoutItemServlet {
	/**
	 *  Calls the Loadout Item Data Access Object (extends CrudRepository).
	 */
	private final LoadoutItemService loadoutItemService;
	/**
	 *  Calls the Loadout Data Access Object (extends CrudRepository)
	 */
	private final LoadoutService loadoutService;
	/**
	 *  Calls the Item Data Access Object (extends CrudRepository)
	 */
	private final ItemService itemService;
	
	/**
	 * Database access dependency injection.
	 * LoadoutItemService LoadoutService ItemService Dependency Injection.
	 * @param loadoutItemService Calls the Loadout Item Data Access Object to interact with database.
	 * @param loadoutService Calls the Loadout Data Access Object to interact with database.
	 * @param itemService Calls the Item Data Access Object to interact with database.
	 */
	@Autowired
	public LoadoutItemServlet(LoadoutItemService loadoutItemService, LoadoutService loadoutService, ItemService itemService) {
		this.loadoutItemService = loadoutItemService;
		this.loadoutService = loadoutService;
		this.itemService = itemService;
	}

	/**
	 * Creates a Loadout Item in the database.
	 * @param loadoutItem LoadoutItem object to be persisted in database.
	 * @return Returns a copy of the persisted object.
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public LoadoutItem createLoadoutItem(@RequestBody LoadoutItem loadoutItem) {
		loadoutItem.setItem(itemService.findItemById(loadoutItem.getItem().getId() ));
		loadoutItem.setLoadout(loadoutService.findLoadoutById(loadoutItem.getLoadout().getId()).get());
		return loadoutItemService.createNewLoadoutItem(loadoutItem);
	}
	
	/**
	 * Retrieves all loadout items from the database. (Mapped to ./loadout_item/all).
	 * @return An Iterable interface that returns the loadout items retrieved from the database
	 */
	@GetMapping("/all")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Iterable<LoadoutItem> getAllLoadoutItems() {
		return loadoutItemService.findAllLoadoutItems();
	}
	
	/**
	 * Retrieves Loadout Items by supplied Loadout id. (Mapped to ./loaodut_item/loadout).
	 * @param id Id of Loadout to be used to retrieve Loadout from the database.
	 * @return An Iterable interface that returns the Loadout Items retrieved from the database.
	 */
	@GetMapping("/loadout")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Iterable<LoadoutItem> findLoadoutsByLoadoutId(int id) {
		return loadoutItemService.findLoadoutsByLoadoutId(id);
	}
	
	/**
	 * Retrieves Loadout Item by supplied id.
	 * @param id Id of Loadout Item to be retrieved from the database.
	 * @return A copy of the Loadout Item.
	 */
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public LoadoutItem findLoadoutItemById(int id) {
		return loadoutItemService.findLoadoutItemById(id).get();
	}
	
	// Can't update item. Everything in item is immutable
	
	/**
	 * Deletes the loadout item with the supplied id from the database
	 * @param id The id of the loadout item to be deleted
	 */
	@DeleteMapping
	@ResponseStatus(HttpStatus.OK)
	public void deleteLoadoutItemById(@RequestParam int id) {
		loadoutItemService.deleteLoadoutItemById(id);
	}
}

package com.revature.trial_by_combat.web.servlets;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
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

import com.revature.trial_by_combat.models.AvatarItem;
import com.revature.trial_by_combat.models.Item;
import com.revature.trial_by_combat.services.AvatarItemService;
import com.revature.trial_by_combat.services.AvatarService;
import com.revature.trial_by_combat.services.ItemService;

/**
 * 
 * REST endpoint for calls to avatar item database
 *
 * @author Philip Wentworth
 * @version 1.0
 * @since 1.0
 *
 */
@RestController
@RequestMapping("/avatar_item")
public class AvatarItemServlet {
	/**
	 *  Calls the Avatar Item Data Access Object (extends CrudRepository)
	 */
	private final AvatarItemService avatarItemService;
	/**
	 *  Calls the Avatar Data Access Object (extends CrudRepository)
	 */
	private final AvatarService avatarService;
	/**
	 *  Calls the Item Data Access Object (extends CrudRepository)
	 */
	private final ItemService itemService;

	/**
	 * Database access dependency injection.
	 * AvatarItemService, AvatarService, ItemService Dependency Injection.
	 * @param avatarItemService calls the Avatar Item Data Access Object to interact with database.
	 * @param avatarService calls the Avatar Data Access Object to interact with database.
	 * @param itemService calls the Item Data Access Object to interact with database.
	 */
	@Autowired
	public AvatarItemServlet(AvatarItemService avatarItemService, AvatarService avatarService,
			ItemService itemService) {
		this.avatarItemService = avatarItemService;
		this.avatarService = avatarService;
		this.itemService = itemService;
	}

	/**
	 * Creates an Avatar Item in the database.
	 * @param avatarItem Avatar Item object to be persisted in database.
	 * @return Returns a copy of the persisted object.
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public AvatarItem createAvatarItem(@RequestBody AvatarItem avatarItem) {
		avatarItem.setAvatar( avatarService.findAvatarById(avatarItem.getAvatar().getId()).get() );
		avatarItem.setItem( itemService.findItemById(avatarItem.getItem().getId() ));
		return avatarItemService.createNewAvatarItem(avatarItem);
	}

	/**
	 * Retrieves Avatar Item by supplied id.
	 * @param id Id of Avatar Item to be retrieved from the database.
	 * @return A copy of the Avatar Item.
	 */
	public AvatarItem findAvatarItemById(@RequestParam int id) {
		return avatarItemService.findAvatarItemsById(id).get();
	}

	/**
	 * Retrieves Avatar Item by supplied id.
	 * @param id Id of Avatar Item to be retrieved from the database.
	 * @return A copy of the Avatar ITem.
	 */
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public AvatarItem findAvatarItemsById(@RequestParam int id) {
		return avatarItemService.findAvatarItemsById(id).get();
	}

	/**
	 * Retrieves all avatar items actions from the database. (Mapped to ./avatar_item/all).
	 * @return An Iterable interface that returns the avatar items retrieved from the database
	 */
	@GetMapping("/all")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Iterable<AvatarItem> findAllAvatarItems() {
		return avatarItemService.findAllAvatarItems();
	}

	/**
	 * Retrieves Avatars Items by supplied Avatar id. (Mapped to ./avatar_item/avatar).
	 * @param id Id of Avatar to be used to retrieve Avatar Items from the database.
	 * @return An Iterable interface that returns the Avatar Items retrieved from the database.
	 */
	@GetMapping("/avatar")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Iterable<AvatarItem> findAvatarItemsByAvatarId(@RequestParam int id) {
		return avatarItemService.findAvatarItemsByAvatarId(id);
	}

	/**
	 * Updates the avatar item's information in the database.
	 * @param avatarItem The avatar item object to updated in the database
	 * @return A copy of the updated avatar item object
	 */

	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public AvatarItem updateAvatarItem(@RequestBody AvatarItem avatarItem) {
		avatarItem.setAvatar( avatarService.findAvatarById(avatarItem.getAvatar().getId()).get() );
		avatarItem.setItem( itemService.findItemById(avatarItem.getItem().getId() ));
		return avatarItemService.updateAvatarItems(avatarItem);
	}

	/**
	 * Deletes the avatar item with the supplied id from the database
	 * @param id The id of the avatar item to be deleted
	 */
	@DeleteMapping
	@ResponseStatus(HttpStatus.OK)
	public void deleteAvatarItem(@RequestParam int id) {
		avatarItemService.deleteAvatarItems(id);
	}
}

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

@RestController
@RequestMapping("/avatar_item")
public class AvatarItemServlet {
	private final AvatarItemService avatarItemService;
	private final AvatarService avatarService;
	private final ItemService itemService;

	@Autowired
	public AvatarItemServlet(AvatarItemService avatarItemService, AvatarService avatarService,
			ItemService itemService) {
		this.avatarItemService = avatarItemService;
		this.avatarService = avatarService;
		this.itemService = itemService;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public AvatarItem createAvatarItem(@RequestBody AvatarItem avatarItem) {
		avatarItem.setAvatar( avatarService.findAvatarById(avatarItem.getAvatar().getId()).get() );
		avatarItem.setItem( itemService.findItemById(avatarItem.getItem().getId() ));
		return avatarItemService.createNewAvatarItem(avatarItem);
	}

	public AvatarItem findAvatarItemById(@RequestParam int id) {
		return avatarItemService.findAvatarItemsById(id).get();
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public AvatarItem findAvatarItemsById(@RequestParam int id) {
		return avatarItemService.findAvatarItemsById(id).get();
	}

	@GetMapping("/all")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Iterable<AvatarItem> findAllAvatarItems() {
		return avatarItemService.findAllAvatarItems();
	}

	@GetMapping("/avatar")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Iterable<AvatarItem> findAvatarItemsByAvatarId(@RequestParam int id) {
		return avatarItemService.findAvatarItemsByAvatarId(id);
	}

	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public AvatarItem updateAvatarItem(@RequestBody AvatarItem avatarItem) {
		avatarItem.setAvatar( avatarService.findAvatarById(avatarItem.getAvatar().getId()).get() );
		avatarItem.setItem( itemService.findItemById(avatarItem.getItem().getId() ));
		return avatarItemService.updateAvatarItems(avatarItem);
	}

	@DeleteMapping
	@ResponseStatus(HttpStatus.OK)
	public void deleteAvatarItem(@RequestParam int id) {
		avatarItemService.deleteAvatarItems(id);
	}
}

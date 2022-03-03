package com.revature.trial_by_combat.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.trial_by_combat.daos.ItemDAO;
import com.revature.trial_by_combat.models.Item;

@Service
public class ItemService {
	private final ItemDAO itemDAO;
	
	@Autowired
	public ItemService(ItemDAO itemDAO) {
		this.itemDAO = itemDAO;
	}
	
	public List<Item> findAllItems() {
		return itemDAO.findAllItems();
	}
	
	public Item findItemById(int id) {
		return itemDAO.findItemById(id);
	}
}

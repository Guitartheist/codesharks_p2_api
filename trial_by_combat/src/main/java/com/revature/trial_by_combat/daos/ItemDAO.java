package com.revature.trial_by_combat.daos;

import java.util.List;
import java.lang.Math;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.trial_by_combat.models.Item;

@Repository
public class ItemDAO {
	private final SessionFactory sessionFactory;
	
	@Autowired
	public ItemDAO(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public List<Item> findAllItems() {
		try (Session session = sessionFactory.openSession()) {
			return (List<Item>) session.createQuery("from Item").getResultList();
		}
		catch (Exception e) {
			
		}
		return null;
	}
	
	public List<Item> findItemsBelow(int budget) {
		try (Session session = sessionFactory.openSession()) {
			List<Item> allItems = (List<Item>) session.createQuery("from Item where price<:Budget").setParameter("Budget", budget).getResultList();
			while(allItems.size() > 3) {
				allItems.remove((int) Math.random() * (allItems.size()-1));
			}
			return allItems;
		} catch (Exception e) {
			
		}
		return null;
	}
	
	public Item findItemById(int id) {
		try (Session session = sessionFactory.openSession()) {
			return (Item) session.createQuery("from Item where id=:id").setParameter("id", id).getSingleResult();
		}
		catch (Exception e) {
			
		}
		return null;
	}
}

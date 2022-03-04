package com.revature.trial_by_combat.daos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.trial_by_combat.models.LoadoutItem;

@Repository
public interface LoadoutItemDAO extends CrudRepository<LoadoutItem, Integer> {
	@Query("from LoadoutItem l where l.loadout.id=:id")
	Iterable<LoadoutItem> findLoadoutItemsByLoadoutId(int id);
}

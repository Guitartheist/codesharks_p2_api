package com.revature.trial_by_combat.daos;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.trial_by_combat.models.HealingPotion;
import com.revature.trial_by_combat.models.Weapon;

@Repository
public interface HealingPotionDAO extends CrudRepository<HealingPotion, Integer> {

	
	@Query("from Item i where i.itemname = :itemname")
	Optional<HealingPotion> findHealingPotionByItemName(String itemname);

	@Query("from HealingPotion h where h.price<:budget")
	List<HealingPotion> findHealingPotionsByPrice(int budget);
}

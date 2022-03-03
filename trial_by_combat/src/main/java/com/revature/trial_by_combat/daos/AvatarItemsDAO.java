package com.revature.trial_by_combat.daos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.trial_by_combat.models.AvatarItems;

@Repository
public interface AvatarItemsDAO extends CrudRepository<AvatarItems, Integer> {
	@Query("from AvatarItems a where a.avatar.id=:id")
	Iterable<AvatarItems> findAvatarItemsByAvatarId(int id);
}

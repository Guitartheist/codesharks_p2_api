package com.revature.trial_by_combat.daos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.trial_by_combat.models.AvatarItem;

@Repository
public interface AvatarItemDAO extends CrudRepository<AvatarItem, Integer> {
	@Query("from AvatarItems a where a.avatar.id=:id")
	Iterable<AvatarItem> findAvatarItemsByAvatarId(int id);
}

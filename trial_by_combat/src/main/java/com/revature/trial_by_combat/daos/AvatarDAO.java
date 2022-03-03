package com.revature.trial_by_combat.daos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.trial_by_combat.models.Avatar;

@Repository
public interface AvatarDAO extends CrudRepository<Avatar, Integer> {
	@Query("from Avatar a where a.player.id=:id")
	Iterable<Avatar> findAllAvatarsByPlayerId(int id);
}

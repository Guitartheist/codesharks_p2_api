package com.revature.trial_by_combat.daos;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.trial_by_combat.models.Loadout;

@Repository
public interface LoadoutDAO extends CrudRepository<Loadout, Integer>{	

	@Query("from Loadout l where l.player.id=:id")
	Iterable<Loadout> findAllLoadoutsByPlayerId(int id);

}
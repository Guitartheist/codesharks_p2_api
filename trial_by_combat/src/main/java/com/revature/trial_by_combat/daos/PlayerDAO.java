package com.revature.trial_by_combat.daos;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.trial_by_combat.models.Player;

@Repository
public interface PlayerDAO extends CrudRepository<Player, Integer> {
	Optional<Player> findPlayerByEmail(String email);

	Optional<Player> findPlayerByUsername(String username);

	@Query("from Player p where p.username = :username and p.password = :password")
	Player findPlayerByUsernameAndPassword(String username, String password);
}

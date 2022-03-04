package com.revature.trial_by_combat.daos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.trial_by_combat.models.ChallengeAction;

@Repository
public interface ChallengeActionDAO extends CrudRepository<ChallengeAction, Integer> {
	@Query("from ChallengeAction c where c.challenge.id=:id order by c.timestamp asc")
	Iterable<ChallengeAction> findByChallengeId(int id);
}

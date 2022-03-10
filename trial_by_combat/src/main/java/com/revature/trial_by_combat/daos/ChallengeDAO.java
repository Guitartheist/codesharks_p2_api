package com.revature.trial_by_combat.daos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.trial_by_combat.models.Challenge;

@Repository
public interface ChallengeDAO extends CrudRepository<Challenge, Integer> {
	@Query("from Challenge c where c.id=:id")
	Challenge findChallengeById(int id);
}
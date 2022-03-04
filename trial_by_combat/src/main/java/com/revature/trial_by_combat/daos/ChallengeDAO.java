package com.revature.trial_by_combat.daos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import com.revature.trial_by_combat.models.Avatar;
import com.revature.trial_by_combat.models.Challenge;

@Repository
public interface ChallengeDAO extends CrudRepository<Challenge, Integer> {

    @Query("from Challenge a where a.avatar.id=:id")
	Iterable<Challenge> findAllChallengesByAvatarId(int id);

    @Query("from Challenge b where b.challenger.id=:id")
	Iterable<Challenge> findAllChallengesByChallengerId(int id);

	@Query("from Challenge c where c.avatar = :avatar and c.challenger = :challenger")
	Challenge findChallengeByAvatarAndChallenger(Avatar avatar, Avatar challenger);
}
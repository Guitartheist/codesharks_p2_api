package com.revature.trial_by_combat.daos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import com.revature.trial_by_combat.models.Avatar;
import com.revature.trial_by_combat.models.Challenge;

@Repository
public interface ChallengeDAO extends CrudRepository<Challenge, Integer> {
	
    Optional<Challenge> findChallengeByAvatar(Avatar avatar);
	Optional<Challenge> findChallengeByChallenger(Avatar challenger);

	@Query("from Challenge c where c.avatar = :avatar and c.challenger = :challenger")
	Challenge findChallengeByAvatarAndChallenger(Avatar avatar, Avatar challenger);
}
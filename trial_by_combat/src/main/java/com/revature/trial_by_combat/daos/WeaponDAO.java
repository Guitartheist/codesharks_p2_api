package com.revature.trial_by_combat.daos;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.trial_by_combat.models.Weapon;

@Repository
public interface WeaponDAO extends CrudRepository<Weapon, Integer> {
	@Query("from Item i where i.itemname=:itemname")
    Optional<Weapon> findWeaponByItemName(String itemname);
}
package com.revature.trial_by_combat.daos;

import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.trial_by_combat.models.Armor;

@Repository
public interface ArmorDAO extends CrudRepository<Armor, Integer> {
		@Query("from Item i where i.itemname=:itemname")
    Optional<Armor> findArmorByItemName(String itemname);
}

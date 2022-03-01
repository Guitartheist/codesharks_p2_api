package com.revature.trial_by_combat.models;

import java.util.Objects;

import javax.persistence.Entity;

@Entity
public class Weapon extends Item {
	int damageDie;
	int damageBonus;

	public int getDamageDie() {
		return damageDie;
	}

	public void setDamageDie(int damageDie) {
		this.damageDie = damageDie;
	}

	public int getDamageBonus() {
		return damageBonus;
	}

	public void setDamageBonus(int damageBonus) {
		this.damageBonus = damageBonus;
	}

	@Override
	public int hashCode() {
		return Objects.hash(damageBonus, damageDie);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Weapon other = (Weapon) obj;
		return damageBonus == other.damageBonus && damageDie == other.damageDie;
	}

	@Override
	public String toString() {
		return "Weapon [damageDie=" + damageDie + ", damageBonus=" + damageBonus + "]";
	}

}

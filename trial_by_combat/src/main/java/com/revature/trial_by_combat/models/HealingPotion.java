package com.revature.trial_by_combat.models;

import java.util.Objects;

import javax.persistence.Entity;

@Entity
public class HealingPotion extends Item {
	int healingDie;
	int healingBonus;

	public int getHealingDie() {
		return healingDie;
	}

	public void setHealingDie(int healingDie) {
		this.healingDie = healingDie;
	}

	public int getHealingBonus() {
		return healingBonus;
	}

	public void setHealingBonus(int healingBonus) {
		this.healingBonus = healingBonus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(healingBonus, healingDie);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		HealingPotion other = (HealingPotion) obj;
		return healingBonus == other.healingBonus && healingDie == other.healingDie;
	}

	@Override
	public String toString() {
		return "HealingPotion [healingDie=" + healingDie + ", healingBonus=" + healingBonus + "]";
	}

}

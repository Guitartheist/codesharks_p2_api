package com.revature.trial_by_combat.models;

import java.util.Objects;

import javax.persistence.Entity;

@Entity
public class Armor extends Item {
	int armorClass;
	int damageReduction;

	public int getArmorClass() {
		return armorClass;
	}

	public void setArmorClass(int armorClass) {
		this.armorClass = armorClass;
	}

	public int getDamageReduction() {
		return damageReduction;
	}

	public void setDamageReduction(int damageReduction) {
		this.damageReduction = damageReduction;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(armorClass, damageReduction);
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
		Armor other = (Armor) obj;
		return armorClass == other.armorClass && damageReduction == other.damageReduction;
	}

	@Override
	public String toString() {
		return "Armor [armorClass=" + armorClass + ", damageReduction=" + damageReduction + "]";
	}

}

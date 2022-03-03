package com.revature.trial_by_combat.models;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Avatar {
	@Id
	@GeneratedValue
	int id;
	@ManyToOne(optional = false, cascade = CascadeType.MERGE)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "player_id", unique = false, nullable = false, updatable = false)
	Player player;
	@Column(unique = false, nullable = false)
	String avatarname;
	int strength;
	int dexterity;
	int constitution;
	int intelligence;
	int wisdom;
	int charisma;
	int gold;
	int currentHealth;
	int maximumHealth;

	public static int rollDice(int dice, int sides) {
		int total = 0;
		for (int i = 0; i < dice; i++) {
			total += (int) (Math.random() * sides) + 1;
		}
		return total;
	}
	
	public int getModifier(int value) {
		if (value < 4) {
			return -3;
		}
		else if (value < 6) {
			return -2;
		}
		else if (value < 9) {
			return -1;
		}
		else if (value < 13) {
			return 0;
		}
		else if (value < 16) {
			return 1;
		}
		else if (value < 18) {
			return 2;
		}
		return value - 15;
	}

	public void rollStatsAndGold() {
		strength = rollDice(3, 6);
		dexterity = rollDice(3, 6);
		constitution = rollDice(3, 6);
		intelligence = rollDice(3, 6);
		wisdom = rollDice(3, 6);
		charisma = rollDice(3, 6);
		gold = rollDice(3, 6) * 10;
		maximumHealth = 0;
		for (int i = 0; i < 3; i++) {
			int health = rollDice(3, 6) + getModifier(constitution);
			maximumHealth += (health>0) ? health : 1;
		}
		currentHealth = maximumHealth;
	}
	
	

	public String getAvatarname() {
		return avatarname;
	}

	public void setAvatarName(String avatarname) {
		this.avatarname = avatarname;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getDexterity() {
		return dexterity;
	}

	public void setDexterity(int dexterity) {
		this.dexterity = dexterity;
	}

	public int getConstitution() {
		return constitution;
	}

	public void setConstitution(int constitution) {
		this.constitution = constitution;
	}

	public int getIntelligence() {
		return intelligence;
	}

	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}

	public int getWisdom() {
		return wisdom;
	}

	public void setWisdom(int wisdom) {
		this.wisdom = wisdom;
	}

	public int getCharisma() {
		return charisma;
	}

	public void setCharisma(int charisma) {
		this.charisma = charisma;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public int getCurrentHealth() {
		return currentHealth;
	}

	public void setCurrentHealth(int currentHealth) {
		this.currentHealth = currentHealth;
	}

	public int getMaximumHealth() {
		return maximumHealth;
	}

	public void setMaximumHealth(int maximumHealth) {
		this.maximumHealth = maximumHealth;
	}

	@Override
	public int hashCode() {
		return Objects.hash(avatarname, charisma, constitution, currentHealth, dexterity, gold, id, intelligence,
				maximumHealth, player, strength, wisdom);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Avatar other = (Avatar) obj;
		return Objects.equals(avatarname, other.avatarname) && charisma == other.charisma
				&& constitution == other.constitution && currentHealth == other.currentHealth
				&& dexterity == other.dexterity && gold == other.gold && id == other.id
				&& intelligence == other.intelligence && maximumHealth == other.maximumHealth
				&& Objects.equals(player, other.player) && strength == other.strength && wisdom == other.wisdom;
	}

	@Override
	public String toString() {
		return "Avatar [id=" + id + ", player=" + player + ", avatarname=" + avatarname + ", strength=" + strength
				+ ", dexterity=" + dexterity + ", constitution=" + constitution + ", intelligence=" + intelligence
				+ ", wisdom=" + wisdom + ", charisma=" + charisma + ", gold=" + gold + ", currentHealth="
				+ currentHealth + ", maximumHealth=" + maximumHealth + "]";
	}

}

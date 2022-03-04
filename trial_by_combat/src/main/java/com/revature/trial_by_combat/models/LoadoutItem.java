package com.revature.trial_by_combat.models;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class LoadoutItem {
	@Id
	@GeneratedValue
	int id;
	@ManyToOne(optional = false, cascade = CascadeType.MERGE)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "loadout_id", unique = false, nullable = false, updatable = false)
	Loadout loadout;
	@ManyToOne(optional = false, cascade = CascadeType.MERGE)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "item_id", unique = false, nullable = false, updatable = false)
	Item item;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Loadout getLoadout() {
		return loadout;
	}

	public void setLoadout(Loadout loadout) {
		this.loadout = loadout;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, item, loadout);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoadoutItem other = (LoadoutItem) obj;
		return id == other.id && Objects.equals(item, other.item) && Objects.equals(loadout, other.loadout);
	}

	@Override
	public String toString() {
		return "LoadoutItem [id=" + id + ", loadout=" + loadout + ", item=" + item + "]";
	}

}

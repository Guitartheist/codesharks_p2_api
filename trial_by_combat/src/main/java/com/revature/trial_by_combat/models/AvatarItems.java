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
public class AvatarItems {
	@Id
	@GeneratedValue
	int id;
	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "avatar_id", unique = false, nullable = false, updatable = false)
	Avatar avatar;
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

	public Avatar getAvatar() {
		return avatar;
	}

	public void setAvatar(Avatar avatar) {
		this.avatar = avatar;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	@Override
	public int hashCode() {
		return Objects.hash(avatar, id, item);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AvatarItems other = (AvatarItems) obj;
		return Objects.equals(avatar, other.avatar) && id == other.id && Objects.equals(item, other.item);
	}

	@Override
	public String toString() {
		return "AvatarItems [id=" + id + ", avatar=" + avatar + ", item=" + item + "]";
	}

}

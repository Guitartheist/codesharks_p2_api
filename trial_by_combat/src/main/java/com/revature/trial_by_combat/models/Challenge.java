package com.revature.trial_by_combat.models;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.lang.Nullable;

@Entity
public class Challenge {
	@Id
	@GeneratedValue
	int id;
	@ManyToOne(optional = false, cascade = CascadeType.MERGE)
	@JoinColumn(name = "creator_id", unique = false, nullable = false, updatable = false)
	Avatar avatar;
	@Nullable
	@ManyToOne(optional = false, cascade = CascadeType.MERGE)
	@JoinColumn(name = "challenger_id", unique = false, nullable = true, updatable = true)
	Avatar challenger;

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

	public Avatar getChallenger() {
		return challenger;
	}

	public void setChallenger(Avatar challenger) {
		this.challenger = challenger;
	}

	@Override
	public int hashCode() {
		return Objects.hash(avatar, challenger, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Challenge other = (Challenge) obj;
		return Objects.equals(avatar, other.avatar) && Objects.equals(challenger, other.challenger) && id == other.id;
	}

	@Override
	public String toString() {
		return "Challenge [id=" + id + ", avatar=" + avatar + ", challenger=" + challenger + "]";
	}

}

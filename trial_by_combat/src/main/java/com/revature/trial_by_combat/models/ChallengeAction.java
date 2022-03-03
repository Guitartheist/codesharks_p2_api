package com.revature.trial_by_combat.models;

import java.time.LocalDateTime;
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
public class ChallengeAction {
	@Id
	@GeneratedValue
	int id;
	LocalDateTime timestamp;
	@ManyToOne(optional = false, cascade = CascadeType.MERGE)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "challenge_id", unique = false, nullable = false, updatable = false)
	Challenge challenge;
	String description;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public Challenge getChallenge() {
		return challenge;
	}

	public void setChallenge(Challenge challenge) {
		this.challenge = challenge;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		return Objects.hash(challenge, description, id, timestamp);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChallengeAction other = (ChallengeAction) obj;
		return Objects.equals(challenge, other.challenge) && Objects.equals(description, other.description)
				&& id == other.id && Objects.equals(timestamp, other.timestamp);
	}

	@Override
	public String toString() {
		return "ChallengeAction [id=" + id + ", timestamp=" + timestamp + ", challenge=" + challenge + ", description="
				+ description + "]";
	}

}

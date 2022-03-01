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
public class ChallengeAction {
	@Id
	@GeneratedValue
	int id;
	int stepNumber;
	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "challenge_id", unique = false, nullable = false, updatable = false)
	Challenge challenge;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStepNumber() {
		return stepNumber;
	}

	public void setStepNumber(int stepNumber) {
		this.stepNumber = stepNumber;
	}

	public Challenge getChallenge() {
		return challenge;
	}

	public void setChallenge(Challenge challenge) {
		this.challenge = challenge;
	}

	@Override
	public int hashCode() {
		return Objects.hash(challenge, id, stepNumber);
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
		return Objects.equals(challenge, other.challenge) && id == other.id && stepNumber == other.stepNumber;
	}

	@Override
	public String toString() {
		return "ChallengeAction [id=" + id + ", stepNumber=" + stepNumber + ", challenge=" + challenge + "]";
	}

}

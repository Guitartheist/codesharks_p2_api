package com.revature.trial_by_combat.models;

import java.util.Objects;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Item {
	@Id
	@GeneratedValue
	int id;
	String itemname;
	String description;
	int price;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getItemname() {
		return itemname;
	}

	public void setItemname(String itemname) {
		this.itemname = itemname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, id, itemname, price);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		return Objects.equals(description, other.description) && id == other.id
				&& Objects.equals(itemname, other.itemname) && price == other.price;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", itemname=" + itemname + ", description=" + description + ", price=" + price + "]";
	}

}

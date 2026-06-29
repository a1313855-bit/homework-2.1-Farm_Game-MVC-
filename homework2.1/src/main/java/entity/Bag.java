package entity;

import java.io.Serializable;

public class Bag implements Serializable{
	private int id;
	private String player_name;
	private String item_name;
	private int quantity;
	
	public Bag() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Bag(String player_name, String item_name, int quantity) {
		super();
		this.player_name = player_name;
		this.item_name = item_name;
		this.quantity = quantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPlayer_name() {
		return player_name;
	}

	public void setPlayer_name(String player_name) {
		this.player_name = player_name;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}

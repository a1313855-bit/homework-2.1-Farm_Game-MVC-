package entity;

import java.io.Serializable;

public class Shop implements Serializable{
	private int id;
	private String seed_name;
	private int buy_price;
	private int sell_price;
	private int grow_time;//秒
	
	public Shop() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Shop(String seed_name, int buy_price, int sell_price, int grow_time) {
		super();
		this.seed_name = seed_name;
		this.buy_price = buy_price;
		this.sell_price = sell_price;
		this.grow_time = grow_time;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSeed_name() {
		return seed_name;
	}
	public void setSeed_name(String seed_name) {
		this.seed_name = seed_name;
	}
	public int getBuy_price() {
		return buy_price;
	}
	public void setBuy_price(int buy_price) {
		this.buy_price = buy_price;
	}
	public int getSell_price() {
		return sell_price;
	}
	public void setSell_price(int sell_price) {
		this.sell_price = sell_price;
	}
	public int getGrow_time() {
		return grow_time;
	}
	public void setGrow_time(int grow_time) {
		this.grow_time = grow_time;
	}
	
	
}

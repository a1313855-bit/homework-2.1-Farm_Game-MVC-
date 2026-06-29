package entity;

import java.util.Date;

public class Farm {
	private int id;
	private String player_name;
	private int farm_number;
	private String seed_name;
	private Date plant_time;
	private int is_unlock;
	
	public Farm() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Farm(String player_name, int farm_number, String seed_name, Date plant_time, int is_unlock) {
		super();
		this.player_name = player_name;
		this.farm_number = farm_number;
		this.seed_name = seed_name;
		this.plant_time = plant_time;
		this.is_unlock = is_unlock;
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
	public int getFarm_number() {
		return farm_number;
	}
	public void setFarm_number(int farm_number) {
		this.farm_number = farm_number;
	}
	public String getSeed_name() {
		return seed_name;
	}
	public void setSeed_name(String seed_name) {
		this.seed_name = seed_name;
	}
	public Date getPlant_time() {
		return plant_time;
	}
	public void setPlant_time(Date plant_time) {
		this.plant_time = plant_time;
	}
	public int getIs_unlock() {
		return is_unlock;
	}
	public void setIs_unlock(int is_unlock) {
		this.is_unlock = is_unlock;
	}
	
	
}

package models;

public class Meal {
	public String name;
	public double hour;
	public String obs;
	public int daysOfWeek;
	public boolean active;
	
	public Meal(String name, double hour, int daysOfWeek, boolean active, String... obs) {
		this.name = name;
		this.hour = hour;
		if(obs.length > 0) {
			this.obs = obs[0];
		}
		this.daysOfWeek = daysOfWeek;
		this.active = active;
	}
}
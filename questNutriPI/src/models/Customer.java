package models;

public class Customer extends DAO {
	public int id;
	public String name;
	
	public Customer(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

}
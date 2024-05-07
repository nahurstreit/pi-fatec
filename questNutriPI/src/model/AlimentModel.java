package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Aliment")
public class AlimentModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idAliment")
	private int idAliment;

	@Column(name = "Active")
	public boolean active;

	@Column(name = "Custom")
	private boolean custom;

	@Column(name = "Name")
	public String name;

	@Column(name = "Group")
	public String group;

	@Column(name = "Kcal")
	public String kcal;

	@Column(name = "Carb")
	public String carb;

	@Column(name = "Carb")
	public String protein;

	@Column(name = "Fat")
	public String fat;


	public AlimentModel() {

	}

	/**
	 * 
	 * @param active - Status of Aliment availability
	 * @param name - Aliment's name
	 * @param group - Group of that kind of Aliment
	 * @param kcal - Kcal quantity of that Aliment
	 * @param carb - Carb of that Aliment
	 * @param protein - Protein of that Aliment
	 * @param fat - Fat of that Aliment
	 */
	public AlimentModel(boolean active, String name, String group, 
			String kcal, String carb, String protein,String fat) {

		this.idAliment = idAliment;
		this.active = active;
		this.custom = custom;
		this.name = name;
		this.group = group;
		this.kcal = kcal;
		this.carb = carb;
		this.protein = protein;
		this.fat = fat;
	}

	public int getIdAliment() {
		return idAliment;
	}

	
	public void setIdAliment(int idAliment) {
		this.idAliment = idAliment;
	}

	
	public boolean isActive() {
		return active;
	}

	
	public void setActive(boolean active) {
		this.active = active;
	}

	
	public boolean isCustom() {
		return custom;
	}

	
	public void setCustom(boolean custom) {
		this.custom = custom;
	}

	
	public String getName() {
		return name;
	}

	
	public void setName(String name) {
		this.name = name;
	}

	
	public String getGroup() {
		return group;
	}

	
	public void setGroup(String group) {
		this.group = group;
	}

	
	public String getKcal() {
		return kcal;
	}

	
	public void setKcal(String kcal) {
		this.kcal = kcal;
	}

	
	public String getCarb() {
		return carb;
	}

	
	public void setCarb(String carb) {
		this.carb = carb;
	}

	
	public String getProtein() {
		return protein;
	}

	
	public void setProtein(String protein) {
		this.protein = protein;
	}

	
	public String getFat() {
		return fat;
	}

	
	public void setFat(String fat) {
		this.fat = fat;
	}


}

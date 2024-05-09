package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Meals")
public class Meal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idMeal")
	public Integer idMeal;

	@Column(name = "idCustomer")
	public Integer idCustomer;

	@Column(name = "meal_daysOfWeek")
	public Integer daysOfWeek;

	@Column(name = "meal_name")
	public String mealName;

	@Column(name = "meal_active")
	public Integer mealActive;

	@Column(name = "meal_hour")
	public Double mealHour;

	@Column(name = "meal_obs")
	public String mealObs;

	private Food[] mealFood = null;

	@Column(name = "meal_createdAt")
	private String mealCreatedAt;

	@Column(name = "meal_deactivatedAt")
	private String mealDeactivatedAt;

	public Meal(Integer idMeal, Integer idCustomer, String mealName, Integer mealActive, Integer daysOfWeek,
			Double mealHour, String mealObs, String mealCreatedAt, String mealDeactivatedAt) {
		this.idMeal = idMeal;
		this.idCustomer = idCustomer;
		this.mealName = mealName;
		this.mealActive = mealActive;
		this.daysOfWeek = daysOfWeek;
		this.mealHour = mealHour;
		this.mealObs = mealObs;
		this.mealCreatedAt = mealCreatedAt;
		this.mealDeactivatedAt = mealDeactivatedAt;
	}

	public Meal() {
		this(null, null, null, null, null, null, null, null, null);
	}

	public Food[] getMealFood() {
		return mealFood;
	}

	public void setMealFood(Food[] mealFood) {
		this.mealFood = mealFood;
	}

	public String getMealCreatedAt() {
		return mealCreatedAt;
	}

	public void setMealCreatedAt(String mealCreatedAt) {
		this.mealCreatedAt = mealCreatedAt;
	}

	public String getMealDeactivatedAt() {
		return mealDeactivatedAt;
	}

	public void setMealDeactivatedAt(String mealDeactivatedAt) {
		this.mealDeactivatedAt = mealDeactivatedAt;
	}
}
package model;

import java.sql.Time;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "Meals")
public class Meal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idMeal")
	public Integer idMeal;
	
	@ManyToOne
	@JoinColumn(name = "idCustomer")
	private Customers customer;

	@Column(name = "meal_daysOfWeek")
	public Integer daysOfWeek;

	@Column(name = "meal_name")
	public String mealName;

	@Column(name = "meal_active")
	public Integer mealActive;

	@Column(name = "meal_hour")
    @Temporal(TemporalType.TIME)
    public Time mealHour;

	@Column(name = "meal_obs")
	public String mealObs;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Food> mealFood;

	@Column(name = "meal_createdAt")
	private String mealCreatedAt;

	@Column(name = "meal_deactivatedAt")
	private String mealDeactivatedAt;

	public Meal(Integer idMeal, Customers customers, String mealName, Integer mealActive, Integer daysOfWeek,
			Time mealHour, String mealObs, String mealCreatedAt, String mealDeactivatedAt) {
		this.idMeal = idMeal;
		this.customer = customers;
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

	public List<Food> getMealFood() {
	    return mealFood;
	}

	public void setMealFood(List<Food> mealFood) {
	        this.mealFood = mealFood;
	}

	public String getMealCreatedAt() {
		return mealCreatedAt;
	}

	private void setMealCreatedAt(String mealCreatedAt) {
		this.mealCreatedAt = mealCreatedAt;
	}

	public String getMealDeactivatedAt() {
		return mealDeactivatedAt;
	}

	private void setMealDeactivatedAt(String mealDeactivatedAt) {
		this.mealDeactivatedAt = mealDeactivatedAt;
	}

	@Override
	public String toString() {
		return "Meal [idMeal=" + idMeal + ", customer=" + customer + ", daysOfWeek=" + daysOfWeek + ", mealName="
				+ mealName + ", mealActive=" + mealActive + ", mealHour=" + mealHour + ", mealObs=" + mealObs
				+ ", mealFood=" + mealFood + ", mealCreatedAt=" + mealCreatedAt + ", mealDeactivatedAt="
				+ mealDeactivatedAt + "]";
	}
	
	
}
package model.entities;

import java.sql.Time;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import model.dao.MealDAO;

@Entity
@Table(name = "Meals")
public class Meal extends MealDAO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idMeal")
	public Integer idMeal;
	
	@ManyToOne
	@JoinColumn(name = "idCustomer")
	private Customer customer;

	@Column(name = "meal_daysOfWeek")
	public Integer daysOfWeek;

	@Column(name = "meal_name")
	public String name;

	@Column(name = "meal_active")
	public Integer active;

	@Column(name = "meal_hour")
    @Temporal(TemporalType.TIME)
    public Time hour;

	@Column(name = "meal_obs")
	public String obs;

	@Column(name = "meal_createdAt")
	private String createdAt;

	@Column(name = "meal_deactivatedAt")
	private String deactivatedAt;

	public Meal(Integer idMeal, Customer customer, String name, Integer active, Integer daysOfWeek,
			Time hour, String obs, String createdAt, String deactivatedAt) {
		super();
		this.idMeal = idMeal;
		this.customer = customer;
		this.name = name;
		this.active = active;
		this.daysOfWeek = daysOfWeek;
		this.hour = hour;
		this.obs = obs;
		this.createdAt = createdAt;
		this.deactivatedAt = deactivatedAt;
	}

	public Meal() {
		this(null, null, null, null, null, null, null, null, null);
	}
	
	public List<Food> getFoods() {
		return Food.findAllByMealPK(this.idMeal);
	}
	
	public Customer getCustomer() {
		return customer;
	}


	@Override
	public String toString() {
		return "Meal: {"
				+ "\n    idMeal: " + idMeal + ","
				+ "\n    owner: " + customer.name + " - id: " + customer.idCustomer +", "
				+ "\n    daysOfWeek: " + daysOfWeek + ", "
				+ "\n    name: " + name + ", "
				+ "\n    active: " + (active == 0? false: true) + ","
				+ "\n    hour: " + hour + ", "
				+ "\n    obs: " + (obs != null? "\""+obs+"\"":  obs) +  ", "
				+ "\n    createdAt: " + createdAt + ", "
				+ "\n    deactivatedAt: "+ deactivatedAt
				+ "\n}";
	}
	
	/**
	 * Método para retornar uma visão pequena do objeto.
	 * @return String contendo apenas alguns dos atributos mais importantes
	 */
	public String smallInfo() {
		return "{id: " + idMeal + ", name: \"" + name+ "\", owner: \"" + customer.name + "\"}";
	}
	
	
}
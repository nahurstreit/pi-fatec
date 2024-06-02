package model.entities;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
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

	@Column(name = "meal_hour")
    @Temporal(TemporalType.TIME)
    public Time hour;

	@Column(name = "meal_obs")
	public String obs;

	@Column(name = "meal_createdAt")
	private LocalDate createdAt;

	@Column(name = "meal_deactivatedAt")
	private LocalDate deactivatedAt;
	

	/**
	 * Método construtor de Meal.
	 * @param customer - Customer owner da refeição.
	 * @param name - Nome da refeição.
	 * @param active - inteiro que representa se a Meal está ativa. Se 1: ativo, se 0: não ativo.
	 * @param daysOfWeek - Inteiro entre 1 e 127 que representa os dias que a refeição acontece
	 * @param hour - hora de acontecimento da refeição
	 * @param obs - observação sobre a refeição.
	 */
	public Meal(Customer customer, String name, Integer daysOfWeek, String hour, String ...obs) {
		super();
		this.customer = customer;
		this.name = name;
		this.daysOfWeek = daysOfWeek;
		this.hour = (hour != null) ? formatHour(hour) : null;
		this.obs = obs.length > 0 ? obs[0] : null;
	}
	
	/**
	 * Método construtor sem atribuir um customer.
	 * @param name - Nome da refeição.
	 * @param active - inteiro que representa se a Meal está ativa. Se 1: ativo, se 0: não ativo.
	 * @param daysOfWeek - Inteiro entre 1 e 127 que representa os dias que a refeição acontece
	 * @param hour - hora de acontecimento da refeição
	 * @param obs - observação sobre a refeição.
	 */
	public Meal(String name, Integer daysOfWeek, String hour, String ...obs) {
		this(null, name, daysOfWeek, hour, obs);
	}

	public Meal() {
		this(null, null, null);
	}
	
	public List<Food> getFoods() {
		return Food.findAllByMealPK(this.idMeal);
	}
	
	public Customer getCustomer() {
		return customer;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Meal setCustomer(Customer customer) {
		if(this.customer == null) this.customer = customer;
		return this;
	}
	
	public Meal setHour(String hour) {
	    try {
	        this.hour = formatHour(hour);
	    } catch (DateTimeParseException e) {
	        e.printStackTrace();
	    }
	    return this;
	}
	
	/**
	 * Método para formatar uma string como Objeto Time
	 * @param hour - String da hora no formato "HH:mm"
	 * @return Um objeto do tipo Time
	 */
	public Time formatHour(String hour) {
		return Time.valueOf(LocalTime.parse(hour, DateTimeFormatter.ofPattern("HH:mm")));
	}
	
	/**
     * Método para obter a hora de um objeto Time
     * @return A hora como um inteiro ou null se hour for null
     */
    public String getHourPart() {
        if (this.hour == null) {
            return null;
        }
        LocalTime localTime = this.hour.toLocalTime();
        return String.format("%02d", localTime.getHour());
    }
    
    /**
     * Método para obter o minuto de um objeto Time
     * @return O minuto como um inteiro ou null se hour for null
     */
    public String getMinutePart() {
        if (this.hour == null) {
            return null;
        }
        LocalTime localTime = this.hour.toLocalTime();
        return String.format("%02d", localTime.getMinute());
    }
	
	/**
	 * Método para criação rápida de Foods em uma Meal já salva.
	 * É preciso já estar vinculada à um cliente.
	 * @param foods
	 */
	public void createFood(Food food) {
		if(this.customer != null) {
			food.setMeal(this);
			food.save();
		}
	}
	
	public boolean isActive() {
		return this.deactivatedAt != null;
	}

    /**
     * Método para adicionar ao objeto, a data e horário atual de registro.
     */
    @PrePersist
    private void prePersist() {
        if(createdAt == null) createdAt = LocalDate.now();
    }

	@Override
	public String toString() {
		return "Meal: {"
				+ "\n    idMeal: " + idMeal + ","
				+ "\n    owner: " + (customer != null ? customer.name + " - id: " + customer.getId() : "null") +", "
				+ "\n    daysOfWeek: " + daysOfWeek + ", "
				+ "\n    name: " + name + ", "
				+ "\n    active: " + (deactivatedAt == null? false: true) + ","
				+ "\n    hour: " + hour + ", "
				+ "\n    obs: " + (obs != null? "\""+obs+"\"":  obs) +  ", "
				+ "\n    createdAt: " + createdAt + ", "
				+ "\n    deactivatedAt: "+ deactivatedAt
				+ "\n}";
	}
	
	
	/**
	 * Reimplementação de Delete para não excluir o registro de Meal e sim desativá-lo
	 */
	@Override
	public boolean delete() {
		this.deactivatedAt = LocalDate.now();
		return this.save();
	}
	
	/**
	 * Método para retornar uma visão pequena do objeto.
	 * @return String contendo apenas alguns dos atributos mais importantes
	 */
	public String smallInfo() {
		return "{id: " + idMeal + ", name: \"" + name+ "\", owner: \"" + (customer != null ? customer.name:"null") + "\"}";
	}
	
	
}
package model.entities;

import java.sql.Time;
import java.time.LocalDateTime;
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
import utils.interfaces.ICopy;

@Entity
@Table(name = "Meals")
public class Meal extends MealDAO implements ICopy<Meal> {
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
	private LocalDateTime createdAt;

	@Column(name = "meal_deactivatedAt")
	private LocalDateTime deactivatedAt;
	

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
	
	@Override
	public Integer getId() {
		return this.idMeal;
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
		return this.deactivatedAt == null;
	}

    /**
     * Método para adicionar ao objeto, a data e horário atual de registro.
     */
    @PrePersist
    private void prePersist() {
        if(createdAt == null) createdAt = LocalDateTime.now();
    }
    
	/**
	 * Reimplementação de Delete para não excluir o registro de Meal e sim desativá-lo
	 */
	@Override
	public boolean delete() {
		this.deactivatedAt = LocalDateTime.now();
		//Desabilitado para testes.
//		if(createdAt == deactivatedAt) { //Se o delete acontecer no mesmo dia de criação, o registro é deletado ao invés de ser desativado, pois não há histórico.
//			return super.delete();
//		}
		return this.save();
	}
	
    public static Meal createCopyFrom(Meal mold) {
    	Meal copy = new Meal();
    	copy.copyFrom(mold);
    	return copy;
    }
	
	@Override
	public Meal copyFrom(Meal originObject) {
		try {
			this.customer = originObject.customer;
			this.name = originObject.name;
			this.daysOfWeek = originObject.daysOfWeek;
			this.hour = originObject.hour;
			this.obs = originObject.obs;
			
			this.save();
			
			copyFoods(originObject, this);
			
		} catch (Exception e) {
			System.err.println("Não foi possível copiar o objeto.");
		}
		
		return this;
	}
	
	
	@Override
	public boolean copyMeTo(Meal destinyObject) {
    	boolean res = true;
    	try {
        	destinyObject.customer= this.customer;
        	destinyObject.name = this.name;
        	destinyObject.daysOfWeek = this.daysOfWeek;
        	destinyObject.hour = this.hour;
        	destinyObject.obs = this.obs;
        	
        	if(destinyObject.idMeal == null) destinyObject.save();
        	
        	copyFoods(this, destinyObject);
        	
		} catch (Exception e) {
			System.err.println("Não foi possível copiar o objeto.");
			e.printStackTrace();
			res = false;
		}

    	return res;
	}
	
	/**
	 * Método que copia todas as foods de uma meal (origin) para outra (destiny).
	 * @param origin - Objeto Meal cujos objetos de Food associados devem ser copiados para destiny.
	 * @param destiny - Objeto Meal que receberá copias de objetos de Food associados ao origin.
	 * @return <b>boolean</b> - O resultado da operação.
	 * <li>Se <b>true</b> - Todas as foods foram copiadas.
	 * <li>Se <b>false</b> - Houve algum erro e a cópia não foi bem sucedida.
	 */
	private boolean copyFoods(Meal origin, Meal destiny) {
		boolean res = true;
		int toCopy = origin.getFoods().size();
		int totalCopied = 0;
		try {
			for(Food food: origin.getFoods()) { //Itera sobre as foods da origin
				try {
					Food copiedFood = Food.createCopyFrom(food); //Copia a food da iteração
					copiedFood.meal = destiny; //Salva a meal copiada na destiny
					copiedFood.save();
					
					totalCopied++;
				} catch (Exception e) {
				}

			}
		} catch (Exception e) {
			res = false;
		}
		
		if(toCopy != totalCopied) res = false; //Verificação adicional para saber se todas as foods foram copiadas.
		
		return res;
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
	 * Método para retornar uma visão pequena do objeto.
	 * @return String contendo apenas alguns dos atributos mais importantes
	 */
	public String smallInfo() {
		return "{id: " + idMeal + ", name: \"" + name+ "\", owner: \"" + (customer != null ? customer.name:"null") + "\"}";
	}
	
	
}
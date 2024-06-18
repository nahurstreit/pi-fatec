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

/**
 * Entidade que representa uma refeição cadastrada no sistema.
 */
@Entity
@Table(name = "Meals")
public class Meal extends MealDAO implements ICopy<Meal> {
	
	/**
	 * Id associado a esse objeto no banco de dados
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idMeal")
	public Integer idMeal;
	
	/**
	 * Cliente que possui essa refeição.
	 */
	@ManyToOne
	@JoinColumn(name = "idCustomer")
	private Customer customer;

	/**
	 * Dias da semana que essa refeição acontece.
	 */
	@Column(name = "meal_daysOfWeek")
	public Integer daysOfWeek;

	/**
	 * Nome da refeição.
	 */
	@Column(name = "meal_name")
	public String name;

	/**
	 * Horário da refeição.
	 */
	@Column(name = "meal_hour")
    @Temporal(TemporalType.TIME)
    public Time hour;

	/**
	 * Data e hora que a refeição foi criada.
	 */
	@Column(name = "meal_createdAt")
	private LocalDateTime createdAt;

	/**
	 * Data e hora em que a refeição foi 'excluída'.
	 */
	@Column(name = "meal_deactivatedAt")
	private LocalDateTime deactivatedAt;
	

	/**
	 * Método construtor de Meal.
	 * @param customer - Customer owner da refeição.
	 * @param name - Nome da refeição.
	 * @param daysOfWeek - Inteiro entre 1 e 127 que representa os dias que a refeição acontece
	 * @param hour - hora de acontecimento da refeição
	 */
	public Meal(Customer customer, String name, Integer daysOfWeek, String hour) {
		super();
		this.customer = customer;
		this.name = name;
		this.daysOfWeek = daysOfWeek;
		this.hour = (hour != null) ? formatHour(hour) : null;
	}
	
	/**
	 * Método construtor sem atribuir um customer.
	 * @param name - Nome da refeição.
	 * @param daysOfWeek - Inteiro entre 1 e 127 que representa os dias que a refeição acontece
	 * @param hour - hora de acontecimento da refeição
	 */
	public Meal(String name, Integer daysOfWeek, String hour) {
		this(null, name, daysOfWeek, hour);
	}

	/**
	 * Constructor padrão da classe Meal
	 */
	public Meal() {
		this(null, null, null);
	}
	
	/**
	 * Obtém o id deste objeto no banco de dados
	 * @return integer -> id no banco de dados
	 */
	@Override
	public Integer getId() {
		return this.idMeal;
	}
	
	/**
     * Método para recuperar uma lista de foods associadas a esta refeição.
     * 
     * @return Uma lista de foods.
     */
	public List<Food> getFoods() {
		return Food.findAllByMealPK(this.idMeal);
	}
	
	/**
     * Método para recuperar o cliente proprietário desta refeição.
     * 
     * @return O cliente proprietário.
     */
	public Customer getCustomer() {
		return customer;
	}
	
	/**
	 * Método para recuperar o nome da Meal.
	 * 
	 * @return O nome da Meal.
	 */
	public String getName() {
		return this.name;
	}
	
	/**
     * Método para definir o cliente proprietário desta refeição.
     * 
     * @param customer O cliente a ser definido como proprietário.
     * @return Retorna a própria Meal, para implementação de fluent interface.
     */
	public Meal setCustomer(Customer customer) {
		if(this.customer == null) this.customer = customer;
		return this;
	}
	
	/**
	 * Método para definir o nome da refeição.
	 * @param name -> Nome da refeição
     * @return Retorna a própria Meal, para implementação de fluent interface.
	 */
	public Meal setName(String name) {
		this.name = name;
		return this;
	}
	
	/**
	 * Método para definir o horário da refeição
	 * @param hour -> String que representa a hora em 'HH:mm'
     * @return Retorna a própria Meal, para implementação de fluent interface.
	 */
	public Meal setHour(String hour) {
	    try {
	        this.hour = formatHour(hour);
	    } catch (DateTimeParseException e) {
	        e.printStackTrace();
	    }
	    return this;
	}
	
	/**
	 * Método para definir os dias de repetição da refeição.
	 * @param daysOfWeek -> número inteiro entre (1 e 127).
     * @return Retorna a própria Meal, para implementação de fluent interface.
	 */
	public Meal setDaysOfWeek(int daysOfWeek) {
		if(daysOfWeek >= 1 && daysOfWeek <= 127) {
			this.daysOfWeek = daysOfWeek;
		}
		return this;
	}
	
	/**
	 * Método para formatar uma string como Objeto Time
	 * 
	 * @param hour - String da hora no formato "HH:mm"
	 * @return Um objeto do tipo Time
	 */
	private Time formatHour(String hour) {
		return Time.valueOf(LocalTime.parse(hour, DateTimeFormatter.ofPattern("HH:mm")));
	}
	
	/**
     * Método para obter a hora de um objeto Time
     * 
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
     * 
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
	 * 
	 * É preciso já estar vinculada à um cliente.
	 * @param food - objeto food a ser criada.
	 */
	public void createFood(Food food) {
		if(this.customer != null) {
			food.setMeal(this);
			food.save();
		}
	}
	
	/**
	 * Método que diz se a refeição está ativa.
	 * @return boolean
	 * <br><b>true</b> - se esta ativa.
	 * <br><b>false</b> - não está ativa.
	 * */
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
	
	/**
	 * Método para criar uma nova instância com o mesmo estado de outra instância.
	 * @param mold - objeto que servirá como molde do estado
	 * @return Um novo objeto do tipo Meal.
	 */
    public static Meal createCopyFrom(Meal mold) {
    	Meal copy = new Meal();
    	copy.copyFrom(mold);
    	return copy;
    }
	
    /**
     * Copia o estado de um objeto Meal para este objeto.
     * @param originObject -> objeto do tipo Meal que tem as informações de origem.
     * @return o mesmo objeto para implementar fluent interface.
     */
	@Override
	public Meal copyFrom(Meal originObject) {
		try {
			this.customer = originObject.customer;
			this.name = originObject.name;
			this.daysOfWeek = originObject.daysOfWeek;
			this.hour = originObject.hour;
			
			this.save();
			
			copyFoods(originObject, this);
			
		} catch (Exception e) {
			System.err.println("Não foi possível copiar o objeto.");
		}
		
		return this;
	}
	
    /**
     * Método que copia o estado deste objeto para outro.
     * @param destinyObject -> objeto que receberá o estado deste objeto.
	 * @return <b>boolean</b> - O resultado da operação.
	 * <br>Se <b>true</b> - O objeto foi copiado.
	 * <br>Se <b>false</b> - O objeto não pôde ser copiado.
     */
	@Override
	public boolean copyMeTo(Meal destinyObject) {
    	boolean res = true;
    	try {
        	destinyObject.customer= this.customer;
        	destinyObject.name = this.name;
        	destinyObject.daysOfWeek = this.daysOfWeek;
        	destinyObject.hour = this.hour;
        	
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
	 * <ul>
	 * <li>Se <b>true</b> - Todas as foods foram copiadas.
	 * <li>Se <b>false</b> - Houve algum erro e a cópia não foi bem sucedida.
	 * </ul>
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
	
	/**
	 * Representação como string do estado desse objeto.
	 * @return String -> string com o estado do objeto.
	 */
	@Override
	public String toString() {
		return "Meal: {"
				+ "\n    idMeal: " + idMeal + ","
				+ "\n    owner: " + (customer != null ? customer.name + " - id: " + customer.getId() : "null") +", "
				+ "\n    daysOfWeek: " + daysOfWeek + ", "
				+ "\n    name: " + name + ", "
				+ "\n    active: " + (deactivatedAt == null? false: true) + ","
				+ "\n    hour: " + hour + ", "
				+ "\n    createdAt: " + createdAt + ", "
				+ "\n    deactivatedAt: "+ deactivatedAt
				+ "\n}";
	}
	
	/**
	 * Método para retornar uma visão pequena do objeto.
	 * 
	 * @return String contendo apenas alguns dos atributos mais importantes
	 */
	public String smallInfo() {
		return "{id: " + idMeal + ", name: \"" + name+ "\", owner: \"" + (customer != null ? customer.name:"null") + "\"}";
	}
	
	
}
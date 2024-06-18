package model.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
import model.dao.FoodDAO;
import utils.interfaces.ICopy;

/**
 * Entidade que representa a associação entre uma entidade Meal e uma entidade Aliment.
 * Extende as funcionalidades básicas de persistência da classe FoodDAO e implementa a interface ICopy.
 */
@Entity
@Table(name = "Foods")
public class Food extends FoodDAO implements ICopy<Food> {
	
	/**
	 * Id associado a esse objeto no banco de dados
	 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idFood")
    public Integer idFood;

    /**
     * Refeição à qual o alimento estará associado.
     */
    @ManyToOne
    @JoinColumn(name = "idMeal")
    public Meal meal;

    /**
     * Alimento básico associado à food.
     */
    @ManyToOne
    @JoinColumn(name = "idAliment")
    public Aliment aliment;

    /**
     * Quantidade do alimento.
     */
    @Column(name = "food_quantity")
    public Double quantity;

    /**
     * Unidade de medida do alimento.
     */
    @Column(name = "food_unityQt")
    public String unityQt;

    /**
     * Data e hora de criação da food.
     */
    @Column(name = "food_createdAt")
    private LocalDateTime createdAt;

    /**
     * Data e hora de desativação da food.
     */
    @Column(name = "food_deactivatedAt")
    private LocalDateTime deactivatedAt;

	/**
	 * Construtor para criar uma instância de Food com parâmetros.
	 * 
	 * @param meal     Recebe uma Refeição (da Classe Meal) a qual essa Comida faz parte.
	 * @param aliment  Recebe um Alimento (da Classe Aliment) que forma essa Comida.
	 * @param quantity Recebe a quantidade (kg/ml) dessa Comida.
	 * @param unityQt  Recebe a unidade dessa Comida.
	 */
    public Food(Meal meal, Aliment aliment, Double quantity, String unityQt) {
    	super();
        this.meal = meal;
        this.aliment = aliment;
        this.quantity = quantity;
        this.unityQt = unityQt;
    }
    
	/**
	 * Construtor padrão.
	 */
    public Food() {
    	this(null, null, null, null);
    }
    
	/**
	 * Obtém o id deste objeto no banco de dados
	 * @return integer -> id no banco de dados
	 */
    @Override
    public Integer getId() {
    	return this.idFood;
    }
    
    /**
     * Obtém a data e hora de criação do registro
     * @return LocalDateTime -> data e hora de criação
     */
    public LocalDateTime getCreationDateTime() {
    	return this.createdAt;
    }
    
    /**
     * Obtém a data de criação do registro
     * @return LocalDate -> data de criação
     */
    public LocalDate getCreationDate() {
    	return this.createdAt.toLocalDate();
    }
    
    /**
     * Método para definir o alimento associado a food.
     *
     * @param aliment O alimento a ser associado.
     * @return Retorna a própria Food, para implementação de fluent interface.
     */
    public Food setAliment(Aliment aliment) {
    	this.aliment = aliment;
    	return this;
    }
    
    /**
     * Método para definir a Meal associada a food.
     *
     * @param meal A refeição a ser associada.
     * @return Retorna a própria Food, para implementação de fluent interface.
     */
    public Food setMeal(Meal meal) {
    	this.meal = meal;
    	return this;
    }

	/**
     * Método para definir a quantidade de food.
     *
     * @param quantity A quantidade.
     * @return Retorna a própria Food, para implementação de fluent interface.
     */
    public Food setQuantity(double quantity) {
    	this.quantity = quantity;
    	return this;
    }
    
	/**
     * Método para definir a quantidade de food.
     *
     * @param quantity A quantidade, como inteiro.
     * @return Retorna a própria Food, para implementação de fluent interface.
     */
    public Food setQuantity(int quantity) {
    	this.quantity = (double) quantity;
    	return this;
    }
    
    /**
     * Método para definir a unidade de medida de food.
     *
     * @param unityQt A unidade de medida de food.
     * @return Retorna a própria Food, para implementação de fluent interface.
     */
    public Food setUnityQt(String unityQt) {
    	this.unityQt = unityQt;
    	return this;
    }
    
    /**
     * Método para recuperar as subfoods associados a food.
     *
     * @return Lista de subfoods.
     */
    public List<SubFood> getSubFoods() {
    	return SubFood.findAllByFoodPK(this.idFood);
    }
    
    /**
     * Método para criar uma subfood associada a essa food.
     * @param sub -> SubFood a ser criada.
     */
    public void createSubFood(SubFood sub) {
    	if(this.meal != null) {
    		sub.setFood(this);
    		sub.save();
    	}
    }
    
    /**
     * Método para adicionar ao objeto, a data e horário atual de registro.
     */
    @PrePersist
    private void prePersist() {
        if(createdAt == null) createdAt = LocalDateTime.now();
    }
    
	/**
	 * Reimplementação de Delete para não excluir o registro e sim desativá-lo
	 */
	@Override
	public boolean delete() {
		this.deactivatedAt = LocalDateTime.now();
		return this.save();
	}
	
	/**
	 * Método para criar uma nova instância com o mesmo estado de outra instância.
	 * @param mold - objeto que servirá como molde do estado
	 * @return Um novo objeto do tipo Food.
	 */
    public static Food createCopyFrom(Food mold) {
    	Food copy = new Food();
    	copy.copyFrom(mold);
    	return copy;
    }
    
    /**
     * Copia o estado de um objeto Food para este objeto.
     * @param originObject -> objeto do tipo food que tem as informações de origem.
     * @return o mesmo objeto para implementar fluent interface.
     */
    @Override
    public Food copyFrom(Food originObject) {
    	try {
        	this.meal = originObject.meal;
        	this.aliment = originObject.aliment;
        	this.quantity = originObject.quantity;
        	this.unityQt = originObject.unityQt;
        	
        	this.save();
        	
        	copySubFoods(originObject, this);
        	
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
    public boolean copyMeTo(Food destinyObject) {
    	boolean res = true;
    	try {
        	destinyObject.meal= this.meal;
        	destinyObject.aliment = this.aliment;
        	destinyObject.quantity = this.quantity;
        	destinyObject.unityQt = this.unityQt;
        	
        	if(destinyObject.idFood == null) destinyObject.save();
        	
        	copySubFoods(this, destinyObject);
        	
		} catch (Exception e) {
			System.err.println("Não foi possível copiar o objeto.");
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
	 * <li>Se <b>true</b> - Todas as subfoods foram copiadas.
	 * <li>Se <b>false</b> - Houve algum erro e a cópia não foi bem sucedida.
	 * </ul>
	 */
	private boolean copySubFoods(Food origin, Food destiny) {
		boolean res = true;
		int toCopy = origin.getSubFoods().size();
		int totalCopied = 0;
		try {
			for(SubFood subFood: origin.getSubFoods()) { //Itera sobre as subFoods da origin
				try {
					SubFood copiedSubFood = SubFood.createCopyFrom(subFood); //Copia a subFood da iteração
					copiedSubFood.food = destiny; //Salva a food copiada na destiny
					copiedSubFood.save();
					totalCopied++;
				} catch (Exception e) {
				}

			}
		} catch (Exception e) {
			res = false;
		}
		
		if(toCopy != totalCopied) res = false; //Verificação adicional para saber se todas as subFoods foram copiadas.
		
		return res;
	}

	/**
	 * Representação como string do estado desse objeto.
	 * @return String -> string com o estado do objeto.
	 */
    @Override
    public String toString() {
    	return "Food: {"
    			+ "\n    idFood: "+idFood + ", "
    			+ "\n    meal_smallInfo: " + (meal != null? meal.smallInfo() : "null")+","
				+ "\n    aliment_smallInfo: " + aliment.smallInfo()+","
				+ "\n    quantity: "+ quantity + ","
				+ "\n    unityQt: "+unityQt + ","
				+ "\n}";
    }
    
    /**
	 * Método para retornar uma visão pequena do objeto.
	 * 
	 * @return String contendo apenas alguns dos atributos mais importantes
	 */
    public String smallInfo() {
    	return "{id: "+idFood+", meal.id: "+(meal != null? meal.idMeal : "null")+ ", aliment: "+aliment.smallInfo()+"}";
    }

}
package model.entities;

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

@Entity
@Table(name = "Foods")
public class Food extends FoodDAO implements ICopy<Food> {	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "idFood")
	    public Integer idFood;

	    @ManyToOne
	    @JoinColumn(name = "idMeal")
	    public Meal meal;

	    @ManyToOne
	    @JoinColumn(name = "idAliment")
	    public Aliment aliment;

	    @Column(name = "food_quantity")
	    public Double quantity;

	    @Column(name = "food_unityQt")
	    public String unityQt;

	    @Column(name = "food_obs")
	    public String obs;
	    
		@Column(name = "food_createdAt")
		private LocalDateTime createdAt;

		@Column(name = "food_deactivatedAt")
		private LocalDateTime deactivatedAt;

	/**
	 * 
	 * @param idFood Recebe o Identificador(Id) da Comida
	 * @param meal Recebe uma Refeição (da Classe Meal) a qual essa Comida faz parte
	 * @param aliment Recebe um Alimento (da Classe Aliment) que forma essa Comida
	 * @param quantity Recebe a quantidade (kg/ml) dessa Comida
	 * @param unityQt Recebe a unidade dessa Comida
	 * @param obs Recebe as observações/anotações dessa Comida
	 */
    public Food(Meal meal, Aliment aliment, Double quantity, String unityQt, String obs) {
    	super();
        this.meal = meal;
        this.aliment = aliment;
        this.quantity = quantity;
        this.unityQt = unityQt;
        this.obs = obs;
    }
    
    public Food() {
    	this(null, null, null, null, null);
    }
    
    @Override
    public Integer getId() {
    	return this.idFood;
    }
    
    public Food setAliment(Aliment aliment) {
    	this.aliment = aliment;
    	return this;
    }
    
    public Food setMeal(Meal meal) {
    	this.meal = meal;
    	return this;
    }
    
    public Food setQuantity(Double quantity) {
    	this.quantity = quantity;
    	return this;
    }
    
    public Food setQuantity(Integer quantity) {
    	this.quantity = (double) quantity;
    	return this;
    }
    
    public Food setUnityQt(String unityQt) {
    	this.unityQt = unityQt;
    	return this;
    }
    
    public List<SubFood> getSubFoods() {
    	return SubFood.findAllByFoodPK(this.idFood);
    }
    
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
	
    public static Food createCopyFrom(Food mold) {
    	Food copy = new Food();
    	copy.copyFrom(mold);
    	return copy;
    }
    
    @Override
    public Food copyFrom(Food originObject) {
    	try {
        	this.meal = originObject.meal;
        	this.aliment = originObject.aliment;
        	this.quantity = originObject.quantity;
        	this.unityQt = originObject.unityQt;
        	this.obs = originObject.obs;
        	
        	this.save();
        	
        	copySubFoods(originObject, this);
        	
		} catch (Exception e) {
			System.err.println("Não foi possível copiar o objeto.");
		}

    	return this;
    }
    
    @Override
    public boolean copyMeTo(Food destinyObject) {
    	boolean res = true;
    	try {
        	destinyObject.meal= this.meal;
        	destinyObject.aliment = this.aliment;
        	destinyObject.quantity = this.quantity;
        	destinyObject.unityQt = this.unityQt;
        	destinyObject.obs = this.obs;
        	
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
	 * <li>Se <b>true</b> - Todas as foods foram copiadas.
	 * <li>Se <b>false</b> - Houve algum erro e a cópia não foi bem sucedida.
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

    @Override
    public String toString() {
    	return "Food: {"
    			+ "\n    idFood: "+idFood + ", "
    			+ "\n    meal_smallInfo: " + (meal != null? meal.smallInfo() : "null")+","
				+ "\n    aliment_smallInfo: " + aliment.smallInfo()+","
				+ "\n    quantity: "+ quantity + ","
				+ "\n    unityQt: "+unityQt + ","
				+ "\n    obs: " + (obs != null? "\"" + obs +"\"": obs) 
				+ "\n}";
    }
    
    public String smallInfo() {
    	return "{id: "+idFood+", meal.id: "+(meal != null? meal.idMeal : "null")+ ", aliment: "+aliment.smallInfo()+"}";
    }

}
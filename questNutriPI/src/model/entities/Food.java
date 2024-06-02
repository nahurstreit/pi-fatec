package model.entities;

import java.time.LocalDate;
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

@Entity
@Table(name = "Foods")
public class Food extends FoodDAO {	
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
		private LocalDate createdAt;

		@Column(name = "food_deactivatedAt")
		private LocalDate deactivatedAt;

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
        if(createdAt == null) createdAt = LocalDate.now();
    }
    
	/**
	 * Reimplementação de Delete para não excluir o registro e sim desativá-lo
	 */
	@Override
	public boolean delete() {
		this.deactivatedAt = LocalDate.now();
		return this.save();
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
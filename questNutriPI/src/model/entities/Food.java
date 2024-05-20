package model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	    public Float quantity;

	    @Column(name = "food_unityQt")
	    public String unityQt;

	    @Column(name = "food_obs")
	    public String obs;

	/**
	 * 
	 * @param idFood Recebe o Identificador(Id) da Comida
	 * @param meal Recebe uma Refeição (da Classe Meal) a qual essa Comida faz parte
	 * @param aliment Recebe um Alimento (da Classe Aliment) que forma essa Comida
	 * @param quantity Recebe a quantidade (kg/ml) dessa Comida
	 * @param unityQt Recebe a unidade dessa Comida
	 * @param obs Recebe as observações/anotações dessa Comida
	 */
    public Food(Integer idFood, Meal meal, Aliment aliment, Float quantity, String unityQt,
            String obs) {
    	super();
        this.idFood = idFood;
        this.meal = meal;
        this.aliment = aliment;
        this.quantity = quantity;
        this.unityQt = unityQt;
        this.obs = obs;
    }
    
    public Food() {
    	this(null, null, null, null, null, null);
    }

    @Override
    public String toString() {
    	return "Food: {"
    			+ "\n    idFood: "+idFood + ", "
    			+ "\n    meal_smallInfo: " + meal.smallInfo()+","
				+ "\n    aliment_smallInfo: " + aliment.smallInfo()+","
				+ "\n    quantity: "+ quantity + ","
				+ "\n    unityQt: "+unityQt + ","
				+ "\n    obs: " + (obs != null? "\"" + obs +"\"": obs) 
				+ "\n}";
    }
    
    public String smallInfo() {
    	return "{id: "+idFood+", meal.id: "+meal.idMeal + ", aliment: "+aliment.smallInfo()+"}";
    }

}
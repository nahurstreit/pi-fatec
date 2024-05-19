package model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import model.dao.SubFoodDAO;

@Entity
@Table(name = "SubFoods")
public class SubFood extends SubFoodDAO{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSubFood")
    public Integer idSubFood;

    @ManyToOne
    @JoinColumn(name = "idFood")
    public Food food;

    @ManyToOne
    @JoinColumn(name = "idAliment")
    public Aliment aliment;

    @Column(name = "subFood_quantity")
    public Float quantity;

    @Column(name = "subFood_unityQt")
    public String unityQt;

    @Column(name = "subFood_obs")
    public String obs;


    public SubFood(Integer idSubFood, Food food, Aliment aliment, Float quantity, String unityQt, String obs) {
        super();
    	this.idSubFood = idSubFood;
        this.food = food;
        this.aliment = aliment;
        this.quantity = quantity;
        this.unityQt = unityQt;
        this.obs = obs;
    }

    public SubFood() {
    	this(null, null, null, null, null, null);
    }
    
    @Override
    public String toString() {
    	return "SubFood: {"
    			+ "\n    idSubFood: "+idSubFood + ", "
    			+ "\n    food_smallInfo: " + food.smallInfo()+","
				+ "\n    aliment_smallInfo: " + aliment.smallInfo()+","
				+ "\n    quantity: "+ quantity + ","
				+ "\n    unityQt: "+unityQt + ","
				+ "\n    obs: " + (obs != null? "\"" + obs +"\"": obs) 
				+ "\n}";
    }
    
    public String smallInfo() {
    	return "{id: "+idSubFood+", food.id: "+food.idFood + ", aliment: "+aliment.smallInfo()+"}";
    }
}
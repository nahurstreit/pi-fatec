package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Foods")
public class Food {
    
	public SubFood[] subFoods = null;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idFood")
	public Integer idFood;

	@Column(name = "idMeal")
	public Integer idMeal;

    @Column(name = "idAliment")
    public Integer idAliment;

    @Column(name = "food_quantity")
    public Float foodQuantity;

    @Column(name = "food_unityQt")
    public String foodUnityQt;

    @Column(name = "food_obs")
    public String foodObs;

    public Food(Integer idFood, Integer idMeal, Integer idAliment, Float foodQuantity, String foodUnityQt,
            String foodObs) {
        this.idFood = idFood;
        this.idMeal = idMeal;
        this.idAliment = idAliment;
        this.foodQuantity = foodQuantity;
        this.foodUnityQt = foodUnityQt;
        this.foodObs = foodObs;
    }
    
    public Food() {
    	this(null, null, null, null, null, null);
    }
}
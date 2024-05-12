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
    public Float quantity;

    @Column(name = "food_unityQt")
    public String unityQt;

    @Column(name = "food_obs")
    public String obs;

    public Food(Integer idFood, Integer idMeal, Integer idAliment, Float quantity, String unityQt,
            String obs) {
        this.idFood = idFood;
        this.idMeal = idMeal;
        this.idAliment = idAliment;
        this.quantity = quantity;
        this.unityQt = unityQt;
        this.obs = obs;
    }
    
    public Food() {
    	this(null, null, null, null, null, null);
    }
}
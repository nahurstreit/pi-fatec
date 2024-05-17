package model.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Foods")
public class Food{	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "idFood")
	    public Integer idFood;

	    @ManyToOne
	    @JoinColumn(name = "idMeal") // Coluna que faz referência à refeição
	    public Meal meal;

	    @OneToMany
	    public List<SubFood> subFoods;

	    @Column(name = "idAliment")
	    public Integer idAliment;

	    @Column(name = "food_quantity")
	    public Float quantity;

	    @Column(name = "food_unityQt")
	    public String unityQt;

	    @Column(name = "food_obs")
	    public String obs;


    public Food(Integer idFood, Meal meal, Integer idAliment, Float quantity, String unityQt,
            String obs) {
        this.idFood = idFood;
        this.meal = meal;
        this.idAliment = idAliment;
        this.quantity = quantity;
        this.unityQt = unityQt;
        this.obs = obs;
    }
    
    public Food() {
    	this(null, null, null, null, null, null);
    }

    @Override
    public String toString() {
        return "Food [idFood=" + idFood + ", meal=" + meal + ", idAliment=" + idAliment + ", quantity=" + quantity
                + ", unityQt=" + unityQt + ", obs=" + obs + "]";
    }

}
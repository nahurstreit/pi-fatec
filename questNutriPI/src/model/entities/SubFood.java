package model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "SubFoods")
public class SubFood {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSubFood")
    public Integer idSubFood;

    @ManyToOne
    @JoinColumn(name = "idFood")
    public Food food;

    @Column(name = "idAliment")
    public Integer idAliment;

    @Column(name = "subFood_quantity")
    public Float quantity;

    @Column(name = "subFood_unityQt")
    public String unityQt;

    @Column(name = "subFood_obs")
    public String obs;


    public SubFood(Integer idSubFood, Integer idFood, Integer idAliment, Float quantity, String unityQt, String obs) {
        this.idSubFood = idSubFood;
        this.idAliment = idAliment;
        this.quantity = quantity;
        this.unityQt = unityQt;
        this.obs = obs;
    }

    public SubFood() {
    	this(null, null, null, null, null, null);
    }
}
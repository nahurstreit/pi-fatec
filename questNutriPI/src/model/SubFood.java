package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "SubFoods")
public class SubFood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSubFood")
    public Integer idSubFood;

    @Column (name = "idFood")
    public Integer idFood;

    @Column(name = "idAliment")
    public Integer idAliment;
    
    public Aliment mainAliment = null;

    @Column(name = "subFood_quantity")
    public Float quantity;

    @Column(name = "subFood_unityQt")
    public String unityQt;

    @Column(name = "subFood_obs")
    public String obs;

    public SubFood(Integer idSubFood, Integer idFood, Integer idAliment, Float quantity, String unityQt, String obs) {
        this.idSubFood = idSubFood;
        this.idFood = idFood;
        this.idAliment = idAliment;
        this.quantity = quantity;
        this.unityQt = unityQt;
        this.obs = obs;
    }

    public SubFood() {
    	this(null, null, null, null, null, null);
    }
}
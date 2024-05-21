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

    /**
     * 
     * @param idSubFood Recebe o Identificador(Id) do Comida Substituta
     * @param food Recebe a Comida (da Classe Food) que poderá ser substituída pela Comida Substituta
     * @param aliment Reccebe o Alimento (da Classe Aliment) que irá compor a Comida Substituta
     * @param quantity Recebe a Quantidade (kg/ml) da Comida Substituta
     * @param unityQt Recebe a Unidade da Comida Substituta
     * @param obs Recebe as Observações da Comida Substituta
     */
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
    
    public SubFood setFood(Food food) {
    	if(this.food == null) this.food = food;
    	return this;
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
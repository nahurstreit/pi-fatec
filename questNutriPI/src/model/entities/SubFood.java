package model.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import model.dao.SubFoodDAO;
import utils.interfaces.ICopy;

@Entity
@Table(name = "SubFoods")
public class SubFood extends SubFoodDAO implements ICopy<SubFood>{
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
    public Double quantity;

    @Column(name = "subFood_unityQt")
    public String unityQt;
    
	@Column(name = "subFood_createdAt")
	private LocalDateTime createdAt;

	@Column(name = "subFood_deactivatedAt")
	private LocalDateTime deactivatedAt;
    

    /**
     * 
     * @param idSubFood Recebe o Identificador(Id) do Comida Substituta
     * @param food Recebe a Comida (da Classe Food) que poderá ser substituída pela Comida Substituta
     * @param aliment Reccebe o Alimento (da Classe Aliment) que irá compor a Comida Substituta
     * @param quantity Recebe a Quantidade (kg/ml) da Comida Substituta
     * @param unityQt Recebe a Unidade da Comida Substituta
     * @param obs Recebe as Observações da Comida Substituta
     */
    public SubFood(Integer idSubFood, Food food, Aliment aliment, Double quantity, String unityQt) {
        super();
    	this.idSubFood = idSubFood;
        this.food = food;
        this.aliment = aliment;
        this.quantity = quantity;
        this.unityQt = unityQt;
    }

    public SubFood() {
    	this(null, null, null, null, null);
    }
    
    @Override
    public Integer getId() {
    	return this.idSubFood;
    }
    
    public SubFood setFood(Food food) {
    	if(this.food == null) this.food = food;
    	return this;
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
	
    public static SubFood createCopyFrom(SubFood mold) {
    	SubFood copy = new SubFood();
    	copy.copyFrom(mold);
    	return copy;
    }
	
	@Override
	public SubFood copyFrom(SubFood originObject) {
    	try {
        	this.food = originObject.food;
        	this.aliment = originObject.aliment;
        	this.quantity = originObject.quantity;
        	this.unityQt = originObject.unityQt;
		} catch (Exception e) {
			System.err.println("Não foi possível copiar o objeto.");
		}
    	
    	return this;
	}
	
	@Override
	public boolean copyMeTo(SubFood destinyObject) {
    	boolean res = true;
    	try {
        	destinyObject.food= this.food;
        	destinyObject.aliment = this.aliment;
        	destinyObject.quantity = this.quantity;
        	destinyObject.unityQt = this.unityQt;
		} catch (Exception e) {
			System.err.println("Não foi possível copiar o objeto.");
			res = false;
		}

    	return res;
	}
    
    @Override
    public String toString() {
    	return "SubFood: {"
    			+ "\n    idSubFood: "+idSubFood + ", "
    			+ "\n    food_smallInfo: " + food.smallInfo()+","
				+ "\n    aliment_smallInfo: " + aliment.smallInfo()+","
				+ "\n    quantity: "+ quantity + ","
				+ "\n    unityQt: "+unityQt + ","
				+ "\n}";
    }
    
    public String smallInfo() {
    	return "{id: "+idSubFood+", food.id: "+food.idFood + ", aliment: "+aliment.smallInfo()+"}";
    }
}
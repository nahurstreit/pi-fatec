/**
 * Package que contém as entidades do sistema.
 */
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
/**
 * Entidade que faz a ligação de um outro Aliment a uma Food, representando uma opção de substituição.
 * Extende as funcionalidades básicas de persistência da classe SubFoodDAO e implementa a interface ICopy.
 */
@Entity
@Table(name = "SubFoods")
public class SubFood extends SubFoodDAO implements ICopy<SubFood>{
	
	/**
	 * Id associado a esse objeto no banco de dados
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSubFood")
    public Integer idSubFood;
	
    /**
     * Food à qual a SubFood estará associada.
     */
    @ManyToOne
    @JoinColumn(name = "idFood")
    public Food food;

    /**
     * Alimento básico associado à subFood.
     */
    @ManyToOne
    @JoinColumn(name = "idAliment")
    public Aliment aliment;

    /**
     * Quantidade do alimento.
     */
    @Column(name = "subFood_quantity")
    public Double quantity;

    /**
     * Unidade de medida do alimento.
     */
    @Column(name = "subFood_unityQt")
    public String unityQt;
    
    /**
     * Data e hora de criação da subfood.
     */
	@Column(name = "subFood_createdAt")
	private LocalDateTime createdAt;

    /**
     * Data e hora de desativação da subfood.
     */
	@Column(name = "subFood_deactivatedAt")
	private LocalDateTime deactivatedAt;
    

    /**
     * Método construtor de uma subfood
     * @param food Recebe a Comida (da Classe Food) que poderá ser substituída pela Comida Substituta
     * @param aliment Reccebe o Alimento (da Classe Aliment) que irá compor a Comida Substituta
     * @param quantity Recebe a Quantidade (kg/ml) da Comida Substituta
     * @param unityQt Recebe a Unidade da Comida Substituta
     */
    public SubFood(Food food, Aliment aliment, Double quantity, String unityQt) {
        super();
        this.food = food;
        this.aliment = aliment;
        this.quantity = quantity;
        this.unityQt = unityQt;
    }

    /**
     * Construtor padrão.
     */
    public SubFood() {
    	this(null, null, null, null);
    }
    
	/**
	 * Obtém o id deste objeto no banco de dados
	 * @return integer -> id no banco de dados
	 */
    @Override
    public Integer getId() {
    	return this.idSubFood;
    }
    
    /**
     * Método para definir a Food associada a esta SubFood.
     * @param food a ser associada.
     * @return Retorna a própria SubFood, para implementação de fluent interface.
     */
    public SubFood setFood(Food food) {
    	if(this.food == null) this.food = food;
    	return this;
    }
    
    /**
     * Método para definir o alimento associado a SubFood.
     *
     * @param aliment O alimento a ser associado.
     * @return Retorna o próprio objeto para implementação de fluent interface.
     */
    public SubFood setAliment(Aliment aliment) {
    	this.aliment = aliment;
    	return this;
    }
    
	/**
     * Método para definir a quantidade de food.
     *
     * @param quantity A quantidade.
     * @return Retorna o próprio objeto para implementação de fluent interface.
     */
    public SubFood setQuantity(double quantity) {
    	this.quantity = quantity;
    	return this;
    }
    
    /**
     * Método para definir a unidade de medida de subfood.
     *
     * @param unityQt A unidade de medida de subfood.
     * @return Retorna o próprio objeto para implementação de fluent interface.
     */
    public SubFood setUnityQt(String unityQt) {
    	this.unityQt = unityQt;
    	return this;
    }
    
	/**
	 * Método que diz se a subFood está ativa.
	 * @return boolean
	 * <br><b>true</b> - se esta ativa.
	 * <br><b>false</b> - não está ativa.
	 * */
    public boolean isActive() {
    	return this.deactivatedAt == null;
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
	
	/**
	 * Método para criar uma nova instância com o mesmo estado de outra instância.
	 * @param mold - objeto que servirá como molde do estado
	 * @return Um novo objeto do tipo SubFood.
	 */
    public static SubFood createCopyFrom(SubFood mold) {
    	SubFood copy = new SubFood();
    	copy.copyFrom(mold);
    	return copy;
    }
	
    /**
     * Copia o estado de um objeto SubFood para este objeto.
     * @param originObject -> objeto do tipo food que tem as informações de origem.
     * @return o mesmo objeto para implementar fluent interface.
     */
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
	
    /**
     * Método que copia o estado deste objeto para outro.
     * @param destinyObject -> objeto que receberá o estado deste objeto.
	 * @return <b>boolean</b> - O resultado da operação.
	 * <br>Se <b>true</b> - O objeto foi copiado.
	 * <br>Se <b>false</b> - O objeto não pôde ser copiado.
     */
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
    
	/**
	 * Representação como string do estado desse objeto.
	 * @return String -> string com o estado do objeto.
	 */
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
    
    /**
	 * Método para retornar uma visão pequena do objeto.
	 * 
	 * @return String contendo apenas alguns dos atributos mais importantes
	 */
    public String smallInfo() {
    	return "{id: "+idSubFood+", food.id: "+food.idFood + ", aliment: "+aliment.smallInfo()+"}";
    }
}
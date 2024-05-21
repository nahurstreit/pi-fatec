package model.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import model.dao.WeightDAO;

@Entity
@Table(name = "Weights")
public class Weight extends WeightDAO{
    
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idWeight")
	public Integer idWeight;

	@ManyToOne
	@JoinColumn(name = "idCustomer")
	public Customer customer;

	@Column(name = "wgt_value")
	public Double value;
	
	@Column(name = "wgt_dateRegister")
	private Date dateRegister;

	/**
	 * Método construtor da classe Weight
	 * @param customer Recebe o Cliente (da Classe Customer) que possui esse Peso
	 * @param value Recebe o valor em kg do Peso
	 * @param dateRegister Recebe a Data de Registro do Peso do Cliente
	 */
    public Weight(Customer customer, Double value) {
        this.customer = customer;
        this.value = value;
    }
    
    public Weight() {
    	this(null, null);
    }
    
    /**
     * Método para adicionar ao objeto, a data e horário atual de registro.
     */
    @PrePersist
    private void prePersist() {
        if(dateRegister == null) dateRegister = new Date();
    }

	@Override
	public String toString() {
		return "Weight: {"
	+ "\n    idWeight:" + idWeight 
	+ "\n    customer:" + customer
	+ "\n    wgtValue:" + value
	+ "\n    wgtDateRegister:" + dateRegister
	+ "\n}";
	}
    
	/**
	 * Método para retornar uma visão pequena do objeto.
	 * @return String contendo apenas alguns dos atributos mais importantes
	 */
	public String smallInfo() {
		return "{id: "+idWeight+", value: "+value+"}";

	}
}
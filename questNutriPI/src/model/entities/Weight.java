package model.entities;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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

/**
 * Entidade que representa um peso de um clinete.
 * Extende as funcionalidades básicas de persistência da classe WeightDAO.
 */
@Entity
@Table(name = "Weights")
public class Weight extends WeightDAO {
    
	/**
	 * Id associado a esse objeto no banco de dados
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idWeight")
	public Integer idWeight;

	/**
	 * Cliente associado a esse peso.
	 */
	@ManyToOne
	@JoinColumn(name = "idCustomer")
	public Customer customer;

	/**
	 * Peso do cliente.
	 */
	@Column(name = "wgt_value")
	public Double value;
	
	/**
	 * Data de registro daquele peso.
	 */
	@Column(name = "wgt_dateRegister")
	private Date dateRegister;

	/**
	 * Método construtor da classe Weight
	 * @param customer Recebe o Cliente (da Classe Customer) que possui esse Peso
	 * @param value Recebe o valor em kg do Peso
	 */
    public Weight(Customer customer, Double value) {
        this.customer = customer;
        this.value = value;
    }
    
	/**
	 * Constructor padrão
	 */
    public Weight() {
    	this(null, null);
    }
    
	/**
	 * Obtém o id deste objeto no banco de dados
	 * @return integer -> id no banco de dados
	 */
    @Override
    public Integer getId() {
    	return this.idWeight;
    }
    
    /**
     * Método para adicionar ao objeto, a data e horário atual de registro.
     */
    @PrePersist
    private void prePersist() {
        if(dateRegister == null) dateRegister = new Date();
    }
    
    /**
     * Método para retornar a data de criação do registro no banco de dados.
     * @return String da data no formato dd/MM/yyyy HH:mm:ss
     */
    public String getRegisterDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime localDateTime = dateRegister.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        return localDateTime.format(formatter);
    }
    
	/**
	 * Representação como string do estado desse objeto.
	 * @return String -> string com o estado do objeto.
	 */
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
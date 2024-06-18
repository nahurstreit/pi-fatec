/**
 * Package que contém as entidades do sistema.
 */
package model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import model.dao.AddressDAO;

/**
 * Entidade que representa um endereço.
 * Herda funcionalidades básicas de acesso a dados de endereço.
 */
@Entity
@Table(name = "Addresses")
public class Address extends AddressDAO {
	
	/**
	 * Id associado a esse objeto no banco de dados
	 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAddress")
    public Integer idAddress;

    /** Rua do endereço. */
    @Column(name = "addr_street")
    public String street;

    /** Número do endereço. */
    @Column(name = "addr_num")
    public Integer number;

    /** Complemento do endereço. */
    @Column(name = "addr_comp")
    public String comp;

    /** CEP do endereço. */
    @Column(name = "addr_cep")
    public String cep;

    /** Bairro do endereço. */
    @Column(name = "addr_neighborhood")
    public String hood;

    /** Cidade do endereço. */
    @Column(name = "addr_city")
    public String city;

    /** Estado do endereço. */
    @Column(name = "addr_state")
    public String state;
    
	/**
	 * Constructor de endereço.
	 * @param street Recebe a Rua do Endereço
	 * @param number Recebe o Número do Endereços
	 * @param comp Recebe o Complemento do Endereço
	 * @param cep Recebe o CEP do Endereço
	 * @param hood Recebe o Bairro do Endereço
	 * @param city Recebe a Cidade do Endereeço
	 * @param state Recebe o Estado do Endereço
	 */
	public Address(String street, Integer number, String comp, String cep,
			String hood, String city, String state) {
		this.street = street;
		this.number = number;
		this.comp = comp;
		this.cep = cep;
		this.hood = hood;
		this.city = city;
		this.state = state;
	}

	/**
	 * Constructor padrão
	 */
	public Address() {
		this(null, null, null, null, null, null, null);
	}

	/**
	 * Método para recuperar o ID do Endereço.
	 *
	 * @return Retorna o ID do Endereço.
	 */
	public Integer getId() {
		return idAddress;
	}

	 /**
	   * Método para recuperar a rua do Endereço.
	   *
	   * @return Retorna a rua do Endereço.
	   */
	public String getStreet() {
		return street;
	}

	 /**
	   * Método para recuperar o número do Endereço.
	   *
	   * @return Retorna o número do Endereço.
	   */
	public Integer getNumber() {
		return number;
	}

	 /**
	   * Método para recuperar o complemento do Endereço.
	   *
	   * @return Retorna o complemento do Endereço.
	   */
	public String getComp() {
		return comp;
	}

	/**
	   * Método para recuperar o CEP do Endereço.
	   *
	   * @return Retorna o CEP do Endereço.
	   */
	public String getCep() {
		return cep;
	}

	/**
	   * Método para recuperar o bairro do Endereço.
	   *
	   * @return Retorna o bairro do Endereço.
	   */
	public String getHood() {
		return hood;
	}

	 /**
	   * Método para recuperar a cidade do Endereço.
	   *
	   * @return Retorna a cidade do Endereço.
	   */
	public String getCity() {
		return city;
	}

	/**
	   * Método para recuperar o estado do Endereço.
	   *
	   * @return Retorna o estado do Endereço.
	   */
	public String getState() {
		return state;
	}

	/**
	   * Método para setar a rua do Endereço.
	   *
	   * @param street - String da rua.
	   * @return Retorna o próprio Address, para implementação de fluent interface.
	   */
	public Address setStreet(String street) {
		this.street = street;
		return this;
	}

	/**
	   * Método para setar o número do Endereço.
	   *
	   * @param number - Integer do número.
	   * @return Retorna o próprio Address, para implementação de fluent interface.
	   */
	public Address setNumber(Integer number) {
		this.number = number;
		return this;
	}

	 /**
	   * Método para setar o complemento do Endereço.
	   *
	   * @param comp - String do complemento.
	   * @return Retorna o próprio Address, para implementação de fluent interface.
	   */
	public Address setComp(String comp) {
		this.comp = comp;
		return this;
	}

	 /**
	   * Método para setar o CEP do Endereço.
	   *
	   * @param cep - String do CEP.
	   * @return Retorna o próprio Address, para implementação de fluent interface.
	   */
	public Address setCep(String cep) {
		this.cep = cep.replaceAll("\\D", "");
		return this;
	}

	/**
	   * Método para setar o bairro do Endereço.
	   *
	   * @param hood - String do bairro.
	   * @return Retorna o próprio Address, para implementação de fluent interface.
	   */
	public Address setHood(String hood) {
		this.hood = hood;
		return this;
	}

	 /**
	   * Método para setar a cidade do Endereço.
	   *
	   * @param city - String da cidade.
	   * @return Retorna o próprio Address, para implementação de fluent interface.
	   */
	public Address setCity(String city) {
		this.city = city;
		return this;
	}

	/**
	   * Método para setar o estado do Endereço.
	   *
	   * @param state - String do estado.
	   * @return Retorna o próprio Address, para implementação de fluent interface.
	   */
	public Address setState(String state) {
		this.state = state;
		return this;
	}

	/**
	 * Representação como string do estado desse objeto.
	 * @return String -> string com o estado do objeto.
	 */
	@Override
	public String toString() {
		return "Address: {"
	+ "\n    idAddress:" + idAddress
	+ "\n    addrStreet:" + street
	+ "\n    addrNum:" + number
	+ "\n    addrComp:" + comp
	+ "\n    addrCep:" + cep
	+ "\n    addrNeighborhood:" + hood
	+ "\n    addrCity:" + city
	+ "\n    addrState:" + state
	+ "\n}";
	}
	
}
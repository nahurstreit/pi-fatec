package model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import model.dao.AddressDAO;

@Entity
@Table(name = "Addresses")
public class Address extends AddressDAO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idAddress")
	public Integer idAddress;

	@Column(name = "addr_street")
	public String street;

	@Column(name = "addr_num")
	
	public Integer number;

	@Column(name = "addr_comp")
	public String comp;

	@Column(name = "addr_cep")
	public String cep;

	@Column(name = "addr_neighborhood")
	public String hood;

	@Column(name = "addr_city")
	public String city;

	@Column(name = "addr_state")
	public String state;

	/**
	 * 
	 * @param idAddress Identificador(Id) do Endereço
	 * @param addrStreet Recebe a Rua do Endereço
	 * @param addrNum Recebe o Número do Endereços
	 * @param addrComp Recebe o Complemento do Endereço
	 * @param addrCep Recebe o CEP do Endereço
	 * @param addrNeighborhood Recebe o Bairro do Endereço
	 * @param addrCity Recebe a Cidade do Endereeço
	 * @param addrState Recebe o Estado do Endereço
	 */
	public Address(Integer idAddress, String street, Integer number, String comp, String cep,
			String hood, String city, String state) {
		this.idAddress = idAddress;
		this.street = street;
		this.number = number;
		this.comp = comp;
		this.cep = cep;
		this.hood = hood;
		this.city = city;
		this.state = state;
	}

	public Address() {
		this(null, null, null, null, null, null, null, null);
	}

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
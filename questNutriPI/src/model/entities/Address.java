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
	public String addrStreet;

	@Column(name = "addr_num")
	
	public Integer addrNum;

	@Column(name = "addr_comp")
	public String addrComp;

	@Column(name = "addr_cep")
	public String addrCep;

	@Column(name = "addr_neighborhood")
	public String addrNeighborhood;

	@Column(name = "addr_city")
	public String addrCity;

	@Column(name = "addr_state")
	public String addrState;

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
	public Address(Integer idAddress, String addrStreet, Integer addrNum, String addrComp, String addrCep,
			String addrNeighborhood, String addrCity, String addrState) {
		this.idAddress = idAddress;
		this.addrStreet = addrStreet;
		this.addrNum = addrNum;
		this.addrComp = addrComp;
		this.addrCep = addrCep;
		this.addrNeighborhood = addrNeighborhood;
		this.addrCity = addrCity;
		this.addrState = addrState;
	}

	public Address() {
		this(null, null, null, null, null, null, null, null);
	}

	@Override
	public String toString() {
		return "Address: {"
	+ "\n    idAddress:" + idAddress
	+ "\n    addrStreet:" + addrStreet
	+ "\n    addrNum:" + addrNum
	+ "\n    addrComp:" + addrComp
	+ "\n    addrCep:" + addrCep
	+ "\n    addrNeighborhood:" + addrNeighborhood
	+ "\n    addrCity:" + addrCity
	+ "\n    addrState:" + addrState
	+ "\n}";
	}
	
}
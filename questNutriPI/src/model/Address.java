package model;

import java.io.Serializable;
import java.util.List;

import org.hibernate.annotations.Type;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Addresses")
public class Address implements Serializable {
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
	
	@OneToMany(mappedBy = "address")
	private List<Customers> customer;

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
}
package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Customers")

public class Customers {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idCustomer")
	public Integer idCustomer;

	@Column(name = "cust_createdAt")
	private String custCreatedAt;

	@Column(name = "cust_name")
	public String custName;

	@Column(name = "cust_email")
	public String custEmail;

	@Column(name = "cpf")
	private String cpf;

	@Column(name = "cust_cellphone")
	private String custCellphone;

	@Column(name = "cust_activityStatus")
	private String custActivityStatus;

	@Column(name = "cust_setKcal")
	private Float custSetKcal;

	@Column(name = "cust_height")
	private Float custHeight;

	@Column(name = "cust_birth")
	private String custBirth;

	@Column(name = "cust_gender")
	private String custGender;

	@Column(name = "idAddress")
	private Integer idAddress;
	
	private Address mainAddress = null;
	
	private Meal[] diet = null;

	public Customers(Integer idCustomer, String custCreatedAt, String custName, String custEmail, String cpf,
			String custCellphone, String custActivityStatus, Float custSetKcal, Float cusHheight, String custBirth,
			String custGender, Integer idAddress) {
	    this.idCustomer = idCustomer;
	    this.custCreatedAt = custCreatedAt;
	    this.custName = custName;
	    this.custEmail = custEmail;
	    this.cpf = cpf;
	    this.custCellphone = custCellphone;
	    this.custActivityStatus = custActivityStatus;
	    this.custSetKcal = custSetKcal;
	    this.custHeight = cusHheight;
	    this.custBirth = custBirth;
	    this.custGender = custGender;
	    this.idAddress = idAddress;
	}

	public Customers() {
		this(null, null, null, null, null, null, null, null, null, null, null, null);
	}

	public String getCustCreatedAt() {
		return custCreatedAt;
	}

	private void setCustCreatedAt(String custCreatedAt) {
		this.custCreatedAt = custCreatedAt;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCustCellphone() {
		return custCellphone;
	}

	public void setCustCellphone(String custCellphone) {
		this.custCellphone = custCellphone;
	}

	public String getCustActivityStatus() {
		return custActivityStatus;
	}

	public void setCustActivityStatus(String custActivityStatus) {
		this.custActivityStatus = custActivityStatus;
	}

	public Float getCustSetKcal() {
		return custSetKcal;
	}

	public void setCustSetKcal(Float custSetKcal) {
		this.custSetKcal = custSetKcal;
	}

	public Float getCustHeight() {
		return custHeight;
	}

	public void setCustHeight(Float custHeight) {
		this.custHeight = custHeight;
	}

	public String getCustBirth() {
		return custBirth;
	}

	public void setCustBirth(String custBirth) {
		this.custBirth = custBirth;
	}

	public String getCustGender() {
		return custGender;
	}

	public void setCustGender(String custGender) {
		this.custGender = custGender;
	}

	public Integer getIdAddress() {
		return idAddress;
	}

	public void setIdAddress(Integer idAddress) {
		this.idAddress = idAddress;
	}

	public Address getMainAddress() {
		return mainAddress;
	}

	public void setMainAddress(Address mainAddress) {
		this.mainAddress = mainAddress;
	}

	public Meal[] getDiet() {
		return diet;
	}

	public void setDiet(Meal[] diet) {
		this.diet = diet;
	}	
}

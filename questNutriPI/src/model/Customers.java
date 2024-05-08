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
	private Integer idCustomer;

	@Column(name = "cust_createdAt")
	private String cust_createdAt;

	@Column(name = "cust_name")
	private String cust_name;

	@Column(name = "cust_email")
	private String cust_email;

	@Column(name = "cpf")
	private String cpf;

	@Column(name = "cust_cellphone")
	private String cust_cellphone;

	@Column(name = "cust_activityStatus")
	private String cust_activityStatus;

	@Column(name = "cust_setKcal")
	private Float cust_setKcal;

	@Column(name = "cust_height")
	private Float cust_height;

	@Column(name = "cust_birth")
	private String cust_birth;

	@Column(name = "cust_gender")
	private String cust_gender;

	// @Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idAddress")
	private Integer idAddress;

	public Customers(Integer idCustomer, String cust_createdAt, String cust_name, String cust_email, String cpf,
			String cust_cellphone, String cust_activityStatus, Float cust_setKcal, Float cust_height, String cust_birth,
			String cust_gender, Integer idAddress) {
	    this.idCustomer = idCustomer;
	    this.cust_createdAt = cust_createdAt;
	    this.cust_name = cust_name;
	    this.cust_email = cust_email;
	    this.cpf = cpf;
	    this.cust_cellphone = cust_cellphone;
	    this.cust_activityStatus = cust_activityStatus;
	    this.cust_setKcal = cust_setKcal;
	    this.cust_height = cust_height;
	    this.cust_birth = cust_birth;
	    this.cust_gender = cust_gender;
	    this.idAddress = idAddress;
	}

	public Customers() {}

	public Integer getIdCustomer() {
		return idCustomer;
	}

	public void setIdCustomer(Integer idCustomer) {
		this.idCustomer = idCustomer;
	}

	public String getCust_createdAt() {
		return cust_createdAt;
	}

	public void setCust_createdAt(String cust_createdAt) {
		this.cust_createdAt = cust_createdAt;
	}

	public String getCust_name() {
		return cust_name;
	}

	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}

	public String getCust_email() {
		return cust_email;
	}

	public void setCust_email(String cust_email) {
		this.cust_email = cust_email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCust_cellphone() {
		return cust_cellphone;
	}

	public void setCust_cellphone(String cust_cellphone) {
		this.cust_cellphone = cust_cellphone;
	}

	public String getCust_activityStatus() {
		return cust_activityStatus;
	}

	public void setCust_activityStatus(String cust_activityStatus) {
		this.cust_activityStatus = cust_activityStatus;
	}

	public Float getCust_setKcal() {
		return cust_setKcal;
	}

	public void setCust_setKcal(Float cust_setKcal) {
		this.cust_setKcal = cust_setKcal;
	}

	public Float getCust_height() {
		return cust_height;
	}

	public void setCust_height(Float cust_height) {
		this.cust_height = cust_height;
	}

	public String getCust_birth() {
		return cust_birth;
	}

	public void setCust_birth(String cust_birth) {
		this.cust_birth = cust_birth;
	}

	public String getCust_gender() {
		return cust_gender;
	}

	public void setCust_gender(String cust_gender) {
		this.cust_gender = cust_gender;
	}

	public Integer getIdAddress() {
		return idAddress;
	}

	public void setIdAddress(Integer idAddress) {
		this.idAddress = idAddress;
	}

	@Override
	public String toString() {
	    return "idCustomer=" + this.getIdCustomer() 
	    + "\n" + "cust_createdAt=" + this.getCust_createdAt() 
	    + "\n" + "cust_name=" + this.getCust_name() 
	    + "\n" + "cust_email=" + this.getCust_email() 
	    + "\n" + "cpf=" + this.getCpf() 
	    + "\n" + "cust_cellphone=" + this.getCust_cellphone() 
	    + "\n" + "cust_activityStatus=" + this.getCust_activityStatus() 
	    + "\n" + "cust_setKcal=" + this.getCust_setKcal() 
	    + "\n" + "cust_height=" + this.getCust_height() 
	    + "\n" + "cust_birth=" + this.getCust_birth() 
	    + "\n" + "cust_gender=" + this.getCust_gender() 
	    + "\n" + "idAddress=" + this.getIdAddress();
	}

	
}

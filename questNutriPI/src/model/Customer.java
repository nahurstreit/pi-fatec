package model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Customers")

public class Customer {
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

	@ManyToOne
    @JoinColumn(name = "idAddress")
    private Address address;
	
	@OneToMany(mappedBy = "customer")
	private List<Meal> diet;

	public Customer(Integer idCustomer, String custCreatedAt, String custName, String custEmail, String cpf,
			String custCellphone, String custActivityStatus, Float custSetKcal, Float cusHheight, String custBirth,
			String custGender, Address address) {
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
	    this.address = address;
	}

	public Customer() {
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


	 public List<Meal> getDiet() {
	        return diet;
	    }
	 
	 public void setDiet(List<Meal> diet) {
	        this.diet = diet;
	    } 
}

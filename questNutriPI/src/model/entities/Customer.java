package model.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import model.dao.CustomerDAO;

@Entity
@Table(name = "Customers")

public class Customer extends CustomerDAO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idCustomer")
	public Integer idCustomer;

	@Column(name = "cust_createdAt")
	private String custCreatedAt;

	@Column(name = "cust_name")
	public String name;

	@Column(name = "cust_email")
	public String email;

	@Column(name = "cpf")
	private String cpf;

	@Column(name = "cust_cellphone")
	private String phoneNumber;

	@Column(name = "cust_activityStatus")
	private String activityStatus;

	@Column(name = "cust_setKcal")
	private Float settedKcal;

	@Column(name = "cust_height")
	private Float height;

	@Column(name = "cust_birth")
	private String birth;

	@Column(name = "cust_gender")
	private String gender;

	@ManyToOne
    @JoinColumn(name = "idAddress")
    private Address address;

	public Customer(Integer idCustomer, String custCreatedAt, String custName, String custEmail, String cpf,
			String custCellphone, String custActivityStatus, Float custSetKcal, Float cusHheight, String custBirth,
			String custGender, Address address) {
		super();
	    this.idCustomer = idCustomer;
	    this.custCreatedAt = custCreatedAt;
	    this.name = custName;
	    this.email = custEmail;
	    this.cpf = cpf;
	    this.phoneNumber = custCellphone;
	    this.activityStatus = custActivityStatus;
	    this.settedKcal = custSetKcal;
	    this.height = cusHheight;
	    this.birth = custBirth;
	    this.gender = custGender;
	    this.address = address;
	}

	public Customer() {
		this(null, null, null, null, null, null, null, null, null, null, null, null);
	}

	public String getCustCreatedAt() {
		return custCreatedAt;
	}

	
	@SuppressWarnings("unused")
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
		return phoneNumber;
	}

	public void setCustCellphone(String custCellphone) {
		this.phoneNumber = custCellphone;
	}

	public String getCustActivityStatus() {
		return activityStatus;
	}

	public void setCustActivityStatus(String custActivityStatus) {
		this.activityStatus = custActivityStatus;
	}

	public Float getCustSetKcal() {
		return settedKcal;
	}

	public void setCustSetKcal(Float custSetKcal) {
		this.settedKcal = custSetKcal;
	}

	public Float getCustHeight() {
		return height;
	}

	public void setCustHeight(Float custHeight) {
		this.height = custHeight;
	}

	public String getCustBirth() {
		return birth;
	}

	public void setCustBirth(String custBirth) {
		this.birth = custBirth;
	}

	public String getCustGender() {
		return gender;
	}

	public void setCustGender(String custGender) {
		this.gender = custGender;
	}


	/**
	 * Recupera a informação de todas as Meals que compõe a dieta do cliente.
	 * @return Uma lista de Meals daquele cliente
	 */
	public List<Meal> getDiet() {
		return Meal.findAllByCustomerPK(this.idCustomer);
	}
	
	public String toString() {
		return "Customer: {"
		+ "\n    idCustomer: "+idCustomer
		+ "\n    name:" +name
		+ "\n}";

	}
	
	/**
	 * Método para retornar uma visão pequena do objeto.
	 * @return String contendo apenas alguns dos atributos mais importantes
	 */
	public String smallInfo() {
		return "{id: "+idCustomer+", name: "+name+"}";

	}
	
}

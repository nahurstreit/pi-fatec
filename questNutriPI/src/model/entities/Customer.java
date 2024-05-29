package model.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import model.dao.CustomerDAO;

@Entity
@Table(name = "Customers")

public class Customer extends CustomerDAO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idCustomer")
	public Integer idCustomer;

	@Column(name = "cust_createdAt")
	private Date createdAt;

	@Column(name = "cust_name")
	public String name;

	@Column(name = "cust_email")
	public String email;

	@Column(name = "cpf")
	public String cpf;

	@Column(name = "cust_phoneNumber")
	public String phoneNumber;

	@Column(name = "cust_activityStatus")
	public Integer activityStatus;

	@Column(name = "cust_setKcal")
	public Double settedKcal;

	@Column(name = "cust_height")
	public Double height;

	@Column(name = "cust_birth")
	public LocalDate birth;

	@Column(name = "cust_gender")
	public String gender;

	@ManyToOne
    @JoinColumn(name = "idAddress")
	public Address address;
	
	@Transient
	public ArrayList<Meal> mealStack = new ArrayList<Meal>();
	
//	public Customer(String name2, String email2, String cpf2, String phoneNumber2, int activityStatus2,
//			double settedKcal2, int i, Date birth2, String gender2) {
//		// TODO Auto-generated constructor stub
//	}
	
	public Customer(String name, String email, String cpf, String phoneNumber,
			Integer activityStatus, Double settedKcal, Double height, LocalDate birth, String gender) {
		super();
		this.name = name;
		this.email = email;
		this.cpf = cpf;
		this.phoneNumber = phoneNumber;
		this.activityStatus = activityStatus;
		this.settedKcal = settedKcal;
		this.height = height;
		this.birth = birth;
		this.gender = gender;
	}

	public Customer() {
		this(null, null, null, null, null, null, null, null, null);
	}

	public int getId() {
		return this.idCustomer;
	}
	
	public String getCPF() {
		return cpf;
	}
	
	/**
	 * Método para setar o nome de um Customer.
	 * @param name - String do nome
	 * @return Retorna o próprio Customer, para implementação de fluent interface.
	 */
	public Customer setName(String name) {
		this.name = name;
		return this;
	}
	
	/**
	 * Método para setar o email de um Customer.
	 * @param email - String do email
	 * @return Retorna o próprio Customer, para implementação de fluent interface.
	 */
	public Customer setEmail(String email) {
		this.email = email;
		return this;
	}
	
	/**
	 * Método para setar o CPF de um Customer.
	 * @param cpf - String do CPF
	 * @return Retorna o próprio Customer, para implementação de fluent interface.
	 */
	public Customer setCpf(String cpf) {
		this.cpf = cpf;
		return this;
	}
	
	/**
	 * Método para setar o número de telefone de um Customer.
	 * @param phoneNumber - String do telefone
	 * @return Retorna o próprio Customer, para implementação de fluent interface.
	 */
	public Customer setPhone(String phoneNumber) {
		this.phoneNumber = phoneNumber;
		return this;
	}
	
	/**
	 * Método para setar o nível de atividade de um Customer.
	 * @param activityStatus - inteiro que representa o nível de atividade
	 * @return Retorna o próprio Customer, para implementação de fluent interface.
	 */
	public Customer setActivity(int activityStatus) {
		//Adicionar validação depois
		this.activityStatus = activityStatus;
		return this;
	}
	
	/**
	 * Método para setar a altura de um Customer.
	 * @param height - Double que representa a altura do cliente
	 * @return Retorna o próprio Customer, para implementação de fluent interface.
	 */
	public Customer setHeight(double height) {
		this.height = height;
		return this;
	}
	
	/**
	 * Método para setar a quantidade de kcal diárias pesonalizadas de um Customer.
	 * @param settedKcal - Double que representa a quantidade de kcal
	 * @return Retorna o próprio Customer, para implementação de fluent interface.
	 */
	public Customer setKcal(double settedKcal) {
		this.settedKcal = settedKcal;
		return this;
	}
	
	/**
	 * Método para setar a data de nascimento de um Customer.
	 * @param day - dia do nascimento
	 * @param month - mês do nascimento
	 * @param year - ano do nascimento
	 * @return Retorna o próprio Customer, para implementação de fluent interface.
	 */
	public Customer setBirth(int day, int month, int year) {
		this.birth = LocalDate.of(year, month, day);
		return this;
	}
	
	/**
	 * Método para setar a data de nascimento de um Customer.
	 * @param date - data como return de LocalDate.of(year, month, day);
	 * @return Retorna o próprio Customer, para implementação de fluent interface.
	 */
	public Customer setBirth(LocalDate date) {
		this.birth = date;
		return this;
	}
	
	/**
	 * Método para setar a data de nascimento de um Customer.
	 * @param date - String que representa o dia de nascimento sob o formato 'dd-MM-yyyy';
	 * @return Retorna o próprio Customer, para implementação de fluent interface.
	 */
	public Customer setBirth(String date) {
        try {
            this.birth = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        } catch (DateTimeParseException e) {
            e.printStackTrace();
        }
        return this;
	}
	
	/**
	 * Método para setar o gênero de um Customer.
	 * @param gender - Caractere que representa o Gênero: 'F' - Feminino, 'M' - Masculino
	 * @return Retorna o próprio Customer, para implementação de fluent interface.
	 */
	public Customer setGender(Character gender) {
		switch(Character.toUpperCase(gender)) {
			case 'M': case 'F':
				this.gender = gender.toString();
				break;
			default:
		}
		return this;
	}
	
	/**
	 * Método para setar o endereço de um Customer.
	 * @param address objeto address
	 * @return Retorna o próprio Customer, para implementação de fluent interface.
	 */
	public Customer setAddr(Address address) {
		this.address = address;
		return this;
	}
	
    /**
     * Método para adicionar ao objeto, a data e horário atual de registro.
     */
    @PrePersist
    private void prePersist() {
        if(createdAt == null) createdAt = new Date();
    }

	/**
	 * Recupera a informação de todas as Meals que compõe a dieta do cliente.
	 * @return Uma lista de Meals daquele cliente
	 */
	public List<Meal> getDiet() {
		return Meal.findAllByCustomerPK(this.idCustomer);
	}
	
	/**
	 * Recupera o último registro de peso
	 * @return Um objeto do tipo Weight que representa o último peso registrado.
	 */
	public Weight getLastWeight() {
		return Weight.findLastRegister(this.idCustomer);
	}
	
	public void createMeal(Meal meal) {
		this.save();
		meal.setCustomer(this);
		meal.save();
	}
	
	public String toString() {
		return "Customer: {"
		+ "\n    idCustomer: "+ idCustomer
		+ "\n    name:" + name
		+ "\n}";
	}
	
	/**
	 * Método para retornar uma visão pequena do objeto.
	 * @return String contendo apenas alguns dos atributos mais importantes
	 */
	public String smallInfo() {
		return "{id: "+idCustomer+", name: "+name+"}";
	}
	
	public String listShow() {
		return name + "     " + cpf + "     "+birth;
	}
	
}

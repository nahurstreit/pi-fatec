package model.entities;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import model.dao.CustomerDAO;
import utils.CopyFactory;

/**
 * Entidade que representa um cliente no sistema.
 * Extende as funcionalidades básicas de persistência da classe CustomerDAO.
 */
@Entity
@Table(name = "Customers")
public class Customer extends CustomerDAO {

	/**
	 * Id associado a esse objeto no banco de dados
	 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCustomer")
    public Integer idCustomer;

    /**
     * Data e hora de criação do cliente.
     */
    @Column(name = "cust_createdAt")
    private LocalDateTime createdAt;

    /**
     * Data e hora de exclusão do cliente.
     */
    @Column(name = "cust_deletedAt")
    private LocalDateTime deletedAt;

    /**
     * Nome do cliente.
     */
    @Column(name = "cust_name")
    public String name;

    /**
     * E-mail do cliente.
     */
    @Column(name = "cust_email")
    public String email;

    /**
     * CPF do cliente.
     */
    @Column(name = "cpf")
    private String cpf;

    /**
     * Número de telefone do cliente.
     */
    @Column(name = "cust_phoneNumber")
    public String phoneNumber;

    /**
     * Nível de atividade física do cliente.
     * <br> 1 -> sedentário
     * <br> 2 -> levemente ativo
     * <br> 3 -> moderadamente ativo
     * <br> 4 -> muito ativo
     * <br> 5 -> extretamente ativo.
     */
    @Column(name = "cust_activityStatus")
    public Integer activityStatus = 1;

    /**
     * Valor de calorias estabelecido para o cliente.
     */
    @Column(name = "cust_setKcal")
    public Double settedKcal;

    /**
     * Altura do cliente em metros.
     */
    @Column(name = "cust_height")
    public Double height;

    /**
     * Data de nascimento do cliente.
     */
    @Column(name = "cust_birth")
    private LocalDate birth;

    /**
     * Gênero do cliente.
     */
    @Column(name = "cust_gender")
    public String gender;

    /**
     * Endereço associado ao cliente.
     */
    @ManyToOne
    @JoinColumn(name = "idAddress")
    public Address address;
	
	
	/**
	 * Construtor da classe Customer com parâmetros.
	 * 
	 * @param name Nome do cliente.
	 * @param email Email do cliente.
	 * @param cpf CPF do cliente.
	 * @param phoneNumber Número de telefone do cliente.
	 * @param activityStatus Nível de atividade do cliente.
	 * @param settedKcal Quantidade de calorias definidas para o cliente.
	 * @param height Altura do cliente.
	 * @param birth Data de nascimento do cliente.
	 * @param gender Gênero do cliente.
	 */
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

	/**
	 * Construtor padrão da classe Customer.
	 */
	public Customer() {
		this(null, null, null, null, null, null, null, null, null);
	}

	/**
     * Método para recuperar o ID do Cliente.
     *
     * @return Retorna o ID do Cliente.
     */
	public Integer getId() {
		return this.idCustomer;
	}
	
	 /**
     * Método para recuperar o nome do Cliente.
     *
     * @return Retorna o nome do Cliente.
     */
	public String getName() {
		return this.name;
	}
	
	/**
     * Método para recuperar o CPF do Cliente.
     *
     * @return Retorna o CPF do Cliente.
     */
	public String getCPF() {
		return cpf;
	}
	
	/**
     * Método para recuperar o CPF formatado do Cliente.
     *
     * @return Retorna o CPF formatado do Cliente.
     */
	public String getFormattedCpf() {
		return MessageFormat.format("{0}.{1}.{2}-{3}", cpf.substring(0, 3), cpf.substring(3, 6), cpf.substring(6, 9), cpf.substring(9));
	}
	
	 /**
     * Método para recuperar o número de telefone do Cliente.
     *
     * @return Retorna o número de telefone do Cliente.
     */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	

	/**
	 * Método para exibir as datas de criação e exclusão do cliente.
	 */
	public void showDates() {
		System.out.println(this.createdAt);
		System.out.println(this.deletedAt);
	}
	
	/**
	 * Método para recuperar o número de telefone formatado do Cliente.
	 *
	 * @return Retorna o número de telefone formatado do Cliente.
	 */
	public String getFormattedPhoneNumber() {
		if(phoneNumber == null) return null;
        // Formatar o número de telefone
        if(phoneNumber.length() == 10) {
            // (00) 0000-0000
            return MessageFormat.format("({0}) {1}-{2}", phoneNumber.substring(0, 2), phoneNumber.substring(2, 6), phoneNumber.substring(6));
        } else if (phoneNumber.length() == 11) {
            // (00) 0 0000-0000
            return MessageFormat.format("({0}) {1} {2}-{3}", phoneNumber.substring(0, 2), phoneNumber.substring(2, 3), phoneNumber.substring(3, 7), phoneNumber.substring(7));
        } else {
            // Retornar o número de telefone sem formatação se não se encaixar nos padrões
            return phoneNumber;
        }
    }
	
	/**
	 * Método para recuperar o nível de atividade do cliente.
	 *
	 * @return Retorna o status de atividade do cliente.
	 */
	public int getActivityStatus() {
		if(activityStatus == null) {
			setActivity(1);
		}
		return activityStatus;
	}
	
	/**
	 * Método para definir o nome de um Customer.
	 * 
	 * @param name - String do nome
	 * @return Retorna o próprio Customer, para implementação de fluent interface.
	 */
	public Customer setName(String name) {
		this.name = name;
		return this;
	}
	
	/**
	 * Método para setar o email de um Customer.
	 * 
	 * @param email - String do email
	 * @return Retorna o próprio Customer, para implementação de fluent interface.
	 */
	public Customer setEmail(String email) {
		this.email = email;
		return this;
	}
	
	/**
	 * Método para setar o CPF de um Customer.
	 * 
	 * @param cpf - String do CPF
	 * @return Retorna o próprio Customer, para implementação de fluent interface.
	 */
	public Customer setCpf(String cpf) {
		this.cpf = cpf.replaceAll("\\D", "");
		return this;
	}
	
	/**
	 * Método para setar o número de telefone de um Customer.
	 * 
	 * @param phoneNumber - String do telefone
	 * @return Retorna o próprio Customer, para implementação de fluent interface.
	 */
	public Customer setPhone(String phoneNumber) {
		this.phoneNumber = phoneNumber.replaceAll("\\D", "");
		return this;
	}
	
	/**
	 * Método para setar o nível de atividade de um Customer.
	 * 
	 * @param activityStatus - inteiro que representa o nível de atividade
	 * @return Retorna o próprio Customer, para implementação de fluent interface.
	 */
	public Customer setActivity(int activityStatus) {
		if(activityStatus >= 1 && activityStatus <= 5) {
			this.activityStatus = activityStatus;
		}
		
		return this;
	}
	
	/**
	 * Método para setar a altura de um Customer.
	 * 
	 * @param height - Double que representa a altura do cliente
	 * @return Retorna o próprio Customer, para implementação de fluent interface.
	 */
	public Customer setHeight(double height) {
		this.height = height;
		return this;
	}
	
	/**
	 * Método para setar a quantidade de kcal diárias pesonalizadas de um Customer.
	 * 
	 * @param settedKcal - Double que representa a quantidade de kcal
	 * @return Retorna o próprio Customer, para implementação de fluent interface.
	 */
	public Customer setKcal(double settedKcal) {
		this.settedKcal = settedKcal;
		return this;
	}
	
	/**
	 * Método para setar a data de nascimento de um Customer.
	 * 
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
	 * 
	 * @param date - data como return de LocalDate.of(year, month, day);
	 * @return Retorna o próprio Customer, para implementação de fluent interface.
	 */
	public Customer setBirth(LocalDate date) {
		this.birth = date;
		return this;
	}
	
	/**
	 * Método para setar a data de nascimento de um Customer.
	 * 
	 * @param date - String que representa o dia de nascimento sob o formato 'dd/MM/yyyy';
	 * @return Retorna o próprio Customer, para implementação de fluent interface.
	 */
	public Customer setBirth(String date) {
        try {
            this.birth = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (Exception e) {
        }
        return this;
	}
	
	/**
	 * Método para setar o gênero de um Customer.
	 * 
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
	 * 
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
        if(createdAt == null) createdAt = LocalDateTime.now();
    }

	/**
	 * Recupera a informação de todas as Meals que compõe a dieta do cliente.
	 * @return Uma lista de Meals daquele cliente
	 */
	public List<Meal> getDiet() {
		List<Meal> list = Meal.findAllByCustomerPK(this.idCustomer);
		if(list.size() == 0) {
			list = null;
		}
		return list;
	}
	
	/**
	 * Recupera o último registro de peso
	 * @return Um objeto do tipo Weight que representa o último peso registrado.
	 */
	public Weight getLastWeight() {
		return Weight.findLastRegister(this.idCustomer);
	}
	
	/**
	 * Método que retorna a data de nascimento no formato 'dd/MM/yyyy'
	 * @return a data de nascimento formatada como string.
	 */
    public String getBirth() {
        if (birth != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return birth.format(formatter);
        }
        return null;
    }
    
    /**
	 * Método que retorna a data de nascimento no formato 'dd/MM/yyyy'
	 * @return LocalDate da data de nascimento.
	 */
    public LocalDate getLocalDateBirth() {
        return birth;
    }
    
    /**
	 * Método para marcar um Customer como excluído.
	 * 
	 * @return Retorna verdadeiro se a operação foi bem-sucedida, falso caso contrário.
	 */
    public boolean softDelete() {
    	try {
			deletedAt = LocalDateTime.now();
			return this.save();
		} catch (Exception e) {
			return false;
		}
    }
    
    /**
	 * Método para reativar um Customer que foi excluído.
	 * 
	 * @return Retorna verdadeiro se a operação foi bem-sucedida, falso caso contrário.
	 */
    public boolean reactivate() {
    	try {
    		deletedAt = null;
    		return this.save();
		} catch (Exception e) {
			return false;
		}
    }
    
    /**
	 * Método para salvar um Customer.
	 * 
	 * @return Retorna verdadeiro se a operação foi bem-sucedida, falso caso contrário.
	 */
    public boolean save() {
    	boolean res = super.save();
    	if(res && this.getId() == null) {
    		res = CopyFactory.clone(Customer.findLast(), this);
    	}
    	
    	return res;
    }
    
    /**
	 * Método para copiar a dieta de um Customer para outro.
	 * 
	 * @param destinyCustomer Customer que receberá a cópia da dieta.
	 * @return Retorna verdadeiro se a operação foi bem-sucedida, falso caso contrário.
	 * @deprecated
	 */
    public boolean copyDietToAnotherCustomer(Customer destinyCustomer) {
    	boolean res = true;
    	try {
			for(Meal meal: this.getDiet()) {
				Meal copy = Meal.createCopyFrom(meal);
				copy.setCustomer(destinyCustomer);
				copy.save();
			}
		} catch (Exception e) {
			res = false;
		}
    	
    	return res;
    }
	
	/**
	 * Método para criar uma refeição associada a um Customer.
	 * 
	 * @param meal Objeto Meal a ser criado.
	 */
	public void createMeal(Meal meal) {
		this.save();
		meal.setCustomer(this);
		meal.save();
	}
	
	/**
	 * Método para retornar uma representação textual do objeto Customer.
	 * 
	 * @return String representando o objeto Customer.
	 */
	public String toString() {
		return "Customer: {"
		+ "\n    idCustomer: "+ idCustomer
		+ "\n    name: " + name
		+ "\n    cpf: "+ getCPF()
		+ "\n    birth: "+getBirth()
		+ "\n    height: "+height
		+ "\n    gender: "+gender
		+ "\n}";
	}
	
	/**
	 * Método para retornar uma visão pequena do objeto.
	 * 
	 * @return String contendo apenas alguns dos atributos mais importantes
	 */
	public String smallInfo() {
		return "{id: "+idCustomer+", name: "+name+"}";
	}
	
}

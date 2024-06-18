package model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import model.dao.UserDAO;
import utils.interfaces.GeneralAppSettings;
import view.QuestNutri;

/**
 * Entidade que representa um usuário do sistema.
 * Extende as funcionalidades básicas de persistência da classe UserDAO.
 */
@Entity
@Table(name = "Users")
public class User extends UserDAO {

	/**
	 * Id associado a esse objeto no banco de dados
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idUser")
	public Integer idUser;

	/**
	 * String que representa o username do usuário, para realizar Login
	 */
	@Column(name = "user_login")
	private String login;

	/**
	 * String que representa a senha do usuário, para realizar Login
	 */
	@Column(name = "user_password")
	private String password;
	
	/**
	 * String que representa o nome do usuário
	 */
	@Column(name = "user_personalName")
	private String firstName;
	
	/**
	 * Número inteiro que representa a linguagem do sistema para esse usuário
	 */
	@Column(name = "user_prefLanguage")
	private Integer prefLanguage = GeneralAppSettings.STD_LANGUAGE;

	/**
	 * Número inteiro que representa o nível de acesso desse usuário.
	 */
	@Column(name = "systemLevel")
	private int systemLevel;


    /**
     * Construtor da classe User.
     *
     * @param login      - login do usuário.
     * @param password   - senha do usuário.
     * @param firstName  - nome do usuário.
     * @param systemLevel - nível do usuário no sistema.
     */
	public User(String login, String password, String firstName, Integer systemLevel) {
		super();
		this.login = login;
		this.password = password;
		this.firstName = firstName;
		this.systemLevel = systemLevel;
	}
	
    /**
     * Construtor padrão.
     */
	public User() {}
 
	/**
	 * Retorna o id deste objeto no banco de dados.
	 * @return integer -> id no banco de dados.
	 */
	@Override
	public Integer getId() {
		return idUser;
	}

	/**
	 * Retorna a string de login
	 * @return String com o login
	 */
	public String getLogin() {
		return login;
	}
	
	/**
	 * Retorna o nome do usuário
	 * @return String com o nome
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Método para setar o nome de um User.
	 * 
	 * @param login - String de login de um User.
	 * @return Retorna o próprio User para implementar fluent interface.
	 */
	public User setLogin(String login) {
		this.login = login;
		return this;
	}
	
    /**
     * Método para definir o nome do usuário.
     *
     * @param name - String do nome do usuário.
	 * @return Retorna o próprio User para implementar fluent interface.
     */
	public User setFirstName(String name) {
		this.firstName = name;
		return this;
	}

    /**
     * Método para obter a senha do usuário.
     *
     * @return A senha do usuário.
     */
	public String getPassword() {
		return password;
	}

	/**
	 * Método para setar a senha de um User.
	 * 
	 * @param password - Senha do User.
	 * @return Retorna a senha do Usuário.
	 */
	public User setPassword(String password) {
		this.password = password;
		return this;
	}
	
    /**
     * Método para definir o idioma preferido do usuário.
     *
     * @param language - Código do idioma preferido.
     * @return Retorna o próprio User.
     */
	public User setPreferredLanguage(int language) {
		this.prefLanguage = language;
		return this;
	}

    /**
     * Método para obter o nível do usuário no sistema.
     *
     * @return O nível do usuário no sistema.
     */
	public Integer getSystemLevel() {
		return systemLevel;
	}

	/**
	 * Método para setar o level de um User.
	 * 
	 * @param systemLevel - Nível do Usuário de 1 a 3.
	 * @return Retorna o nível de um Usuário no sistema.
	 */
	public User setSystemLevel(int systemLevel) {
		if(QuestNutri.isAdminControl()) {
			if(systemLevel > 0 && systemLevel <= 3) {
				this.systemLevel = systemLevel;
			}
		}
		return this;
	}
	
    /**
     * Método para obter o idioma preferido do usuário.
     *
     * @return O código do idioma preferido.
     */
	public int userPrefLanguage() {
		return this.prefLanguage;
	}
	
    /**
     * Método para obter o nível do usuário no sistema como string.
     *
     * @return O nível do usuário no sistema.
     */
	public String getSysLevel() {
		return this.systemLevel + "";
	}

	
	@Override
	public String toString() {
		return "User: {" 
	+ "\n    idUser" + idUser
	+ "\n    login:" + login
	+ "\n    systemLevel:"	+ systemLevel 
	+ "\n}";
	}

}

package model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import model.dao.UserDAO;

@Entity
@Table(name = "Users")

public class User extends UserDAO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idUser")
	public Integer idUser;

	@Column(name = "user_login")
	private String login;

	@Column(name = "user_password")
	private String password;
	
	@Column(name = "user_personalName")
	private String firstName;
	
	@Column(name = "user_prefLanguage")
	private Integer prefLanguage;

	@Column(name = "systemLevel")
	private Integer systemLevel;

	public User(String login, String password, String firstName, Integer systemLevel) {
		super();
		this.login = login;
		this.password = password;
		this.firstName = firstName;
		this.systemLevel = systemLevel;
	}
	
	public User() {}
 
	public Integer getId() {
		return idUser;
	}

	public String getLogin() {
		return login;
	}
	
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Método para setar o nome de um User.
	 * 
	 * @param name - String do nome de um User.
	 * @return Retorna o próprio User.
	 */
	public User setLogin(String login) {
		this.login = login;
		return this;
	}

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

	public Integer getSystemLevel() {
		return systemLevel;
	}

	/**
	 * Método para setar o level de um User.
	 * 
	 * @param systemLevel - Nível do Usuário de 1 a 3.
	 * @return Retorna o nível de um Usuário no sistema.
	 */
	@SuppressWarnings("unused")
	private User setSystemLevel(Integer systemLevel) {
		switch (systemLevel) {
		case 1:
		case 2:
		case 3:
			this.systemLevel = systemLevel;
			break;
		default:
		}
		return this;
	}
	
	public int userPrefLanguage() {
		return this.prefLanguage;
	}
	
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

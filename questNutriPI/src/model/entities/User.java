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

	@Column(name = "userName")
	private String name;

	@Column(name = "usePassword")
	private String password;

	@Column(name = "systemLevel")
	private Integer systemLevel;

	public User(Integer idUser, String name, String password, Integer systemLevel) {
		super();
		this.idUser = idUser;
		this.name = name;
		this.password = password;
		this.systemLevel = systemLevel;
	}

	public User() {
		this(null, null, null, null);
	}

	public Integer getId() {
		return idUser;
	}

	public String getName() {
		return name;
	}

	/**
	 * Método para setar o nome de um User.
	 * 
	 * @param name - String do nome de um User.
	 * @return Retorna o próprio User.
	 */
	public User setName(String name) {
		this.name = name;
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
	public User setSystemLevel(Integer systemLevel) {
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

	/**
	 * Implementação para encontrar um usuário baseado em um campo específico
	 * @param fieldName
	 * @param value
	 * @return
	 */
	public static User findOne(String fieldName, String value) {
		return null; 
	}

	
	@Override
	public String toString() {
		return "User: {" 
	+ "\n    idUser" + idUser
	+ "\n    name:" + name
	+ "\n    systemLevel:"	+ systemLevel 
	+ "\n}";
	}

}

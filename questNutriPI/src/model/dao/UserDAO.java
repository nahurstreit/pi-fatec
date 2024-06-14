package model.dao;

import java.util.List;

import model.entities.User;

public abstract class UserDAO extends GenericDAO<User> {

	/**
	 * Método que retorna uma lista de todos os Users que correspondem aos
	 * parâmetros fornecidos
	 *
	 * @param params - Parâmetros de filtro opcionais para a consulta devem ser
	 * escritos de acordo com a declaração das classes de entidade
	 * 
	 * @return Lista de User que correspondem aos parâmetros fornecidos.
	 */
	public static List<User> findAll(String... params) {
		return GenericDAO.findAll(User.class, params);
	}

	/**
	 * Método que retorna um User pelo seu id
	 * 
	 * @param id de um User a ser buscado.
	 * @return User que corresponde ao id fornecido ou null se não encontrado.
	 */
	public static User findByPK(int id) {
		return GenericDAO.findByPK(User.class, id);
	}

	/**
	 * Método que procura e retorna apenas o primeiro registro dentro de todos os
	 * registros de User, que corresponde aos parâmetros fornecidos
	 *
	 * @param Parâmetros de filtro opcionais para a consulta devem ser escritos de
	 * acordo com a declaração das classes de entidade.
	 * 
	 * @return Primeiro registro de User que corresponde aos parâmetros
	 * fornecidos.
	 */
	public static User findOne(String... params) {
		return GenericDAO.findOne(User.class, params);
	}

}

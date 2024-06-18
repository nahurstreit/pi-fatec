package model.dao;

import java.util.List;

import model.entities.Customer;

/**
 * Classe abstrata que define operações de acesso a dados para a entidade Customer.
 * Implementa operações genéricas definidas em GenericDAO para Customer.
 */
public abstract class CustomerDAO extends GenericDAO<Customer> {

	/**
	 * Método que retorna uma lista de todos os Customers que correspondem aos
	 * parâmetros fornecidos
	 *
	 * @param params Parâmetros de filtro opcionais para a consulta devem ser escritos de
	 * acordo com a declaração das classes de entidade EX:
	 * "CustName = Matheus"
	 * 
	 * @return Lista de Customers que correspondem aos parâmetros fornecidos.
	 */
	public static List<Customer> findAll(String... params) {
		return GenericDAO.findAll(Customer.class, params);
	}

	/**
	 * Método que retorna um Customer pelo seu id
	 * 
	 * @param id do Customer a ser buscado
	 * @return Customer que corresponde ao id fornecido ou null se não encontrado.
	 */
	public static Customer findByPK(int id) {
		return GenericDAO.findByPK(Customer.class, id);
	}

	/**
	 * Método que procura e retorna apenas o primeiro registro dentro de todos os
	 * registros de Customer, que corresponde aos parâmetros fornecidos
	 *
	 * @param params Parâmetros de filtro opcionais para a consulta devem ser escritos de
	 * acordo com a declaração das classes de entidade.
	 * 
	 * @return Primeiro registro de Customer que corresponde aos parâmetros
	 * fornecidos.
	 */
	public static Customer findOne(String... params) {
		return GenericDAO.findOne(Customer.class, params);
	}

	/**
	 * Método que procura e retorna o último registro de Customer.
	 * 
	 * @return Uma instância de Customer caso encontrada, senão null.
	 */
	public static Customer findLast() {
		return GenericDAO.findLast(Customer.class);
	}

}
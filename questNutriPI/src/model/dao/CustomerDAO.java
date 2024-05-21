package model.dao;

import java.util.List;

import model.entities.Customer;

public abstract class CustomerDAO extends GenericDAO<Customer>{
	
	/**
     * Método que retorna uma lista de todos os Customers 
     * que correspondem aos parâmetros fornecidos
     *
     * @param Parâmetros de filtro opcionais para a consulta
     * devem ser escritos de acordo com a declaração das classes de entidade
     * EX: "CustName = Matheus"
     * 
     * @return Lista de Customers que correspondem aos parâmetros fornecidos.
     */
	public static List<Customer> findAll(String ...params) {
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
	
	public static Customer findOne(String ...params) {
		return GenericDAO.findOne(Customer.class, params);
	}
     
}
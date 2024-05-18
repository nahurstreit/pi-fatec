package model.dao;

import java.util.List;

import model.entities.Customer;

public abstract class CustomerDAO extends GenericDAO<Customer>{
	/**
	 * 
	 * @param params
	 * @return
	 */
	public static List<Customer> findAll(String ...params) {
		return GenericDAO.findAll(Customer.class, params);
	}
	
	public static Customer findByPK(int id) {
		return GenericDAO.findByPK(Customer.class, id);
	}
	
	public static Customer findOne(String ...params) {
		return GenericDAO.findOne(Customer.class, params);
	}
}
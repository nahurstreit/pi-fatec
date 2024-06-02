package model.dao;

import java.util.List;

import model.entities.Weight;

public abstract class WeightDAO extends GenericDAO<Weight> {
	
	public static List<Weight> findAll(String... params) {
		return GenericDAO.findAll(Weight.class, params);
	}

	public static List<Weight> findAllByCustomerPK(int id) {
		return Weight.findAll("customer.id = " + id + "ORDER BY dateRegister DESC, id DESC");
	}
	
	public static Weight findByPK(int id) {
		return GenericDAO.findByPK(Weight.class, id);
	}
	
	public static Weight findLastRegister(int idCustomer) {
		List<Weight> results = findAllByCustomerPK(idCustomer);
		if(results.size() <= 0) return null;
		return results.get(0);
	}
}
package model.dao;

import java.util.List;
import model.entities.Weight;

public abstract class WeightDAO extends GenericDAO<Weight> {
	
	public static List<Weight> findAll(String... params) {
		return GenericDAO.findAll(Weight.class, params);
	}

	public static List<Weight> findAllByCustomerPK(int id) {
		return Weight.findAll("customer.id = " + id);
	}
	
	public static Weight findByPK(int id) {
		return GenericDAO.findByPK(Weight.class, id);
	}
	
	public void createWeight(Weight weight){
		create(weight);
	}

	public void deleteWeight(Weight weight){
		delete(weight);
	}

	public void updateWeight(Weight weight){
		update(weight);
	}
}
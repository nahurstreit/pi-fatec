package model.dao;

import java.util.List;
import model.entities.SubFood;

public abstract class SubFoodDAO extends GenericDAO<SubFood> {
	
	public static List<SubFood> findAll(String ...params) {
		return GenericDAO.findAll(SubFood.class, params);
	}
	
	public static List<SubFood> findAllByFoodPK(int id) {
		return SubFood.findAll("food.id = " + id);
	}
	
	public static SubFood findByPK(int id) {
		return GenericDAO.findByPK(SubFood.class, id);
	}
	
	public void createSubFood(SubFood subFood){
		create(subFood);
	}

	public void deleteSubFood(SubFood subFood){
		delete(subFood);
	}

	public void updateSubFood(SubFood subFood){
		update(subFood);
	}
}
package model.dao;

import java.util.List;

import model.entities.SubFood;

public abstract class SubFoodDAO extends GenericDAO<SubFood> {
	
	public static List<SubFood> findAll(String ...params) {
		return GenericDAO.findAll(SubFood.class, params);
	}
	
	public static List<SubFood> findAllByFoodPK(int id) {
		return SubFood.findAll("food.id = " + id + " AND deactivatedAt IS NULL ORDER BY id DESC");
	}
	
	public static SubFood findByPK(int id) {
		return GenericDAO.findByPK(SubFood.class, id);
	}
	
}
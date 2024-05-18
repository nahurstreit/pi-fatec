package model.dao;

import java.util.List;

import model.entities.Meal;

public abstract class MealDAO extends GenericDAO<Meal>{
	public static List<Meal> findAll(String ...params) {
		return GenericDAO.findAll(Meal.class, params);
	}
	
	public static List<Meal> findAllByCustomerPK(int id) {
		return Meal.findAll("customer.id = " + id);
	}
	
	public static Meal findByPK(int id) {
		return GenericDAO.findByPK(Meal.class, id);
	}

}
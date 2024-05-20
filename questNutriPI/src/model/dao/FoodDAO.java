package model.dao;

import java.util.List;
import model.entities.Food;

public abstract class FoodDAO extends GenericDAO<Food> {
	
	public static List<Food> findAll(String ...params) {
		return GenericDAO.findAll(Food.class, params);
	}
	
	/**
	 * Método simplificado para obter as Foods pelo id da Meal
	 * @param id -> id da Meal a ser buscada
	 * @return Lista de Foods associadas ao id indicado.
	 */
	public static List<Food> findAllByMealPK(int id) {
		return Food.findAll("meal.id = " + id);
	}
	
	public static Food findByPK(int id) {
		return GenericDAO.findByPK(Food.class, id);
	}
	
	public void createFood(Food food){
		create(food);
	}

	public void deleteFood(Food food){
		delete(food);
	}

	public void updateFood(Food food){
		update(food);
	}
}
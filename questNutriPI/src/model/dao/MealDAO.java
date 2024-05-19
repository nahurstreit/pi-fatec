package model.dao;

import java.util.List;
import model.entities.Meal;

public abstract class MealDAO extends GenericDAO<Meal>{
	
	public static List<Meal> findAll(String ...params) {
		return GenericDAO.findAll(Meal.class, params);
	}
	
	/**
	 * Método simplificado para obter as Meals pelo id do Customer
	 * 
	 * @param id do Customer a ser buscado
	 * @return Lista de Meals associadas ao id indicado.
	 */
	public static List<Meal> findAllByCustomerPK(int id) {
		return Meal.findAll("customer.id = " + id);
	}
	
	/**
     * Método que retorna uma Meal pelo seu id
     * 
     * @param id da Meal a ser buscada
     * @return Meal que corresponde ao id fornecido ou null se não encontrado.
     */
	public static Meal findByPK(int id) {
		return GenericDAO.findByPK(Meal.class, id);
	}

}
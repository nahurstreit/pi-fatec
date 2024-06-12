package model.dao;

import java.util.List;

import model.entities.Food;

public abstract class FoodDAO extends GenericDAO<Food> {

	/**
	 * Método que encontra todos os registros de Food e retorna uma lista de todas
	 * as Foods que correspondem aos parâmetros fornecidos
	 *
	 * @param Parâmetros de filtro opcionais para a consulta devem ser escritos de
	 * acordo com a declaração das classes de entidade.
	 * 
	 * @return Lista de Foods que correspondem aos parâmetros fornecidos.
	 */
	public static List<Food> findAll(String... params) {
		return GenericDAO.findAll(Food.class, params);
	}

	/**
	 * Método simplificado para obter as Foods pelo id da Meal
	 * 
	 * @param id -> id da Meal a ser buscada
	 * @return Lista de Foods associadas ao id indicado.
	 */
	public static List<Food> findAllByMealPK(int id) {
		return Food.findAll("meal.id = " + id);
	}

	/**
	 * Método para obter um registro de Food pelo id
	 * 
	 * @param id -> id da Food a ser buscada
	 * @return registro de Food associada ao id indicado.
	 */
	public static Food findByPK(int id) {
		return GenericDAO.findByPK(Food.class, id);
	}

}
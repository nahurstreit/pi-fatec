package model.dao;

import java.util.List;

import model.entities.Food;

/**
 * Classe abstrata que define operações de acesso a dados para a entidade Food.
 * Implementa operações genéricas definidas em GenericDAO para Food.
 */
public abstract class FoodDAO extends GenericDAO<Food> {

	/**
	 * Método que encontra todos os registros de Food e retorna uma lista de todas
	 * as Foods que correspondem aos parâmetros fornecidos
	 *
	 * @param params Parâmetros de filtro opcionais para a consulta devem ser escritos de
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
		return Food.findAll("meal.id = " + id + " AND deactivatedAt IS NULL ORDER BY id DESC");
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
	
	/**
	 * Método para encontrar a última Food salva.
	 * 
	 * @return Objeto Food da última Food salva.
	 */
	public static Food findLast() {
		return GenericDAO.findLast(Food.class, " deactivatedAt IS NULL ");
	}
	
}
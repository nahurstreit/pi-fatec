package model.dao;

import java.util.List;

import model.entities.SubFood;

public abstract class SubFoodDAO extends GenericDAO<SubFood> {

	/**
	 * Método que encontra todos os registros de SubFood e retorna uma lista de
	 * todas as SubFoods que correspondem aos parâmetros fornecidos
	 *
	 * @param Parâmetros de filtro opcionais para a consulta devem ser escritos de
	 * acordo com a declaração das classes de entidade.
	 * 
	 * @return Lista de SubFoods que correspondem aos parâmetros fornecidos.
	 */
	public static List<SubFood> findAll(String... params) {
		return GenericDAO.findAll(SubFood.class, params);
	}

	/**
	 * Método simplificado para obter as SubFoods pelo id da Food
	 * 
	 * @param id -> id da Food a ser buscada
	 * @return Lista de SubFoods associadas ao id indicado.
	 */
	public static List<SubFood> findAllByFoodPK(int id) {
		return SubFood.findAll("food.id = " + id);
	}

	/**
	 * Método para obter um registro de SubFood pelo id
	 * 
	 * @param id -> id da SubFood a ser buscada
	 * @return registro de SubFood associada ao id indicado.
	 */
	public static SubFood findByPK(int id) {
		return GenericDAO.findByPK(SubFood.class, id);
	}

}
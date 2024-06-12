package model.dao;

import java.util.List;

import model.entities.Meal;

public abstract class MealDAO extends GenericDAO<Meal> {

	/**
	 * Método que encontra todos os registros de Meal e retorna uma lista de todas
	 * as Meals que correspondem aos parâmetros fornecidos
	 *
	 * @param Parâmetros de filtro opcionais para a consulta devem ser escritos de
	 * acordo com a declaração das classes de entidade.
	 * 
	 * @return Lista de Meals que correspondem aos parâmetros fornecidos.
	 */
	public static List<Meal> findAll(String... params) {
		return GenericDAO.findAll(Meal.class, params);
	}

	/**
	 * Método simplificado para obter as Meals pelo id do Customer
	 * 
	 * @param id - do Customer a ser buscado
	 * @param getNotActive - valor booleano opcional que faz incluir no retorno as
	 * refeições que foram desativadas.
	 * @return Lista de Meals associadas ao id indicado.
	 */
	public static List<Meal> findAllByCustomerPK(int id, boolean... getNotActive) {
		boolean onlyActive = true;
		if (getNotActive.length > 0) {
			if (getNotActive[0])
				onlyActive = !onlyActive; // Se for passado true, onlyActive é desativado e a query é apenas customer.id
		}
		;

		String query = "customer.id = " + id;
		if (onlyActive)
			query += " AND deactivatedAt IS NULL ";
		query += " ORDER BY hour ASC";

		return Meal.findAll(query);
	}

	/**
	 * Método que retorna uma Meal pelo seu id
	 * 
	 * @param id da Meal a ser buscada
	 * @param getNotActive - valor booleano opcional que faz incluir no retorno as
	 * refeições que foram desativadas.
	 * @return Meal que corresponde ao id fornecido ou null se não encontrado.
	 */
	public static Meal findByPK(int id, boolean... getNotActive) {
		boolean onlyActive = true;
		if (getNotActive.length > 0) {
			if (getNotActive[0])
				onlyActive = !onlyActive; // Se for passado true, onlyActive e o objeto retornado pode estar desativado
		}
		;

		Meal found = GenericDAO.findByPK(Meal.class, id);
		if (found != null) {
			if (found.isActive() || !onlyActive)
				return found;
		}

		return null;
	}

}
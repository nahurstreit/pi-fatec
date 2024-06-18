package model.dao;

import java.util.List;

import model.entities.Aliment;

/**
 * Classe abstrata que define operações de acesso a dados para a entidade Aliment.
 * Implementa operações genéricas definidas em GenericDAO para Aliment.
 */
public abstract class AlimentDAO extends GenericDAO<Aliment> {

	/**
	 * Método que encontra todos os registros de Aliments e retorna uma lista de
	 * todos os Aliments que correspondem aos parâmetros fornecidos
	 *
	 * @param params Parâmetros de filtro opcionais para a consulta devem ser escritos de
	 * acordo com a declaração das classes de entidade.
	 * 
	 * @return Lista de Aliments que correspondem aos parâmetros fornecidos.
	 */
	public static List<Aliment> findAll(String... params) {
		return GenericDAO.findAll(Aliment.class, params);
	}

	/**
	 * Método que retorna um Aliment pelo seu id
	 * 
	 * @param id -> do Aliment a ser buscado
	 * @return Aliment que corresponde ao id fornecido ou null se não encontrado.
	 */
	public static Aliment findByPK(int id) {
		return GenericDAO.findByPK(Aliment.class, id);
	}

	/**
	 * Método que procura e retorna apenas o primeiro registro dentro de todos os
	 * registros de Aliment que corresponde aos parâmetros fornecidos
	 *
	 * @param params Parâmetros de filtro opcionais para a consulta devem ser escritos de
	 * acordo com a declaração das classes de entidade 
	 * 
	 * @return primeiro registro de Aliment que corresponde aos parâmetros
	 *         fornecidos.
	 */
	public static Aliment findOne(String... params) {
		return GenericDAO.findOne(Aliment.class, params);
	}

	/**
	 * Método que procura e retorna o último registro de Aliment.
	 * 
	 * @return Uma instância de `Aliment` caso encontrada, senão null.
	 */
	public static Aliment findLast() {
		return GenericDAO.findLast(Aliment.class);
	}

}
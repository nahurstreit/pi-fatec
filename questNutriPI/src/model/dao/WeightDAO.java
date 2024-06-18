package model.dao;

import java.util.List;

import model.entities.Weight;

/**
 * Classe abstrata que define operações de acesso a dados para a entidade Weight.
 * Implementa operações genéricas definidas em GenericDAO para Weight.
 */
public abstract class WeightDAO extends GenericDAO<Weight> {
	
	 /**
	   * Método que encontra todos os registros de Weight e retorna
	   * uma lista de todos os Weights que correspondem aos parâmetros fornecidos.
	   *
	   * @param params Parâmetros de filtro opcionais para a consulta
	   * devem ser escritos de acordo com a declaração das classes
	   * de entidade.
	   * 
	   * @return Lista de Weights que correspondem aos parâmetros fornecidos.
	   */
	public static List<Weight> findAll(String... params) {
		return GenericDAO.findAll(Weight.class, params);
	}

	 /**
	   * Método simplificado para obter os Weights pelo id do cliente.
	   *
	   * @param id -> id do Customer a ser buscado
	   * @return Lista de Weights associados ao id indicado.
	   */
	public static List<Weight> findAllByCustomerPK(int id) {
		return Weight.findAll("customer.id = " + id + "ORDER BY dateRegister DESC, id DESC");
	}
	
	/**
	   * Método para obter um registro de Weight pelo id
	   * 
	   * @param id -> id do Weight a ser buscado
	   * @return registro de Weight associado ao id indicado.
	   */
	public static Weight findByPK(int id) {
		return GenericDAO.findByPK(Weight.class, id);
	}
	
	/**
	   * Método para obter o último registro de Weight de um cliente específico.
	   *
	   * @param idCustomer -> id do Customer a ser buscado
	   * @return O último registro de Weight associado ao id do cliente indicado, ou
	   * null * se nenhum registro for encontrado.
	   */
	public static Weight findLastRegister(int idCustomer) {
		List<Weight> results = findAllByCustomerPK(idCustomer);
		if(results.size() <= 0) return null;
		return results.get(0);
	}
}
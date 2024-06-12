package model.dao;

import model.entities.Address;

public abstract class AddressDAO extends GenericDAO<Address> {

	/**
	 * Método que procura e retorna um Address pelo seu id
	 * 
	 * @param id -> id do Address a ser buscado
	 * @return Address que corresponde ao id fornecido ou null se não encontrado.
	 */
	public static Address findByPK(int id) {
		return GenericDAO.findByPK(Address.class, id);
	}

	/**
	 * Método que procura e retorna o último registro de Address.
	 * 
	 * @return Address caso encontrada, senão null.
	 */
	public static Address findLast() {
		return GenericDAO.findLast(Address.class);
	}

}
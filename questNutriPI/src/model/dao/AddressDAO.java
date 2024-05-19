package model.dao;

import java.util.List;
import model.entities.Address;

public abstract class AddressDAO extends GenericDAO<Address>{
	
	public static List<Address> findAll(String ...params) {
		return GenericDAO.findAll(Address.class, params);
	}
	
	public static Address findByPK(int id) {
		return GenericDAO.findByPK(Address.class, id);
	}
	
}
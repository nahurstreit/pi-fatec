package model.dao;

import model.entities.Address;

public abstract class AddressDAO extends GenericDAO<Address>{
	
	public static Address findByPK(int id) {
		return GenericDAO.findByPK(Address.class, id);
	}
	
	public void createAddress(Address address){
		create(address);
	}

	public void deleteAddress(Address address){
		delete(address);
	}

	public void updateAddress(Address address){
		update(address);
	}
}
package controller;

import model.entities.Address;

public class AddressController {
	public static boolean createNewAddress(String cep, String number, String comp, 
    		String street, String hood, String city, String addrState) {
		try {
			Address addr = new Address().setCep(cep)
								.setNumber(Integer.parseInt(number))
								.setComp(comp)
								.setStreet(street)
								.setHood(hood)
								.setCity(city)
								.setState(addrState);
			return addr.save();
		} catch (Exception e) {
			return false;
		}		
	}
}
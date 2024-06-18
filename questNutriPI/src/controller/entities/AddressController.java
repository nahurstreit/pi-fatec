/**
 * Package que contém os controllers da aplicação.
 */
package controller.entities;

import model.entities.Address;

/**
 * Controlador responsável por operações relacionadas a endereços na aplicação.
 */
public abstract class AddressController {
	/**
	 * Método para criar um novo endereço.
	 *
	 * @param cep        - CEP do endereço.
	 * @param number     - Número do endereço.
	 * @param comp       - Complemento do endereço.
	 * @param street     - Rua do endereço.
	 * @param hood       - Bairro do endereço.
	 * @param city       - Cidade do endereço.
	 * @param addrState  - Estado do endereço.
	 * @return true se o endereço foi criado com sucesso, false caso contrário.
	 */

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
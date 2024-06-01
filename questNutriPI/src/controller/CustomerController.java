package controller;

import java.text.MessageFormat;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import model.entities.Address;
import model.entities.Customer;
import view.frames.CustomerFrame;
import view.frames.NewCustomerFrame;

public class CustomerController {
    public static JScrollPane getCustomerList(List<Customer> originList) {
        return InterfaceController.createTable(
                originList,
                new Object[]{"Nome", "CPF", "Telefone"},
                customer -> new Object[]{
                        customer.name,
                        formatCPF(customer.getCPF()),
                        formatPhoneNumber(customer.phoneNumber)
                },
                customer -> () -> openCustomerFrame(customer)
        );
    }
    
	public static JScrollPane getCustomerList() {
		return getCustomerList(Customer.findAll());
	}
    
    public static List<Customer> searchCustomers(String searchField, String searchTerm) {
        String searchParam = "";
        searchTerm = searchTerm.replaceAll("\\W", "");
        if (!searchTerm.isBlank()) {
            switch (searchField) {
                case "CPF":
                    searchTerm = searchTerm.replaceAll("[^\\d]", "");
                    searchParam = "cpf";
                    break;
                case "Nome":
                    searchParam = "name";
                    break;
                case "Telefone":
                    searchTerm = searchTerm.replaceAll("[^\\d]", "");
                    searchParam = "phoneNumber";
                    break;
            }
            
            searchParam += " LIKE '%" + searchTerm + "%'";
        }
        
        return Customer.findAll(searchParam);
    }

    public static CustomerFrame openCustomerFrame(Customer customer) {
        CustomerFrame frame = new CustomerFrame(customer);
        frame.setSideBarMenu(
        		frame.sbProfilePage(), 
        		frame.sbDietPage())
        	 .init();
        return frame;
    }
    
    public static NewCustomerFrame openNewCustomerFrame() {
    	NewCustomerFrame frame = new NewCustomerFrame();
    	return frame;
    }
    
    /**
     * Método para criar um novo customer a partir do frame de newCustomer
     * @param frame
     * @param customer
     * @param name
     * @param birth
     * @param cpf
     * @param height
     * @param gender
     * @param email
     * @param phoneNumber
     */
    public static void createNewCustomer(NewCustomerFrame frame, 
    		Customer customer, String name, String birth, String cpf, String height, String gender,
    		String email, String phoneNumber) {
    	try {
    		customer.setName(name)
    				.setBirth(birth)
    				.setCpf(cpf)
    				.setHeight(Double.parseDouble(height))
    				.setGender(gender.charAt(0))
    				.setEmail(email)
    				.setPhone(phoneNumber);
    		
    		if(customer.save()) {
    			frame.dispose();
    			openCustomerFrame(Customer.findLast());
    		};
		} catch (Exception e) {
			frame.dispose();
			JOptionPane.showMessageDialog(null, "Ocorreu um erro!");
		}
    	
    }
    
    public static boolean saveCustPersonalInfo(Customer customer, String name, String birth, String cpf, String height, String gender) {
    	customer.setName(name)
    			.setBirth(birth)
    			.setCpf(cpf)
    			.setHeight(Double.parseDouble(height))
    			.setGender(gender.charAt(0));
    	
    	return customer.save();
    }
    
    public static boolean saveCustomerContactInfo(Customer customer, String email, String phoneNumber) {
    	customer.setEmail(email)
    			.setPhone(phoneNumber);
    	
    	return customer.save();
    }
    

    /**
     * Método para salvar o endereço de um cliente.
     * @param customer
     * @param cep
     * @param number
     * @param comp
     * @param street
     * @param hood
     * @param city
     * @param addrState
     * @return O estado da operação:
     * <li><b>true</b> - se o endereço tiver sido falso e o vínculo com o cliente estabelecido.
     * <li><b>false</b> - se não foi possível salvar o endereço ou se o vínculo com o cliente não pôde ser estabelecido.
     */
    public static boolean saveCustomerAddrInfo(
    		Customer customer, String cep, String number, String comp, 
    		String street, String hood, String city, String addrState) {
    	
    	if(AddressController.createNewAddress(cep, number, comp, street, hood, city, addrState)) {
    		try {
        		customer.setAddr(Address.findLast());
        		return customer.save();
			} catch (Exception e) {
				return false;
			}
    	};
    	
    	return false;
    	
    }
    
	private static String formatCPF(String cpf) {
        // Formatar o CPF para o formato: 000.000.000-00
	    return MessageFormat.format("{0}.{1}.{2}-{3}", cpf.substring(0, 3), cpf.substring(3, 6), cpf.substring(6, 9), cpf.substring(9));
    }
	
	private static String formatPhoneNumber(String phoneNumber) {
        // Formatar o número de telefone
        if (phoneNumber.length() == 10) {
            // (00) 0000-0000
            return MessageFormat.format("({0}) {1}-{2}", phoneNumber.substring(0, 2), phoneNumber.substring(2, 6), phoneNumber.substring(6));
        } else if (phoneNumber.length() == 11) {
            // (00) 0 0000-0000
            return MessageFormat.format("({0}) {1} {2}-{3}", phoneNumber.substring(0, 2), phoneNumber.substring(2, 3), phoneNumber.substring(3, 7), phoneNumber.substring(7));
        } else {
            // Retornar o número de telefone sem formatação se não se encaixar nos padrões
            return phoneNumber;
        }
    }

}
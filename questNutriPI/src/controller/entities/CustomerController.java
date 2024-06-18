package controller.entities;

import java.awt.Dimension;
import java.text.MessageFormat;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import controller.app.InterfaceController;
import model.entities.Address;
import model.entities.Customer;
import utils.ConfirmDialog;
import utils.CopyFactory;
import utils.view.LanguageUtil;
import view.QuestNutri;
import view.components.QuestNutriJOP;
import view.frames.CustomerFrame;
import view.frames.NewCustomerFrame;

/**
 * Controlador responsável por operações relacionadas a clientes.
 */
public abstract class CustomerController {
	
    /**
     * Retorna um JScrollPane contendo uma tabela de clientes baseada na lista fornecida.
     *
     * @param originList A lista de clientes a ser exibida na tabela.
     * @return Um JScrollPane contendo a tabela de clientes.
     */
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
    
    /**
     * Retorna um JScrollPane contendo uma tabela de todos os clientes.
     *
     * @return Um JScrollPane contendo a tabela de clientes.
     */
	public static JScrollPane getCustomerList() {
		return getCustomerList(Customer.findAll());
	}
    
    /**
     * Pesquisa clientes com base em um campo de pesquisa e termo de pesquisa especificados.
     *
     * @param searchField O campo para pesquisar por (ex.: "CPF", "Nome", "Telefone").
     * @param searchTerm O termo de pesquisa.
     * @return Uma lista de clientes correspondentes aos crit�rios de pesquisa.
     */
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
	            case "Name":
	                searchParam = "name";
	                break;
	            case "Telefone":
	            case "Phone Number":
	                searchTerm = searchTerm.replaceAll("[^\\d]", "");
	                searchParam = "phoneNumber";
	                break;
	        }

	        searchParam += " LIKE '%" + searchTerm + "%'";
	    }

	    if (!searchParam.isBlank()) {
	        searchParam += " AND";
	    }
	    
	    searchParam += " deletedAt IS NULL ";

	    return Customer.findAll(searchParam);
	}

    /**
     * Abre o frame do cliente especificado.
     *
     * @param customer O cliente a ser exibido no frame.
     * @return O frame do cliente aberto.
     */
    public static CustomerFrame openCustomerFrame(Customer customer) {
        CustomerFrame frame = new CustomerFrame(customer);
        if(QuestNutri.isEditAuth()) {
            frame.setSideBarMenu(
            		frame.sbProfilePage(),
            		frame.sbDietPage()
            );
        } else {
        	frame.setSideBarMenu(
            		frame.sbProfilePage()
            );
        }
        		
        frame.init();
        frame.setMinimumSize(new Dimension(1000, 500));;
        return frame;
    }
    
    /**
     * Abre o frame para criar um novo cliente.
     *
     * @param onCreate Ação a ser executada quando o cliente é criado.
     * @return O frame para criar um novo cliente.
     */
    public static NewCustomerFrame openNewCustomerFrame(Runnable onCreate) {
    	NewCustomerFrame frame = new NewCustomerFrame(onCreate);
    	return frame;
    }
    
    /**
     * Método para criar um novo customer a partir do frame de newCustomer
     * @param frame - frame de origem
     * @param customer - objeto Customer
     * @param name - novo nome.
     * @param birth - data de nascimento como string 'dd/mm/yyyy'
     * @param cpf - cpf do cliente
     * @param height - altura como string
     * @param gender - gênero
     * @param email - email
     * @param phoneNumber - número de telefone
     */
    public static void createNewCustomer(NewCustomerFrame frame, 
    		Customer customer, String name, String birth, String cpf, String height, String gender,
    		String email, String phoneNumber) {
    	if(QuestNutri.isEditAuth()) {
        	Double convertedHeight = null;
        	try {
        		convertedHeight= Double.parseDouble(height);
    		} catch (Exception e) {
    		}
        	
        	
    		
        	try {
        		customer.setName(name)
        				.setBirth(birth)
        				.setCpf(cpf)
        				.setHeight(convertedHeight)
        				.setGender(gender.charAt(0))
        				.setEmail(email)
        				.setPhone(phoneNumber);
        		Customer c_cpf = Customer.findOne(" cpf LIKE '"+customer.getCPF()+"' ");
        		if(c_cpf != null) {
        			if (ConfirmDialog.ask(
        			        new LanguageUtil("Um usuário com esse CPF foi encontrado. Deseja reativá-lo?", "A user with this CPF was found. Do you want to reactivate them?").get(),
        			        new LanguageUtil("Usuário antigo encontrado.", "Old user found.").get())) {
        				CopyFactory.clone(c_cpf, customer);
        				customer.reactivate();
        			} else {
        				c_cpf.delete();
        			}
        		}
        		
        		if(customer.save()) {
        			frame.dispose();
        			openCustomerFrame(customer);
        		} else {
        			frame.dispose();
        			QuestNutri.heraldOfDarkness();;
        		}
    		} catch (Exception e) {
    			frame.dispose();
    			e.printStackTrace();
    			QuestNutriJOP.showMessageDialog(null, new LanguageUtil("Ocorreu um erro!", "An error occurred!").get());
    		}
    	} else {
    		QuestNutriJOP.showMessageDialog(null, 
    				new LanguageUtil(
    						"Usuário não tem autorização para criar um Novo Cliente", 
							"User is not authorized to create a New Customer"
    						).get(),
    				new LanguageUtil("Não Autorizado", "Unauthorized").get(), 1, null);
    	}
    }
    
    /**
     * Salva informações pessoais do cliente.
     *
     * @param customer O cliente cujas informações serão salvas.
     * @param name     Novo nome do cliente.
     * @param birth    Nova data de nascimento do cliente.
     * @param cpf      Novo CPF do cliente.
     * @param height   Nova altura do cliente.
     * @param gender   Novo gênero do cliente.
     * @param activity Nova atividade do cliente.
     */
    public static void saveCustPersonalInfo(Customer customer, String name, String birth, String cpf, String height, String gender, String activity) {
    	Double convertedHeight = null;
    	try {
    		convertedHeight= Double.parseDouble(height);
		} catch (Exception e) {
		}
    	
    	int activityStatus = 1;
    	if(activity != null && !activity.isBlank()) {
    		try {
    			activityStatus = Integer.parseInt(activity);
			} catch (Exception e) {
			}
    		
    	}
    	
        customer.setName(name)
		        .setBirth(birth)
		        .setCpf(cpf)
		        .setHeight(convertedHeight)
		        .setActivity(activityStatus)
		        .setGender(gender.charAt(0));
		if(customer.save()) {
			QuestNutriJOP.showMessageDialog(null, new LanguageUtil("Dados salvos com sucesso!", "Data saved successfully!").get());
		};
    }
    
    /**
     * Salva informações de contato do cliente.
     *
     * @param customer     O cliente cujas informações serão salvas.
     * @param email        Novo e-mail do cliente.
     * @param phoneNumber  Novo número de telefone do cliente.
     * @return true se as informações foram salvas com sucesso, false caso contrário.
     */
    public static boolean saveCustomerContactInfo(Customer customer, String email, String phoneNumber) {
    	customer.setEmail(email)
    			.setPhone(phoneNumber);
    	
    	return customer.save();
    }
    

    /**
     * Salva o endereço de um cliente.
     *
     * @param customer   O cliente para o qual o endereço será salvo.
     * @param cep        CEP do endereço.
     * @param number     Número do endereço.
     * @param comp       Complemento do endereço.
     * @param street     Rua do endereço.
     * @param hood       Bairro do endereço.
     * @param city       Cidade do endereço.
     * @param addrState  Estado do endereço.
     * @return O estado da operação:
     * <br><b>true</b> - se o endereço tiver sido salvo e o vínculo com o cliente estabelecido.
     * <br><b>false</b> - se não foi possível salvar o endereço ou se o vínculo com o cliente não pôde ser estabelecido.
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
    
    /**
     * Formata o CPF para o formato específico.
     *
     * @param cpf CPF a ser formatado.
     * @return CPF formatado no padrão XXX.XXX.XXX-XX.
     */
	private static String formatCPF(String cpf) {
        // Formatar o CPF para o formato: 000.000.000-00
	    return MessageFormat.format("{0}.{1}.{2}-{3}", cpf.substring(0, 3), cpf.substring(3, 6), cpf.substring(6, 9), cpf.substring(9));
    }
	
	/**
	 * Formata o número de telefone para um formato específico.
	 *
	 * @param phoneNumber Número de telefone a ser formatado.
	 * @return Número de telefone formatado no padrão (00) 0000-0000 ou (00) 0 0000-0000.
	 */
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
	
	/**
	 * Exclui um cliente do sistema após confirmação.
	 *
	 * @param customer Cliente a ser excluído.
	 * @return true se o cliente foi excluído com sucesso, false caso contrário.
	 */
	public static boolean deleteCustomer(Customer customer) {
		String[] options = {new LanguageUtil("Sim", "Yes").get(), new LanguageUtil("Não", "No").get()};
		boolean status = false;
		
		int choice = QuestNutriJOP.showOptionDialog(
	            null, // Componente pai (null para centralizar na tela)
	            new LanguageUtil("Você está tentando excluir o cliente: ", "You are trying to delete the customer: ").get() + customer.getName() + "."
                + "\n" + new LanguageUtil("Você tem certeza disso?", "Are you sure about that?").get(),
                new LanguageUtil("Confirmação", "Confirmation").get(), // Título
	            JOptionPane.YES_NO_CANCEL_OPTION, // Tipo de opções
	            JOptionPane.QUESTION_MESSAGE, // �?cone
	            null,
	            options,
	            options[1]
	    );
		
		if(choice == 0) {
			if(customer.softDelete()) {
				QuestNutriJOP.showMessageDialog(null, new LanguageUtil("O Cliente foi excluído!", "The customer has been deleted!").get());
			    status = true;
			} else {
				QuestNutriJOP.showMessageDialog(null, new LanguageUtil("Erro ao desativar o cliente.", "Error deactivating the customer.").get());
			}
		}
		
		return status;
	}

}
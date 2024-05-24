package controller;

import javax.swing.JOptionPane;

import model.entities.Customer;
import view.frames.CustomerFrame;
import view.panels.pages.CustomersPage;

public class CustomerController {
	public static void openCustomerFrame(int id) {
		JOptionPane.showMessageDialog(null, "Você abriu o JFrame do Customer: "+Customer.findByPK(id));
	}
	
	public static void searchCustomerList(CustomersPage page) {
		String searchParam = "";
		String input = page.inputSearchBox.getText();
		input = input.replace("Digite aqui...", "").replaceAll("\\W", "");
		if(!input.isBlank()) { // Verifica se o input é vazio
			switch((String) page.comboBox.getSelectedItem()) {
				case "CPF":
					input = input.replaceAll("[^\\d]", "");
					if(input.isBlank()) JOptionPane.showMessageDialog(null, "Digite apenas números!");
					searchParam = "cpf";
					break;
				case "Nome":
					searchParam = "name";
					break;
				case "Telefone":
					input = input.replaceAll("[^\\d]", "");
					if(input.isBlank()) JOptionPane.showMessageDialog(null, "Digite apenas números!");
					searchParam = "phoneNumber";
					break;
			}
			
			searchParam += " LIKE '%" + input + "%'";
		}

		System.out.println(searchParam);
		System.out.println(Customer.findAll(searchParam));
		page.setCustomersList(InterfaceController.getCustomerList(Customer.findAll(searchParam)));
	}
	
	public static void openCustomerFrame(Customer customer) {
		CustomerFrame frame = new CustomerFrame(customer);
		frame.setVisible(true);
	}

}
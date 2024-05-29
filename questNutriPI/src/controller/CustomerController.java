package controller;

import java.util.List;

import javax.swing.JOptionPane;

import model.entities.Customer;
import view.frames.CustomerFrame;

public class CustomerController {
    
    public static void openCustomerFrame(int id) {
        JOptionPane.showMessageDialog(null, "Você abriu o JFrame do Customer: " + Customer.findByPK(id));
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

    public static void openCustomerFrame(Customer customer) {
        CustomerFrame frame = new CustomerFrame(customer);
        frame.setVisible(true);
    }
}
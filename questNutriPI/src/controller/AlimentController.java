package controller;

import java.util.List;

import javax.swing.JOptionPane;

import model.entities.Aliment;
import model.entities.Customer;
import view.frames.CustomerFrame;

public class AlimentController {
    public static void openAlimentFrame(int id) {
        JOptionPane.showMessageDialog(null, "Você abriu o JFrame do Customer: " + Customer.findByPK(id));
    }
    
    public static List<Aliment> searchAliments(String searchField, String searchTerm) {
        String searchParam = "";
        searchTerm = searchTerm.replaceAll("\\W", "");
        if (!searchTerm.isBlank()) {
            switch (searchField) {
                case "Nome":
                    searchParam = "name";
                    break;
                case "Grupo":
                    searchParam = "alimentGroup";
                    break;
            }
            
            searchParam += " LIKE '%" + searchTerm + "%'";
        }
        
        return Aliment.findAll(searchParam);
    }

    public static void openCustomerFrame(Customer customer) {
        CustomerFrame frame = new CustomerFrame(customer);
        frame.setVisible(true);
    }
}
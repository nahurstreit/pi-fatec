package view.panels.pages;

import java.util.List;

import controller.CustomerController;
import controller.InterfaceController;
import model.entities.Customer;
import view.panels.components.GenericJPanel;

public class CustomersPage extends GenericListPage<Customer> {
    private static final long serialVersionUID = 1L;

    public CustomersPage(GenericJPanel ownerPanel) {
        super(ownerPanel, new String[]{"CPF", "Nome", "Telefone"}, "Controle de Clientes", InterfaceController::getCustomerList);
    }

    @Override
    protected void performSearch() {
        String searchField = (String) comboBox.getSelectedItem();
        String searchTerm = inputSearchBox.getText();
        List<Customer> results = searchItems(searchField, searchTerm);
        setItemList(InterfaceController.getCustomerList(results));
    }

    @Override
    protected List<Customer> fetchInitialList() {
        return Customer.findAll();
    }
    
    @Override
    protected List<Customer> searchItems(String searchField, String searchTerm) {
        return CustomerController.searchCustomers(searchField, searchTerm);
    }
}
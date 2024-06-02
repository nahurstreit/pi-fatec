package view.panels.pages;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

import controller.CustomerController;
import model.entities.Customer;
import view.QuestNutri;
import view.panels.components.GenericJPanel;
import view.utils.LanguageUtil;

public class CustomersPage extends GenericListPage<Customer> {
    private static final long serialVersionUID = 1L;
    
    public static String placeHolderTxtName = new LanguageUtil("Nome", "Name").get();
    public static String placeHolderTxtPhoneNumber = new LanguageUtil("Telefone", "Phone Number").get();

    public CustomersPage(GenericJPanel ownerPanel) {
        super(ownerPanel, new String[]{"CPF", placeHolderTxtName, placeHolderTxtPhoneNumber}, new LanguageUtil("Controle de Clientes", "Customer's Control").get());
        if(QuestNutri.isEditAuth()) setCreateBtn(() -> CustomerController.openNewCustomerFrame(() -> updateList()));
    }

    @Override
    protected List<Customer> setInitialList() {
        return CustomerController.searchCustomers("", "");
    }
    
    @Override
    protected BiFunction<String, String, List<Customer>> setSearchMethod() {
    	return CustomerController::searchCustomers;
    }

    @Override
    protected Object[] setColumnNames() {
    	return new String[]{placeHolderTxtName, "CPF", placeHolderTxtPhoneNumber};
    }
    
    @Override
    protected Function<Customer, Object[]> setRowMapper() {
    	return customer -> new Object[]{customer.getName(), customer.getFormattedCpf(), customer.getFormattedPhoneNumber()};
    }
    
    @Override
    protected Function<Customer, Runnable> setDoubleClickAction() {
    	return customer -> () -> CustomerController.openCustomerFrame(customer);
    }
    
    @Override
    protected void setPopUpOptions() {
    	if(QuestNutri.isEditAuth()) createDeleteItemPopUpOption(new LanguageUtil("Excluir Registro", "Delete Record").get(), customer -> () -> CustomerController.deleteCustomer(customer));
    }
}

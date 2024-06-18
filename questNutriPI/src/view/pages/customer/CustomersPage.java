/**
 * Package que contém as páginas relacionadas a entidade Customer.
 */
package view.pages.customer;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

import controller.entities.CustomerController;
import model.entities.Customer;
import utils.view.LanguageUtil;
import view.QuestNutri;
import view.components.generics.GenericJPanel;
import view.pages.generics.GenericListPage;

/**
 * Página para controle de clientes, que lista e permite gerenciar os clientes.
 */
public class CustomersPage extends GenericListPage<Customer> {
    private static final long serialVersionUID = 1L;

    /**
     * Construtor da página de clientes.
     *
     * @param ownerPanel Painel proprietário onde esta página será exibida.
     */
    public CustomersPage(GenericJPanel ownerPanel) {
        super(ownerPanel, 
        	  new String[]{"CPF", new LanguageUtil("Nome", "Name").get(), new LanguageUtil("Telefone", "Phone Number").get()}, 
        	  new LanguageUtil("Controle de Clientes", "Customer's Control").get());
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
        return new Object[] {
                new LanguageUtil("Nome", "Name").get(), 
                "CPF",
                new LanguageUtil("Telefone", "Phone Number").get(), 
            };

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

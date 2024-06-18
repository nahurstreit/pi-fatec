package view.pages.customer.profile;

import controller.entities.CustomerController;
import model.entities.Customer;
import utils.validations.Validate;
import utils.view.LanguageUtil;
import view.components.buttons.StdButton;
import view.components.generics.GenericJPanel;
import view.frames.NewCustomerFrame;
import view.pages.generics.GenericFormPage;

/**
 * Página de formulário para criação de um novo cliente.
 * Estende a classe CustomerFormPage.
 */
public class NewCustomerForm extends CustomerFormPage {
	private static final long serialVersionUID = 1L;

	private NewCustomerFrame frame;
	private Runnable onCreate;
	
    /**
     * Construtor da página de formulário para um novo cliente.
     *
     * @param ownerPanel Painel proprietário que contém esta página.
     * @param frame      Janela de criação de novo cliente.
     * @param onCreate   Ação a ser executada após a criação do cliente.
     */
	public NewCustomerForm(GenericJPanel ownerPanel, NewCustomerFrame frame, Runnable onCreate) {
		super(ownerPanel, new Customer());
		this.frame = frame;
		this.onCreate = onCreate;
	}
	
    /**
     * Constrói o formulário para criação de um novo cliente.
     *
     * @return o próprio objeto para implementar fluent interface.
     */
	@Override
	protected GenericFormPage buildForm() {
		contentPanel.add(btnCreateCustomer(), contentPanel.gbc);
		contentPanel.gbc.yP();
		
		build(personalInfo().setInteractBtn(null).setNameUpperDistance(0).setNameLowerDistance(0).init(), 
			  contactInfo().setInteractBtn(null).setNameUpperDistance(0).init());
		
		return this;
	}
	
    /**
     * Cria um botão para criar um novo cliente.
     *
     * @return Botão configurado para criar um novo cliente.
     */
	public StdButton btnCreateCustomer() {
		StdButton btnCreate = StdButton.stdBtnConfig(new LanguageUtil("Criar", "Create").get());
		btnCreate.setAction(() -> {
			if(Validate.formFields(name, birth, cpf, height, gender, email, phoneNumber)) {
				CustomerController.createNewCustomer(frame, customer, 
						name.getValue(), birth.getValue(), cpf.getValue(),
						height.getValue(), gender.getValue(),
						email.getValue(), phoneNumber.getValue());
				onCreate.run();
			}
		});
		
		return btnCreate;
	}

}
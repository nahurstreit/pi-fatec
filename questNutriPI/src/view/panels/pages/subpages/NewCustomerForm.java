package view.panels.pages.subpages;

import controller.CustomerController;
import model.entities.Customer;
import view.components.StdButton;
import view.frames.NewCustomerFrame;
import view.panels.components.GenericJPanel;
import view.panels.pages.GenericFormPage;

public class NewCustomerForm extends CustomerFormPage {
	private static final long serialVersionUID = 1L;

	private NewCustomerFrame frame;
	
	public NewCustomerForm(GenericJPanel ownerPanel, NewCustomerFrame frame) {
		super(ownerPanel, new Customer());
		this.frame = frame;
	}
	
	@Override
	protected GenericFormPage buildForm() {
		contentPanel.add(btnCreateCustomer(), contentPanel.gbc);
		contentPanel.gbc.yP();
		
		build(personalInfo().setInteractBtn(null).setUpperDistance(0).setDownDistance(0).init(), 
			  contactInfo().setInteractBtn(null).setUpperDistance(0).init());
		
		return this;
	}
	
	public StdButton btnCreateCustomer() {
		StdButton btnCreate = stdBtnConfig("Criar");
		
		btnCreate.setAction(() -> {
			CustomerController.createNewCustomer(frame, customer, 
					name.getValue(), birth.getValue(), cpf.getValue(),
					height.getValue(), gender.getValue(),
					email.getValue(), phoneNumber.getValue());
		});
		
		return btnCreate;
	}

}
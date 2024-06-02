package view.panels.pages.subpages;

import controller.CustomerController;
import model.entities.Customer;
import validate.Validate;
import view.components.StdButton;
import view.frames.NewCustomerFrame;
import view.panels.components.GenericJPanel;
import view.panels.pages.GenericFormPage;

public class NewCustomerForm extends CustomerFormPage {
	private static final long serialVersionUID = 1L;

	private NewCustomerFrame frame;
	private Runnable onCreate;
	
	
	public NewCustomerForm(GenericJPanel ownerPanel, NewCustomerFrame frame, Runnable onCreate) {
		super(ownerPanel, new Customer());
		this.frame = frame;
		this.onCreate = onCreate;
	}
	
	@Override
	protected GenericFormPage buildForm() {
		contentPanel.add(btnCreateCustomer(), contentPanel.gbc);
		contentPanel.gbc.yP();
		
		build(personalInfo().setInteractBtn(null).setNameUpperDistance(0).setNameLowerDistance(0).init(), 
			  contactInfo().setInteractBtn(null).setNameUpperDistance(0).init());
		
		return this;
	}
	
	public StdButton btnCreateCustomer() {
		StdButton btnCreate = StdButton.stdBtnConfig("Criar");
		
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
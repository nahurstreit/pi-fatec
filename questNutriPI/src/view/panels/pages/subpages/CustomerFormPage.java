package view.panels.pages.subpages;

import model.entities.Customer;
import view.components.StdButton;
import view.panels.components.FormComponent;
import view.panels.components.GenericJPanel;
import view.panels.pages.GenericPage;
import view.panels.pages.subpages.components.FormBoxInput;

public class CustomerFormPage extends GenericPage {
	private static final long serialVersionUID = 1L;

	public CustomerFormPage(GenericJPanel ownerPanel, Customer customer) {
		super(ownerPanel);
		ltGridBag();
		
		//Criando as linhas do form
		FormBoxInput name = new FormBoxInput(this).setLbl("Nome").setValue(customer.name);
		FormBoxInput email = new FormBoxInput(this).setLbl("E-mail").setValue(customer.email);
		FormBoxInput birth = new FormBoxInput(this)
								.setLbl("Data de Nascimento")
								.setMask("##/##/####")
								.setValue(customer.birth.toString())
								.clearMaskOnSelect(false);
		
		FormBoxInput cpf = new FormBoxInput(this)
								.setLbl("CPF")
								.setMask("###-###.###-##")
								.setValue(customer.getCPF())
								.clearMaskOnSelect(true);
		
		FormBoxInput telefone = new FormBoxInput(this)
								.setLbl("Telefone")
								.setMask("(##) # ####-####")
								.setValue(customer.phoneNumber)
								.clearMaskOnSelect(false);
		
		
		//Criando o form de informações pessoais
		FormComponent customerForm = new FormComponent(this)
			.addRow(name, email)
			.addRow(cpf, telefone, birth)
			.init();

		add(customerForm, gbc.grid(0).fill("HORIZONTAL").wgt(1.0).anchor("NORTHWEST"));
		add(new StdButton("Testando", () -> showInputs(name, email, cpf, telefone)), gbc.yP());
	}
	
	private void showInputs(FormBoxInput ...inputs) {
		for(FormBoxInput input: inputs) {
			System.out.println(input.getValue());
		}
	}

}
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
		FormBoxInput birth = new FormBoxInput(this)
								.setLbl("Data de Nascimento")
								.setMask("##/##/####")
								.setValue(customer.birth.toString())
								.clearMaskOnSelect(false);
		
		FormBoxInput cpf = new FormBoxInput(this)
								.setLbl("CPF")
								.setMask("###-###.###-##")
								.setValue(customer.getCPF())
								.clearMaskOnSelect(true)
								.setValidation(value -> {
									try {
										Integer.parseInt(value);
										return true;
									} catch (Exception e) {
										return false;
									}
								});
		
		
		FormBoxInput height = new FormBoxInput(this)
									.setLbl("Altura")
									.setValue(customer.height + "")
									.setValidation(value -> {
										try {
											double pValue = Double.parseDouble(value);
											return pValue >= 0.0 && pValue <= 300.00;
										} catch (Exception e) {
											return false;
										}
									}, "Altura inválida!")
									.setResponseMiddleware(Double::parseDouble);
		
		FormBoxInput gender = new FormBoxInput(this)
									.setLbl("Gênero")
									.setValue(customer.gender + "")
									.setValidation(value -> {
										try {
											char c = value.toUpperCase().charAt(0);
											if(c == 'M' || c == 'F') {
												return true;
											}
											return false;
										} catch (Exception e) {
											return false;
										}
									}, "Gênero inválido!");
		//Criando o form de informações pessoais
		FormComponent personalInfo = new FormComponent(this)
			.addRow(name, birth, cpf)
			.addRow(height, gender)
			.setUpName("Informações pessoais")
			.init();
		add(personalInfo, gbc.grid(0).fill("HORIZONTAL").wgt(1.0).anchor("NORTHWEST"));
		
		
		
		FormBoxInput email = new FormBoxInput(this).setLbl("E-mail").setValue(customer.email);
		FormBoxInput phoneNumber = new FormBoxInput(this)
				.setLbl("Telefone")
				.setMask("(##) # ####-####")
				.setValue(customer.phoneNumber)
				.clearMaskOnSelect(false);
		
		//Criando o form de informações de contato
		FormComponent contactInfo = new FormComponent(this)
			.addRow(email, phoneNumber)
			.setUpName("Informações de contato")
			.init();
		add(contactInfo, gbc.yP());
		
		
		FormBoxInput addrCep = new FormBoxInput(this)
										.setLbl("CEP")
										.setMask("#####-###")
										.clearMaskOnSelect(true)
										.setValue(customer.address.cep);
		
		FormBoxInput addrNumber = new FormBoxInput(this)
				.setLbl("Número")
				.setValue(customer.address.number +"");
		
		FormBoxInput addrComp = new FormBoxInput(this)
				.setLbl("Complemento")
				.setValue(customer.address.comp +"");
		
		FormBoxInput addrStreet = new FormBoxInput(this)
				.setLbl("Rua")
				.setValue(customer.address.street);
		
		FormBoxInput addrHood = new FormBoxInput(this)
				.setLbl("Bairro")
				.setValue(customer.address.hood);
		
		FormBoxInput addrCity = new FormBoxInput(this)
				.setLbl("Cidade")
				.setValue(customer.address.city);
		
		FormBoxInput addrState = new FormBoxInput(this)
				.setLbl("Estado")
				.setValue(customer.address.state);
		
		//Criando o form de informações de endereço
		FormComponent addrInfo = new FormComponent(this)
			.addRow(addrCep, addrNumber, addrComp)
			.addRow(addrStreet)
			.addRow(addrHood, addrCity, addrState)
			.setUpName("Endereço")
			.init();
		add(addrInfo, gbc.yP());
		
		add(new StdButton("Testando", () -> showInputs(height)), gbc.yP());
	}
	
	private void showInputs(FormBoxInput ...inputs) {
		for(FormBoxInput input: inputs) {
			System.out.println(input.getValue() instanceof Double);
		}
	}

}
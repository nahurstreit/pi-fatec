package view.panels.pages.subpages;

import java.awt.Color;

import javax.swing.JScrollPane;

import controller.CustomerController;
import model.entities.Customer;
import utils.CpfValidate;
import validate.Validate;
import validate.ValidationRule;
import view.components.FormBoxInput;
import view.components.FormSection;
import view.components.StdButton;
import view.panels.components.GeneralJPanelSettings;
import view.panels.components.GenericJPanel;
import view.panels.pages.GenericPage;

public class CustomerFormPage extends GenericPage {
	private static final long serialVersionUID = 1L;

	public CustomerFormPage(GenericJPanel ownerPanel, Customer customer) {
		super(ownerPanel);
		ltGridBag();
		setBackground(Color.white);
		
		// Criação de um JPanel para conter os formulários
        GenericJPanel contentPanel = new GenericJPanel().ltGridBag();
        
     // Configuração do JScrollPane
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		//Criando as linhas do form
		FormBoxInput name = new FormBoxInput(this)
								.setLbl("Nome")
								.setValue(customer.name)
								.addValidation(
										new ValidationRule(
												value -> {
													return !Validate.hasNumber(value);
												},
												"Não é permitido ter números!"
										),
										new ValidationRule(
												value -> {
													return Validate.sizeBetween(value, 5, 50);
												},
												"Tamanho precisa ser entre 5 e 50.")
								);//#end addValidation
		
		FormBoxInput birth = new FormBoxInput(this)
								.setLbl("Data de Nascimento")
								.setMask("##/##/####")
								.setValue(customer.getBirth())
								.clearMaskOnSelect(false);
		
		FormBoxInput cpf = new FormBoxInput(this)
								.setLbl("CPF")
								.setMask("###.###.###-##")
								.setValue(customer.getCPF())
								.clearMaskOnSelect(true)
								.addValidation(
										new ValidationRule(
												value -> {	
													return CpfValidate.cpfValidate(value);
												},
												"CPF inválido!")
								);//#end addValidation
		
		FormBoxInput height = new FormBoxInput(this)
									.setLbl("Altura (cm)")
									.setValue(customer.height + "")
									.addValidation(
											new ValidationRule(
													value -> {
														try {
															double pValue = Double.parseDouble(value);
															return pValue >= 0.0 && pValue <= 300.00;
														} catch (Exception e) {
															return false;
														}
													},
													"Altura inválida!")
									)//#end addValidation
;
		
		FormBoxInput gender = new FormBoxInput(this).setComboBoxInput(customer.gender, "M", "F")
									.setLbl("Gênero");
		
		//Criando o form de informações pessoais
		FormSection personalInfo = new FormSection(this)
			.addRow(name, birth, cpf)
			.addRow(height, gender)
			.setUpName("Informações pessoais")
			.setInteractBtn(
					new StdButton("Salvar", 
							() -> CustomerController.saveCustomerPersonalInfo(
										customer,
										(String) name.getValue(),
										(String) birth.getValue(),
										(String) cpf.getValue(),
										Double.parseDouble((String) height.getValue()),
										(String) gender.getValue()
									))
					.setUpFont(GeneralJPanelSettings.STD_BOLD_FONT.deriveFont(12f))
					.setColors(Color.white, STD_BLUE_COLOR))
			.init();
		contentPanel.add(personalInfo, contentPanel.gbc.grid(0).fill("BOTH").wgt(1.0).anchor("NORTHWEST"));
		
		
		
		FormBoxInput email = new FormBoxInput(this).setLbl("E-mail").setValue(customer.email);
		FormBoxInput phoneNumber = new FormBoxInput(this)
				.setLbl("Telefone")
				.setMask("(##) # ####-####")
				.setValue(customer.phoneNumber)
				.clearMaskOnSelect(false);
		
		//Criando o form de informações de contato
		FormSection contactInfo = new FormSection(this)
			.addRow(email, phoneNumber)
			.setUpName("Informações de contato")
			.init();
		contentPanel.add(contactInfo, contentPanel.gbc.yP());
		
		
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
		
		String[] brazilStates = {
			    "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", 
			    "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", 
			    "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"
			};
		
		FormBoxInput addrState = new FormBoxInput(this).setComboBoxInput(customer.address.state, brazilStates)
				.setLbl("Estado");
		
		//Criando o form de informações de endereço
		FormSection addrInfo = new FormSection(this)
			.addRow(addrCep, addrNumber, addrComp)
			.addRow(addrStreet)
			.addRow(addrHood, addrCity, addrState)
			.setUpName("Endereço")
			.init();
		contentPanel.add(addrInfo, contentPanel.gbc.yP());
		
		
		add(scrollPane, gbc.fill("BOTH").wgt(1.0).grid(0));
	}
	
	private void showInputs(FormBoxInput ...inputs) {
		for(FormBoxInput input: inputs) {
			System.out.println(input.getValue() instanceof Double);
		}
	}

}
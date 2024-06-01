package view.panels.pages.subpages;

import javax.swing.JOptionPane;

import controller.CustomerController;
import model.entities.Customer;
import utils.CpfValidate;
import validate.Validate;
import validate.ValidationRule;
import view.components.FormBoxInput;
import view.components.FormSection;
import view.components.StdButton;
import view.panels.components.GenericJPanel;
import view.panels.pages.GenericFormPage;

public class CustomerFormPage extends GenericFormPage {
	private static final long serialVersionUID = 1L;
	protected Customer customer;
	

	//Campos do formulário para poder acessar os resultados rapidamente
		//personalInfo
			protected FormBoxInput name;
			protected FormBoxInput birth;
			protected FormBoxInput cpf;
			protected FormBoxInput height;
			protected FormBoxInput gender;

		//contactInfo
			protected FormBoxInput email;
			protected FormBoxInput phoneNumber;
			
		//addrInfo!
			protected FormBoxInput addrCep;
			protected FormBoxInput addrNumber;
			protected FormBoxInput addrComp;
			protected FormBoxInput addrStreet;
			protected FormBoxInput addrHood;
			protected FormBoxInput addrCity;
			protected FormBoxInput addrState;
			
		//Valores padrões de validação
			private final int MIN_SIZE_NAME = 5;
			private final int MAX_SIZE_NAME = 50;
			
			private final double MIN_HEIGHT = 0.0;
			private final double MAX_HEIGHT = 300.0;
			
			private final String STD_DATE_PATTERN = "dd/mm/aaaa";
			
	
	public CustomerFormPage(GenericJPanel ownerPanel, Customer customer) {
		super(ownerPanel);
		this.customer = customer;
		buildForm();
	}

	@Override
	protected GenericFormPage buildForm() {
		build(personalInfo().init(), contactInfo().init(), addrInfo().init());
		return this;
	}
	
	private StdButton btnSavePersonalInfo() {
		StdButton saveBtn = stdBtnConfig("Salvar");
		
		saveBtn.setAction(() -> {
			if(validateFields(name, birth, cpf, height, gender)) {
			   	if(CustomerController.saveCustPersonalInfo(customer, 
			   			name.getValue(), birth.getValue(), cpf.getValue(), 
			   			height.getValue(), gender.getValue())
			   		) {
				   		JOptionPane.showMessageDialog(null, "Dados salvos!");
				   	} else {
				   		JOptionPane.showMessageDialog(null, "Não foi possível salvar.");
				   	}
			}

	   });
		
		return saveBtn;
	}
	
	protected FormSection personalInfo() {
		//Criando as linhas do form
		name = new FormBoxInput(this).setLbl("Nome")
									 .setValue(customer.name)
									 .addValidation(
										  new ValidationRule(
												  value -> {
													  return !Validate.hasNumber(value);
												  }, "Não é permitido ter números!"),
										  new ValidationRule(
												  value -> {
													  return Validate.sizeBetween(value, MIN_SIZE_NAME, MAX_SIZE_NAME);
												  }, "Tamanho precisa ser entre "+MIN_SIZE_NAME+" e "+MAX_SIZE_NAME+"."));
		
		birth = new FormBoxInput(this).setLbl("Data de Nascimento")
									  .setMask("##/##/####")
									  .setValue(customer.getBirth())
									  .clearMaskOnSelect(false)
									  .addValidation(
											  new ValidationRule(
													  value -> {
														  return !Validate.hasChar(value, '/');
													  }, "Não é permitido ter letras!"),
											  new ValidationRule(
													  value -> {
														  return Validate.isDate(value);
													  }, "Data inválida! Preencha: "+STD_DATE_PATTERN));
		
		cpf = new FormBoxInput(this).setLbl("CPF")
									.setMask("###.###.###-##")
									.setValue(customer.getCPF())
									.clearMaskOnSelect(true)
									.addValidation(
											new ValidationRule(
													  value -> {
														  return !Validate.hasChar(value, '.', '-');
													  }, "Não é permitido ter letras!"),
											new ValidationRule(
													  value -> {
														  String v = value.replaceAll("\\D", "");
														  if(!v.isBlank()) {
															  return (v.length() == 11);
														  }
														  return false;
													  }, "Digite um CPF válido."),
											new ValidationRule(
													value -> {	
														return CpfValidate.cpfValidate(value);
													}, "CPF inválido!"));
		
		height = new FormBoxInput(this).setLbl("Altura (cm)")
									   .setValue(customer.height + "")
									   .addValidation(
											   new ValidationRule(
														  value -> {
															  return !Validate.hasChar(value, '.', ',');
														  }, "Não é permitido ter letras!"),
											   new ValidationRule(
														  value -> {
															  return !Validate.haveSpecifChar(value, ',');
														  }, "Use ponto no lugar de vírgula."),
											   new ValidationRule(
													   value -> {
														   try {
															   double pValue = Double.parseDouble(value);
															   return pValue >= MIN_HEIGHT && pValue <= MAX_HEIGHT;
														   } catch (Exception e) {
															   return false;
														   }
													   },"Digite uma altura entre "+ MIN_HEIGHT +" e "+MAX_HEIGHT+"!"));
		
		
		gender = new FormBoxInput(this).setLbl("Gênero")
									   .setComboBoxInput(customer.gender, "M", "F");
		
		//Criando o form de informações pessoais
		FormSection personalInfo = new FormSection(this).setUpName("Informações pessoais")
														.addRow(name, birth, cpf)
														.addRow(height, gender)
														.setInteractBtn(btnSavePersonalInfo());
		
		return personalInfo;
	}
	
	private StdButton btnSaveContactInfo() {
		StdButton saveBtn = stdBtnConfig("Salvar");
		
		saveBtn.setAction(() -> {
		   	if(CustomerController.saveCustomerContactInfo(
		   			customer,
		   			email.getValue(),
		   			phoneNumber.getValue())
		   	  ) { //end if
			   		JOptionPane.showMessageDialog(null, "Dados salvos!");
			   	} else {
			   		JOptionPane.showMessageDialog(null, "Não foi possível salvar.");
			   	}
	   });
		
		return saveBtn;
	}
	
	protected FormSection contactInfo() {
		
		email = new FormBoxInput(this).setLbl("E-mail")
								      .setValue(customer.email);
		
		phoneNumber = new FormBoxInput(this).setLbl("Telefone")
										 	.setMask("(##) # ####-####")
										 	.setValue(customer.phoneNumber)
										 	.clearMaskOnSelect(false)
										 	.addValidation(
										 			new ValidationRule(
															  value -> {
																  return !Validate.hasChar(value, '(', ')', '-');
															  }, "Não é permitido ter letras!"));
		
		//Criando o form de informações de contato
		FormSection contactInfo = new FormSection(this).setUpName("Informações de contato")
													   .addRow(email, phoneNumber)
													   .setInteractBtn(btnSaveContactInfo());
		
		return contactInfo;
	}
	
	private StdButton btnSaveAddrInfo() {
		StdButton saveBtn = stdBtnConfig("Salvar");
		
		saveBtn.setAction(() -> {
		   	if(CustomerController.saveCustomerAddrInfo(
		   			customer,
		   			addrCep.getValue(),
		   			addrNumber.getValue(),
		   			addrComp.getValue(),
		   			addrStreet.getValue(),
		   			addrHood.getValue(),
		   			addrCity.getValue(),
		   			addrState.getValue())
		   	  ) { //end if
			   		JOptionPane.showMessageDialog(null, "Dados salvos!");
			   	} else {
			   		JOptionPane.showMessageDialog(null, "Não foi possível salvar.");
			   	}
	   });
		
		return saveBtn;
	}
	
	protected FormSection addrInfo() {
		addrCep = new FormBoxInput(this).setLbl("CEP")
 									    .setMask("#####-###")
 									    .clearMaskOnSelect(true);
		
		addrNumber = new FormBoxInput(this).setLbl("Número");
	
		addrComp = new FormBoxInput(this).setLbl("Complemento");
		
		addrStreet = new FormBoxInput(this).setLbl("Rua");
		
		addrHood = new FormBoxInput(this).setLbl("Bairro");
		
		addrCity = new FormBoxInput(this).setLbl("Cidade");
	
		String[] brazilStates = {
			    "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", 
			    "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", 
			    "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"
			};
	
		addrState = new FormBoxInput(this).setLbl("Estado");
		
		if(customer.address != null) {
			addrCep.setValue(customer.address.cep + "");
			addrNumber.setValue(customer.address.number + ""); 
			addrComp.setValue(customer.address.comp);
			addrStreet.setValue(customer.address.street);
			addrHood.setValue(customer.address.hood);
			addrCity.setValue(customer.address.city);
			addrState.setComboBoxInput(customer.address.state, brazilStates);
		} else {
			addrState.setComboBoxInput(null, brazilStates);
		}
	
		//Criando o form de informações de endereço
		FormSection addrInfo = new FormSection(this).setUpName("Endereço")
													.addRow(addrCep, addrNumber, addrComp)
													.addRow(addrStreet)
													.addRow(addrHood, addrCity, addrState)
													.setInteractBtn(btnSaveAddrInfo());
		return addrInfo;
	}
}
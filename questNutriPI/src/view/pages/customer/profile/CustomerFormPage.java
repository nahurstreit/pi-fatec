/**
 * Package que contém as classes que controlam a visualização do perfil de Customers.
 */
package view.pages.customer.profile;

import controller.entities.CustomerController;
import controller.entities.WeightController;
import model.entities.Customer;
import utils.interfaces.CustomerFieldRules;
import utils.validations.Validate;
import utils.validations.ValidationRule;
import utils.view.LanguageUtil;
import view.QuestNutri;
import view.components.QuestNutriJOP;
import view.components.buttons.StdButton;
import view.components.forms.FormBoxInput;
import view.components.forms.FormSection;
import view.components.generics.GenericJPanel;
import view.pages.generics.GenericFormPage;

/**
 * Página de formulário para edição do perfil do cliente.
 * Estende a classe GenericFormPage e implementa CustomerFieldRules.
 */
public class CustomerFormPage extends GenericFormPage implements CustomerFieldRules {
	private static final long serialVersionUID = 1L;
	protected Customer customer;

	//Campos do formulário para poder acessar os resultados rapidamente
		//personalInfo
			protected FormBoxInput name;
			protected FormBoxInput birth;
			protected FormBoxInput cpf;
			protected FormBoxInput height;
			protected FormBoxInput gender;
			protected FormBoxInput activityStatus;
			protected FormBoxInput lastWeight;

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
			
		private String txtHolder_btnSave = new LanguageUtil("Salvar", "Save").get();
			
	
    /**
     * Construtor da página de formulário do cliente.
     *
     * @param ownerPanel Painel proprietário que contém esta página.
     * @param customer   Cliente cujo perfil está sendo editado.
     */
	public CustomerFormPage(GenericJPanel ownerPanel, Customer customer) {
		super(ownerPanel);
		this.customer = customer;
		buildForm();
	}
	

    /**
     * Constrói o formulário, chamando métodos para configurar as seções do formulário.
     *
     * @return o próprio objeto para implementar fluent interface.
     */
	@Override
	protected GenericFormPage buildForm() {
		build(personalInfo().init(), contactInfo().init(), addrInfo().init());
		return this;
	}
	
    /**
     * Cria um botão padrão para salvar as informações pessoais do cliente.
     *
     * @return Botão configurado para salvar informações pessoais.
     */
	private StdButton btnSavePersonalInfo() {
		StdButton saveBtn = StdButton.stdBtnConfig(txtHolder_btnSave);
		
		saveBtn.setAction(() -> {
			if(Validate.formFields(name, birth, cpf, height, gender)) {
				CustomerController.saveCustPersonalInfo(customer, 
			   			name.getValue(), birth.getValue(), cpf.getValue(), 
			   			height.getValue(), gender.getValue(), activityStatus.getValue() );
			}

		});
		
		return saveBtn;
	}
	
    /**
     * Método para criação de regra de validação de tamanho de string entre min e max.
     *
     * @param min Tamanho mínimo da string.
     * @param max Tamanho máximo da string.
     * @return Regra de validação de tamanho.
     */
	protected ValidationRule strSizeBetween(int min, int max) {
		return new ValidationRule(value -> {
			if(value.length() != 0) {
				return value.length() >= min && value.length() <= max;
			} else {
				return true;
			}
			
		} , new LanguageUtil("Campo precisa ter entre "+min+ " e "+max+".", "Field size must be between "+min+ " and "+max+".").get());
	}
	
    /**
     * Configuração da seção de informações pessoais no formulário.
     *
     * @return Seção de informações pessoais configurada.
     */
	protected FormSection personalInfo() {
		//Criando as linhas do form
		name = new FormBoxInput(this).setLbl(new LanguageUtil("Nome", "Name").get())
									 .setValue(customer.name)
									 .addValidation(
										  new ValidationRule(
												  value -> {
													  return !Validate.hasNumber(value);
	                                              }, new LanguageUtil("Não é permitido ter números!", "Numbers are not allowed!").get()),
										  new ValidationRule(
												  value -> {
													  return Validate.sizeBetween(value, MIN_SIZE_NAME, MAX_SIZE_NAME);
	                                              }, new LanguageUtil("Tamanho precisa ser entre "+MIN_SIZE_NAME+" e "+MAX_SIZE_NAME+".", "Size must be between "+MIN_SIZE_NAME+" and "+MAX_SIZE_NAME+".").get())
									  )
									 .setRequired();
		
		birth = new FormBoxInput(this).setLbl(new LanguageUtil("Data de Nascimento", "Date of Birth").get())
									  .setMask("##/##/####")
									  .setValue(customer.getBirth())
									  .clearMaskOnSelect(false)
									  .addValidation(
											  new ValidationRule(
													  value -> {
														  return !Validate.hasChar(value, '/');
	                                                  }, new LanguageUtil("Digite apenas números.", "Enter only numbers.").get()),
											  new ValidationRule(
													  value -> {
														  return Validate.isDate(value);
	                                                  }, new LanguageUtil("Data inválida! Preencha: "+STD_DATE_PATTERN, "Invalid date! Format: "+STD_DATE_PATTERN).get()))
									  .setRequired();
		
		cpf = new FormBoxInput(this).setLbl("CPF")
									.setMask("###.###.###-##")
									.setValue(customer.getCPF())
									.clearMaskOnSelect(true)
									.setRequired();
		
		height = new FormBoxInput(this).setLbl(new LanguageUtil("Altura (cm)", "Height (cm)").get())
									   .setValue(customer.height + "")
									   .addValidation(
											   new ValidationRule(
														  value -> {
															  return !Validate.hasChar(value, '.', ',');
			                                              }, new LanguageUtil("Não é permitido ter letras!", "Letters are not allowed!").get()),
											   new ValidationRule(
														  value -> {
															  return !Validate.haveSpecifChar(value, ',');
			                                              }, new LanguageUtil("Use ponto no lugar de vírgula.", "Use dot instead of comma.").get()),
											   new ValidationRule(
													   value -> {
														   try {
															   double pValue = Double.parseDouble(value);
															   return pValue >= MIN_HEIGHT && pValue <= MAX_HEIGHT;
														   } catch (Exception e) {
															   return false;
														   }
		                                               }, new LanguageUtil("Digite uma altura entre "+ MIN_HEIGHT +" e "+MAX_HEIGHT+"!", "Enter a height between "+ MIN_HEIGHT +" and "+MAX_HEIGHT+"!").get()))
									   .setRequired();
		
		
		gender = new FormBoxInput(this).setLbl(new LanguageUtil("Gênero", "Gender").get())
									   .setComboBoxInput(customer.gender, "M", "F")
									   .setRequired();
		
		
        lastWeight = new FormBoxInput(this).setLbl(new LanguageUtil("Último peso (kg)", "Last Weight (kg)").get(), 14f)
										   .lockInput()
                                           .setHint(new LanguageUtil("Nenhum registro.", "No registers yet.").get());
		
		//Criando o form de informações pessoais
		FormSection personalInfo = new FormSection(this).setUpName(new LanguageUtil("Informações pessoais", "Personal Information").get())
														.addRow(name, birth, cpf);
		
		if(!QuestNutri.isEditAuth()) {
			name.lockInput();
			birth.lockInput();
			cpf.lockInput();
			height.lockInput();
			gender.lockInput();
			activityStatus.lockInput();
			
		} else {
			cpf.addValidation(
					new ValidationRule(
							  value -> {
								  return !Validate.hasChar(value, '.', '-');
                            }, new LanguageUtil("Não é permitido ter letras!", "Letters are not allowed!").get()),
					new ValidationRule(
							  value -> {
								  String v = value.replaceAll("\\D", "");
								  if(!v.isBlank()) {
									  return (v.length() == 11);
								  }
								  return false;
                            }, new LanguageUtil("Digite um CPF válido.", "Enter a valid CPF.").get()),
					new ValidationRule(
							  value -> {
								  return Validate.checkCpf(value);
                            }, new LanguageUtil("CPF inválido!", "Invalid CPF!").get()));
			
			personalInfo.setInteractBtn(btnSavePersonalInfo());
			lastWeight.setButtonBox(btnAccessWeights() , true);
		}
														
		
		if(customer.idCustomer != null) {
			if(customer.getLastWeight() != null) {
				lastWeight.setValue(customer.getLastWeight().value + "kg");
			}
			
			activityStatus = new FormBoxInput(this).setLbl(new LanguageUtil("Nível de atividade", "Activity Level").get())
												   .setComboBoxInput(customer.getActivityStatus()+"", "1", "2", "3", "4", "5");
			if(!QuestNutri.isEditAuth()) activityStatus.lockInput();
			personalInfo.addRow(height, gender, activityStatus, lastWeight);
		} else {
			personalInfo.addRow(height, gender);
		}
		
		return personalInfo;
	}
	
    /**
     * Cria um botão padrão para acessar os pesos registrados.
     *
     * @return Botão configurado para acesso aos pesos.
     */
	private StdButton btnAccessWeights() {
		StdButton btn = StdButton.stdBtnConfig(new LanguageUtil("Registrar", "Add new").get());
		btn.setAction(() -> {
			WeightController.openWeightFrame(this.getCallerFrame(), customer, lastWeight);
		});
		
		return btn;
	}
	
    /**
     * Cria um botão padrão para salvar as informações de contato do cliente.
     *
     * @return Botão configurado para salvar informações de contato.
     */
	private StdButton btnSaveContactInfo() {
		StdButton saveBtn = StdButton.stdBtnConfig(txtHolder_btnSave);
		
		saveBtn.setAction(() -> {
			if(Validate.formFields(email, phoneNumber)) {
			   	if(CustomerController.saveCustomerContactInfo(
			   			customer,
			   			email.getValue(),
			   			phoneNumber.getValue())
			   	  ) { //end if
			   			QuestNutriJOP.showMessageDialog(null, new LanguageUtil("Dados salvos!", "Data saved!").get());
				   	} else {
				   		QuestNutriJOP.showMessageDialog(null, new LanguageUtil("Não foi possível salvar.", "Unable to save.").get());
				   	}
			}

	   });
		
		return saveBtn;
	}
	
    /**
     * Configuração da seção de informações de contato no formulário.
     *
     * @return Seção de informações de contato configurada.
     */
	protected FormSection contactInfo() {
		email = new FormBoxInput(this).setLbl("E-mail")
								      .setValue(customer.email)
								      .setRequired()
								      .addValidation(strSizeBetween(MIN_SIZE_EMAIL, MAX_SIZE_EMAIL));
		
		phoneNumber = new FormBoxInput(this).setLbl(new LanguageUtil("Telefone", "Phone Number").get())
										 	.setMask("(##) # ####-####", text -> {
										 		if(text.length() == 11) {
										 			return true;
										 		} else {
										 			return false;
										 		}
										 	})
										 	.setMask("(##) ####-####", text -> {
										 		if(text.length() == 10) {
										 			return true;
										 		} else {
										 			return false;
										 		}
										 	})
										 	.setValue(customer.getFormattedPhoneNumber())
										 	.clearMaskOnSelect(false)
										 	.addValidation(
										 			new ValidationRule(
															  value -> {
																  return !Validate.hasChar(value, '(', ')', '-');
		                                                      }, new LanguageUtil("Não é permitido ter letras!", "Letters are not allowed!").get()),
										 			strSizeBetween(MIN_SIZE_PHONE, MAX_SIZE_PHONE))
										 	.setRequired();
		
		//Criando o form de informações de contato
		FormSection contactInfo = new FormSection(this).setUpName(new LanguageUtil("Informações de contato", "Contact Information").get())
													   .addRow(email, phoneNumber)
													   .setInteractBtn(btnSaveContactInfo());
		
		return contactInfo;
	}
	
    /**
     * Cria um botão padrão para salvar as informações de endereço do cliente.
     *
     * @return Botão configurado para salvar informações de endereço.
     */
	private StdButton btnSaveAddrInfo() {
		StdButton saveBtn = StdButton.stdBtnConfig(txtHolder_btnSave);
		
		saveBtn.setAction(() -> {
			if(Validate.formFields(addrCep, addrNumber, addrComp, addrStreet, addrHood, addrCity, addrState)) {
			   	if(CustomerController.saveCustomerAddrInfo(
			   			customer,
			   			addrCep.getValue(),
			   			addrNumber.getValue(),
			   			addrComp.isHintOn()? null : addrComp.getValue(),
			   			addrStreet.getValue(),
			   			addrHood.getValue(),
			   			addrCity.getValue(),
			   			addrState.getValue())
			   	  ) { //end if
			   			QuestNutriJOP.showMessageDialog(null, new LanguageUtil("Dados salvos!", "Data saved!").get());
				   	} else {
				   		QuestNutriJOP.showMessageDialog(null, new LanguageUtil("Não foi possível salvar.", "Unable to save.").get());
				   	}
			}

	   });
		
		return saveBtn;
	}
	
    /**
     * Configuração da seção de informações de endereço no formulário.
     *
     * @return Seção de informações de endereço configurada.
     */
	protected FormSection addrInfo() {
		addrCep = new FormBoxInput(this).setLbl("CEP")
 									    .setMask("#####-###")
 									    .clearMaskOnSelect(true)
 									    .addValidation(
 												new ValidationRule(
 														value -> {
 															return !Validate.hasChar(value, '-');
 		                                                }, new LanguageUtil("Não é permitido ter letras!", "Letters are not allowed!").get()),
 												new ValidationRule(
 														value -> {
 															return Validate.isBiggerThan(value, 8);
 		                                                }, new LanguageUtil("CEP Inválido! Deve ter 8 digitos.", "Invalid CEP! Must have 8 digits.").get()))
 									    .setRequired();
		
		addrNumber = new FormBoxInput(this).setLbl(new LanguageUtil("Número", "Number").get())
										   .addValidation(
												new ValidationRule(
														  value -> {
															  return !Validate.hasChar(value);
	                                                      }, new LanguageUtil("Não é permitido ter letras!", "Letters are not allowed!").get()))
										   .setRequired();
	
		addrComp = new FormBoxInput(this).setLbl(new LanguageUtil("Complemento", "Address 2").get());
		
		addrStreet = new FormBoxInput(this).setLbl(new LanguageUtil("Rua", "Street").get()).setRequired();
		
		addrHood = new FormBoxInput(this).setLbl(new LanguageUtil("Bairro", "Neighborhood").get()).setRequired();
		
		addrCity = new FormBoxInput(this).setLbl(new LanguageUtil("Cidade", "City").get()).setRequired();
	
		String[] brazilStates = {
			    "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", 
			    "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", 
			    "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"
			};
	
		addrState = new FormBoxInput(this).setLbl(new LanguageUtil("Estado", "State").get()).setRequired();
		
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
		FormSection addrInfo = new FormSection(this).setUpName(new LanguageUtil("Endereço", "Address").get())
													.addRow(addrCep, addrNumber, addrComp)
													.addRow(addrStreet)
													.addRow(addrHood, addrCity, addrState)
													.setInteractBtn(btnSaveAddrInfo());
		return addrInfo;
	}
}
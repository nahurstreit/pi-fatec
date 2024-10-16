/**
 * Package que contém as paginas relacionadas aos Alimentos sistema.
 */
package view.pages.aliment;

import controller.entities.AlimentController;
import model.entities.Aliment;
import utils.FoodUtil;
import utils.interfaces.IDoAction;
import utils.validations.Validate;
import utils.validations.ValidationRule;
import utils.view.LanguageUtil;
import view.components.buttons.StdButton;
import view.components.forms.FormBoxInput;
import view.components.forms.FormSection;
import view.components.generics.GenericJPanel;
import view.pages.generics.GenericFormPage;

/**
 * Página de formulário para gerenciar informações de alimentos.
 */
public class AlimentFormPage extends GenericFormPage {
	private static final long serialVersionUID = 1L;
	
	private Aliment aliment;
	
	//Essenciais
		private FormBoxInput name;
		private FormBoxInput group;
		private FormBoxInput kcalBox;
		private FormBoxInput carb;
		private FormBoxInput protein;
		private FormBoxInput fat;

	//Secundários
		private FormBoxInput humidity;
		private FormBoxInput kJ;
		private FormBoxInput dietaryFiber;
		private FormBoxInput cholesterol;
		private FormBoxInput sodium;
		private FormBoxInput calcium;
		private FormBoxInput magnesium;
		private FormBoxInput manganese;
		private FormBoxInput phosphorus;
		private FormBoxInput iron;
		private FormBoxInput potassium;
		private FormBoxInput copper;
		private FormBoxInput zinc;
		private FormBoxInput retinol;
		private FormBoxInput rE;
		private FormBoxInput rAE;
		private FormBoxInput thiamine;
		private FormBoxInput riboflavin;
		private FormBoxInput pyridoxine;
		private FormBoxInput niacin;
		private FormBoxInput vitaminC;
		private FormBoxInput ash;
	
	// Valores padrões de validação
		private final int MIN_SIZE_NAME = 5;
		private final int MAX_SIZE_NAME = 50;
		
		private IDoAction onUpdate;

    /**
     * Construtor para inicializar a página de formulário de alimentos.
     * 
     * @param ownerPanel O painel proprietário onde esta página será exibida.
     * @param aliment    O alimento associado a esta página de formulário.
     * @param onUpdate   Ação a ser executada quando uma atualização é realizada.
     */
	public AlimentFormPage(GenericJPanel ownerPanel, Aliment aliment, IDoAction onUpdate) {
		super(ownerPanel);
		this.aliment = aliment;
		if(onUpdate != null) {
			this.onUpdate = onUpdate;
		} else {
			this.onUpdate = () -> {};
		}
		buildForm();
	}
	
    /**
     * Regra de validação para valores do tipo double.
     * 
     * @return A regra de validação para verificar se o valor é um double.
     */
	public ValidationRule isDouble() {
		return new ValidationRule(
					value -> {
						if(value.equals("Digite aqui...") || value.equals("Type here...")) {
							return true;
						}
						try {
						Double.parseDouble(value.replace(',', '.'));
						return true;
					} catch (Exception e) {
						return false;
					}
					}, new LanguageUtil("Não é permitido ter letras!", "Letters are not allowed!").get());
		
	}
	
	@Override
	protected AlimentFormPage buildForm() {
		GenericJPanel upperPanel = new GenericJPanel();
		
		 if (aliment.isCustom()) {
		        upperPanel.add(saveBtn(), contentPanel.gbc);
		        contentPanel.gbc.yP();
		    }
		
		this.contentPanel.add(upperPanel);
		
		build(essentialInfo().init(),basicInfo().init());
		return this;
	}
	
    /**
     * Cria o botão de salvar para o formulário.
     * 
     * @return O botão configurado para salvar as informações do alimento.
     */
	protected StdButton saveBtn() {
		StdButton saveBtn = StdButton.stdBtnConfig(new LanguageUtil("Salvar", "Save").get());
		
		saveBtn.setAction(() -> {
			if(Validate.formFields(name, kcalBox, carb, protein, fat, humidity, dietaryFiber, cholesterol, sodium, calcium, 
								  magnesium, manganese, phosphorus, iron, potassium, copper, zinc, retinol, rE, rAE, thiamine,
								  riboflavin,pyridoxine,niacin,vitaminC,ash)) {
				AlimentController.saveAliment(
						aliment,
						name.getValue(), 
						group.getValue(), 
						kcalBox.getValue(), 
						kJ.getValue(),
						carb.getValue(),
						protein.getValue(),  
						fat.getValue(),  
						humidity.getValue(),  
						dietaryFiber.getValue(),  
						cholesterol.getValue(),
						sodium.getValue(),  
						calcium.getValue(),  
						magnesium.getValue(),  
						manganese.getValue(),  
						phosphorus.getValue(),  
						iron.getValue(),
						potassium.getValue(),  
						copper.getValue(),  
						zinc.getValue(),  
						retinol.getValue(),
						rE.getValue(),
						rAE.getValue(),
						thiamine.getValue(),
						riboflavin.getValue(),
						pyridoxine.getValue(),
						niacin.getValue(),
						vitaminC.getValue(),
						ash.getValue(),
						onUpdate
						);

			}
			
		});
		
		return saveBtn;
	}
	
	 /**
     * Configura a seção de informações essenciais do formulário.
     * 
     * @return A seção configurada para exibir as informações essenciais do alimento.
     */
	protected FormSection essentialInfo() {
		//Essenciais
			name = new FormBoxInput(this).setLbl(new LanguageUtil("Nome", "Name").get())
										 .setValue(aliment.name)
										 .addValidation(
												 new ValidationRule(
														 value -> {
															 return !Validate.hasNumber(value);
														 }, new LanguageUtil("Não é permitido ter números!", "Numbers are not allowed!").get()),
												 new ValidationRule(
														 value -> {
															 return Validate.sizeBetween(value, MIN_SIZE_NAME, MAX_SIZE_NAME);
														 }, new LanguageUtil("Tamanho precisa ser entre "+MIN_SIZE_NAME+" e "+MAX_SIZE_NAME+".", "Size must be between "+MIN_SIZE_NAME+" and "+MAX_SIZE_NAME+".").get())
												 );
			
			group = new FormBoxInput(this).setLbl(new LanguageUtil("Grupo Alimentar", "Group").get())
										  .setValue(aliment.alimentGroup)
										  .lockInput();
			
			kcalBox = new FormBoxInput(this).setLbl(new LanguageUtil("Calorias (kcal)", "Calories (kcal)").get())
					 					 	.setValue(FoodUtil.formatNumber(aliment.kcal))
					 					 	.addValidation(isDouble());
			
			carb = new FormBoxInput(this).setLbl(new LanguageUtil("Carboidratos (g)", "Carb (g)").get())
					 					 .setValue(aliment.carb)
					 					 .addValidation(isDouble());
			
			protein = new FormBoxInput(this).setLbl(new LanguageUtil("Proteína (g)", "Protein (g)").get())
					 						.setValue(aliment.protein)
					 						.addValidation(isDouble());
			
			fat = new FormBoxInput(this).setLbl(new LanguageUtil("Gordura", "Fat").get())
					 					.setValue(aliment.fat)
					 					.addValidation(isDouble());
			
		
			
		
		FormSection essentialInfo = new FormSection(this).setUpName(new LanguageUtil("Informações essenciais", "Essential information").get());
		
		if(aliment.isCustom()) {
			//Essenciais
				name.setRequired();
				kcalBox.setRequired();
				carb.setRequired();
				protein.setRequired();
				fat.setRequired();
			
			group.setValue("Customizado");
			
			
		} else {
			//Essenciais
				name.lockInput();
				kcalBox.lockInput();
				carb.lockInput();
				protein.lockInput();
				fat.lockInput();
			
				essentialInfo.hideRequiredLbl();
		}
		
		essentialInfo.addRow(name, group, kcalBox)
				 .addRow(carb, protein, fat);
		
		return essentialInfo;
	}

    /**
     * Configura a seção de informações básicas do formulário.
     * 
     * @return A seção configurada para exibir as informações básicas do alimento.
     */
	protected FormSection basicInfo() {
		//Secundários
		humidity = new FormBoxInput(this).setLbl(new LanguageUtil("Umidade (%)", "Humidity (%)").get())
										 .setValue(aliment.humidity)
										 .addValidation(isDouble());
		
		kJ = new FormBoxInput(this).setLbl(new LanguageUtil("quilojoule (kj)", "kilojoule (kj)").get())
				 .setValue(aliment.kJ)
				 .addValidation(isDouble());
		
		dietaryFiber = new FormBoxInput(this).setLbl(new LanguageUtil("Fibra dietética", "Dietary fiber").get())	
											 .setValue(aliment.dietaryFiber)
											 .addValidation(isDouble());
		
		cholesterol = new FormBoxInput(this).setLbl(new LanguageUtil("Colesterol (mg)", "Cholesterol (mg)").get())	
				 							.setValue(aliment.cholesterol)
				 							.addValidation(isDouble());
		
		sodium = new FormBoxInput(this).setLbl(new LanguageUtil("Sódio (mg)", "Sodium (mg)").get())	
									   .setValue(aliment.sodium)
									   .addValidation(isDouble());
		
		calcium = new FormBoxInput(this).setLbl(new LanguageUtil("Cálcio (mg)", "Calcium (mg)").get())	
										.setValue(aliment.calcium)
										.addValidation(isDouble());
		
		magnesium = new FormBoxInput(this).setLbl(new LanguageUtil("Magnésio (mg)", "Magnesium (mg)").get())	
				                          .setValue(aliment.magnesium)
				                          .addValidation(isDouble());

		manganese = new FormBoxInput(this).setLbl(new LanguageUtil("Manganês (mg)", "Manganese (mg)").get())	
                						  .setValue(aliment.manganese)
                						  .addValidation(isDouble());
		
		phosphorus = new FormBoxInput(this).setLbl(new LanguageUtil("Fósforo (mg)", "Phosphorus (mg)").get())	
				  						   .setValue(aliment.phosphorus)
				  						   .addValidation(isDouble());

		iron = new FormBoxInput(this).setLbl(new LanguageUtil("Ferro (mg)", "Iron (mg)").get())	
				   				     .setValue(aliment.iron)
				   				     .addValidation(isDouble());

		potassium = new FormBoxInput(this).setLbl(new LanguageUtil("Potássio (mg)", "Potassium (mg)").get())	
				     						  .setValue(aliment.potassium)
				     						  .addValidation(isDouble());

		copper = new FormBoxInput(this).setLbl(new LanguageUtil("Cobre (mg)", "Copper (mg)").get())	
									   .setValue(aliment.copper)
									   .addValidation(isDouble());

		zinc = new FormBoxInput(this).setLbl(new LanguageUtil("Zinco (mg)", "Zinc (mg)").get())	
									 .setValue(aliment.zinc)
									 .addValidation(isDouble());

		retinol = new FormBoxInput(this).setLbl(new LanguageUtil("Retinol (mcg)", "Retinol (mcg)").get())	
				 						.setValue(aliment.retinol)
				 						.addValidation(isDouble());

		rE = new FormBoxInput(this).setLbl(new LanguageUtil("RE (mcg)", "RE (mcg)").get())	
								   .setValue(aliment.rE)
								   .addValidation(isDouble());
		
		rAE = new FormBoxInput(this).setLbl(new LanguageUtil("RAE (mcg)", "RAE (mcg)").get())	
									.setValue(aliment.rAE)
									.addValidation(isDouble());

		thiamine = new FormBoxInput(this).setLbl(new LanguageUtil("Tiamina (mg)", "Thiamine (mg)").get())	
										 .setValue(aliment.thiamine)
										 .addValidation(isDouble());
		
		riboflavin = new FormBoxInput(this).setLbl(new LanguageUtil("Riboflavina (mg)", "Riboflavin (mg)").get())	
										   .setValue(aliment.riboflavin)
										   .addValidation(isDouble());

		pyridoxine = new FormBoxInput(this).setLbl(new LanguageUtil("Piridoxina (mg)", "Pyridoxine (mg)").get())	
				   						   .setValue(aliment.pyridoxine)
				   						   .addValidation(isDouble());

		niacin = new FormBoxInput(this).setLbl(new LanguageUtil("Niacina (mg)", "Niacin (mg)").get())	
				   					   .setValue(aliment.niacin)
				   					   .addValidation(isDouble());
		
		vitaminC = new FormBoxInput(this).setLbl(new LanguageUtil("Vitamina C (mg)", "VitaminC (mg)").get())	
				   						.setValue(aliment.vitaminC)
				   						.addValidation(isDouble());
		
		ash = new FormBoxInput(this).setLbl(new LanguageUtil("Cinzas", "Ash").get())
									.setValue(aliment.ash)
									.addValidation(isDouble());
		
		FormSection basicInfo = new FormSection(this).setUpName(new LanguageUtil("Informações basicas", "Basic information").get());
		
		if(aliment.isCustom()) {
			basicInfo.hideRequiredLbl();
		} else {
			humidity.lockInput();
			dietaryFiber.lockInput();
			cholesterol.lockInput();
			sodium.lockInput();
			calcium.lockInput();
			magnesium.lockInput();
			manganese.lockInput();
			phosphorus.lockInput();
			iron.lockInput();
			potassium.lockInput();
			copper.lockInput();
			zinc.lockInput();
			retinol.lockInput();
			rE.lockInput();
			rAE.lockInput();
			thiamine.lockInput();
			riboflavin.lockInput();
			pyridoxine.lockInput();
			niacin.lockInput();
			vitaminC.lockInput();
			ash.lockInput();
		
			basicInfo.hideRequiredLbl();
		}
		
		basicInfo.addRow(humidity, dietaryFiber, cholesterol,sodium)
		 .addRow(calcium, magnesium,manganese,phosphorus)
		 .addRow(iron,potassium, copper, zinc)
		 .addRow(retinol, rE, rAE)
		 .addRow(thiamine, riboflavin, pyridoxine)
		 .addRow(niacin, vitaminC, ash);
		
		return basicInfo;
	}
}
package view.pages.aliment;

import jakarta.persistence.Column;
import model.entities.Aliment;
import utils.FoodUtil;
import utils.validations.Validate;
import utils.validations.ValidationRule;
import utils.view.LanguageUtil;
import view.components.QuestNutriJOP;
import view.components.buttons.StdButton;
import view.components.forms.FormBoxInput;
import view.components.forms.FormSection;
import view.components.generics.GenericJPanel;
import view.pages.generics.GenericFormPage;

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
		private FormBoxInput ash;
	
	// Valores padrões de validação
		private final int MIN_SIZE_NAME = 5;
		private final int MAX_SIZE_NAME = 50;

	public AlimentFormPage(GenericJPanel ownerPanel, Aliment aliment) {
		super(ownerPanel);
		this.aliment = aliment;
		buildForm();
	}
	
	public ValidationRule isDouble() {
		return new ValidationRule(
					value -> {
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
		build(basicInfo().init());
		return this;
	}
	
	protected StdButton saveBtn() {
		StdButton saveBtn = StdButton.stdBtnConfig(new LanguageUtil("Salvar", "Save").get());
		
		saveBtn.setAction(() -> {
			if(Validate.formFields(name, kcalBox, carb)) {
				
				
				QuestNutriJOP.showMessageDialog(null, "Você tentou salvar");
			}
			
		});
		
		return saveBtn;
	}
	
	protected FormSection basicInfo() {
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
			
			kcalBox = new FormBoxInput(this).setLbl(new LanguageUtil("Kcal", "Calories").get())
					 					 	.setValue(FoodUtil.formatNumber(aliment.kcal))
					 					 	.addValidation(isDouble());
			
			carb = new FormBoxInput(this).setLbl(new LanguageUtil("Carboidratos", "Carb").get())
					 					 .setValue(aliment.carb)
					 					 .addValidation(isDouble());
			
			protein = new FormBoxInput(this).setLbl(new LanguageUtil("Proteína", "Protein").get())
					 						.setValue(aliment.protein)
					 						.addValidation(isDouble());
			
			fat = new FormBoxInput(this).setLbl(new LanguageUtil("Gordura", "Fat").get())
					 					.setValue(aliment.fat)
					 					.addValidation(isDouble());
			
		//Secundários
			humidity = new FormBoxInput(this).setLbl(new LanguageUtil("Umidade (%)", "Humidity (%)").get())
											 .setValue(aliment.humidity)
											 .addValidation(
						 					 			new ValidationRule(
						 					 					value -> {
						 					 						return !Validate.hasChar(value, ',', '.', '%');
					                                              }, new LanguageUtil("Não é permitido ter letras!", "Letters are not allowed!").get())
						 					 			);
//			@Column(name = "dietaryFiber")
//			public String dietaryFiber;
//
//			@Column(name = "cholesterol")
//			public String cholesterol;
//
//			@Column(name = "sodium")
//			public String sodium;
//
//			@Column(name = "calcium")
//			public String calcium;
//
//			@Column(name = "magnesium")
//			public String magnesium;
//
//			@Column(name = "manganese")
//			public String manganese;
//
//			@Column(name = "phosphorus")
//			public String phosphorus;
//
//			@Column(name = "iron")
//			public String iron;
//
//			@Column(name = "potassium")
//			public String potassium;
//
//			@Column(name = "copper")
//			public String copper;
//
//			@Column(name = "zinc")
//			public String zinc;
//
//			@Column(name = "retinol")
//			public String retinol;
//
//			@Column(name = "RE")
//			public String rE;
//
//			@Column(name = "RAE")
//			public String rAE;
//
//			@Column(name = "thiamine")
//			public String thiamine;
//
//			@Column(name = "riboflavin")
//			public String riboflavin;
//
//			@Column(name = "pyridoxine")
//			public String pyridoxine;
//
//			@Column(name = "niacin")
//			public String niacin;
//
//			@Column(name = "vitaminC")
//			public String vitaminC;
			
			ash = new FormBoxInput(this).setLbl(new LanguageUtil("Cinzas", "Ash").get())
										.setValue(aliment.ash)
										.addValidation(isDouble());
			
			
		
		FormSection basicInfo = new FormSection(this).setUpName(new LanguageUtil("Informações Básicas", "Basic Information").get());
		
		if(aliment.isCustom()) {
			//Essenciais
				name.setRequired();
				kcalBox.setRequired();
				carb.setRequired();
				protein.setRequired();
				fat.setRequired();
			
			group.setValue("Customizado");
			
			basicInfo.setInteractBtn(saveBtn()); //Adicionando botão de save
		} else {
			//Essenciais
				name.lockInput();
				kcalBox.lockInput();
				carb.lockInput();
				protein.lockInput();
				fat.lockInput();
			
			//Secundários
				humidity.lockInput();
				ash.lockInput();
			
			basicInfo.hideRequiredLbl(); //Tirando o requiredLbL
		}
		
		basicInfo.addRow(name, group, kcalBox)
				 .addRow(carb, protein, fat)
				 .addRow(humidity, ash);
		
		
		return basicInfo;
	}

}
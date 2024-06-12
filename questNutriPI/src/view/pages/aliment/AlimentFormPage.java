package view.pages.aliment;

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
					}, new LanguageUtil("Inválido", "Invalid").get());
		
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
										 .setValue(aliment.name);
			
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
					 						.setValue(aliment.protein);
			
			fat = new FormBoxInput(this).setLbl(new LanguageUtil("Gordura", "Fat").get())
					 					.setValue(aliment.fat);
			
		//Secundários
			humidity = new FormBoxInput(this).setLbl(new LanguageUtil("Umidade (%)", "Humidity (%)").get())
											 .setValue(aliment.humidity);
			
			ash = new FormBoxInput(this).setLbl(new LanguageUtil("Cinzas", "Ash").get())
										.setValue(aliment.ash);
		
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
				 .addRow(ash);
		
		
		return basicInfo;
	}

}
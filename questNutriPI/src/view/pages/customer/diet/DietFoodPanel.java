package view.pages.customer.diet;

import java.awt.Dimension;

import javax.swing.JLabel;

import controller.entities.FoodController;
import model.entities.Food;
import utils.FoodUtil;
import utils.validations.Validate;
import utils.view.LanguageUtil;
import view.components.QuestNutriJOP;
import view.components.buttons.StdButton;
import view.components.forms.FormBoxInput;
import view.components.forms.FormSection;
import view.components.generics.GenericJPanel;
import view.components.labels.ActionLbl;

/**
 * Painel para exibição e edição de detalhes de alimentos em uma refeição específica.
 */
public class DietFoodPanel extends GenericJPanel {
	private static final long serialVersionUID = 1L;
	
	private ActionLbl lblAliName;
	private FormBoxInput quantityInput;
	private FormBoxInput unityQtInput;
	private Food food;
	
	private DietMealPanel mealPanel;
	
	private boolean unsavedChanges;

    /**
     * Construtor para inicializar o DietFoodPanel com um alimento específico.
     *
     * @param ownerPanel Painel de refeição pai que contém este DietFoodPanel.
     * @param food Food a ser exibida e editada neste painel.
     */
	public DietFoodPanel(DietMealPanel ownerPanel, Food food) {
		super(ownerPanel);
		this.mealPanel = ownerPanel;
		ltGridBag();
		setBGColor(STD_BLUE_COLOR);
		this.food = food;
		
		lblAliName = new ActionLbl(food.aliment.name).setUpFont(STD_BOLD_FONT.deriveFont(12f))
													 .setUpColor(STD_WHITE_COLOR)
													 .setAction(() -> {
														 FoodController.openFoodInfo(getCallerFrame(), food, ownerPanel::foodWasUpdated);
													 });
		lblAliName.setPreferredSize(new Dimension(100, 75));
		
		quantityInput = new FormBoxInput(this).setLbl(new LanguageUtil("Quantidade", "Quantity").get(), 8f)
											  .setValue(food.quantity + "")
											  .setLblColor(STD_WHITE_COLOR)
											  .setLateralDistance(null, 0);
		
		unityQtInput = new FormBoxInput(this).setLbl(new LanguageUtil("Unidade de Medida", "Measure Unity").get(), 8f)
											 .setLblColor(STD_WHITE_COLOR)
											 .setComboBoxInput(food.unityQt, "gramas")
											 .setComboFont(STD_BOLD_FONT.deriveFont(12f));
		
		FormSection alimentVariables = new FormSection(this).addRow(quantityInput, unityQtInput)
															.setInternalColor(STD_BLUE_COLOR)
															.hideRequiredLbl()
															.hideBorder()
															.setUpperDistance(0)
															.setLowerDistance(0)
															.setLateralDistance(0)
															.init();
		
		GenericJPanel infoAndSaveBtn = new GenericJPanel().ltGridBag().setBGColor(STD_NULL_COLOR);
		JLabel kcalInfo = new JLabel(
				String.format("%.2f", FoodUtil.calculateFoodKcal(food)) + " kcal",
				JLabel.CENTER
		);
		
		kcalInfo.setFont(STD_BOLD_FONT.deriveFont(13f));
		kcalInfo.setForeground(STD_WHITE_COLOR);
		
		infoAndSaveBtn.add(kcalInfo, infoAndSaveBtn.gbc.fill("HORIZONTAL").anchor("CENTER").grid(0).wgt(1.0, 0).insets("3", 0, 10));
		
		
		StdButton saveBtn = StdButton.stdBtnConfigInvert(new LanguageUtil("Salvar", "Save").get());
		saveBtn.setAction(() -> {
			if(saveThis()) {
				QuestNutriJOP.showMessageDialog(null, new LanguageUtil("Alimento salvo!", "Food saved!").get());
				unsavedChanges = false;
				mealPanel.foodWasUpdated();
			} else {
				QuestNutriJOP.showMessageDialog(null, new LanguageUtil("Não foi possível salvar o alimento.", "The food couldn't be saved.").get());

			}
		});
		
		infoAndSaveBtn.add(saveBtn, infoAndSaveBtn.gbc.yP().insets(10));
		
		this.add(lblAliName, gbc.fill("HORIZONTAL").anchor("WEST").wgt(1.0).grid(0).insets(0, 10));
		this.add(alimentVariables, gbc.xP());
		this.add(infoAndSaveBtn, gbc.xP().fill("VERTICAL").wgt(0, 1.0));
	}
	
    /**
     * Verifica se as alterações neste painel foram salvas.
     *
     * @return true se as alterações foram salvas, false caso contrário.
     */
	public boolean isSaved() {
		return !unsavedChanges;
	}
	
	
    /**
     * Salva as informações atuais da Food.
     *
     * @return true se a Food foi salva com sucesso, false caso contrário.
     */
	public boolean saveThis() {
		if(Validate.formFields(quantityInput)) {
			return FoodController.updateFoodInfo(food, unityQtInput.getValue(), quantityInput.getValue());
		} else {
			return false;
		}
	}

}
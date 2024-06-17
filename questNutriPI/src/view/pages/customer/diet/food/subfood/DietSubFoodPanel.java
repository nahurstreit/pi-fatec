package view.pages.customer.diet.food.subfood;

import java.awt.Dimension;

import controller.entities.SubFoodController;
import model.entities.Aliment;
import model.entities.Food;
import model.entities.SubFood; // Importa a entidade SubFood
import utils.interfaces.ActionFunction;
import utils.validations.Validate;
import utils.view.LanguageUtil;
import view.components.QuestNutriJOP;
import view.components.buttons.StdButton;
import view.components.forms.FormBoxInput;
import view.components.forms.FormSection;
import view.components.generics.GenericJPanel;
import view.components.labels.ActionLbl;
import view.components.utils.IDoAction;
import view.pages.customer.diet.food.FoodInfoPanel;

public class DietSubFoodPanel extends GenericJPanel {
    private static final long serialVersionUID = 1L;
    
    private ActionLbl lblAliName;
    private FormBoxInput quantityInput;
    private FormBoxInput unityQtInput;
    private SubFood subFood;
    
    @SuppressWarnings("unused")
	private Food food;
    
    private GenericJPanel editPanel;
    
    @SuppressWarnings("unused")
	private IDoAction afterUpdate;

    public DietSubFoodPanel(FoodInfoPanel ownerPanel, Food ownerFood, SubFood subFood, ActionFunction<Aliment, SubFood> updateSubFoodTable, IDoAction afterUpdate) {
        super(ownerPanel);
    	this.food = ownerFood;
        ltGridBag();
        setBGColor(STD_BLUE_COLOR);
        this.subFood = subFood;
        if(afterUpdate != null) {
        	this.afterUpdate = afterUpdate;
        } else {
        	this.afterUpdate = afterUpdate;
        }
        
        this.setMaximumSize(new Dimension(10, 110));
        this.setMaximumSize(getMaximumSize());
        this.setPreferredSize(getMaximumSize());
        
        
        lblAliName = new ActionLbl(subFood.aliment.name).setUpFont(STD_BOLD_FONT.deriveFont(12f))
                                                       .setUpColor(STD_WHITE_COLOR);
        if(updateSubFoodTable != null) {
            lblAliName.setNewAction(() -> {
                updateSubFoodTable.execute(subFood);
            });
        }
                                                       
        lblAliName.setPreferredSize(new Dimension(100, 75));
        
        quantityInput = new FormBoxInput(this).setLbl(new LanguageUtil("Quantidade", "Quantity").get(), 8f)
                                              .setValue(subFood.quantity + "")
                                              .setLblColor(STD_WHITE_COLOR)
                                              .setLateralDistance(0);
        
        unityQtInput = new FormBoxInput(this).setLbl(new LanguageUtil("Unidade de Medida", "Measure Unity").get(), 8f)
                                             .setLblColor(STD_WHITE_COLOR)
                                             .setComboBoxInput(subFood.unityQt, "gramas")
                                             .setComboFont(STD_REGULAR_FONT.deriveFont(9f))
                                             .setLateralDistance(10);
        
        FormSection alimentVariables = new FormSection(this).addRow(quantityInput, unityQtInput)
                                                            .setInternalColor(STD_BLUE_COLOR)
                                                            .hideRequiredLbl()
                                                            .hideBorder()
                                                            .setUpperDistance(0)
                                                            .setLowerDistance(0)
                                                            .setLateralDistance(0)
                                                            .init();
        
        GenericJPanel infoAndSaveBtn = new GenericJPanel().ltGridBag().setBGColor(STD_NULL_COLOR);
        
        
        StdButton saveBtn = StdButton.stdBtnConfigInvert(new LanguageUtil("Salvar", "Save").get());
        saveBtn.setAction(() -> {
            if(saveThis()) {
            	afterUpdate.execute();
                QuestNutriJOP.showMessageDialog(null, new LanguageUtil("Alimento Substituto salvo!", "Replacement Food saved!").get());
            } else {
                QuestNutriJOP.showMessageDialog(null, new LanguageUtil("Não foi possível salvar o alimento.", "The food couldn't be saved.").get());
            }
        });
        
        infoAndSaveBtn.add(saveBtn, infoAndSaveBtn.gbc.yP().insets(10, 10, 0, 10));
        
        // Edit panel initialization
        editPanel = new GenericJPanel().ltGridBag().setBGColor(STD_BLUE_COLOR);
        
        StdButton deleteBtn = StdButton.stdBtnConfig(new LanguageUtil("Excluir", "Delete").get()).setBgColor(STD_RED_COLOR);
        deleteBtn.setAction(() -> {
        	if(SubFoodController.deleteSubFood(subFood)) {
        		afterUpdate.execute();
        		if(ownerPanel.getLookingId() == subFood.idSubFood) {
        			ownerPanel.emptySubFoodTable();
        		}
        	}
        	
        });
        
        StdButton chooseNewBtn = StdButton.stdBtnConfigInvert(new LanguageUtil("Escolher Novo", "Choose New").get());
        chooseNewBtn.setAction(() -> {
        	SubFoodController.openSubFoodUpdate(getCallerFrame(), subFood, () -> {
        		afterUpdate.execute();
        		updateSubFoodTable.execute(subFood);
        	});
        });
        
        editPanel.add(deleteBtn, editPanel.gbc.fill("HORIZONTAL").anchor("CENTER").wgt(1.0, 0).grid(0).insets(0, 0, 0, 10));
        editPanel.add(chooseNewBtn, editPanel.gbc.grid(1, 0).insets());
        
        StdButton editBtn = StdButton.stdBtnConfig(new LanguageUtil("Editar", "Edit").get()).setBgColor(STD_STRONG_GRAY_COLOR);
        editBtn.setAction(() -> {
            expandEdit();
        });
        
        infoAndSaveBtn.add(editBtn, infoAndSaveBtn.gbc.yP().insets(0, 10, 10, 10));
       
        
        this.add(lblAliName, gbc.fill("HORIZONTAL").anchor("WEST").wgt(1.0).grid(0).insets(0, 10));
        this.add(alimentVariables, gbc.xP());
        this.add(infoAndSaveBtn, gbc.xP().fill("VERTICAL").wgt(0, 1.0).insets(10));
    }
    
    private void expandEdit() {
        if (this.getComponentCount() > 0 && this.getComponent(this.getComponentCount() - 1) == editPanel) {
            this.remove(editPanel);
        } else {
            this.add(editPanel, gbc.fill("HORIZONTAL").grid(0, 1).wgt(1.0, 0).width("REMAINDER").insets(10));
        }
        this.revalidate();
        this.repaint();
    }
    
    public boolean saveThis() {
        if(Validate.formFields(quantityInput)) {
            return SubFoodController.updateSubFoodInfo(subFood, unityQtInput.getValue(), quantityInput.getValue());
        } else {
            return false;
        }
    }

}

package view.pages.customer.diet.food;

import java.awt.Dimension;

import javax.swing.JLabel;

import model.entities.Aliment;
import model.entities.Food;
import model.entities.SubFood; // Importa a entidade SubFood
import utils.interfaces.ActionFunction;
import utils.view.LanguageUtil;
import view.components.QuestNutriJOP;
import view.components.buttons.StdButton;
import view.components.forms.FormBoxInput;
import view.components.forms.FormSection;
import view.components.generics.GenericJPanel;
import view.components.labels.ActionLbl;

public class DietSubFoodPanel extends GenericJPanel {
    private static final long serialVersionUID = 1L;
    
    private ActionLbl lblAliName;
    private FormBoxInput quantityInput;
    private FormBoxInput unityQtInput;
    private SubFood subFood;
    
    private Food food;
    
    private boolean unsavedChanges;
    private GenericJPanel editPanel;

    public DietSubFoodPanel(Food ownerFood, SubFood subFood, ActionFunction<Aliment, SubFood> updateSubFoodTable) {
        this.food = ownerFood;
        ltGridBag();
        setBGColor(STD_BLUE_COLOR);
        this.subFood = subFood;
        
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
                                              .setLateralDistance(null, 0)
                                              .setValueChangedListener(value -> {
                                                    unsavedChanges = true;
                                                    System.out.println(unsavedChanges); // Debug para verificar se o listener está sendo invocado corretamente
                                                });
        
        unityQtInput = new FormBoxInput(this).setLbl(new LanguageUtil("Unidade de Medida", "Measure Unity").get(), 8f)
                                             .setLblColor(STD_WHITE_COLOR)
                                             .setComboBoxInput(subFood.unityQt, "gramas")
                                             .setComboFont(STD_REGULAR_FONT.deriveFont(9f))
                                             .setValueChangedListener(value -> {
                                                 unsavedChanges = true;
                                                 System.out.println(unsavedChanges);
                                             });
        
        FormSection alimentVariables = new FormSection(this).addRow(quantityInput, unityQtInput)
                                                            .setInternalColor(STD_BLUE_COLOR)
                                                            .hideRequiredLbl()
                                                            .hideBorder()
                                                            .setUpperDistance(0)
                                                            .setLowerDistance(0)
                                                            .setLateralDistance(0)
                                                            .init();
        
        GenericJPanel infoAndSaveBtn = new GenericJPanel().ltGridBag().setBGColor(STD_NULL_COLOR);
        JLabel kcalInfo = new JLabel("",
//                String.format("%.2f", FoodUtil.calculateFoodKcal(subFood)) + " kcal",
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
            } else {
                QuestNutriJOP.showMessageDialog(null, new LanguageUtil("Não foi possível salvar o alimento.", "The food couldn't be saved.").get());

            }
        });
        
        infoAndSaveBtn.add(saveBtn, infoAndSaveBtn.gbc.yP().insets(0, 10, 0, 10));
        
        // Edit panel initialization
        editPanel = new GenericJPanel().ltGridBag().setBGColor(STD_BLUE_COLOR);
        
        StdButton deleteBtn = StdButton.stdBtnConfig(new LanguageUtil("Excluir", "Delete").get()).setBgColor(STD_RED_COLOR);
        deleteBtn.setAction(() -> {
            // Handle delete action
        });
        
        StdButton chooseNewBtn = StdButton.stdBtnConfigInvert(new LanguageUtil("Escolher Novo", "Choose New").get());
        chooseNewBtn.setAction(() -> {
            // Handle choose new action
        });
        
        editPanel.add(deleteBtn, editPanel.gbc.fill("HORIZONTAL").anchor("CENTER").wgt(1.0, 0).grid(0).insets(0, 0, 0, 10));
        editPanel.add(chooseNewBtn, editPanel.gbc.grid(1, 0).insets());
        
        StdButton editBtn = StdButton.stdBtnConfig(new LanguageUtil("Editar", "Edit").get());
        editBtn.setAction(() -> {
            expandEdit();
        });
        
        infoAndSaveBtn.add(editBtn, infoAndSaveBtn.gbc.yP().insets(0, 10, 10, 10));
       
        
        this.add(lblAliName, gbc.fill("HORIZONTAL").anchor("WEST").wgt(1.0).grid(0).insets(0, 10));
        this.add(alimentVariables, gbc.xP());
        this.add(infoAndSaveBtn, gbc.xP().fill("VERTICAL").wgt(0, 1.0));
    }
    
    private void expandEdit() {
        // Toggle edit panel visibility
        if (this.getComponentCount() > 0 && this.getComponent(this.getComponentCount() - 1) == editPanel) {
            this.remove(editPanel);
        } else {
            this.add(editPanel, gbc.fill("HORIZONTAL").grid(0, 1).wgt(1.0, 0).width("REMAINDER").insets(10));
        }
        this.revalidate();
        this.repaint();
    }
    
    public boolean isSaved() {
        return !unsavedChanges;
    }
    
    public boolean saveThis() {
    	return true;
//        if(Validate.formFields(quantityInput)) {
//            return FoodController.updateSubFoodInfo(subFood, unityQtInput.getValue(), quantityInput.getValue());
//        } else {
//            return false;
//        }
    }

}

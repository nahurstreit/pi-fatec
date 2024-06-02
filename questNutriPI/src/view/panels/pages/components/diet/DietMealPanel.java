package view.panels.pages.components.diet;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JCheckBox;
import javax.swing.JLabel;

import model.entities.Food;
import model.entities.Meal;
import utils.FoodUtil;
import validate.Validate;
import validate.ValidationRule;
import view.components.BreakActionLbl;
import view.components.FormBoxInput;
import view.components.FormSection;
import view.components.StdButton;
import view.panels.components.GenericJPanel;
import view.utils.LanguageUtil;

public class DietMealPanel extends GenericJPanel {
    private static final long serialVersionUID = 1L;

    private Meal meal;
    private DietDayPanel dayPanel;

    private  FormSection nameBox;
    private FormSection timeBox;
    
    private GenericJPanel infoBox = new GenericJPanel().ltGridBag().setBGColor(Color.white);
    private BreakActionLbl lblMealName;
    private FormBoxInput inputMealName;
    
    private JLabel lblMealHour;
    private FormBoxInput inputMealHour;
    private FormBoxInput inputMealMinute;
    
    private JLabel lblMealKcal;
    
    private GenericJPanel foodsPanel = new GenericJPanel().ltGridBag();
    
    private GenericJPanel daysPanel = new GenericJPanel().ltGridBag().setBGColor(STD_STRONG_GRAY);
    
    //Botões de interação
    private StdButton saveBtn;
    private StdButton deleteBtn;
    private StdButton addFoodBtn;

    public DietMealPanel(Meal meal, DietDayPanel dayPanel) {
        this.meal = meal;
        this.dayPanel = dayPanel;
        ltGridBag();
        setBGColor(Color.white);
        insertFoods();
        
        lblMealHour = new JLabel(meal.hour.toString(), JLabel.CENTER);
        lblMealHour.setForeground(STD_BLUE_COLOR);
        makeSmallFont(lblMealHour);
        
        lblMealKcal = new JLabel(String.format("%.2f", FoodUtil.calculateMealKcal(meal)) + "kcal", JLabel.CENTER);
        lblMealKcal.setForeground(STD_BLUE_COLOR);
        makeSmallFont(lblMealKcal);
        
        lblMealName = new BreakActionLbl().setUpText(meal.name)
										  .setUpFont(STD_BOLD_FONT.deriveFont(15f))
										  .setUpColor(STD_BLUE_COLOR)
										  .centerText()
										  .init();
        
        placeLabels();
        
        this.add(infoBox, gbc.fill("BOTH").wgt(1.0).grid(0));
        
        if (!((meal.daysOfWeek & this.dayPanel.weekDay) == this.dayPanel.weekDay)) {
            this.setVisible(false);
        }
        
        initButtons();
    }

    //Método expandMeal
    public void expandMeal() {
    	placeDaysInfo();
        removeLabels();
        placeInputs();
        this.add(foodsPanel, gbc.yP().fill("BOTH").wgt(1.0));
        this.refresh();
        dayPanel.refresh();
    }

    //Método retractMeal
    public void retractMeal() {
    	removeDaysInfo();
    	removeInputs();
    	placeLabels();
    	this.remove(foodsPanel);
        this.refresh();
        dayPanel.refresh();
    }
    
    private void placeLabels() {
        infoBox.add(lblMealName, infoBox.gbc.grid(0).fill("BOTH").wgt(1.0).anchor("CENTER"));
        infoBox.add(lblMealHour, infoBox.gbc.yP());
    }
    
    private void removeLabels() {
    	infoBox.remove(lblMealName);
    	infoBox.remove(lblMealHour);
        infoBox.remove(lblMealKcal);
    }
    
    private void placeInputs() {
        //Adicionando a caixa de input do nome da refeição.
        inputMealName = new FormBoxInput(this).setValue(meal.name).setLbl("Nome da refeição", 8f);
        nameBox = new FormSection(this).addRow(inputMealName)
				   .hideRequiredLbl()
				   .setLowerDistance(0)
				   .setLateralDistance(0)
				   .setUpperDistance(0)
				   .setInternalColor(STD_WHITE_COLOR)
				   .hideBorder()
				   .init();
        infoBox.add(nameBox, infoBox.gbc.grid(0).fill("BOTH").wgt(1.0).anchor("CENTER"));
        
        inputMealHour = new FormBoxInput(this).setValue(meal.getHourPart())
        									  .setLbl("Hora", 8f)
        									  .setLateralDistance(null, 0)
        									  .addValidation(
        											  new ValidationRule(
        													  value -> {
        														  try {
        															int numberValue = Integer.parseInt(value);
																	return Validate.numberBetween(numberValue, 0, 23);
																} catch (Exception e) {
																	return false;
																}
        													  }, "Hora inválida."));
        inputMealHour.setMaximumSize(new Dimension(10, 10));
        
        inputMealMinute = new FormBoxInput(this).setValue(meal.getMinutePart()).setLbl("Minutos", 8f)
        										.addValidation(new ValidationRule(
  													  value -> {
														  try {
															int numberValue = Integer.parseInt(value);
															return Validate.numberBetween(numberValue, 0, 59);
														} catch (Exception e) {
															return false;
														}
													  }, "Minuto inválido."));
        timeBox = new FormSection(this).addRow(inputMealHour, inputMealMinute)
				   .hideRequiredLbl()
				   .setLowerDistance(0)
				   .setLateralDistance(0)
				   .setUpperDistance(0)
				   .setInternalColor(STD_WHITE_COLOR)
				   .hideBorder()
				   .init();
        infoBox.add(timeBox, infoBox.gbc.grid(1, 0).fill("BOTH").wgt(1.0).anchor("CENTER"));
        
        infoBox.add(makeBigFont(lblMealKcal), infoBox.gbc.grid(2, 0));
    }
    
    private void removeInputs() {
    	infoBox.remove(nameBox);
    	infoBox.remove(timeBox);
    	infoBox.remove(lblMealKcal);
    }
    
    private void placeDaysInfo() {
        // Array com os caracteres dos dias da semana
        String[] days = {"D", "S", "T", "Q", "Q", "S", "S"};
        int[] daysNumber = {64, 32, 16, 8, 4, 2, 1};

        daysPanel.gbc.anchor("CENTER"); // Ajusta o alinhamento
        // Adiciona os labels e checkboxes ao painel
        for (int i = 0; i < days.length; i++) {
            JLabel label = new JLabel(days[i]);
            label.setFont(STD_BOLD_FONT); // Define a fonte
            label.setForeground(Color.WHITE); // Define a cor do texto
            label.setBackground(STD_STRONG_GRAY); // Define a cor de fundo
            label.setOpaque(true); // Torna o fundo opaco
            daysPanel.add(label, daysPanel.gbc.grid(i, 0).wgt(1.0).insets("3", 0, 5).anchor("CENTER")); // Adiciona espaçamento

            JCheckBox checkBox = new JCheckBox();
            checkBox.setPreferredSize(new Dimension(30, 30));
            checkBox.setBackground(STD_STRONG_GRAY); // Define a cor de fundo da checkbox
            checkBox.setOpaque(true); // Torna o fundo opaco
            daysPanel.add(checkBox, daysPanel.gbc.yP().insets(5, 12, 5, 5)); // Adiciona espaçamento e centraliza
            
            if((daysNumber[i] & meal.daysOfWeek) == daysNumber[i]) {
            	checkBox.setSelected(true);
            }
        }

        this.remove(infoBox);
        
        this.add(daysPanel, gbc.grid(0).fill("BOTH").wgt(1.0));
        this.add(infoBox, gbc.fill("BOTH").wgt(1.0).grid(0, 1));
    }
    
    private void removeDaysInfo() {
    	this.remove(daysPanel);
    	this.remove(infoBox);
        this.add(infoBox, gbc.fill("BOTH").wgt(1.0).grid(0));
    }
    
    private void insertFoods() {
    	foodsPanel.gbc.fill("BOTH").wgt(1.0).anchor("NORTHWEST").grid(0).insets(10);
    	for(Food food: meal.getFoods()) {
    		foodsPanel.add(new DietFoodPanel(this, food), foodsPanel.gbc);
    		foodsPanel.gbc.yP();
    	}
    }
    
    private JLabel makeSmallFont(JLabel lbl) {
    	lbl.setFont(STD_BOLD_FONT.deriveFont(12f));
    	return lbl;
    }
    
    private JLabel makeBigFont(JLabel lbl) {
        lblMealKcal.setFont(STD_BOLD_FONT.deriveFont(15f));
        return lbl;
    }
    
    private void initButtons() {
    	saveBtn = StdButton.stdBtnConfig(new LanguageUtil("Salvar", "Save").get());
    	saveBtn.setFont(STD_BOLD_FONT.deriveFont(10f));
    	
    	deleteBtn = StdButton.stdBtnConfig(new LanguageUtil("Excluir", "Delete").get());
    	deleteBtn.setFont(STD_BOLD_FONT.deriveFont(10f));
    	
    	addFoodBtn = StdButton.stdBtnConfig(new LanguageUtil("Adicionar Alimento", "Add Food").get());
    	addFoodBtn.setFont(STD_BOLD_FONT.deriveFont(10f));
    }

}
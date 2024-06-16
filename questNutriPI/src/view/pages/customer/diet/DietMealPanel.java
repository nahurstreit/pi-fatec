package view.pages.customer.diet;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;

import controller.entities.FoodController;
import controller.entities.MealController;
import model.entities.Food;
import model.entities.Meal;
import utils.FoodUtil;
import utils.validations.Validate;
import utils.validations.ValidationRule;
import utils.view.LanguageUtil;
import view.components.QuestNutriJOP;
import view.components.buttons.StdButton;
import view.components.forms.FormBoxInput;
import view.components.forms.FormSection;
import view.components.generics.GenericJPanel;
import view.components.labels.BreakActionLbl;

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
    
    private GenericJPanel lateralRBox = new GenericJPanel().ltGridBag().setBGColor(STD_NULL_COLOR);
    private JLabel lblMealKcal;
    private StdButton expandOptBtn;
    
    private GenericJPanel foodsPanel = new GenericJPanel().ltGridBag();
    
    private DaySelectionPanel daysPanel;
    
    private GenericJPanel editPanel;
    
    
    private GenericJPanel buttonBox;
    
    //Botões de interação
    private StdButton saveBtn;
    private StdButton deleteBtn;
    private StdButton addFoodBtn;
    private StdButton copyMealBtn;

    public DietMealPanel(DietDayPanel dayPanel, Meal meal) {
    	super(dayPanel);
        this.meal = meal;
        this.dayPanel = dayPanel;
        ltGridBag();
        setBGColor(STD_WHITE_COLOR);
        insertFoods();
        initButtons();
        
        lblMealHour = new JLabel(meal.hour.toString(), JLabel.CENTER);
        lblMealHour.setForeground(STD_BLUE_COLOR);
        makeSmallFont(lblMealHour);
        
        lblMealKcal = new JLabel(String.format("%.2f", calculateMealKcal()) + "kcal", JLabel.CENTER);
        lblMealKcal.setForeground(STD_BLUE_COLOR);
        makeSmallFont(lblMealKcal);
        
        lblMealName = new BreakActionLbl().setUpText(meal.name)
										  .setUpFont(STD_BOLD_FONT.deriveFont(15f))
										  .setUpColor(STD_BLUE_COLOR)
										  .centerText()
										  .setAction(() -> {dayPanel.callFocus(this);})
										  .init();
        
        lateralRBox.add(makeBigFont(lblMealKcal), lateralRBox.gbc.grid(0).fill("BOTH").wgt(1.0).anchor("CENTER").height("REMAINDER"));
        
        buttonBox = new GenericJPanel().ltGridBag().setBGColor(STD_WHITE_COLOR);
        buttonBox.add(saveBtn, buttonBox.gbc.grid(0).height(1).width("REMAINDER").fill("HORIZONTAL"));
        buttonBox.add(expandOptBtn, buttonBox.gbc.grid(0, 1).width(1).fill("NONE"));
        buttonBox.add(addFoodBtn, buttonBox.gbc.grid(1, 1));
        lateralRBox.add(buttonBox, lateralRBox.gbc.xP());
        
    	editPanel = new GenericJPanel().ltGridBag().setBGColor(STD_WHITE_COLOR);
    	editPanel.add(deleteBtn, editPanel.gbc.fill("NONE").anchor("CENTER").grid(1, 0));
    	editPanel.add(copyMealBtn, editPanel.gbc.grid(0, 0).insets(0, 0, 0, 60));
        
    	daysPanel = new DaySelectionPanel(this, meal.daysOfWeek);
    	
        placeLabels();
        
        this.add(infoBox, gbc.fill("BOTH").wgt(1.0).grid(0));
        
        if (!((meal.daysOfWeek & this.dayPanel.weekDay) == this.dayPanel.weekDay)) {
            this.setVisible(false);
        }
        
    }
    
    public double calculateMealKcal() {
    	return FoodUtil.calculateMealKcal(this.meal);
    }
    
    public void updateMealKcal() {
    	lblMealKcal.setText(String.format("%.2f", calculateMealKcal()) + "kcal");
    }
    
    public void foodWasUpdated() {
    	dayPanel.callUpdate();
    	insertFoods();
    	updateMealKcal();
    	dayPanel.refresh();
    	this.refresh();
    }

    /**
     * Método que expande a visualização do painel de Meal, exibindo suas foods.
     */
    public void expandMeal() {
    	placeDaysInfo();
        removeLabels();
        placeInputs();
        this.add(foodsPanel, gbc.yP().fill("BOTH").wgt(1.0)); //Adicionando o painel de Meals
        this.refresh();
        dayPanel.refresh();
    }

    //Método retractMeal
    public void retractMeal() {
    	removeDaysInfo();
    	removeInputs();
    	placeLabels();
    	retractEdit();
    	this.remove(foodsPanel);
        this.refresh();
        dayPanel.refresh();
    }
    
    private void placeLabels() {
    	lblMealName.setText(meal.name);
    	lblMealHour.setText(meal.hour.toString());
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
        inputMealName = new FormBoxInput(this).setValue(meal.name).setLbl(new LanguageUtil("Nome da refeição", "Meal name").get(), 8f);
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
        									  .setLbl(new LanguageUtil("Hora", "Hour").get(), 8f)
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
        													  }, new LanguageUtil("Hora inválida.", "Invalid hour.").get()));
        inputMealHour.setMaximumSize(new Dimension(10, 10));
        
        inputMealMinute = new FormBoxInput(this).setValue(meal.getMinutePart()).setLbl(new LanguageUtil("Minuto", "Minute").get(), 8f)
        										.addValidation(new ValidationRule(
  													  value -> {
														  try {
															int numberValue = Integer.parseInt(value);
															return Validate.numberBetween(numberValue, 0, 59);
														} catch (Exception e) {
															return false;
														}
													  }, new LanguageUtil("Minuto inválido.", "Invalid minute.").get()));
        timeBox = new FormSection(this).addRow(inputMealHour, inputMealMinute)
				   .hideRequiredLbl()
				   .setLowerDistance(0)
				   .setLateralDistance(0)
				   .setUpperDistance(0)
				   .setInternalColor(STD_WHITE_COLOR)
				   .hideBorder()
				   .init();
        infoBox.add(timeBox, infoBox.gbc.grid(1, 0).fill("BOTH").wgt(1.0).anchor("CENTER"));
        
        infoBox.add(lateralRBox, infoBox.gbc.grid(2, 0).fill("BOTH").wgt(1.0).anchor("CENTER").insets(0, 10));
        infoBox.gbc.insets(); //Resetando o insets do gbc
    }
    
    public void removeInputs() {
    	try {
    		infoBox.remove(nameBox);
		} catch (Exception e) {}
    	
    	try {
    		infoBox.remove(timeBox);
		} catch (Exception e) {}
    	
    	try {
    		infoBox.remove(lateralRBox);
		} catch (Exception e) {}
    	
    }
    
//    private void createDaysSelection() {
//        //Array com os caracteres dos dias da semana
//        String[] days = {"D", "S", "T", "Q", "Q", "S", "S"};
//        int[] daysNumber = {64, 32, 16, 8, 4, 2, 1};
//
//        daysPanel.gbc.anchor("CENTER"); // Ajusta o alinhamento
//        //Adiciona os labels e checkboxes ao painel
//        for (int i = 0; i < days.length; i++) {
//            JLabel label = new JLabel(days[i]);
//            label.setFont(STD_BOLD_FONT); // Define a fonte
//            label.setForeground(Color.WHITE); // Define a cor do texto
//            label.setBackground(STD_STRONG_GRAY_COLOR); // Define a cor de fundo
//            label.setOpaque(true); // Torna o fundo opaco
//            daysPanel.add(label, daysPanel.gbc.grid(i, 0).wgt(1.0).insets("3", 0, 5).anchor("CENTER")); // Adiciona espaçamento
//
//            JCheckBox checkBox = new JCheckBox();
//            checkBox.setPreferredSize(new Dimension(30, 30));
//            checkBox.setBackground(STD_STRONG_GRAY_COLOR); // Define a cor de fundo da checkbox
//            checkBox.setOpaque(true); // Torna o fundo opaco
//            daysPanel.add(checkBox, daysPanel.gbc.yP().insets(5, 12, 5, 5)); // Adiciona espaçamento e centraliza
//            
//            if((daysNumber[i] & meal.daysOfWeek) == daysNumber[i]) {
//            	checkBox.setSelected(true);
//            }
//        }
//    }
    
    private void placeDaysInfo() {
        this.remove(infoBox);
        
        this.add(daysPanel, gbc.grid(0).fill("BOTH").wgt(1.0));
        this.add(infoBox, gbc.fill("BOTH").wgt(1.0).grid(0, 1));
    }
    
    private void removeDaysInfo() {
    	this.remove(daysPanel);
    	this.remove(infoBox);
        this.add(infoBox, gbc.fill("BOTH").wgt(1.0).grid(0));
    }
    
    public void insertFoods() {
    	try {
			foodsPanel.removeAll();
		} catch (Exception e) {}
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
    	saveBtn.setAction(() -> {
    		String hour = inputMealHour.getValue() + ":" + inputMealMinute.getValue();
    		
    		if(MealController.saveMeal(meal, inputMealName.getValue(), hour, daysPanel.getSelectedDaysValue())) {
    			QuestNutriJOP.showMessageDialog(null, new LanguageUtil("Refeição salva!", "Meal saved!").get());
    			dayPanel.mealWasUpdated(() -> {
    				dayPanel.rollTo(this);
    			});
    		} else {
    			QuestNutriJOP.showMessageDialog(null, new LanguageUtil("Não foi possível salvar", "Meal couldn't be saved!").get());
    		}
    		
    	});
    	
    	deleteBtn = StdButton.stdBtnConfig(new LanguageUtil("Excluir", "Delete").get()).setBgColor(STD_RED_COLOR);
    	deleteBtn.setAction(() -> {
    		meal.delete();
    		dayPanel.mealWasUpdated();
    		QuestNutriJOP.showMessageDialog(null, "Delete Meal");
    	});
    	
    	addFoodBtn = StdButton.stdBtnConfig("+").setBgColor(STD_STRONG_GRAY_COLOR);
    	addFoodBtn.setToolTipText(new LanguageUtil("Adicionar novo alimento", "Add new food").get());
    	addFoodBtn.setAction(() -> {
    		FoodController.openNewFoodFrame(meal);
    	});
    	
        expandOptBtn = StdButton.stdBtnConfig(new LanguageUtil("Editar", "Edit").get()).setBgColor(STD_STRONG_GRAY_COLOR);
        expandOptBtn.setAction(() -> {
        	expandEdit();
        });
        
        copyMealBtn = StdButton.stdBtnConfig(new LanguageUtil("Copiar esta Refeição", "Copy this Meal").get());
        copyMealBtn.setAction(() -> {
    		QuestNutriJOP.showMessageDialog(null, "Copy Meal");
    	});
    	
    }
    
    private void expandEdit() {
    	expandOptBtn.setAction(() -> {
    		retractEdit();
    	});
    	
    	this.remove(foodsPanel);
    	this.add(editPanel, gbc.grid(0, 3).fill("BOTH").wgt(1.0).insets(10, 0));
    	this.add(foodsPanel, gbc.yP().fill("BOTH").wgt(1.0)); //Adicionando o painel de Meals
    	this.refresh();
    	dayPanel.refresh();

    }
    
    private void retractEdit() {
    	expandOptBtn.setAction(() -> {
    		expandEdit();
    	});
    	
    	try {
        	this.remove(editPanel);
        	this.remove(foodsPanel);
        	this.add(foodsPanel, gbc.grid(0, 3).fill("BOTH").wgt(1.0)); //Adicionando o painel de Meals
        	this.refresh();
        	dayPanel.refresh();
		} catch (Exception e) {
		
		}
    }
 
}
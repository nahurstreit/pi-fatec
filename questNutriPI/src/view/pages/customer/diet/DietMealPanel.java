package view.pages.customer.diet;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

import controller.entities.FoodController;
import controller.entities.MealController;
import model.entities.Food;
import model.entities.Meal;
import utils.FoodUtil;
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
    
    //Labels dos macronutrientes
    private JLabel lblCarbMacro;
    private JLabel lblProteinMacro;
    private JLabel lblFatMacro;
    
    private final String CARB_MACRO_LBL;
    private final String PROTEIN_MACRO_LBL;
    private final String FAT_MACRO_LBL;
    
    private GenericJPanel macroPanel = new GenericJPanel().ltGridBag();
    
    private GenericJPanel foodsPanel = new GenericJPanel().ltGridBag();
    private List<DietFoodPanel> foodCards;
    
    private DaySelectionPanel daysPanel;
    
    private GenericJPanel editPanel;
    
    private GenericJPanel buttonBox;
    
    //Botões de interação
    private StdButton saveBtn;
    private StdButton deleteBtn;
    private StdButton addFoodBtn;
    
    private StdButton copyMealBtn;
    
    private double[] mealMacro;

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
    	//editPanel.add(copyMealBtn, editPanel.gbc.grid(0, 0).insets(0, 0, 0, 60));
        
    	daysPanel = new DaySelectionPanel(this, meal.daysOfWeek);
    	
        placeLabels();
        
        CARB_MACRO_LBL = new LanguageUtil("Carboidratos: ", "Carb: ").get();
        PROTEIN_MACRO_LBL = new LanguageUtil("Proteínas: ", "Protein: ").get();
        FAT_MACRO_LBL = new LanguageUtil("Gorduras: ", "Fat: ").get();
        
        lblCarbMacro = new JLabel("", JLabel.CENTER);
        lblCarbMacro.setFont(STD_BOLD_FONT.deriveFont(12f));
        lblCarbMacro.setForeground(STD_BLUE_COLOR);
        
        lblProteinMacro = new JLabel("", JLabel.CENTER);
        lblProteinMacro.setFont(STD_BOLD_FONT.deriveFont(12f));
        lblProteinMacro.setForeground(STD_BLUE_COLOR);
        
        lblFatMacro = new JLabel("", JLabel.CENTER);
        lblFatMacro.setFont(STD_BOLD_FONT.deriveFont(12f));
        lblFatMacro.setForeground(STD_BLUE_COLOR);
        
        macroPanel.add(lblCarbMacro, macroPanel.gbc.grid(0).anchor("CENTER").wgt(1.0).fill("BOTH"));
        macroPanel.add(lblProteinMacro, macroPanel.gbc.xP());
        macroPanel.add(lblFatMacro, macroPanel.gbc.xP());
        
        macroPanel.setBackground(infoBox.getBackground());
        
        this.add(infoBox, gbc.fill("BOTH").wgt(1.0).grid(0));
        
        if (!((meal.daysOfWeek & this.dayPanel.weekDay) == this.dayPanel.weekDay)) {
            this.setVisible(false);
        }
        
    }
    
    public double calculateMealKcal() {
    	double res = 0.0;
    	try {
			res = FoodUtil.calculateMealKcal(this.meal);
		} catch (Exception e) {
		}
    	return res;
    }
    
    public boolean hasThisMeal(Meal meal) {
    	return this.meal.equals(meal);
    }
    
    public void updateMealKcal() {
    	lblMealKcal.setText(String.format("%.2f", calculateMealKcal()) + "kcal");
    }
    
    public void foodWasUpdated() {
    	dayPanel.callUpdate();
    	insertFoods();
    	updateMealKcal();
    	updateMealMacro();
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
        updateMealMacro();
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
        
        inputMealHour = new FormBoxInput(this).setLbl(new LanguageUtil("Hora", "Hour").get(), 8f)
        									  .setLateralDistance(null, 0)
        									  .setSpinnerInput(2, 0, 23, 1)
        									  .setValue(meal.getHourPart());
        inputMealHour.setMaximumSize(new Dimension(10, 10));
        
        inputMealMinute = new FormBoxInput(this).setLbl(new LanguageUtil("Minuto", "Minute").get(), 8f)
        										.setSpinnerInput(0, 0, 59, 1)
        										.setValue(meal.getMinutePart());
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
        
        infoBox.add(macroPanel, infoBox.gbc.grid(0, 1).fill("BOTH").wgt(1.0).width("REMAINDER").insets(10));
        
        infoBox.gbc.insets().width(1); //Resetando o insets do gbc
    }
    
    private void updateMealMacro() {
    	mealMacro = FoodUtil.calculateMacronutrients(meal);
        lblCarbMacro.setText(CARB_MACRO_LBL+String.format("%.2f", mealMacro[0]));
        lblProteinMacro.setText(PROTEIN_MACRO_LBL+String.format("%.2f", mealMacro[1]));
        lblFatMacro.setText(FAT_MACRO_LBL+String.format("%.2f", mealMacro[2]));
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
    	
    	try {
    		infoBox.remove(macroPanel);
		} catch (Exception e) {}
    	
    }
    
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
			foodCards = new ArrayList<DietFoodPanel>();
		} catch (Exception e) {}
    		foodsPanel.gbc.fill("BOTH").wgt(1.0).anchor("NORTHWEST").grid(0).insets(10);
    		try {
    	   		for(Food food: meal.getFoods()) {
    	   			DietFoodPanel f = new DietFoodPanel(this, food);
    	   			foodCards.add(f);
    	   			foodsPanel.add(f, foodsPanel.gbc);
    	    		foodsPanel.gbc.yP();
        		}
			} catch (Exception e) {
				e.printStackTrace();
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
    		String hour = String.format("%02d:%02d", Integer.parseInt(inputMealHour.getValue()), 
    												 Integer.parseInt(inputMealMinute.getValue()));
    		
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
    		if(MealController.deleteMeal(meal)) {
    			dayPanel.mealWasUpdated();
    		}
    	});
    	
    	addFoodBtn = StdButton.stdBtnConfig("+").setBgColor(STD_STRONG_GRAY_COLOR);
    	addFoodBtn.setToolTipText(new LanguageUtil("Adicionar novo alimento", "Add new food").get());
    	addFoodBtn.setAction(() -> {
    		FoodController.openNewFoodFrame(getCallerFrame(), this, meal);
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
    
    public void forceSaveFoods() {
    	for(DietFoodPanel foodCard: foodCards) {
    		foodCard.saveThis();
    	}
    }
 
}
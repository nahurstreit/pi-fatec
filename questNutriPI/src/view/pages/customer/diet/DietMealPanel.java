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

/**
 * Painel para exibição e edição de uma refeição específica em um dia da dieta.
 * Este painel permite visualizar os detalhes da refeição, incluindo alimentos, 
 * macros nutricionais e botões de interação para salvar, excluir e editar a refeição.
 */
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

    /**
     * Construtor para inicializar o painel de refeição da dieta.
     * 
     * @param dayPanel Painel do dia da dieta ao qual esta refeição pertence.
     * @param meal     Refeição que será exibida e editada neste painel.
     */
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
    
    /**
     * Calcula o total de calorias da refeição com base nos alimentos associados.
     * 
     * @return Total de calorias da refeição.
     */
    public double calculateMealKcal() {
    	double res = 0.0;
    	try {
			res = FoodUtil.calculateMealKcal(this.meal);
		} catch (Exception e) {
		}
    	return res;
    }
    
    /**
     * Verifica se esta instância de DietMealPanel corresponde à refeição fornecida.
     * 
     * @param meal Refeição para comparar.
     * @return true se esta instância corresponder à refeição fornecida, false caso contrário.
     */
    public boolean hasThisMeal(Meal meal) {
    	return this.meal.equals(meal);
    }
    
    /**
     * Atualiza o texto das calorias da refeição com o valor atualizado.
     */
    public void updateMealKcal() {
    	lblMealKcal.setText(String.format("%.2f", calculateMealKcal()) + "kcal");
    }
    
    /**
     * Método chamado quando um alimento associado à refeição é atualizado.
     * Atualiza todos os componentes relacionados à exibição da refeição.
     */
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

    /**
     * Reduz a visualização do painel da refeição, ocultando os detalhes de alimentos.
     */
    public void retractMeal() {
    	removeDaysInfo();
    	removeInputs();
    	placeLabels();
    	retractEdit();
    	this.remove(foodsPanel);
        this.refresh();
        dayPanel.refresh();
    }
    
    /**
     * Coloca os labels de nome e horário da refeição no painel de informações.
     */
    private void placeLabels() {
    	lblMealName.setText(meal.name);
    	lblMealHour.setText(meal.hour.toString());
        infoBox.add(lblMealName, infoBox.gbc.grid(0).fill("BOTH").wgt(1.0).anchor("CENTER"));
        infoBox.add(lblMealHour, infoBox.gbc.yP());
    }
    
    /**
     * Remove os labels de nome, horário e calorias da refeição do painel de informações.
     */
    private void removeLabels() {
    	infoBox.remove(lblMealName);
    	infoBox.remove(lblMealHour);
        infoBox.remove(lblMealKcal);
    }
    
    /**
     * Coloca os campos de entrada para edição do nome, horário e outros detalhes da refeição.
     */
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
    
    /**
     * Remove os campos de entrada do painel de informações da refeição.
     */
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
    
    /**
     * Atualiza os valores dos macros nutricionais da refeição com base nos alimentos associados.
     */
    private void updateMealMacro() {
    	mealMacro = FoodUtil.calculateMacronutrients(meal);
        lblCarbMacro.setText(CARB_MACRO_LBL+String.format("%.2f", mealMacro[0]));
        lblProteinMacro.setText(PROTEIN_MACRO_LBL+String.format("%.2f", mealMacro[1]));
        lblFatMacro.setText(FAT_MACRO_LBL+String.format("%.2f", mealMacro[2]));
    }
    
    /**
     * Coloca o painel de seleção de dias da semana para a refeição no painel principal.
     */
    private void placeDaysInfo() {
        this.remove(infoBox);
        
        this.add(daysPanel, gbc.grid(0).fill("BOTH").wgt(1.0));
        this.add(infoBox, gbc.fill("BOTH").wgt(1.0).grid(0, 1));
    }
    
    /**
     * Remove o painel de seleção de dias da semana da refeição do painel principal.
     */
    private void removeDaysInfo() {
    	this.remove(daysPanel);
    	this.remove(infoBox);
        this.add(infoBox, gbc.fill("BOTH").wgt(1.0).grid(0));
    }
    
    /**
     * Insere os paineis de Foods referentes a essa Meal
     */
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
    
    /**
     * Aplica uma fonte menor ao rótulo fornecido.
     * @param lbl JLabel ao qual a fonte será aplicada.
     * @return JLabel com a fonte menor aplicada.
     */
    private JLabel makeSmallFont(JLabel lbl) {
    	lbl.setFont(STD_BOLD_FONT.deriveFont(12f));
    	return lbl;
    }
    
    /**
     * Aplica uma fonte maior ao rótulo fornecido.
     * @param lbl JLabel ao qual a fonte será aplicada.
     * @return JLabel com a fonte maior aplicada.
     */
    private JLabel makeBigFont(JLabel lbl) {
        lblMealKcal.setFont(STD_BOLD_FONT.deriveFont(15f));
        return lbl;
    }
    
    /**
     * Inicializa e configura os botões de interação do painel da refeição.
     */
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
    
    /**
     * Expande a visualização do painel para mostrar opções de edição da refeição.
     */
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
    
    /**
     * Retrai a visualização expandida do painel de edição da refeição.
     */
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
    
    /**
     * Força o salvamento das Foods associadas à refeição.
     */
    public void forceSaveFoods() {
    	for(DietFoodPanel foodCard: foodCards) {
    		foodCard.saveThis();
    	}
    }
 
}
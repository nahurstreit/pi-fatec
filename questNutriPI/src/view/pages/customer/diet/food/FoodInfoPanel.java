package view.pages.customer.diet.food;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.entities.FoodController;
import controller.entities.SubFoodController;
import model.entities.Aliment;
import model.entities.Food;
import model.entities.SubFood;
import utils.FoodUtil;
import utils.interfaces.ActionFunction;
import utils.validations.Validate;
import utils.view.LanguageUtil;
import view.components.QuestNutriJOP;
import view.components.buttons.StdButton;
import view.components.forms.FormBoxInput;
import view.components.forms.FormSection;
import view.components.generics.GenericJFrame;
import view.components.generics.GenericJPanel;
import view.components.tables.AlimentNutritionalTable;
import view.components.utils.IDoAction;
import view.pages.customer.diet.food.subfood.DietSubFoodPanel;
import view.pages.generics.GenericPage;

public class FoodInfoPanel extends GenericPage {
    private static final long serialVersionUID = 1L;
    
    private Food food;
    private JScrollPane currentInfoScroll;
    private JTable currentTable;
    
    private GenericJPanel subFoodInfo = new GenericJPanel().ltGridBag();
    private JLabel currentViewingSubFood;
    private JScrollPane subFoodInfoScroll;
    private JTable subFoodTable;
    
    private GenericJPanel subFoodsPanel = new GenericJPanel().ltGridBag();
    private JScrollPane subFoodsScroll = new JScrollPane();
    
    private JSplitPane topSplitPane;
    private JSplitPane bottomSplitPane;
    private JSplitPane mainSplitPane;
    
    private FormBoxInput currentAlimentTitle;
    private FormBoxInput foodQuantityInput;
    private FormBoxInput foodUnityQtInput;
    private FormBoxInput chooseAnotherFoodBtn;
    private FormBoxInput deleteFoodBtn;
    private FormBoxInput saveFoodBtn;
    
    private SubFood lookingSubFood;
    
    private IDoAction afterUpdate;
    
    private GenericJPanel holderNoSub = new GenericJPanel().ltGridBag();
    private JLabel lblNoSubFoodYet;

    public FoodInfoPanel(GenericJPanel ownerPanel, GenericJFrame callerFrame, Food food, IDoAction afterUpdate) {
        super(ownerPanel);
        setCallerFrame(callerFrame);
        ltGridBag();
        this.food = food;
        if(afterUpdate != null) {
        	this.afterUpdate = afterUpdate;
        } else {
        	this.afterUpdate = () -> {};
        }
        
        //Lbl do alimento atual da food
        	GenericJPanel foodPanel = new GenericJPanel().ltGridBag();
	        
	        
	        currentAlimentTitle = new FormBoxInput(this).setLbl(new LanguageUtil("Selecionado atualmente", "Current Selected").get(), STD_REGULAR_FONT.deriveFont(10f))
	        											.setCentralTitle(food.aliment.name, false, STD_BOLD_FONT.deriveFont(25f));
	        
	        
	        foodQuantityInput = new FormBoxInput(this).setLbl(new LanguageUtil("Quantidade", "Quantity").get(), 8f)
													  .setValue(food.quantity + "")
													  .setLblColor(STD_BLACK_COLOR);

	        foodUnityQtInput = new FormBoxInput(this).setLbl(new LanguageUtil("Unidade de Medida", "Measure Unity").get(), 8f)
													 .setLblColor(STD_BLACK_COLOR)
													 .setComboBoxInput(food.unityQt, "gramas")
													 .setComboFont(STD_BOLD_FONT.deriveFont(12f));
	        
	        chooseAnotherFoodBtn = new FormBoxInput(this).setLbl("")
	        										  	 .setButtonBox(StdButton.stdBtnConfig(new LanguageUtil("Escolher Novo Alimento", "Choose New Food").get())
	        										  			 				.setAction(this::chooseAnotherFood)
	        										  			 				.setBgColor(STD_STRONG_GRAY_COLOR)
	        										  			 				, false);
	        
	        deleteFoodBtn = new FormBoxInput(this).setLbl("")
					  						   	  .setButtonBox(StdButton.stdBtnConfig(new LanguageUtil("Deletar", "Delete").get())
					  						   			  				 .setAction(() -> {
					  						   			  					 if(FoodController.deleteFood(food)) {
					  						   			  						 afterUpdate.execute();
					  						   			  						 getCallerFrame().dispose();
					  						   			  					 }
					  						   			  				 })
					  						   			  						 
					  						   			  				 .setBgColor(STD_RED_COLOR), false);
	        
	        saveFoodBtn = new FormBoxInput(this).setLbl("")
				   	  							.setButtonBox(StdButton.stdBtnConfig(new LanguageUtil("Salvar", "Save").get())
				   	  									 			   .setAction(this::saveFood), false);
	        
			
			FormSection alimentVariables = new FormSection(this).setInternalColor(STD_WHITE_COLOR)
												.hideRequiredLbl()
												.hideBorder()
												.setAllFieldsLateralDistance(0, null)
												.addRow(currentAlimentTitle)
												.addRow(foodQuantityInput, foodUnityQtInput)
												.addRow(saveFoodBtn)
												.addRow(chooseAnotherFoodBtn, deleteFoodBtn)
												.setUpperDistance(0)
												.setLowerDistance(0)
												.init();
        
			foodPanel.add(alimentVariables, foodPanel.gbc.fill("BOTH").wgt(1.0).yP());
			
    	//Informação nutricional do alimento atual
	        GenericJPanel currentInfo = new GenericJPanel().ltGridBag();
	        currentInfo.setBackground(getBackground());
	        JLabel lblCurrent = new JLabel(new LanguageUtil("Informações Nutricionais do Alimento Atual", "Current Food Nutritional info").get());
	        lblCurrent.setFont(STD_BOLD_FONT.deriveFont(13f));
	        currentInfo.add(lblCurrent, currentInfo.gbc.grid(0).fill("HORIZONTAL").wgt(1.0, 0).insets(20, 20, 0, 20));
	        
	        currentInfoScroll = new JScrollPane();
	        currentTable = new JTable();
	        updateCurrentFoodInfoTable();
	        currentInfoScroll.setViewportView(currentTable);
	        currentInfo.add(currentInfoScroll, currentInfo.gbc.yP().fill("BOTH").wgt(1.0).insets(5, 20, 20, 20));
        
	        
	        GenericJPanel subFoodMainPanel = new GenericJPanel().ltGridBag();
	        JLabel lblSubFoodPanel = new JLabel(new LanguageUtil("Opções de Substituição", "Replacement option").get());
	        lblSubFoodPanel.setFont(STD_BOLD_FONT.deriveFont(15f));
	        subFoodMainPanel.add(lblSubFoodPanel, subFoodMainPanel.gbc.fill("HORIZONTAL").wgt(1.0, 0).grid(0).insets(20, 20, 10, 20).anchor("NORTHWEST"));
	        StdButton createSubFood = StdButton.stdBtnConfig(new LanguageUtil("Adicionar Opção", "Add Option").get());
	        subFoodMainPanel.add(createSubFood, subFoodMainPanel.gbc.fill("VERTICAL").grid(1, 0).anchor("NORTHEAST").wgt(0));
	        createSubFood.setAction(() -> {
	        	SubFoodController.openNewSubFoodFrame(getCallerFrame(), food, this::addSubFoods);
	        });
	        
        //ScrollPane de todas as subfoods
    		subFoodsScroll.setViewportView(subFoodsPanel);
    		subFoodMainPanel.add(subFoodsScroll, subFoodMainPanel.gbc.fill("BOTH").wgt(1.0).grid(0, 1).insets(0, 20, 20, 20).width("REMAINDER"));
    		subFoodMainPanel.setBGColor(getBackground());
    		
        
    	//Informações Nutricionais da subFoodSelecionada
	        subFoodInfoScroll = new JScrollPane();
	        subFoodTable = new JTable();
	        subFoodInfoScroll.setViewportView(subFoodTable);
        
        
        //Painel que reune as infos de subFood + lbl.
	        JLabel upperLblCurrentViewing = new JLabel(new LanguageUtil("Visualizando atualmente:", "Currently Viewing:").get());
	        upperLblCurrentViewing.setFont(STD_REGULAR_FONT.deriveFont(10f));
	        subFoodInfo.add(upperLblCurrentViewing, subFoodInfo.gbc.fill("HORIZONTAL").grid(0).wgt(1.0, 0).insets(20, 20, 0, 20));
	        
	        currentViewingSubFood = new JLabel(new LanguageUtil("Nenhum.", "None").get());
	        currentViewingSubFood.setFont(STD_BOLD_FONT.deriveFont(13f));
	        subFoodInfo.add(currentViewingSubFood, subFoodInfo.gbc.fill("HORIZONTAL").grid(0, 1).wgt(1.0, 0).insets(0, 20, 0, 20));
	        
	        subFoodInfo.add(subFoodInfoScroll, subFoodInfo.gbc.fill("BOTH").yP().wgt(1.0).width("REMAINDER").insets(5, 20, 20, 20));
	        subFoodInfo.setBGColor(getBackground());
        
	        
	        int divisor = 500;
        //Configuração dos SplitPane
	        topSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, foodPanel, currentInfo);
	        topSplitPane.setResizeWeight(0.5); // 50% para cada lado
	        topSplitPane.setDividerSize(0);
	        topSplitPane.setDividerLocation(divisor);
	        
	        bottomSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, subFoodMainPanel, subFoodInfo);
	        bottomSplitPane.setResizeWeight(0.5); // 50% para cada lado
	        bottomSplitPane.setDividerSize(0);
	        bottomSplitPane.setDividerLocation(divisor);
	        
	        
	        mainSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, topSplitPane, bottomSplitPane);
	        mainSplitPane.setResizeWeight(0.3); // 50% para cada lado
	        mainSplitPane.setDividerSize(0);
	    //
	    
	    holderNoSub.setBGColor(getBackground());
        lblNoSubFoodYet = new JLabel(new LanguageUtil("Nenhuma opção de substituição.", "No other food options.").get());
        lblNoSubFoodYet.setFont(STD_REGULAR_FONT.deriveFont(12f));
        holderNoSub.add(lblNoSubFoodYet, holderNoSub.gbc.fill("VERTICAL").wgt(0, 1.0).anchor("NORTHWEST").insets(10));
        
        addSubFoods();
        
        this.add(mainSplitPane, gbc.grid(0).fill("BOTH").wgt(1.0));
    }
    
    public void addSubFoods() {
        try {
            ActionFunction<Aliment, SubFood> updateSubFoodTable = (subFoods) -> {
                if (subFoods != null && subFoods.length > 0) {
                    SubFood subFood = subFoods[0];
                    subFoodTable.setModel(new AlimentNutritionalTable(subFood.aliment, subFood.quantity, FoodUtil.calculateTotalNutrients(food.meal, food, subFood)));
                    currentViewingSubFood.setText(subFood.aliment.name);
                    lookingSubFood = subFood;
                }
                return null;
            };
            
            if(!(food.getSubFoods().size() > 0)) {
            	subFoodsScroll.setViewportView(holderNoSub);
            } else {
            	subFoodsPanel.removeAll();
            	subFoodsScroll.setViewportView(subFoodsPanel);
            }

            subFoodsPanel.gbc.fill("BOTH").wgt(1.0, 0).anchor("NORTHWEST").grid(0).insets(10);
            for (SubFood subFood : food.getSubFoods()) {
                DietSubFoodPanel s = new DietSubFoodPanel(this, food, subFood, updateSubFoodTable, () -> {
                	addSubFoods();
                	if(lookingSubFood != null && !lookingSubFood.isActive()) {
                		subFoodTable.setModel(new DefaultTableModel());
                		lookingSubFood = null;
                	} else {
                        subFoodTable.setModel(new AlimentNutritionalTable(subFood.aliment, subFood.quantity, FoodUtil.calculateTotalNutrients(food.meal, food, subFood)));
                	}
                });
                subFoodsPanel.add(s, subFoodsPanel.gbc);
                subFoodsPanel.gbc.yP();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void updateCurrentFoodInfoTable() {
        currentTable.setModel(new AlimentNutritionalTable(food.aliment, food.quantity, FoodUtil.calculateTotalNutrients(food.meal)));
    }
    
    private void chooseAnotherFood() {
        FoodController.openFoodUpdate(getCallerFrame(), food, () -> {
        	currentAlimentTitle.setValue(this.food.aliment.name);
        	updateCurrentFoodInfoTable();
        	afterUpdate.execute();
        });
    }
    
    private void saveFood() {
    	if(Validate.formFields(foodQuantityInput)) {
    		if(FoodController.updateFoodInfo(food, foodUnityQtInput.getValue(), foodQuantityInput.getValue())) {
				QuestNutriJOP.showMessageDialog(null, new LanguageUtil("Alimento salvo!", "Food saved!").get());
				updateCurrentFoodInfoTable();
				afterUpdate.execute();
    		} else {
				QuestNutriJOP.showMessageDialog(null, new LanguageUtil("Não foi possível salvar o alimento.", "The food couldn't be saved.").get());
    		}
    	}
    }
    
    public int getLookingId() {
    	if(lookingSubFood != null) {
    		return lookingSubFood.idSubFood;
    	}
    	return 0;
    }
    
    public void emptySubFoodTable() {
		subFoodTable.setModel(new DefaultTableModel());
    }

}

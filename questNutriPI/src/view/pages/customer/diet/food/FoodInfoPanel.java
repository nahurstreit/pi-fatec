package view.pages.customer.diet.food;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;

import controller.entities.FoodController;
import model.entities.Aliment;
import model.entities.Food;
import model.entities.SubFood;
import utils.FoodUtil;
import utils.interfaces.ActionFunction;
import utils.view.LanguageUtil;
import view.components.buttons.StdButton;
import view.components.forms.FormBoxInput;
import view.components.forms.FormSection;
import view.components.generics.GenericJFrame;
import view.components.generics.GenericJPanel;
import view.components.labels.ActionLbl;
import view.components.tables.AlimentNutritionalTable;
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
    
    private FormBoxInput foodQuantityInput;
    private FormBoxInput foodUnityQtInput;
    private FormBoxInput chooseAnotherFoodBtn;
    private FormBoxInput deleteFoodBtn;
    private FormBoxInput saveFoodBtn;

    public FoodInfoPanel(GenericJPanel ownerPanel, GenericJFrame callerFrame, Food food) {
        super(ownerPanel);
        setCallerFrame(callerFrame);
        ltGridBag();
        this.food = food;
        
        
        //Lbl do alimento atual da food
        	GenericJPanel foodPanel = new GenericJPanel().ltGridBag();
        
	        ActionLbl lblFoodInfo = new ActionLbl(food.aliment.name);
	        lblFoodInfo.setFont(STD_BOLD_FONT.deriveFont(25f));
	        lblFoodInfo.setNewAction(() -> {
	            FoodController.openFoodUpdate(callerFrame, food, () -> {
	                lblFoodInfo.setText(this.food.aliment.name);
	            });
	        });
	        
	        foodPanel.add(lblFoodInfo, foodPanel.gbc.grid(0).fill("HORIZONTAL").wgt(1.0, 0));
	        
	        foodQuantityInput = new FormBoxInput(this).setLbl(new LanguageUtil("Quantidade", "Quantity").get(), 8f)
													  .setValue(food.quantity + "")
													  .setLblColor(STD_BLACK_COLOR);

	        foodUnityQtInput = new FormBoxInput(this).setLbl(new LanguageUtil("Unidade de Medida", "Measure Unity").get(), 8f)
													 .setLblColor(STD_BLACK_COLOR)
													 .setComboBoxInput(food.unityQt, "gramas")
													 .setComboFont(STD_BOLD_FONT.deriveFont(12f));
	        
	        chooseAnotherFoodBtn = new FormBoxInput(this).setLbl("")
	        										  	 .setButtonBox(StdButton.stdBtnConfig("Escolher outro"), false);
	        
	        deleteFoodBtn = new FormBoxInput(this).setLbl("")
					  						   	  .setButtonBox(StdButton.stdBtnConfig("Deletar"), false);
	        
	        saveFoodBtn = new FormBoxInput(this).setLbl("")
				   	  							.setButtonBox(StdButton.stdBtnConfig("Salvar"), false);
	        
			
			FormSection alimentVariables = new FormSection(this).setInternalColor(STD_WHITE_COLOR)
												.hideRequiredLbl()
												.hideBorder()
												.setUpperDistance(0)
												.setLowerDistance(0)
												.setLateralDistance(0)
												.addRow(foodQuantityInput, foodUnityQtInput)
												.addRow(chooseAnotherFoodBtn, deleteFoodBtn)
												.addRow(saveFoodBtn)
												.init();
        
			foodPanel.add(alimentVariables, foodPanel.gbc.fill("BOTH").wgt(1.0).yP());
			
    	//Informação nutricional do alimento atual
	        GenericJPanel currentInfo = new GenericJPanel().ltGridBag();
	        currentInfo.setBackground(getBackground());
	        JLabel lblCurrent = new JLabel(new LanguageUtil("Informações Nutricionais do Alimento Atual", "Current Food Nutritional info").get());
	        lblCurrent.setFont(STD_BOLD_FONT.deriveFont(13f));
	        currentInfo.add(lblCurrent, currentInfo.gbc.grid(0).fill("HORIZONTAL").wgt(1.0, 0).insets(0, 0, 10, 0));
	        
	        currentInfoScroll = new JScrollPane();
	        currentTable = new JTable(new AlimentNutritionalTable(food.aliment, food.quantity, FoodUtil.calculateTotalNutrients(food.meal)));
	        currentInfoScroll.setViewportView(currentTable);
	        currentInfo.add(currentInfoScroll, currentInfo.gbc.yP().fill("BOTH").wgt(1.0).insets());
        
        
        //ScrollPane de todas as subfoods
    		subFoodsScroll.setViewportView(subFoodsPanel);
        
    	//Informações Nutricionais da subFoodSelecionada
	        subFoodInfoScroll = new JScrollPane();
	        subFoodTable = new JTable();
	        subFoodInfoScroll.setViewportView(subFoodTable);
        
        
        //Painel que reune as infos de subFood + lbl.
	        JLabel upperLblCurrentViewing = new JLabel(new LanguageUtil("Visualizando atualmente:", "Currently Viewing:").get());
	        upperLblCurrentViewing.setFont(STD_REGULAR_FONT.deriveFont(10f));
	        subFoodInfo.add(upperLblCurrentViewing, subFoodInfo.gbc.fill("HORIZONTAL").grid(0).wgt(1.0, 0).insets(0));
	        
	        
	        currentViewingSubFood = new JLabel(new LanguageUtil("Nenhum.", "None").get());
	        currentViewingSubFood.setFont(STD_BOLD_FONT.deriveFont(13f));
	        subFoodInfo.add(currentViewingSubFood, subFoodInfo.gbc.fill("HORIZONTAL").grid(0, 1).wgt(1.0, 0).insets(0, 0, 10, 0));
	        
	        subFoodInfo.add(subFoodInfoScroll, subFoodInfo.gbc.fill("BOTH").yP().wgt(1.0).width("REMAINDER").insets());
	        subFoodInfo.setBGColor(getBackground());
        
	        
	        int divisor = 500;
        //Configuração dos SplitPane
	        topSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, foodPanel, currentInfo);
	        topSplitPane.setResizeWeight(0.5); // 50% para cada lado
	        topSplitPane.setDividerSize(0);
	        topSplitPane.setDividerLocation(divisor);
	        
	        bottomSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, subFoodsScroll, subFoodInfo);
	        bottomSplitPane.setResizeWeight(0.5); // 50% para cada lado
	        bottomSplitPane.setDividerSize(0);
	        bottomSplitPane.setDividerLocation(divisor);
	        
	        
	        mainSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, topSplitPane, bottomSplitPane);
	        mainSplitPane.setResizeWeight(0.5); // 50% para cada lado
	        mainSplitPane.setDividerSize(0);
        
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
                }
                return null;
            };

            subFoodsPanel.gbc.fill("BOTH").wgt(1.0).anchor("NORTHWEST").grid(0).insets(10);
            for (SubFood subFood : food.getSubFoods()) {
                DietSubFoodPanel s = new DietSubFoodPanel(food, subFood, updateSubFoodTable);
                subFoodsPanel.add(s, subFoodsPanel.gbc);
                subFoodsPanel.gbc.yP();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

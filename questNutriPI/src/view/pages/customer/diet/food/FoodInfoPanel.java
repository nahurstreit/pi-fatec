package view.pages.customer.diet.food;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import controller.entities.FoodController;
import model.entities.Food;
import utils.FoodUtil;
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
	
	private JScrollPane subFoodsScroll;

	public FoodInfoPanel(GenericJPanel ownerPanel, GenericJFrame callerFrame, Food food) {
		super(ownerPanel);
		setCallerFrame(callerFrame);
		ltGridBag();
		this.food = food;
		
		ActionLbl lblFoodInfo = new ActionLbl(food.aliment.name);
		lblFoodInfo.setFont(STD_BOLD_FONT.deriveFont(25f));
		lblFoodInfo.setNewAction(() -> {
			FoodController.openFoodUpdate(callerFrame, food, () -> {
				lblFoodInfo.setText(this.food.aliment.name);
			});
		});
		this.add(lblFoodInfo, gbc.grid(0).wgt(1.0, 0).fill("HORIZONTAL"));
		
		currentInfoScroll = new JScrollPane();
		currentTable = new JTable(new AlimentNutritionalTable(food.aliment, food.quantity, FoodUtil.calculateTotalNutrients(food.meal)));
		currentInfoScroll.setViewportView(currentTable);
		
		this.add(currentInfoScroll, gbc.grid(0, 1).wgt(1.0).fill("BOTH"));
	}
	
	
}
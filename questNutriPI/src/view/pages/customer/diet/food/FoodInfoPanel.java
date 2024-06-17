package view.pages.customer.diet.food;

import controller.entities.FoodController;
import model.entities.Food;
import view.components.generics.GenericJFrame;
import view.components.generics.GenericJPanel;
import view.components.labels.ActionLbl;
import view.pages.generics.GenericPage;

public class FoodInfoPanel extends GenericPage {
	private static final long serialVersionUID = 1L;
	
	private Food food;

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
	}
}
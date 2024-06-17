package view.frames;

import controller.entities.FoodController;
import model.entities.Food;
import utils.view.LanguageUtil;
import view.components.buttons.StdButton;
import view.components.generics.GenericJFrame;
import view.pages.customer.diet.DietMealPanel;
import view.pages.customer.diet.food.SelectNewAlimentPanel;

public class NewFoodFrame extends SubFrame {
	private static final long serialVersionUID = 1L;
	
	private SelectNewAlimentPanel panel;
	private Food food;
	private DietMealPanel dietMealPanel;
	
	public NewFoodFrame(GenericJFrame callerFrame, DietMealPanel dietMealPanel,  Food food) {
		super(dietMealPanel.getCallerFrame(), null);
		this.dietMealPanel = dietMealPanel; 
		this.food = food;
		setBounds(100, 100, 650, 440);
		setResizable(false);
		setContentPane(panel = new SelectNewAlimentPanel(createBtn()));
	}
	
	public StdButton createBtn() {
		StdButton btn = StdButton.stdBtnConfig(new LanguageUtil("Selecionar Novo Alimento", "Select New Aliment").get());
		btn.setAction(() -> {
			if(panel.getSelectedAliment() != null) {
				food.setAliment(panel.getSelectedAliment())
					.setQuantity(0.0)
					.setUnityQt("gramas");
				if(FoodController.createNewFood(food)) {
					dietMealPanel.foodWasUpdated();
					this.dispose();
				};
			}
		});
		
		return btn;
	}
	
}
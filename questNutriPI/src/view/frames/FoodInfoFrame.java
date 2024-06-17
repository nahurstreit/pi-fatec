package view.frames;

import java.awt.Dimension;

import model.entities.Food;
import utils.view.LanguageUtil;
import view.components.generics.GenericJFrame;
import view.pages.customer.diet.DietMealPanel;
import view.pages.customer.diet.food.FoodInfoPanel;

public class FoodInfoFrame extends SubFrame {
	private static final long serialVersionUID = 1L;

	public FoodInfoFrame(GenericJFrame callerFrame, Food food) {
		super(callerFrame, new Dimension(1000, 500));
		setFrameName("Food frame");
		setTitle(new LanguageUtil("Gerenciamento de alimento", "Food management").get());
		setContentPane(new FoodInfoPanel(null, this, food));
	}
	
	public static void main(String[] args) {
		FoodInfoFrame frame = new FoodInfoFrame(null, Food.findByPK(1));
		frame.setDefaultCloseApp();
		frame.setVisible(true);
	}
}
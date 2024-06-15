package controller.entities;

import model.entities.Food;
import model.entities.Meal;
import view.frames.NewFoodFrame;

public class FoodController {
	public static void openNewFoodFrame(Meal meal) {
		Food food = new Food().setMeal(meal);
		NewFoodFrame foodFrame = new NewFoodFrame(food);
		foodFrame.setVisible(true);
	}
	
}
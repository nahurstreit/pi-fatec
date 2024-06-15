package controller.entities;

import java.time.LocalDateTime;

import model.entities.Aliment;
import model.entities.Food;
import model.entities.Meal;
import view.components.generics.GenericJFrame;
import view.frames.NewFoodFrame;
import view.frames.UpdateFoodFrame;
import view.pages.customer.diet.DietMealPanel;

public class FoodController {
	public static void openNewFoodFrame(Meal meal) {
		Food food = new Food().setMeal(meal);
		NewFoodFrame foodFrame = new NewFoodFrame(food);
		foodFrame.setVisible(true);
	}
	
	public static void openFoodUpdate(GenericJFrame callerFrame, Food food, DietMealPanel onUpdate) {
		UpdateFoodFrame frame = new UpdateFoodFrame(callerFrame, food, onUpdate);
		frame.setVisible(true);
	}
	
	public static boolean updateFood(Food food, Aliment aliment) {
		boolean res = true;
		try {
			if(food.getCreationDateTime() != LocalDateTime.now()) {
				Food updated = Food.createCopyFrom(food);
				food.delete();
				updated.aliment = aliment;
				updated.save();
			}
		} catch (Exception e) {
			e.printStackTrace();
			res = false;
		}
		
		return res;
	}
	
}
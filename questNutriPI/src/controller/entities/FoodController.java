package controller.entities;

import model.entities.Aliment;
import model.entities.Food;
import model.entities.Meal;
import utils.view.LanguageUtil;
import view.components.QuestNutriJOP;
import view.components.generics.GenericJFrame;
import view.components.utils.IDoAction;
import view.frames.FoodInfoFrame;
import view.frames.NewFoodFrame;
import view.frames.UpdateFoodFrame;
import view.pages.customer.diet.DietMealPanel;

public class FoodController {
	public static void openNewFoodFrame(DietMealPanel dietMealPanel, Meal meal) {
		Food food = new Food().setMeal(meal);
		NewFoodFrame foodFrame = new NewFoodFrame(dietMealPanel, food);
		foodFrame.setVisible(true);
	}
	
	public static void openFoodUpdate(GenericJFrame callerFrame, Food food, IDoAction afterUpdate) {
		UpdateFoodFrame frame = new UpdateFoodFrame(callerFrame, food, afterUpdate);
		frame.setVisible(true);
	}
	
	public static boolean updateFoodAliment(Food food, Aliment aliment) {
		boolean res = true;
		try {
			food.aliment = aliment;
			res = food.save();
		} catch (Exception e) {
			e.printStackTrace();
			res = false;
		}
		
		return res;
	}
	
	public static boolean updateFoodInfo(Food food, String unityQt, String quantity) {
		boolean res = true;
		try {
			food.setUnityQt(unityQt);
			try {
				food.setQuantity(Double.parseDouble(quantity.replace(',', '.')));
			} catch (Exception e) {}
			food.save();
		} catch (Exception e) {
			e.printStackTrace();
			res = false;
		}
		
		return res;
	}
	
	public static boolean createNewFood(Food food) {
		boolean res;
		try {
			res = food.save();
			if(res) {
				QuestNutriJOP.showMessageDialog(null, new LanguageUtil("Novo alimento adicionado!", "New food added!").get());
			} else {
				QuestNutriJOP.showMessageDialog(null, new LanguageUtil("Não foi possível adicionar o novo alimento.", "Unable to add new food.").get());
			}
		} catch (Exception e) {
			QuestNutriJOP.showMessageDialog(null, new LanguageUtil("Erro no servidor.", "Server error.").get());
			e.printStackTrace();
			res = false;
		}

		return res;
	}
	
	public static void openFoodInfo(GenericJFrame callerFrame, Food food, IDoAction afterUpdate) {
		FoodInfoFrame f = new FoodInfoFrame(callerFrame, food, afterUpdate);
		f.setVisible(true);
	}
	
}
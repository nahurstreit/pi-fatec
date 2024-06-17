package controller.entities;

import model.entities.Aliment;
import model.entities.Food;
import model.entities.Meal;
import utils.ConfirmDialog;
import utils.view.LanguageUtil;
import view.components.QuestNutriJOP;
import view.components.generics.GenericJFrame;
import view.components.utils.IDoAction;
import view.frames.FoodInfoFrame;
import view.frames.NewFoodFrame;
import view.frames.UpdateFoodFrame;
import view.pages.customer.diet.DietMealPanel;

public class FoodController {
	public static void openNewFoodFrame(GenericJFrame callerFrame, DietMealPanel dietMealPanel, Meal meal) {
		Food food = new Food().setMeal(meal);
		NewFoodFrame foodFrame = new NewFoodFrame(callerFrame, dietMealPanel, food);
		foodFrame.setVisible(true);
	}
	
	public static void openFoodUpdate(GenericJFrame callerFrame, Food food, IDoAction afterUpdate) {
		UpdateFoodFrame frame = new UpdateFoodFrame(callerFrame.getCallerFrame(), food, afterUpdate);
		frame.setVisible(true);
	}
	
	public static boolean updateFoodAliment(Food food, Aliment aliment) {
		boolean res = false;
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
		boolean res = false;
		try {
			food.setUnityQt(unityQt);
			try {
				food.setQuantity(Double.parseDouble(quantity.replace(',', '.')));
			} catch (Exception e) {}
			res = food.save();
		} catch (Exception e) {
			e.printStackTrace();
			res = false;
		}
		
		return res;
	}
	
	public static boolean createNewFood(Food food) {
		boolean res = false;
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
	
	public static boolean deleteFood(Food food) {
		boolean res = false;
		boolean choice = ConfirmDialog.ask(new LanguageUtil("Deseja realmente excluir esse alimento?", "Do you really want to delete this food?").get(), 
						  new LanguageUtil("Confirmar Exclusão", "Confirm Delete").get());
		if(choice) {
			try {
				res = food.delete();
				if(res) QuestNutriJOP.showMessageDialog(null, new LanguageUtil("Alimento excluído.", "Food deleted.").get());
			} catch (Exception e) {
				QuestNutriJOP.showMessageDialog(null, new LanguageUtil("Não foi possível excluir o alimento.", "The food couldn't be excluded.").get());
			}	
		}
		
		return res;
	}
	
}
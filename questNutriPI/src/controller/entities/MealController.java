package controller.entities;

import model.entities.Meal;
import utils.ConfirmDialog;
import utils.view.LanguageUtil;
import view.components.QuestNutriJOP;

public class MealController {
	public static boolean saveMeal(Meal meal, String name, String hour, int daysOfWeek) {
		boolean res = true;
		try {
			meal.setName(name)
				.setHour(hour)
				.setDaysOfWeek(daysOfWeek);
			
			res = meal.save();
		} catch (Exception e) {
			res = false;
			e.printStackTrace();
		}
		
		return res;
		
	}
	
	public static boolean deleteMeal(Meal meal) {
		boolean res = false;
		if(ConfirmDialog.ask(new LanguageUtil("Você deseja mesmo excluir essa refeição?", "Do you really want to delete this meal?").get(), 
                new LanguageUtil("Excluir refeição.", "Delete meal.").get())) {
			try {
				res = meal.delete();
				if(res) QuestNutriJOP.showMessageDialog(null, new LanguageUtil("Refeição excluída!", "Meal deleted!").get());
				else QuestNutriJOP.showMessageDialog(null, new LanguageUtil("Houve um erro ao deletar a refeição.", "An error occurred while deleting the meal.").get());
			} catch (Exception e) {
				QuestNutriJOP.showMessageDialog(null, new LanguageUtil("Erro interno do servidor.", "Internal server error.").get());
			}
		}
		
		return res;

	}

}
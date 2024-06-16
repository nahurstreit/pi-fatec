package controller.entities;

import model.entities.Meal;

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

}
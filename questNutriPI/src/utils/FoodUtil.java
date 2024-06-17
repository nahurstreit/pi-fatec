package utils;

import model.entities.Aliment;
import model.entities.Food;
import model.entities.Meal;

/**
 * classe que fornece métodos para calcular o total de calorias de uma refeição.
 */
public class FoodUtil {

	/**
	 * Método que calcula o total de calorias de uma refeição com base nas informações das comidas presentes na refeição.
	 * 
	 * @param meal Meal para a qual calcular o total de calorias.
	 * @return O total de calorias da refeição.
	 */
	public static double calculateMealKcal(Meal meal) {
		double totalKcal = 0.0;

		//Iterar sobre todas as comidas presentes na refeição
		for(Food food: meal.getFoods()) {
			try {				
				String kcalPer100gString = food.aliment.kcal;

				String kcalPer100gFormatted = kcalPer100gString.replace(',', '.');

				double kcalPer100g = Double.parseDouble(kcalPer100gFormatted);

				double quantity = food.quantity;

				double kcal = (quantity * kcalPer100g) / 100;

				totalKcal += kcal;
			} catch (Exception e) {
				continue;
			}

		}

		return totalKcal;
	}
	
	public static double calculateFoodKcal(Food food) {
		double totalKcal = 0.0;
		
		try {
			String kcalPer100gString = food.aliment.kcal;

			String kcalPer100gFormatted = kcalPer100gString.replace(',', '.');

			double kcalPer100g = Double.parseDouble(kcalPer100gFormatted);

			double quantity = food.quantity;

			totalKcal = (quantity * kcalPer100g) / 100;

		} catch (Exception e) {
		}

		return totalKcal;
	}
	
	public static double[] calculateMacronutrients(Meal meal) {
        double totalCarb = 0.0;
        double totalProtein = 0.0;
        double totalFat = 0.0;

        //Iterar sobre todas as comidas presentes na refeição
        for(Food food: meal.getFoods()) {
            try {
                //Acessar o objeto Aliment associado a cada Food
                Aliment aliment = food.aliment;

                //Extrair e converter os valores para double
                try {
                	double carb = Double.parseDouble(aliment.carb.replace(',', '.'))*food.quantity/100;
                	totalCarb += carb;
				} catch (Exception e) {}
                
                try {
                	double protein = Double.parseDouble(aliment.protein.replace(',', '.'))*food.quantity/100;
                	totalProtein += protein;
				} catch (Exception e) {}
                
                try {
                	double fat = Double.parseDouble(aliment.fat.replace(',', '.'))*food.quantity/100;
                	totalFat += fat;
				} catch (Exception e) {}
                
            } catch (NumberFormatException e) {
                continue;
            }
        }
        
        //Retornar os totais como um array de doubles
        return new double[] { totalCarb, totalProtein, totalFat };
    }
	
	public static String formatNumber(String str, int ...point) {
		if(str == null) return "";
		int pointValue = 2;
		
		if(point.length > 0) {
			pointValue = point[0];
		}
		
		String res = str;
		try {
			res = String.format("%."+pointValue+"f", str.replace(',', '.'));
		} catch (Exception e) {
		}
		
		return res;
	}
}

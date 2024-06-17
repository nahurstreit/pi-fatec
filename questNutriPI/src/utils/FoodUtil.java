package utils;

import model.entities.Aliment;
import model.entities.Food;
import model.entities.Meal;
import model.entities.SubFood;

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
	
	public static Aliment calculateTotalNutrients(Meal meal) {
        return calculateTotalNutrients(meal, null, null);
    }

    public static Aliment calculateTotalNutrients(Meal meal, Food blockedFood, SubFood replacedFood) {
        double totalCarb = 0.0;
        double totalProtein = 0.0;
        double totalFat = 0.0;
        double totalKcal = 0.0;
        double totalKJ = 0.0;
        double totalHumidity = 0.0;
        double totalDietaryFiber = 0.0;
        double totalCholesterol = 0.0;
        double totalSodium = 0.0;
        double totalCalcium = 0.0;
        double totalMagnesium = 0.0;
        double totalManganese = 0.0;
        double totalPhosphorus = 0.0;
        double totalIron = 0.0;
        double totalPotassium = 0.0;
        double totalCopper = 0.0;
        double totalZinc = 0.0;
        double totalRetinol = 0.0;
        double totalRE = 0.0;
        double totalRAE = 0.0;
        double totalThiamine = 0.0;
        double totalRiboflavin = 0.0;
        double totalPyridoxine = 0.0;
        double totalNiacin = 0.0;
        double totalVitaminC = 0.0;
        double totalAsh = 0.0;

        // Iterar sobre todas as comidas presentes na refeição
        for (Food food : meal.getFoods()) {
            Aliment aliment = food.aliment;
            Double quantity = food.quantity;

            // Verificar se a comida atual deve ser substituída
            if (blockedFood != null && food.equals(blockedFood)) {
            	quantity = replacedFood.quantity;
                aliment = replacedFood.aliment;
            }

            try {
                totalCarb += Double.parseDouble(aliment.carb.replace(',', '.')) * quantity / 100;
            } catch (Exception e) {}

            try {
                totalProtein += Double.parseDouble(aliment.protein.replace(',', '.')) * quantity / 100;
            } catch (Exception e) {}

            try {
                totalFat += Double.parseDouble(aliment.fat.replace(',', '.')) * quantity / 100;
            } catch (Exception e) {}

            try {
                totalKcal += Double.parseDouble(aliment.kcal.replace(',', '.')) * quantity / 100;
            } catch (Exception e) {}

            try {
                totalKJ += Double.parseDouble(aliment.kJ.replace(',', '.')) * quantity / 100;
            } catch (Exception e) {}

            try {
                totalHumidity += Double.parseDouble(aliment.humidity.replace(',', '.')) * quantity / 100;
            } catch (Exception e) {}

            try {
                totalDietaryFiber += Double.parseDouble(aliment.dietaryFiber.replace(',', '.')) * quantity / 100;
            } catch (Exception e) {}

            try {
                totalCholesterol += Double.parseDouble(aliment.cholesterol.replace(',', '.')) * quantity / 100;
            } catch (Exception e) {}

            try {
                totalSodium += Double.parseDouble(aliment.sodium.replace(',', '.')) * quantity / 100;
            } catch (Exception e) {}

            try {
                totalCalcium += Double.parseDouble(aliment.calcium.replace(',', '.')) * quantity / 100;
            } catch (Exception e) {}

            try {
                totalMagnesium += Double.parseDouble(aliment.magnesium.replace(',', '.')) * quantity / 100;
            } catch (Exception e) {}

            try {
                totalManganese += Double.parseDouble(aliment.manganese.replace(',', '.')) * quantity / 100;
            } catch (Exception e) {}

            try {
                totalPhosphorus += Double.parseDouble(aliment.phosphorus.replace(',', '.')) * quantity / 100;
            } catch (Exception e) {}

            try {
                totalIron += Double.parseDouble(aliment.iron.replace(',', '.')) * quantity / 100;
            } catch (Exception e) {}

            try {
                totalPotassium += Double.parseDouble(aliment.potassium.replace(',', '.')) * quantity / 100;
            } catch (Exception e) {}

            try {
                totalCopper += Double.parseDouble(aliment.copper.replace(',', '.')) * quantity / 100;
            } catch (Exception e) {}

            try {
                totalZinc += Double.parseDouble(aliment.zinc.replace(',', '.')) * quantity / 100;
            } catch (Exception e) {}

            try {
                totalRetinol += Double.parseDouble(aliment.retinol.replace(',', '.')) * quantity / 100;
            } catch (Exception e) {}

            try {
                totalRE += Double.parseDouble(aliment.rE.replace(',', '.')) * quantity / 100;
            } catch (Exception e) {}

            try {
                totalRAE += Double.parseDouble(aliment.rAE.replace(',', '.')) * quantity / 100;
            } catch (Exception e) {}

            try {
                totalThiamine += Double.parseDouble(aliment.thiamine.replace(',', '.')) * quantity / 100;
            } catch (Exception e) {}

            try {
                totalRiboflavin += Double.parseDouble(aliment.riboflavin.replace(',', '.')) * quantity / 100;
            } catch (Exception e) {}

            try {
                totalPyridoxine += Double.parseDouble(aliment.pyridoxine.replace(',', '.')) * quantity / 100;
            } catch (Exception e) {}

            try {
                totalNiacin += Double.parseDouble(aliment.niacin.replace(',', '.')) * quantity / 100;
            } catch (Exception e) {}

            try {
                totalVitaminC += Double.parseDouble(aliment.vitaminC.replace(',', '.')) * quantity / 100;
            } catch (Exception e) {}

            try {
                totalAsh += Double.parseDouble(aliment.ash.replace(',', '.')) * quantity / 100;
            } catch (Exception e) {}
        }

        // Criar um objeto Aliment com os totais calculados
        Aliment totalAliment = new Aliment();
        totalAliment.setCarb(String.valueOf(totalCarb));
        totalAliment.setProtein(String.valueOf(totalProtein));
        totalAliment.setFat(String.valueOf(totalFat));
        totalAliment.setKcal(String.valueOf(totalKcal));
        totalAliment.setkJ(String.valueOf(totalKJ));
        totalAliment.setHumidity(String.valueOf(totalHumidity));
        totalAliment.setDietaryFiber(String.valueOf(totalDietaryFiber));
        totalAliment.setCholesterol(String.valueOf(totalCholesterol));
        totalAliment.setSodium(String.valueOf(totalSodium));
        totalAliment.setCalcium(String.valueOf(totalCalcium));
        totalAliment.setMagnesium(String.valueOf(totalMagnesium));
        totalAliment.setManganese(String.valueOf(totalManganese));
        totalAliment.setPhosphorus(String.valueOf(totalPhosphorus));
        totalAliment.setIron(String.valueOf(totalIron));
        totalAliment.setPotassium(String.valueOf(totalPotassium));
        totalAliment.setCopper(String.valueOf(totalCopper));
        totalAliment.setZinc(String.valueOf(totalZinc));
        totalAliment.setRetinol(String.valueOf(totalRetinol));
        totalAliment.setrE(String.valueOf(totalRE));
        totalAliment.setrAE(String.valueOf(totalRAE));
        totalAliment.setThiamine(String.valueOf(totalThiamine));
        totalAliment.setRiboflavin(String.valueOf(totalRiboflavin));
        totalAliment.setPyridoxine(String.valueOf(totalPyridoxine));
        totalAliment.setNiacin(String.valueOf(totalNiacin));
        totalAliment.setVitaminC(String.valueOf(totalVitaminC));
        totalAliment.setAsh(String.valueOf(totalAsh));

        return totalAliment;
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

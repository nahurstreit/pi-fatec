package utils;

import model.entities.Aliment;
import model.entities.Food;
import model.entities.Meal;

/**
 * classe que fornece métodos para calcular o total de calorias de uma refeição.
 */
public class CalculateKcal {

	/**
	 * Método que calcula o total de calorias de uma refeição com base nas informações das comidas presentes na refeição.
	 * 
	 * @param meal Meal para a qual calcular o total de calorias.
	 * @return O total de calorias da refeição.
	 */
	public static double calculateKcal(Meal meal) {
		double totalKcal = 0.0;

		// Iterar sobre todas as comidas presentes na refeição
		for (Food food : meal.getFoods()) {
			// Obter o alimento associado à comida
			Aliment aliment1 = food.aliment;
			
			// Obter a string de calorias por 100g da comida
			String kcalPer100gString = food.aliment.kcal;

			// Substituir vírgulas por pontos
			String kcalPer100gFormatted = kcalPer100gString.replace(',', '.');

			// Converter a string formatada em um número double
			double kcalPer100g = Double.parseDouble(kcalPer100gFormatted);

			// Obter a quantidade da comida na refeição
			double quantity = food.quantity;

			// Calcular a quantidade real de calorias da comida na refeição
			double kcal = (quantity * kcalPer100g) / 100;

			// Adicionar as calorias da comida ao total de calorias da refeição
			totalKcal += kcal;
		}

		return totalKcal;
	}
}

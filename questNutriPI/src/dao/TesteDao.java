package dao;



import java.util.List;

import model.Food;
import model.Meal;

public class TesteDao {

	public static void main(String[] args) {
		// Criando uma instância do FoodDAO
		DaoGenerico<Food> foodDAO = new DaoGenerico<>(Food.class);

		List<Food> foods = foodDAO.findAll();
		for (Food food : foods) {
		    System.out.println(food);
		}


//		int foodId = 3;
//		Food retrievedFood = foodDAO.findByPK(foodId);
//		if (retrievedFood != null) {
//			System.out.println("Food encontrado: " + retrievedFood);
//			System.out.println(foodDAO.toString());
//		} else {
//			System.out.println("Food com o ID " + foodId + " não encontrado.");
//		}
	}
}

package model.dao;
import java.util.List;

import model.entities.Customer;
import model.entities.Food;

public class TesteDao {

	public static void main(String[] args) {
//		// Criando uma instância do FoodDAO
//		GenericDAO<Food> foodDAO = new GenericDAO<>(Food.class);
//
//		List<Food> foods = foodDAO.findAll(Food.class);
//		for (Food food : foods) {
//		    System.out.println(food);
//		
		
//		for(Customer cliente: Customer.findAll()) {
//			System.out.println(cliente);
//		}
		
		
		System.out.println(Customer.findByPK(6));
		

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

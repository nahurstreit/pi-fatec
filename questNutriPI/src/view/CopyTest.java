package view;

import model.entities.Food;
import model.entities.SubFood;

//food_smallInfo: {id: 8, meal.id: 3, aliment: {id: 1, name: "Arroz, integral, cozido"}},

public class CopyTest {
	public static void main(String[] args) {
		for(SubFood subFood: Food.findByPK(8).getSubFoods()) {
			System.out.println(subFood);
		}
		
		System.out.println("\n\n");
		
		for(SubFood subFood: Food.findByPK(192).getSubFoods()) {
			System.out.println(subFood);
		}
	}
}
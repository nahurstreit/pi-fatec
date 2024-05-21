package model.dao;

import model.entities.Customer;
import model.entities.Meal;
import model.entities.Weight;

public class TesteDao {

	public static void main(String[] args) {		
//		Customer c1 = Customer.findByPK(3);
		Weight w1 = Weight.findLastWeightRecord(1);
		System.out.println(w1);
//		for(Meal meal: c1.getDiet()) {
//			System.out.println(meal.getFoods());
//		}
	}
	
}

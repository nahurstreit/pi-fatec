package model.dao;
import model.entities.Customer;
import model.entities.Meal;

public class TesteDao {

	public static void main(String[] args) {		
		Customer c1 = Customer.findByPK(3);
		for(Meal meal: c1.getDiet()) {
			System.out.println(meal.getFoods());
		}
	}
}

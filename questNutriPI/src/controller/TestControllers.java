package controller;

import java.util.List;

import model.entities.Customer;
import model.entities.Meal;

public class TestControllers {

	private static final Customer TESTE_CUSTOMER = Customer.findByPK(2);
	
	public static void main(String[] args) {
		PdfGeneratorController.generate(TESTE_CUSTOMER);
	}

}

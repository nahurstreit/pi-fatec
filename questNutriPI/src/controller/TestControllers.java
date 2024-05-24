package controller;

import model.entities.Customer;

public class TestControllers {

	private static final Customer TESTE_CUSTOMER = Customer.findByPK(2);
	
	public static void main(String[] args) {
		PdfGneratorController.generate(TESTE_CUSTOMER);
	}

}

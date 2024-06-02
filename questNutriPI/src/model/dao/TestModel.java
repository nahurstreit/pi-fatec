package model.dao;

import model.entities.Meal;

public class TestModel {
	public static void main(String[] args) {
		System.out.println(Meal.findAllByCustomerPK(1));
	}
}
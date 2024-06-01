package model.dao;

import model.entities.Address;

public class TestModel {
	public static void main(String[] args) {
		System.out.println(Address.findLast());
	}
}
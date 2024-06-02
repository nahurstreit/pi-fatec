package controller;

import javax.swing.JOptionPane;

import model.entities.Meal;
import utils.CpfValidate;
import utils.FoodUtil;

public class TestMethods {

	public static void main(String[] args) {
		
		Meal meal1 = Meal.findByPK(3);
		double totalKcal = FoodUtil.calculateMealKcal(meal1);
		System.out.println("Total de calorias da refeição: " + totalKcal);
		  String cpf = JOptionPane.showInputDialog("Digite um CPF:");
		
		  System.out.printf("\nResultado: ");
	      if(CpfValidate.cpfValidate(cpf) == true)
	    	  System.out.println(CpfValidate.imprimeCPF(cpf));
	        else System.out.println("\nErro, Cpf invalido");

	}

}

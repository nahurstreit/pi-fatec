package view;

import controller.entities.AlimentController;
import model.entities.Aliment;

public class TestAliment {
	public static void main(String[] args) {
		AlimentController.openAlimentFrame(Aliment.findByPK(5));
		AlimentController.openAlimentFrame(new Aliment());
	}
}
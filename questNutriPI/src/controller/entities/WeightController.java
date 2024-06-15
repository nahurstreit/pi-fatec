package controller.entities;

import javax.swing.JFrame;

import model.entities.Customer;
import model.entities.Weight;
import utils.interfaces.GeneralVisualSettings;
import view.components.QuestNutriJOP;
import view.components.forms.FormBoxInput;
import view.components.generics.GenericJFrame;
import view.frames.WeightFrame;

public class WeightController implements GeneralVisualSettings {

	
	public WeightController(JFrame callerFrame, Customer customer) {
		
	}
	
	public static void openWeightFrame(GenericJFrame callerFrame, Customer customer, FormBoxInput updateInput) {
		WeightFrame wgtFrame = new WeightFrame(callerFrame, customer, updateInput);
		wgtFrame.init();
	}
	
	public static boolean deleteWeight(Weight wgt, FormBoxInput updateInput) {
		boolean status = false;
		int customerId = wgt.customer.getId();
		
		String choice = QuestNutriJOP.showInputDialog(null, 
				"Você está tentando excluir o peso: \nPeso (em kg):" + wgt.value + " - Registrado em: "+wgt.getRegisterDate()+"."
                + "\nEssa ação é IRREVERS�?VEL. Digite '"+STD_DELETE_STRING+"' para deletar o registro.");
		
		if(choice != null) {
			if(!choice.isBlank()) {
				if(choice.equals(STD_DELETE_STRING)) {
					status = wgt.delete();
					if(status) {
						QuestNutriJOP.showMessageDialog(null, "Peso excluído!");
					} else {
						QuestNutriJOP.showMessageDialog(null, "Falha ao excluir o peso.");
					}
				} else {
					QuestNutriJOP.showMessageDialog(null, "Texto inválido.");
				};
			}
		}
		
		Double value;
		try {
			value = Weight.findLastRegister(customerId).value;
		} catch (Exception e) {
			value = null;
		}
		
		if(value != null) {
			updateInput.setValue(value + "");
		} else {
			updateInput.setValue("Nenhum registro.");
		}
		
		return status;
	}
}
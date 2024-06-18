package controller.entities;

import model.entities.Customer;
import model.entities.Weight;
import utils.interfaces.GeneralVisualSettings;
import utils.view.LanguageUtil;
import view.components.QuestNutriJOP;
import view.components.forms.FormBoxInput;
import view.components.generics.GenericJFrame;
import view.frames.WeightFrame;

/**
 * Controlador responsável por operações relacionadas ao peso do cliente.
 */
public abstract class WeightController implements GeneralVisualSettings {
    /**
     * Abre o frame para visualização e atualização do peso do cliente.
     *
     * @param callerFrame   Frame que chamou a abertura do frame de peso.
     * @param customer      Cliente cujo peso está sendo visualizado/atualizado.
     * @param updateInput   Input de formulário para atualização visual do peso.
     */
	public static void openWeightFrame(GenericJFrame callerFrame, Customer customer, FormBoxInput updateInput) {
		WeightFrame wgtFrame = new WeightFrame(callerFrame, customer, updateInput);
		wgtFrame.init();
	}
	
    /**
     * Exclui um registro de peso do cliente.
     *
     * @param wgt           Registro de peso a ser excluído.
     * @param updateInput   Input de formulário para atualização visual do peso após exclusão.
     * @return true se o registro foi excluído com sucesso, false caso contrário.
     */
	public static boolean deleteWeight(Weight wgt, FormBoxInput updateInput) {
		boolean status = false;
		int customerId = wgt.customer.getId();
		final String STD_DELETE_STRING = new LanguageUtil("EXCLUIR", "DELETE").get();
		
		String choice = QuestNutriJOP.showInputDialog(null, 
                new LanguageUtil(
                        "Você está tentando excluir o peso: \nPeso (em kg):" + wgt.value + " - Registrado em: " + wgt.getRegisterDate() + "."
                                + "\nEssa ação é IRREVERSÍVEL. Digite '" + STD_DELETE_STRING + "' para deletar o registro.",
                        "You are attempting to delete the weight: \nWeight (in kg):" + wgt.value + " - Recorded at: " + wgt.getRegisterDate() + "."
                                + "\nThis action is IRREVERSIBLE. Type '" + STD_DELETE_STRING + "' to delete the record."
                ).get()	
			);
		
		if(choice != null) {
		    if (!choice.isBlank()) {
		        if (choice.equals(STD_DELETE_STRING)) {
		            status = wgt.delete();
		            if (status) {
                        QuestNutriJOP.showMessageDialog(null, new LanguageUtil("Peso excluído!", "Weight deleted!").get());
		            } else {
                        QuestNutriJOP.showMessageDialog(null, new LanguageUtil("Falha ao excluir o peso.", "Failed to delete weight.").get());
		            }
		        } else {
                    QuestNutriJOP.showMessageDialog(null, new LanguageUtil("Erro ao excluir o peso.", "Error deleting weight.").get());
		        }
		    }
		}
		
		Double value;
		try {
			value = Weight.findLastRegister(customerId).value;
		} catch (Exception e) {
			value = null;
		}
		
		if(value != null) {
			updateInput.setValue(value + "kg");
		} else {
			updateInput.setValue(new LanguageUtil("Nenhum registro.", "No records.").get());
		}
		
		return status;
	}
}
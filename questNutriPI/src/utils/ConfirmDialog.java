/**
 * Package que contém as classes utilitárias do sistema.
 */
package utils;

import javax.swing.JOptionPane;

import utils.view.LanguageUtil;
import view.components.QuestNutriJOP;

/**
 * Classe utilit�ria para exibir di�logos de confirma��o personalizados.
 */
public class ConfirmDialog {
	
	/**
     * Exibe um di�logo de confirma��o com op��es customizadas.
     *
     * @param message   Mensagem a ser exibida no di�logo.
     * @param title     T�tulo do di�logo.
     * @return true se o usu�rio escolheu "Sim", false se escolheu "N�o".
     */
	public static boolean ask(String message, String title) {
		String[] options = {new LanguageUtil("Sim", "Yes").get(), new LanguageUtil("Não", "No").get()};
		
		int choice = QuestNutriJOP.showOptionDialog(
	            null,
	            message,
                title,
	            JOptionPane.YES_NO_CANCEL_OPTION, // Tipo de opçõees
	            JOptionPane.QUESTION_MESSAGE, // �cone
	            null,
	            options,
	            options[1]
	    );
		
		return choice == 0; // Retorna true se "Sim" foi escolhido, false se "N�o" foi escolhido
	}
	
}
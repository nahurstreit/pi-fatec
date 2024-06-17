package utils;

import javax.swing.JOptionPane;

import utils.view.LanguageUtil;
import view.components.QuestNutriJOP;

public class ConfirmDialog {
	
	public static boolean ask(String message, String title) {
		String[] options = {new LanguageUtil("Sim", "Yes").get(), new LanguageUtil("Não", "No").get()};
		
		int choice = QuestNutriJOP.showOptionDialog(
	            null,
	            message,
                title,
	            JOptionPane.YES_NO_CANCEL_OPTION, // Tipo de opções
	            JOptionPane.QUESTION_MESSAGE, // �?cone
	            null,
	            options,
	            options[1]
	    );
		
		return choice == 0;
	}
	
}
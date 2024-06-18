package utils.view;

import java.util.ArrayList;
import java.util.List;

import view.QuestNutri;

/**
 * Classe de utilidade geral na camada de View para manipulação de diferentes liguagens do sistema.
 */
public final class LanguageUtil {
	/**
	 * Strings contendo as opções de linguagem, sendo
	 * <br>[0] - português
	 * <br>[1] - inglês.
	 */
	private List<String> options = new ArrayList<String>();
	
	/**
	 * Constructor padrão do objeto de controle de linguagens do sistema.
	 * @param portuguese -> String em português
	 * @param english -> String em inglês
	 */
	public LanguageUtil(String portuguese, String english) {
		options.add(portuguese);
		options.add(english);
	}
	
	/**
	 * Método que retorna a string armazenada que vá de encontro com a definição do sistema.
	 * @return String armazenada de acordo com a linguagem do sistema.
	 */
	public String get() {
		return options.get(QuestNutri.language());
	}

}
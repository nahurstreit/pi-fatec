package utils.view;

import java.awt.Font;

import javax.swing.JLabel;

import utils.interfaces.GeneralVisualSettings;
import view.components.generics.GenericJPanel;

/**
 * Classe de utilidade geral na camada de View para funções de utilidade geral.
 */
public class PanelsUtil implements GeneralVisualSettings {
	/**
	 * Aplica um GIF de carregamento em um painel genérico com a opção de exibir um texto de carregamento.
	 * 
	 * @param targetPanel o painel onde o GIF de carregamento será aplicado
	 * @param width a largura desejada para o GIF de carregamento
	 * @param height a altura desejada para o GIF de carregamento
	 * @param showLabel true se o texto de carregamento deve ser exibido; false caso contrário
	 * @param labelFont a fonte a ser usada para o texto de carregamento; se for null, será usada uma fonte padrão
	 * @param labelPosition a posição do texto de carregamento em relação ao GIF de carregamento:
	 * <ul>
	 * <li> 0 para acima do GIF, qualquer outro valor para abaixo do GIF
	 * </ul>
	 * @param labelDistance a distância entre o GIF de carregamento e o texto de carregamento, em pixels
	 */
	public static void applyLoadingGif(GenericJPanel targetPanel, int width, int height, boolean showLabel, Font labelFont, int labelPosition, int labelDistance) {
		targetPanel.ltGridBag();
		JLabel textLoading = new JLabel(new LanguageUtil("Carregando...", "Loading...").get(), JLabel.CENTER);
		if(labelFont != null) {
			textLoading.setFont(labelFont);
		} else {
			textLoading.setFont(STD_BOLD_FONT.deriveFont(12f));
		}
        JLabel loadingGif = new JLabel(ImagesUtil.gifIcon("Loading", width, height), JLabel.CENTER);
        
        if(showLabel) {
        	if(labelPosition == 0) {
        		targetPanel.add(textLoading, targetPanel.gbc.anchor("CENTER"));
            	targetPanel.add(loadingGif, targetPanel.gbc.xP().insets(labelDistance, 0, 0, 0));
        	} else {
        		targetPanel.add(loadingGif, targetPanel.gbc.anchor("CENTER"));
        		targetPanel.add(textLoading, targetPanel.gbc.xP().insets(labelDistance, 0, 0, 0));
        	}
        }
        
    }
	
	/**
	 * Aplica um GIF de carregamento em um painel genérico sem exibir um texto de carregamento.
	 * 
	 * @param targetPanel o painel onde o GIF de carregamento será aplicado
	 * @param width a largura desejada para o GIF de carregamento
	 * @param height a altura desejada para o GIF de carregamento
	 */
	public static void applyLoadingGif(GenericJPanel targetPanel, int width, int height) {
		applyLoadingGif(targetPanel, width, height, false, null, 0, 5);
    }
}
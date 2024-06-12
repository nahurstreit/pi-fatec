package view.utils;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JLabel;

import view.QuestNutri;
import view.panels.components.GeneralJPanelSettings;
import view.panels.components.GenericJPanel;

/**
 * Classe de utilidade geral na camada de View para funções de utilidade geral.
 */
public class VUtils {
	/**
	 * Método que carrega uma instância de Font com o nome indicado. A fonte deve estar salva dentro do caminho:
	 * <pre>/src/view/assets/fonts</pre>
	 * O arquivo da fonte deve ter como extensão: 'ttf';
	 * @param fontName -> (String) com o nome da fonte a ser instânciada.
	 * @param size -> (float) valor opcional que define o tamanho da fonte
	 * @return <b>Font</b> -> Instância de Font indicada.
	 */
	public static Font loadFont(String fontName, float ...size) {
		float stdSize = 12f;
		if(size.length > 0) stdSize = size[0];
		//Prepara o retorno
		Font loadedFont;
        try {
        	//Encontra o arquivo da fonte
            InputStream fontStream = QuestNutri.class.getResourceAsStream("/view/assets/fonts/"+fontName+".ttf");
            
            //Carrega a fonte e atribui a loadedFont
            loadedFont = Font.createFont(Font.TRUETYPE_FONT, fontStream);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            
            //Define a fonte Arial como padrão em caso de erro no load.
            loadedFont = new Font("Arial", Font.PLAIN, 18);
        }
        return loadedFont.deriveFont(stdSize);
    }
	
	
	/**
	 * Aplica um GIF de carregamento em um painel genérico com a opção de exibir um texto de carregamento.
	 * 
	 * @param targetPanel o painel onde o GIF de carregamento será aplicado
	 * @param width a largura desejada para o GIF de carregamento
	 * @param height a altura desejada para o GIF de carregamento
	 * @param showLabel true se o texto de carregamento deve ser exibido; false caso contrário
	 * @param labelFont a fonte a ser usada para o texto de carregamento; se for null, será usada uma fonte padrão
	 * @param labelPosition a posição do texto de carregamento em relação ao GIF de carregamento:
	 * <li> 0 para acima do GIF, qualquer outro valor para abaixo do GIF
	 * @param labelDistance a distância entre o GIF de carregamento e o texto de carregamento, em pixels
	 */
	public static void applyLoadingGif(GenericJPanel targetPanel, int width, int height, boolean showLabel, Font labelFont, int labelPosition, int labelDistance) {
		targetPanel.ltGridBag();
		JLabel textLoading = new JLabel(new LanguageUtil("Carregando...", "Loading...").get(), JLabel.CENTER);
		if(labelFont != null) {
			textLoading.setFont(labelFont);
		} else {
			textLoading.setFont(GeneralJPanelSettings.STD_BOLD_FONT.deriveFont(12f));
		}
        JLabel loadingGif = new JLabel(VMakePicture.gifIcon("Loading", width, height), JLabel.CENTER);
        
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
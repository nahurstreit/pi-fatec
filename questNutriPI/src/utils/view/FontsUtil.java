/**
 * Package que contém as classes utilitárias de View.
 */
package utils.view;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

import view.QuestNutri;

/**
 * Classe utilitária para lidar com fontes do sistema.
 */
public abstract class FontsUtil {
	/**
	 * Método que carrega uma instância de Font com o nome indicado. A fonte deve estar salva dentro do caminho:
	 * <pre>/src/view/assets/fonts</pre>
	 * O arquivo da fonte deve ter como extensão: 'ttf';
	 * @param fontName -> (String) com o nome da fonte a ser instânciada.
	 * @param size -> (float) valor opcional que define o tamanho da fonte
	 * @return <b>Font</b> -> Instância de Font indicada.
	 */
	public static Font load(String fontName, float ...size) {
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
}
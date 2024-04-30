package view.utils;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

import view.QuestNutri;

public class VUtils {
	public static Font loadFont(String fontName) {
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
        return loadedFont;
    }
}
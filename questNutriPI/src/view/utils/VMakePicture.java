package view.utils;
import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;

import org.apache.batik.swing.JSVGCanvas;

import view.QuestNutri;

/**
 * Classe de utilidade geral na camada de View para manipulação de imagens
 */
public class VMakePicture {
	/**
	 * Cria um JSVGCanvas que pode ser interpretado como Icone adicionáveis à JPanels a partir de uma imagem SVG.
	 * @param assetName -> (String) nome do arquivo dentro da pasta "/view/assets/" com extensão .svg
	 * @return <b>JSVGCanvas</b> -> Canvas com a imagem vetorizada.
	 */
	public static JSVGCanvas svgIcon(String assetName) {
        JSVGCanvas canvas = new JSVGCanvas();
        canvas.setURI(QuestNutri.class.getResource("/view/assets/"+assetName+".svg").toString());
        canvas.setBackground(new Color(0,0,0,0));
        return canvas;
	}
	
	/**
	 * Cria um ícone a partir de um arquivo de gif. Deve ser usado para substituir animações.
	 * @param assetName -> (String) nome do arquivo dentro da pasta "/view/assets/" com extensão .gif
	 * @return <b>ImageIcon</b> -> Icone gerado a partir do gif.
	 */
	public static ImageIcon gifIcon(String assetName) {
        ImageIcon icon = new ImageIcon(QuestNutri.class.getResource("/view/assets/"+assetName+".gif"));
        
        Image resizedImage = icon.getImage().getScaledInstance(350, 350, Image.SCALE_DEFAULT);
        
        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        return resizedIcon;
	}
	
	public static ImageIcon gifIcon(String assetName, int width, int height) {
	    ImageIcon icon = new ImageIcon(QuestNutri.class.getResource("/view/assets/"+assetName+".gif"));
	    Image gifImage = icon.getImage();
	    Image resizedImage = gifImage.getScaledInstance(width, height, Image.SCALE_DEFAULT);
	    return new ImageIcon(resizedImage);
	}
	
	/**
	 * Cria um ícone a partir de um arquivo png.
	 * @param assetName -> (String) nome do arquivo dentro da pasta "/view/assets/" com extensão .png
	 * @return <b>ImageIcon</b> -> Icone gerado a partir do png.
	 */
	public static ImageIcon sizedImg(String assetName, int width, int height) {
		ImageIcon icon = new ImageIcon(QuestNutri.class.getResource("/view/assets/"+assetName+".png"));
		if(width == 0) width = 100;
		if(height == 0) height = 100;
        Image resizedImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);    
		return new ImageIcon(resizedImage);
	}
	
}
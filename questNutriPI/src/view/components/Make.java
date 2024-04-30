package view.components;
import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;

import org.apache.batik.swing.JSVGCanvas;

import view.QuestNutri;

public class Make {
	public static JSVGCanvas svgIcon(String assetName, boolean... startRoot) {
        JSVGCanvas canvas = new JSVGCanvas();
        canvas.setURI(QuestNutri.class.getResource("/view/assets/"+assetName+".svg").toString());
        canvas.setBackground(new Color(0,0,0,0));
        return canvas;
	}
	
	public static ImageIcon gifIcon(String assetName) {
        ImageIcon icon = new ImageIcon(QuestNutri.class.getResource("/view/assets/"+assetName+".gif"));
        
        Image resizedImage = icon.getImage().getScaledInstance(350, 350, Image.SCALE_DEFAULT);
        
        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        return resizedIcon;
	}
	
	public static ImageIcon sizedImg(String assetName, int width, int height) {
		ImageIcon icon = new ImageIcon(QuestNutri.class.getResource("/view/assets/"+assetName+".png"));
		if(width == 0) width = 100;
		if(height == 0) height = 100;
        Image resizedImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);    
		return new ImageIcon(resizedImage);
	}
	
}
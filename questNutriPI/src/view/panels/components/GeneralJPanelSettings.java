package view.panels.components;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;

import view.components.utils.StdGBC;
import view.utils.VUtils;

public abstract class GeneralJPanelSettings extends JPanel {	
	private static final long serialVersionUID = 1L;
	
	public static Color STD_BLUE_COLOR = new Color(85, 183, 254);
	public static Color STD_LIGHT_GRAY = new Color(217, 217, 217);
	public static Color STD_STRONG_GRAY = new Color(103, 103, 103);
	public static Color STD_NULL_COLOR = new Color(0, 0, 0, 0);
	
	public static Font STD_LIGHT_FONT = VUtils.loadFont("Montserrat-Light");
	public static Font STD_REGULAR_FONT = VUtils.loadFont("Montserrat-Regular");
	public static Font STD_MEDIUM_FONT = VUtils.loadFont("Montserrat-Medium");
	public static Font STD_BOLD_FONT = VUtils.loadFont("Montserrat-Bold");
	
	public StdGBC gbc = new StdGBC();
}
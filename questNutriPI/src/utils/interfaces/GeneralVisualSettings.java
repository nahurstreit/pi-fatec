package utils.interfaces;

import java.awt.Color;
import java.awt.Font;

import utils.view.FontsUtil;
import utils.view.LanguageUtil;

public interface GeneralVisualSettings {
	//Cores do sistema
	public static final Color STD_BLUE_COLOR = new Color(85, 183, 254);
	public static final Color STD_LIGHT_GRAY_COLOR = new Color(217, 217, 217);
	public static final Color STD_STRONG_GRAY_COLOR = new Color(103, 103, 103);
	public static final Color STD_NULL_COLOR = new Color(0, 0, 0, 0);
	public static final Color STD_WHITE_COLOR = Color.WHITE;
	public static final Color STD_RED_COLOR = Color.RED;
	public static final Color STD_BLACK_COLOR = Color.BLACK;
	
	//Fontes do sistema
	public static final Font STD_EXTRA_LIGHT_FONT = FontsUtil.load("Montserrat-ExtraLight");
	public static final Font STD_LIGHT_FONT = FontsUtil.load("Montserrat-Light");
	public static final Font STD_REGULAR_FONT = FontsUtil.load("Montserrat-Regular");
	public static final Font STD_MEDIUM_FONT = FontsUtil.load("Montserrat-Medium");
	public static final Font STD_BOLD_FONT = FontsUtil.load("Montserrat-Bold");
	
	//Textos padrão
	public static final String STD_DELETE_STRING = new LanguageUtil("EXCLUIR", "DELETE").get();
	
	//Assets do sistema
	public static final String QUESTNUTRI_SVG = "QuestNutri";
	public static final String QUESTNUTRI_FRAME_ICON_PNG = "QuestNutriAlphaChannel";
	public static final String LOGO_REVEAL_GIF = "LogoRevealQuestNutri";
	public static final String LOADING_GIF = "LogoRevealQuestNutri";
}
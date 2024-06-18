package utils.interfaces;

import java.awt.Image;

import utils.view.ImagesUtil;

/**
 * Interface para evitar a sobrecarga de load do ícone das páginas.
 */
public interface IAssetsLoad extends GeneralVisualSettings {
	public final Image FRAME_ICON = ImagesUtil.sizedImg("QuestNutriAlphaChannel", 8680, 4540).getImage();
}
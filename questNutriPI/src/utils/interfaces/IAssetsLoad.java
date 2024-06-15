package utils.interfaces;

import java.awt.Image;

import utils.view.ImagesUtil;

public interface IAssetsLoad extends GeneralVisualSettings {
	public final Image FRAME_ICON = ImagesUtil.sizedImg("QuestNutriAlphaChannel", 8680, 4540).getImage();
}
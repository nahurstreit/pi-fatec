package view.panels.pages.components.diet;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

import models.Meal;
import view.panels.pages.components.GenericComponent;
import view.utils.VUtils;

public class DietMealPanel extends GenericComponent {
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unused")
	private Meal meal;
	private DietDayPanel dayPanel;
	private final Color BG_COLOR = Color.white;
	private final Color TEXT_COLOR = new Color(85, 183, 254);
	private final Font NORMAL_FONT = VUtils.loadFont("Montserrat-SemiBold", 13f);
	

	public DietMealPanel(Meal meal, DietDayPanel dayPanel) {
		this.meal = meal;
		this.dayPanel = dayPanel;

		this.setBackground(BG_COLOR);
		JLabel lblMealName = new JLabel(meal.name);
		lblMealName.setFont(NORMAL_FONT);
		lblMealName.setForeground(TEXT_COLOR);
		
		this.add(lblMealName);
		if(!((meal.daysOfWeek & this.dayPanel.weekDay) == this.dayPanel.weekDay)) {
			this.setVisible(false);
		}
	}
}
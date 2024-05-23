package view.panels.pages.components.diet;

import java.awt.Color;

import javax.swing.JLabel;

import model.entities.Meal;
import view.panels.components.GenericJPanel;

public class DietMealPanel extends GenericJPanel {
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unused")
	private Meal meal;
	private DietDayPanel dayPanel;
	private final Color BG_COLOR = Color.white;
	private final Color TEXT_COLOR = STD_BLUE_COLOR;
	

	public DietMealPanel(Meal meal, DietDayPanel dayPanel) {
		this.meal = meal;
		this.dayPanel = dayPanel;

		this.setBackground(BG_COLOR);
		JLabel lblMealName = new JLabel(meal.name);
		lblMealName.setFont(STD_REGULAR_FONT.deriveFont(15f));
		lblMealName.setForeground(TEXT_COLOR);
		
		this.add(lblMealName);
		if(!((meal.daysOfWeek & this.dayPanel.weekDay) == this.dayPanel.weekDay)) {
			this.setVisible(false);
		}
	}
}
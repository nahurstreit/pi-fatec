package view.panels.pages.components.diet;

import java.awt.Color;

import javax.swing.JLabel;
import models.Meal;
import view.panels.pages.components.GenericComponent;

public class DietMealPanel extends GenericComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Meal meal;
	private DietDayPanel dayPanel;

	public DietMealPanel(Meal meal, DietDayPanel dayPanel) {
		this.meal = meal;
		this.dayPanel = dayPanel;

		this.setBackground(Color.white);
		this.add(new JLabel(meal.name));
		if(!((meal.daysOfWeek & this.dayPanel.weekDay) == this.dayPanel.weekDay)) {
			this.setVisible(false);
		}
	}
}
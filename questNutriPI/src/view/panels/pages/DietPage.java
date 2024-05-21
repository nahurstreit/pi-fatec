package view.panels.pages;

import models.Meal;
import view.panels.components.GenericJPanel;
import view.panels.pages.components.diet.DietWeekPanel;

public class DietPage extends GenericPage {
	private static final long serialVersionUID = 1L;
	
	private static Meal[] mealsExample = {
			new Meal("Café da Manhã", 10000, 84, true),
			new Meal("Café da Manhã", 10000, 42, true),
			new Meal("Café da Manhã", 10000, 1, true),
			new Meal("Almoço", 20000, 84, true),
			new Meal("Almoço", 20000, 42, true),
			new Meal("Almoço", 20000, 1, true),
			new Meal("Jantar", 20000, 84, true),
			new Meal("Jantar", 20000, 42, true),
			new Meal("Jantar", 20000, 1, true),
			
			new Meal("Café da tarde", 20000, 64, true),
			new Meal("Café da tarde", 20000, 16, true),
			new Meal("Café da tarde", 20000, 2, true),
		};
	
	public DietPage(GenericJPanel ownerPanel) {
		this(mealsExample, ownerPanel);
	}
	
	public DietPage(Meal[] meals, GenericJPanel ownerPanel) {
		super(ownerPanel);
		this.ltGridBag();
		this.add(new DietWeekPanel(meals, this), gbc.fill("BOTH").wgt(1.0));
	}

}
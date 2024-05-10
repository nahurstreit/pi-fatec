package view.panels.pages;

import models.Meal;
import view.panels.pages.components.diet.DietMainPanel;

public class DietPage extends GenericPage {
	private static final long serialVersionUID = 1L;
	
	private static Meal[] mealsExample = {
			new Meal("Café da Manhã A", 10000, 84, true),
			new Meal("Café da Manhã B", 10000, 42, true),
			new Meal("Café da Manhã c", 10000, 1, true),
			new Meal("Almoço A", 20000, 84, true),
			new Meal("Almoço B", 20000, 42, true),
			new Meal("Almoço C", 20000, 1, true),
			new Meal("Jantar A", 20000, 84, true),
			new Meal("Jantar B", 20000, 42, true),
			new Meal("Jantar C", 20000, 1, true),
			
			new Meal("Café da tarde X", 20000, 64, true),
			new Meal("Café da tarde Y", 20000, 16, true),
			new Meal("Café da tarde Z", 20000, 2, true),
		};
	
	public DietPage() {
		this(mealsExample);
	}
	
	public DietPage(Meal[] meals) {
		super();
		this.ltGridBag();
		
		this.add(new DietMainPanel(meals), 
				gbc.grid(0).apple(10).anchor("WEST"));
	}

}
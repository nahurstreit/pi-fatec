package view.panels.pages;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import models.Meal;
import view.panels.pages.components.diet.DietMainPanel;

public class DietPage extends GenericPage {
	
	public DietPage() {
		super();
		
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		initGbc(gbc);
		
		Meal[] meals = {
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
		
		this.add(new DietMainPanel(meals), gbc);
	}
	
	public DietPage(Meal[] meals) {
		super();
		
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		initGbc(gbc);
		
		this.add(new DietMainPanel(meals), gbc);
	}
	
	protected void initGbc(GridBagConstraints gbc) {
		super.initGbc(gbc);
		gbc.ipadx = 100;
		gbc.ipady = 100;
		gbc.insets = new Insets(25, 25, 25, 25);
	}
}
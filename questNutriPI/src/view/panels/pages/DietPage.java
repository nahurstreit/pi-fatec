package view.panels.pages;

import java.util.List;

import model.entities.Customer;
import model.entities.Meal;
import view.panels.components.GenericJPanel;
import view.panels.pages.components.diet.DietWeekPanel;

public class DietPage extends GenericPage {
	private static final long serialVersionUID = 1L;
	
	private List<Meal> meals;
	
	private GenericJPanel weekPanel = new GenericJPanel().ltGridBag();
	
	private int pagePadding = 10;
	
	public DietPage(GenericJPanel ownerPanel, Customer customer) {
		super(ownerPanel);
		this.ltGridBag();
		try {
			meals = customer.getDiet();
		} catch (Exception e) {
			meals = null;
		}
	
		weekPanel.add(new DietWeekPanel(this, meals), weekPanel.gbc.fill("BOTH").wgt(1.0));
		
		this.add(weekPanel, gbc.fill("BOTH").wgt(1.0).insets(pagePadding));
	}

}
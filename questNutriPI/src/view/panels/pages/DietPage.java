package view.panels.pages;

import java.util.List;

import model.entities.Customer;
import model.entities.Meal;
import view.panels.components.GenericJPanel;
import view.panels.pages.components.diet.DietWeekPanel;

public class DietPage extends GenericPage {
	private static final long serialVersionUID = 1L;
	
	public DietPage(GenericJPanel ownerPanel) {
		this(ownerPanel, Customer.findByPK(1).getDiet());
	}
	
	public DietPage(GenericJPanel ownerPanel, List<Meal> meals) {
		super(ownerPanel);
		this.ltGridBag();
		this.add(new DietWeekPanel(this, meals), gbc.fill("BOTH").wgt(1.0));
	}

}
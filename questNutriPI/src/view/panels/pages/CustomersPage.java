package view.panels.pages;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import models.Meal;
import view.QuestNutri;
import view.panels.LoggedPanel;

public class CustomersPage extends GenericPage {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public CustomersPage(LoggedPanel mainPanel) {
		super();
		this.menuBarPageName = "Clientes";
		this.add(new JLabel("Customers Page"));
		JButton c1 = new JButton("Cliente 1");
		
		Meal[] mealsCliente1 = {
				new Meal("Café da Manhã A - C1", 10000, 84, true),
				new Meal("Café da Manhã B - C1", 10000, 42, true),
				new Meal("Café da Manhã C - C1", 10000, 1, true),
				new Meal("Almoço A - C1", 20000, 84, true),
				new Meal("Almoço B - C1", 20000, 42, true),
				new Meal("Almoço C - C1", 20000, 1, true),
				new Meal("Jantar A - C1", 20000, 84, true),
				new Meal("Jantar B - C1", 20000, 42, true),
				new Meal("Jantar C - C1", 20000, 1, true),
				
				new Meal("Café da tarde X - C1", 20000, 64, true),
				new Meal("Café da tarde Y - C1", 20000, 16, true),
				new Meal("Café da tarde Z - C1", 20000, 2, true),
			};
		
		c1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainPanel.swapLoggedMainPanel(new DietPage(mealsCliente1));
			}
		});
		this.add(c1);
		
		JButton c2 = new JButton("Cliente 2");
		Meal[] mealsCliente2 = {
				new Meal("Café da Manhã - C2", 10000, 127, true),
				new Meal("Lanche Manhã - C2", 10000, 127, true),
				new Meal("Almoço - C2", 10000, 127, true),
				new Meal("Lanche da Tarde - C2", 10000, 127, true),
				new Meal("Jantar - C2", 10000, 127, true),
				new Meal("Lanche da Noite - C2", 10000, 127, true),
			};
		c2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainPanel.swapLoggedMainPanel(new DietPage(mealsCliente2));
			}
		});

		this.add(c2);
	}
}
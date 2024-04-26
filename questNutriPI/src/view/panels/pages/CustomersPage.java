package view.panels.pages;

import javax.swing.JLabel;

public class CustomersPage extends GenericPage {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public CustomersPage() {
		super();
		this.menuBarPageName = "Clientes";
		this.add(new JLabel("Customers Page"));
	}

}

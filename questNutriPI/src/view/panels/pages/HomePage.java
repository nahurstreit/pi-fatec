package view.panels.pages;

import javax.swing.JLabel;

public class HomePage extends GenericPage {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public HomePage() {
		super();
		this.menuBarPageName = "Home";		
		this.add(new JLabel("Home"));
	}
}
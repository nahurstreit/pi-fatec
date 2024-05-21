package view.panels.pages;

import javax.swing.JLabel;

import view.panels.components.GenericJPanel;

public class HomePage extends GenericPage {

	private static final long serialVersionUID = 1L;

	public HomePage(GenericJPanel ownerPanel) {
		super(ownerPanel);
		this.add(new JLabel("Home"));
	}
}
package view.pages;

import javax.swing.JLabel;

import view.components.generics.GenericJPanel;
import view.pages.generics.GenericPage;

public class HomePage extends GenericPage {

	private static final long serialVersionUID = 1L;

	public HomePage(GenericJPanel ownerPanel) {
		super(ownerPanel);
		this.add(new JLabel("Home"));
	}
}
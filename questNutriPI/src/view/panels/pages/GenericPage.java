package view.panels.pages;

import java.awt.Color;

import view.panels.components.GenericJPanel;

public class GenericPage extends GenericJPanel {
	private static final long serialVersionUID = 1L;
	
	public GenericPage(GenericJPanel ownerPanel) {
		super(ownerPanel);
		this.setBackground(Color.white);
	}
}
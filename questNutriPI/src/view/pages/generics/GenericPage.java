package view.pages.generics;

import view.components.generics.GenericJPanel;

public class GenericPage extends GenericJPanel {
	private static final long serialVersionUID = 1L;
	
	public GenericPage(GenericJPanel ownerPanel) {
		super(ownerPanel);
		this.setBackground(STD_WHITE_COLOR);
	}
}
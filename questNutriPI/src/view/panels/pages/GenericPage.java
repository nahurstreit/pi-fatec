package view.panels.pages;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class GenericPage extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public String menuBarPageName = "Definir nome";
	public BufferedImage icon = null;

	/**
	 * Create the panel.
	 */
	public GenericPage() {
		super();
		this.setBackground(Color.white);
	}
	
	protected void initGbc(GridBagConstraints gbc) {
		gbc.gridx = 0; //número horizontal da célula no grid
		gbc.gridy = 0; //número vertical da célula no grid
		gbc.weightx = 1.0; //redimensionamento horizontal
		gbc.weighty = 1.0; //redimensionamento vertical
		gbc.ipadx = 10;  //distância horizontal conteúdo até as bordas do grid
		gbc.ipady = 10;//distância vertical conteúdo até as bordas do grid
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.BOTH; //modo de preenchimento do grid
	}

}

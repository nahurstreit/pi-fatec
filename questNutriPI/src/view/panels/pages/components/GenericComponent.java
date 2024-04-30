package view.panels.pages.components;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;

public class GenericComponent extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected GridBagConstraints gbc = new GridBagConstraints();
	
	public GenericComponent() {
		this.setLayout(new GridBagLayout());
		initGbc();
	}

	protected void initGbc() {
		gbc.gridx = 0; //número horizontal da célula no grid
		gbc.gridy = 0; //número vertical da célula no grid
		gbc.weightx = 1.0; //redimensionamento horizontal
		gbc.weighty = 1.0; //redimensionamento vertical
		gbc.ipadx = 10;  //distância horizontal conteúdo até as bordas do grid
		gbc.ipady = 10;//distância vertical conteúdo até as bordas do grid
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.BOTH; //modo de preenchimento do grid
	}
	
	protected void initGbc(GridBagConstraints destinyGbc) {
		destinyGbc.gridx = 0; //número horizontal da célula no grid
		destinyGbc.gridy = 0; //número vertical da célula no grid
		destinyGbc.weightx = 1.0; //redimensionamento horizontal
		destinyGbc.weighty = 1.0; //redimensionamento vertical
		destinyGbc.ipadx = 10;  //distância horizontal conteúdo até as bordas do grid
		destinyGbc.ipady = 10;//distância vertical conteúdo até as bordas do grid
		destinyGbc.anchor = GridBagConstraints.WEST;
		destinyGbc.fill = GridBagConstraints.BOTH; //modo de preenchimento do grid
	}
	
	protected void setInset(int value) {
		gbc.insets = new Insets(value, value, value, value);
	}
	

}
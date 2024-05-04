package view.panels.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;

import view.utils.VUtils;

public abstract class GeneralJPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected Color STD_BLUE_COLOR = new Color(85, 183, 254);
	protected Color STD_LIGHT_GRAY = new Color(217, 217, 217);
	protected Color STD_STRONG_GRAY = new Color(103, 103, 103);
	protected Color STD_NULL_COLOR = new Color(0, 0, 0, 0);
	
	protected Font STD_LIGHT_FONT = VUtils.loadFont("Montserrat-Light");
	protected Font STD_REGULAR_FONT = VUtils.loadFont("Montserrat-Regular");
	protected Font STD_MEDIUM_FONT = VUtils.loadFont("Montserrat-Medium");
	protected Font STD_BOLD_FONT = VUtils.loadFont("Montserrat-Bold");
	
	protected GridBagConstraints gbc = new GridBagConstraints();
	
	/**
	 * Método para rápida definição de layout como gridBagLayout;
	 */
	protected void ltGridBag() {
		this.setLayout(new GridBagLayout());
	}
	
	/**
	 * Método para rápido reset da variável gbc.
	 */
	protected void resetGbc() {
		this.gbc = new GridBagConstraints();
	}
	
	/**
	 * Método para rápida inserção dos insets
	 * @param insets -> Valores inteiros para a atribuição dos insets. A atribuição dos insets será condicionada da seguinte maneira:
	 * <li><b>setGbcInset()</b>-> Será tratado como: new Insets(0, 0, 0, 0);</li>
	 * <li><b>setGbcInset(1)</b>-> Será tratado como: new Insets(1, 1, 1, 1);</li>
	 * <li><b>setGbcInset(1, 2)</b>-> Será tratado como: new Insets(1, 2, 1, 2);</li>
	 * <li><b>setGbcInset(1, 2, 3)</b>-> Será tratado como: new Insets(0, 0, 0, 0);</li>
	 * <li><b>setGbcInset(1, 2, 3, 4)</b>-> Será tratado como: new Insets(1, 2, 3, 4);</li>
	 */
	protected void gbcInsets(int ...insets) {
		int top = 0, left = 0, bottom = 0, right = 0;
		switch(insets.length) {
		case 1:
			top = insets[0];
			left = insets[0];
			bottom = insets[0];
			right = insets[0];
			break;
		case 2:
			top = insets[0];
			left = insets[1];
			bottom = insets[0];
			right = insets[1];
			break;
		case 4:
			top = insets[0];
			left = insets[1];
			bottom = insets[2];
			right = insets[3];
			break;
		default:
		}
		
		this.gbc.insets = new Insets(top, left, bottom, right);
	}
	
	/**
	 * Método para aumentar rapidamente uma unidade no eixo y de distribuição do GridBagConstraints
	 * @return <b>gbc</b> aumentando gridy em uma unidade.
	 */
	protected GridBagConstraints gbcYp() {
		this.gbc.gridy++;
		return gbc;
	}
	
	/**
	 * Método para aumentar rapidamente uma unidade no eixo x de distribuição do GridBagConstraints
	 * @return <b>gbc</b> aumentando gridx em uma unidade.
	 */
	protected GridBagConstraints gbcXp() {
		this.gbc.gridx++;
		return gbc;
	}
	
	/**
	 * Método para atribuir rapidamente o modo de fill da variável gbc.
	 * @param mode
	 */
	protected void gbcFill(String mode) {
		switch(mode) {
		case "BOTH":
			gbc.fill = GridBagConstraints.BOTH;
			break;
		case "HORIZONTAL":
			gbc.fill = GridBagConstraints.HORIZONTAL;
			break;
		case "VERTICAL":
			gbc.fill = GridBagConstraints.VERTICAL;
			break;
		case "NONE":
			gbc.fill = GridBagConstraints.NONE;
			break;
		}
	}
	
	/**
	 * Método para atribuir rapidamente o modo de anchor da variável gbc.
	 * @param mode
	 */
	protected void gbcAnchor(String mode) {
		switch(mode) {
		case "WEST":
			gbc.anchor = GridBagConstraints.WEST;
			break;
		}
	}
	
	/**
	 * Método para atribuir rapidamente o valor de gbc.weightx
	 * @param value -> valor para atribuir à gbc.weightx;
	 */
	protected void gbcWgX(double value) {
		this.gbc.weightx = value;
	}
	
	/**
	 * Método para atribuir rapidamente o valor de gbc.weighty
	 * @param value -> valor para atribuir à gbc.weighty;
	 */
	protected void gbcWgY(double value) {
		this.gbc.weighty = value;
	}
	
	/**
	 * Método para atribuir rapidamente o valor de gbc.weightx e gbc.weighty ao mesmo tempo.
	 * @param x -> valor de gbc.weightx
	 * @param y -> valor de gbc.weighty
	 */
	protected void gbcWgXY(double x, double y) {
		this.gbcWgX(x);
		this.gbcWgY(y);
	}
	
	/**
	 * Método para atribuir rapidamente o valor de gbc.weightx e gbc.weighty ao mesmo tempo.
	 * @param value -> valor de ambos gbc.weightx e gbc.weighty
	 */
	protected void gbcWgXY(double value) {
		this.gbcWgXY(value, value);
	}
	
	/**
	 * Método para atribuir rapidamente o valor de gbc.ipadx.
	 * @param value -> valor inteiro para ser atribuído em gbc.ipadx
	 */
	protected void gbcPadX(int value) {
		this.gbc.ipadx = value;
	}
	
	/**
	 * Método para atribuir rapidamente o valor de gbc.ipady.
	 * @param value -> valor inteiro para ser atribuído em gbc.ipady
	 */
	protected void gbcPadY(int value) {
		this.gbc.ipady = value;
	}
	
	/**
	 * Método para atribuir rapidamente o valor de gbc.ipadx e gbc.ipady ao mesmo tempo.
	 * @param x -> valor inteiro para ser atribuído em gbc.ipadx
	 * @param y -> valor inteiro para ser atribuído em gbc.ipady
	 */
	protected void gbcApple(int x, int y) {
		this.gbcPadX(x);
		this.gbcPadY(y);
	}
	
	/**
	 * Método para atribuir rapidamente o valor de gbc.ipadx e gbc.ipady ao mesmo tempo.
	 * @param value -> valor inteiro para ser atribuído em gbc.ipadx e em gbc.ipady
	 */
	protected void gbcApple(int value) {
		this.gbcPadX(value);
		this.gbcPadY(value);
	}
	
	/**
	 * Método para atribuir rapidamente o valor de gbc.gridx
	 * @param value -> valor inteiro para ser atribuído em gbc.gridx
	 */
	protected void gbcGridX(int value) {
		this.gbc.gridx = value;
	}
	
	/**
	 * Método para atribuir rapidamente o valor de gbc.gridy
	 * @param value -> valor inteiro para ser atribuído em gbc.gridy
	 */
	protected void gbcGridY(int value) {
		this.gbc.gridy = value;
	}
	
	/**
	 * Método para atribuir rapidamente o valor de gbc.gridx e de gbc.gridy ao mesmo tempo
	 * @param x -> valor inteiro para ser atribuído em gbc.gridx
	 * @param y -> valor inteiro para ser atribuído em gbc.gridy
	 */
	protected void gbcGridXY(int x, int y) {
		this.gbcGridX(x);
		this.gbcGridY(y);
	}
	
	/**
	 * Método para atribuir rapidamente o valor de gbc.gridx e de gbc.gridy ao mesmo tempo
	 * @param value -> valor inteiro para ser atribuído em gbc.gridx e gbc.gridy
	 */
	protected void gbcGridXY(int value) {
		this.gbcGridX(value);
		this.gbcGridY(value);
	}
}
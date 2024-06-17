package view.components.utils;

import java.awt.GridBagConstraints;
import java.awt.Insets;

public class StdGBC extends GridBagConstraints {
	private static final long serialVersionUID = 1L;

	public StdGBC() {
		super();
	}
	
	/**
	 * Método para rápida inserção dos insets.
	 * <br>Ordem de referência dos insets: top, left, bottom, right.
	 * @param values -> Valores inteiros para a atribuição dos insets. A atribuição dos insets será condicionada da seguinte maneira:
	 * <li><b>inset()</b>-> Será tratado como: new Insets(0, 0, 0, 0);</li>
	 * <li><b>inset(1)</b>-> Será tratado como: new Insets(1, 1, 1, 1);</li>
	 * <li><b>inset(1, 2)</b>-> Será tratado como: new Insets(1, 2, 1, 2);</li>
	 * <li><b>inset(1, 2, 3)</b>-> Será tratado como: new Insets(0, 0, 0, 0);</li>
	 * <li><b>inset(1, 2, 3, 4)</b>-> Será tratado como: new Insets(1, 2, 3, 4);</li>
	 */
	public StdGBC insets(int ...values) {
		int top = 0, left = 0, bottom = 0, right = 0;
		switch(values.length) {
		case 1:
			top = values[0];
			left = values[0];
			bottom = values[0];
			right = values[0];
			break;
		case 2:
			top = values[0];
			left = values[1];
			bottom = values[0];
			right = values[1];
			break;
		case 4:
			top = values[0];
			left = values[1];
			bottom = values[2];
			right = values[3];
			break;
		default:
		}
		
		this.insets = new Insets(top, left, bottom, right);
		return this;
	}
	
	
	/**
	 * Método para rápida inserção dos insets
	 * <br>Ordem de referência dos insets: top, left, bottom, right.
	 * @param values -> Valores inteiros para a atribuição dos insets. A atribuição dos insets será condicionada da seguinte maneira:
	 * <li><b>inset()</b>-> Será tratado como: new Insets(0, 0, 0, 0);</li>
	 * <li><b>inset(1)</b>-> Será tratado como: new Insets(1, 1, 1, 1);</li>
	 * <li><b>inset(1, 2)</b>-> Será tratado como: new Insets(1, 2, 1, 2);</li>
	 * <li><b>inset(1, 2, 3)</b>-> Será tratado como: new Insets(0, 0, 0, 0);</li>
	 * <li><b>inset(1, 2, 3, 4)</b>-> Será tratado como: new Insets(1, 2, 3, 4);</li>
	 */
	public StdGBC insets(String position, int positionValue, int remaindersValues) {
		int top = remaindersValues, left = remaindersValues, bottom = remaindersValues, right = remaindersValues;
		switch(position) {
		case "1":
			top = positionValue;
			break;
		case "2":
			left = positionValue;
			break;
		case "3":
			bottom = positionValue;
			break;
		case "4":
			right = positionValue;
			break;
		default:
			return this.insets(remaindersValues);
		}
		
		this.insets = new Insets(top, left, bottom, right);
		return this;
	}
	
	/**
	 * Método para aumentar rapidamente uma unidade no eixo y de distribuição do GridBagConstraints
	 * @return <b>gbc</b> aumentando gridy em uma unidade.
	 */
	public StdGBC yP() {
		this.gridy += 1;
		return this;
	}
	
	/**
	 * Método para aumentar rapidamente uma unidade no eixo x de distribuição do GridBagConstraints
	 * @return <b>gbc</b> aumentando gridx em uma unidade.
	 */
	public StdGBC xP() {
		this.gridx += 1;
		return this;
	}
	
	/**
	 * Método para atribuir rapidamente o modo de fill da variável gbc.
	 * @param mode
	 */
	public StdGBC fill(String mode) {
		switch(mode) {
		case "BOTH":
			this.fill = GridBagConstraints.BOTH;
			break;
		case "HORIZONTAL":
			this.fill = GridBagConstraints.HORIZONTAL;
			break;
		case "VERTICAL":
			this.fill = GridBagConstraints.VERTICAL;
			break;
		case "NONE":
			this.fill = GridBagConstraints.NONE;
			break;
		}
		return this;
	}
	
	/**
	 * Método para atribuir rapidamente o modo de anchor da variável gbc.
	 * @param mode
	 */
	public StdGBC anchor(String mode) {
		switch(mode) {
		case "WEST":
			this.anchor = GridBagConstraints.WEST;
			break;
		case "NORTHWEST":
			this.anchor = GridBagConstraints.NORTHWEST;
			break;
		case "EAST":
			this.anchor = GridBagConstraints.EAST;
			break;
		case "NORTHEAST":
			this.anchor = GridBagConstraints.NORTHEAST;
			break;
		case "CENTER":
			this.anchor = GridBagConstraints.CENTER;
			break;
		}
		return this;
	}
	
	/**
	 * Método para atribuir rapidamente o valor de gbc.weightx
	 * @param value -> valor para atribuir à gbc.weightx;
	 */
	public StdGBC wgtX(double value) {
		this.weightx = value;
		return this;
	}
	
	/**
	 * Método para atribuir rapidamente o valor de gbc.weighty
	 * @param value -> valor para atribuir à gbc.weighty;
	 */
	public StdGBC wgtY(double value) {
		this.weighty = value;
		return this;
	}
	
	/**
	 * Método para atribuir rapidamente o valor de gbc.weightx e gbc.weighty ao mesmo tempo.
	 * @param x -> valor de gbc.weightx
	 * @param y -> valor de gbc.weighty
	 */
	public StdGBC wgt(double x, double y) {
		this.wgtX(x);
		this.wgtY(y);
		return this;
	}
	
	/**
	 * Método para atribuir rapidamente o valor de gbc.weightx e gbc.weighty ao mesmo tempo.
	 * @param value -> valor de ambos gbc.weightx e gbc.weighty
	 */
	public StdGBC wgt(double value) {
		this.wgt(value, value);
		return this;
	}
	
	/**
	 * Método para atribuir rapidamente o valor de gbc.ipadx.
	 * @param value -> valor inteiro para ser atribuído em gbc.ipadx
	 */
	public StdGBC ipadX(int value) {
		this.ipadx = value;
		return this;
	}
	
	/**
	 * Método para atribuir rapidamente o valor de gbc.ipady.
	 * @param value -> valor inteiro para ser atribuído em gbc.ipady
	 */
	public StdGBC ipadY(int value) {
		this.ipady = value;
		return this;
	}
	
	/**
	 * Método para atribuir rapidamente o valor de gbc.ipadx e gbc.ipady ao mesmo tempo.
	 * @param x -> valor inteiro para ser atribuído em gbc.ipadx
	 * @param y -> valor inteiro para ser atribuído em gbc.ipady
	 */
	public StdGBC apple(int x, int y) {
		this.ipadX(x);
		this.ipadY(y);
		return this;
	}
	
	/**
	 * Método para atribuir rapidamente o valor de gbc.ipadx e gbc.ipady ao mesmo tempo.
	 * @param value -> valor inteiro para ser atribuído em gbc.ipadx e em gbc.ipady
	 */
	public StdGBC apple(int value) {
		this.apple(value, value);
		return this;
	}
	
	/**
	 * Método para atribuir rapidamente o valor de gbc.gridx
	 * @param value -> valor inteiro para ser atribuído em gbc.gridx
	 */
	public StdGBC gridX(int value) {
		this.gridx = value;
		return this;
	}
	
	/**
	 * Método para atribuir rapidamente o valor de gbc.gridy
	 * @param value -> valor inteiro para ser atribuído em gbc.gridy
	 */
	public StdGBC gridY(int value) {
		this.gridy = value;
		return this;
	}
	
	/**
	 * Método para atribuir rapidamente o valor de gbc.gridx e de gbc.gridy ao mesmo tempo
	 * @param x -> valor inteiro para ser atribuído em gbc.gridx
	 * @param y -> valor inteiro para ser atribuído em gbc.gridy
	 */
	public StdGBC grid(int x, int y) {
		this.gridX(x);
		this.gridY(y);
		return this;
	}
	
	/**
	 * Método para atribuir rapidamente o valor de gbc.gridx e de gbc.gridy ao mesmo tempo
	 * @param value -> valor inteiro para ser atribuído em gbc.gridx e gbc.gridy
	 */
	public StdGBC grid(int value) {
		this.grid(value, value);
		return this;
	}
	
	public StdGBC width(int value) {
		this.gridwidth = value;
		return this;
	}
	
	public StdGBC width(String value) {
		switch(value) {
		case "REMAINDER":
			this.width(GridBagConstraints.REMAINDER);
			break;
		}
		return this;
	}
	
	public StdGBC height(int value) {
		this.gridheight = value;
		return this;
	}
	
	public StdGBC height(String value) {
		switch(value) {
		case "REMAINDER":
			this.height(GridBagConstraints.REMAINDER);
			break;
		}
		return this;
	}
	
	@Override
	public String toString() {
		return "StdGBC [gridx=" + gridx + ", gridy=" + gridy + ", gridwidth=" + gridwidth + ", gridheight=" + gridheight
				+ ", weightx=" + weightx + ", weighty=" + weighty + ", anchor=" + anchor + ", fill=" + fill
				+ ", insets=" + insets + ", ipadx=" + ipadx + ", ipady=" + ipady
				+ ", toString()=" + super.toString() + "]";
	}

	public StdGBC size(int width, int height) {
		this.width(width);
		this.height(height);
		return this;
	}

}
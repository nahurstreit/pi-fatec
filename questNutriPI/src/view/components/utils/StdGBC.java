package view.components.utils;

import java.awt.GridBagConstraints;
import java.awt.Insets;

/**
 * Extensão de GridBagConstraints para configuração simplificada de layout de grade.
 */
public class StdGBC extends GridBagConstraints {
	private static final long serialVersionUID = 1L;

    /**
     * Construtor padrão que inicializa GridBagConstraints com os valores padrão.
     */
	public StdGBC() {
		super();
	}
	
	/**
	 * Método para rápida inserção dos insets.
	 * <br>Ordem de referência dos insets: top, left, bottom, right.
	 * @param values -> Valores inteiros para a atribuição dos insets. A atribuição dos insets será condicionada da seguinte maneira:
	 * <ul>
	 * <li><b>inset()</b>-> Será tratado como: new Insets(0, 0, 0, 0);</li>
	 * <li><b>inset(1)</b>-> Será tratado como: new Insets(1, 1, 1, 1);</li>
	 * <li><b>inset(1, 2)</b>-> Será tratado como: new Insets(1, 2, 1, 2);</li>
	 * <li><b>inset(1, 2, 3)</b>-> Será tratado como: new Insets(0, 0, 0, 0);</li>
	 * <li><b>inset(1, 2, 3, 4)</b>-> Será tratado como: new Insets(1, 2, 3, 4);</li>
	 * </ul>
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
	 * <ul>
	 * <li><b>inset()</b>-> Será tratado como: new Insets(0, 0, 0, 0);</li>
	 * <li><b>inset(1)</b>-> Será tratado como: new Insets(1, 1, 1, 1);</li>
	 * <li><b>inset(1, 2)</b>-> Será tratado como: new Insets(1, 2, 1, 2);</li>
	 * <li><b>inset(1, 2, 3)</b>-> Será tratado como: new Insets(0, 0, 0, 0);</li>
	 * <li><b>inset(1, 2, 3, 4)</b>-> Será tratado como: new Insets(1, 2, 3, 4);</li>
	 * </ul>
	 * 
	 * @param position - Posição a ser atribuida um valor diferente.
	 * @param positionValue - Valor dessa posição.
	 * @param remaindersValues - valor dos outros insets.
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
     * Incrementa rapidamente o valor de gridy do GridBagConstraints.
     * 
     * @return o próprio objeto para implementar fluent interface.
     */
	public StdGBC yP() {
		this.gridy += 1;
		return this;
	}
	
    /**
     * Incrementa rapidamente o valor de gridx do GridBagConstraints.
     * 
     * @return o próprio objeto para implementar fluent interface.
     */
	public StdGBC xP() {
		this.gridx += 1;
		return this;
	}
	
    /**
     * Configura rapidamente o modo de fill do GridBagConstraints.
     * 
     * @param mode modo de fill ("BOTH", "HORIZONTAL", "VERTICAL", "NONE")
     * @return o próprio objeto para implementar fluent interface.
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
     * Configura rapidamente o modo de anchor do GridBagConstraints.
     * 
     * @param mode modo de anchor ("WEST", "NORTHWEST", "EAST", "NORTHEAST", "CENTER")
     * @return o próprio objeto para implementar fluent interface.
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
     * Configura o valor de weightx do GridBagConstraints.
     * 
     * @param value valor para atribuir a weightx
     * @return o próprio objeto para implementar fluent interface.
     */
	public StdGBC wgtX(double value) {
		this.weightx = value;
		return this;
	}
	
	/**
     * Configura o valor de weighty do GridBagConstraints.
     * 
     * @param value valor para atribuir a weighty
     * @return o próprio objeto para implementar fluent interface.
     */
	public StdGBC wgtY(double value) {
		this.weighty = value;
		return this;
	}
	
    /**
     * Configura os valores de weightx e weighty do GridBagConstraints ao mesmo tempo.
     * 
     * @param x valor de weightx
     * @param y valor de weighty
     * @return o próprio objeto para implementar fluent interface.
     */
	public StdGBC wgt(double x, double y) {
		this.wgtX(x);
		this.wgtY(y);
		return this;
	}
	
    /**
     * Configura os valores de weightx e weighty do GridBagConstraints ao mesmo tempo.
     * 
     * @param value valor para atribuir a weightx e weighty
     * @return o próprio objeto para implementar fluent interface.
     */
	public StdGBC wgt(double value) {
		this.wgt(value, value);
		return this;
	}
	
    /**
     * Configura o valor de ipadx do GridBagConstraints.
     * 
     * @param value valor inteiro para atribuir a ipadx
     * @return o próprio objeto para implementar fluent interface.
     */
	public StdGBC ipadX(int value) {
		this.ipadx = value;
		return this;
	}
	
    /**
     * Configura o valor de ipady do GridBagConstraints.
     * 
     * @param value valor inteiro para atribuir a ipady
     * @return o próprio objeto para implementar fluent interface.
     */
	public StdGBC ipadY(int value) {
		this.ipady = value;
		return this;
	}
	
    /**
     * Configura os valores de ipadx e ipady do GridBagConstraints ao mesmo tempo.
     * 
     * @param x valor inteiro para atribuir a ipadx
     * @param y valor inteiro para atribuir a ipady
     * @return o próprio objeto para implementar fluent interface.
     */
	public StdGBC apple(int x, int y) {
		this.ipadX(x);
		this.ipadY(y);
		return this;
	}
	
    /**
     * Configura os valores de ipadx e ipady do GridBagConstraints ao mesmo tempo.
     * 
     * @param value valor inteiro para atribuir a ipadx e ipady
     * @return o próprio objeto para implementar fluent interface.
     */
	public StdGBC apple(int value) {
		this.apple(value, value);
		return this;
	}
	
    /**
     * Configura o valor de gridx do GridBagConstraints.
     * 
     * @param value valor inteiro para atribuir a gridx
     * @return o próprio objeto para implementar fluent interface.
     */
	public StdGBC gridX(int value) {
		this.gridx = value;
		return this;
	}
	
    /**
     * Configura o valor de gridy do GridBagConstraints.
     * 
     * @param value valor inteiro para atribuir a gridy
     * @return o próprio objeto para implementar fluent interface.
     */
	public StdGBC gridY(int value) {
		this.gridy = value;
		return this;
	}
	
    /**
     * Configura os valores de gridx e gridy do GridBagConstraints ao mesmo tempo.
     * 
     * @param x valor inteiro para atribuir a gridx
     * @param y valor inteiro para atribuir a gridy
     * @return o próprio objeto para implementar fluent interface.
     */
	public StdGBC grid(int x, int y) {
		this.gridX(x);
		this.gridY(y);
		return this;
	}
	
    /**
     * Configura os valores de gridx e gridy do GridBagConstraints ao mesmo tempo.
     * 
     * @param value valor inteiro para atribuir a gridx e gridy
     * @return o próprio objeto para implementar fluent interface.
     */
	public StdGBC grid(int value) {
		this.grid(value, value);
		return this;
	}
	
    /**
     * Configura o valor de gridwidth do GridBagConstraints.
     * 
     * @param value valor inteiro para atribuir a gridwidth
     * @return o próprio objeto para implementar fluent interface.
     */
	public StdGBC width(int value) {
		this.gridwidth = value;
		return this;
	}
	
    /**
     * Configura o valor de gridwidth do GridBagConstraints para "REMAINDER" (usado para a última célula da linha).
     * 
     * @param value valor para configurar como "REMAINDER"
     * @return o próprio objeto para implementar fluent interface.
     */
	public StdGBC width(String value) {
		switch(value) {
		case "REMAINDER":
			this.width(GridBagConstraints.REMAINDER);
			break;
		}
		return this;
	}
	
    /**
     * Configura o valor de gridheight do GridBagConstraints.
     * 
     * @param value valor inteiro para atribuir a gridheight
     * @return o próprio objeto para implementar fluent interface.
     */
	public StdGBC height(int value) {
		this.gridheight = value;
		return this;
	}
	
    /**
     * Configura o valor de gridheight do GridBagConstraints para "REMAINDER" (usado para a última célula da coluna).
     * 
     * @param value valor para configurar como "REMAINDER"
     * @return o próprio objeto para implementar fluent interface.
     */
	public StdGBC height(String value) {
		switch(value) {
		case "REMAINDER":
			this.height(GridBagConstraints.REMAINDER);
			break;
		}
		return this;
	}
	
    /**
     * Retorna uma representação em string do objeto StdGBC, exibindo os valores atuais dos campos.
     */
	@Override
	public String toString() {
		return "StdGBC [gridx=" + gridx + ", gridy=" + gridy + ", gridwidth=" + gridwidth + ", gridheight=" + gridheight
				+ ", weightx=" + weightx + ", weighty=" + weighty + ", anchor=" + anchor + ", fill=" + fill
				+ ", insets=" + insets + ", ipadx=" + ipadx + ", ipady=" + ipady
				+ ", toString()=" + super.toString() + "]";
	}

    /**
     * Configura os valores de gridwidth e gridheight do GridBagConstraints ao mesmo tempo.
     * 
     * @param width  valor para atribuir a gridwidth
     * @param height valor para atribuir a gridheight
     * @return o próprio objeto para implementar fluent interface.
     */
	public StdGBC size(int width, int height) {
		this.width(width);
		this.height(height);
		return this;
	}

}
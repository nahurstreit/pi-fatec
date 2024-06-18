/**
 * Package que contém a classe de compontente botão.
 */
package view.components.buttons;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.border.LineBorder;

import utils.interfaces.GeneralVisualSettings;
import view.components.utils.RoundedBorder;

/**
 * Classe personalizada que estende JButton para criar botões com configurações visuais padronizadas.
 * Implementa a interface GeneralVisualSettings para definir configurações visuais gerais.
 */
public class StdButton extends JButton implements GeneralVisualSettings {
	private static final long serialVersionUID = 1L;
	
	private Color btnColor;

    /**
     * Interface funcional para ação do botão.
     */
	public interface Action {
		void execute();
	}
	
	private Action action;
	
    /**
     * Construtor que cria um botão com o texto e a ação especificados.
     *
     * @param text Texto a ser exibido no botão.
     * @param action Ação a ser executada quando o botão é clicado.
     */
	public StdButton(String text, Action action) {
		super(text);
		this.setAction(action);
	}
	
    /**
     * Construtor padrão para criar um botão sem texto e sem ação.
     */
	public StdButton() {
		this("", null);
	}
	
    /**
     * Construtor que cria um botão com o texto especificado, sem ação.
     *
     * @param text Texto a ser exibido no botão.
     */
	public StdButton(String text) {
		super(text, null);
	}
	
    /**
     * Define o botão com borda arredondada.
     *
     * @return O próprio objeto StdButton para implementar fluent interface.
     * @deprecated
     */
	public StdButton setRounded() {
		this.setBorder(new RoundedBorder(10));
		return this;
	}
	
    /**
     * Define a fonte do texto do botão.
     *
     * @param font Fonte a ser aplicada ao texto do botão.
     * @return O próprio objeto StdButton para implementar fluent interface.
     */
	public StdButton setUpFont(Font font) {
		setFont(font);
		return this;
	}
	
    /**
     * Define a cor do texto do botão.
     *
     * @param color Cor do texto a ser aplicada ao botão.
     * @return O próprio objeto StdButton para implementar fluent interface.
     */
	public StdButton setFontColor(Color color) {
		setForeground(color);
		return this;
	}
	
    /**
     * Define a cor de fundo do botão.
     *
     * @param color Cor de fundo a ser aplicada ao botão.
     * @return O próprio objeto StdButton para implementar fluent interface.
     */
	public StdButton setBgColor(Color color) {
		setBackground(color);
		return this;
	}

    /**
     * Define a cor do texto e a cor de fundo do botão.
     *
     * @param font Cor do texto a ser aplicada ao botão.
     * @param background Cor de fundo a ser aplicada ao botão.
     * @return O próprio objeto StdButton para implementar fluent interface.
     */
	public StdButton setColors(Color font, Color background) {
		setForeground(font);
		setBackground(background);
		btnColor = background;
		return this;
	}
	
    /**
     * Define o tamanho preferencial, mínimo e máximo do botão.
     *
     * @param d Dimensão a ser aplicada ao botão.
     * @return O próprio objeto StdButton para implementar fluent interface.
     */
	public StdButton setUpSize(Dimension d) {
		setPreferredSize(d);
		setMinimumSize(d);
		setMaximumSize(d);
		setSize(d);
		return this;
	}
	
    /**
     * Remove a borda do botão.
     *
     * @return O próprio objeto StdButton para implementar fluent interface.
     */
	public StdButton setNoBorder() {
		this.setBorder(null);
		return this;
	}
	
    /**
     * Define a cor da borda do botão.
     *
     * @param color Cor da borda a ser aplicada ao botão.
     * @return O próprio objeto StdButton para implementar fluent interface.
     */
	public StdButton setBorderColor(Color color) {
		this.setBorder(new LineBorder(color));
		return this;
	}
	
    /**
     * Define a cor da borda do botão para a cor de fundo atual do botão.
     * Se a cor de fundo não estiver definida, remove a borda.
     *
     * @return O próprio objeto StdButton para implementar fluent interface.
     */
	public StdButton setBorderColor() {
		if(btnColor != null) {
			this.setBorder(new LineBorder(btnColor));
		} else {
			setNoBorder();
		}
		
		return this;
	}
	
    /**
     * Define a ação a ser executada quando o botão é clicado.
     *
     * @param action Ação a ser executada quando o botão é clicado.
     * @return O próprio objeto StdButton para implementar fluent interface.
     */
	public StdButton setAction(Action action) {
		removeAllActions();
		this.action = action;
		this.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(action != null) {
					action.execute();
				}
			}
		});

		return this;
	}
	
    /**
     * Remove todas as ações associadas ao botão.
     *
     * @return O próprio objeto StdButton para implementar fluent interface.
     */
	public StdButton removeAllActions() {
		ActionListener[] listeners = this.getActionListeners();
		for (ActionListener listener : listeners) {
			this.removeActionListener(listener);
		}
		return this;
	}
	
    /**
     * Obtém a ação associada ao botão.
     *
     * @return A ação associada ao botão.
     */
	public Action getInteactionAction() {
		return action;
	}
	
    /**
     * Configura um botão padrão com texto especificado, fonte negrito de tamanho 12,
     * cor de texto branca e cor de fundo azul padrão da QuestNutri.
     *
     * @param text Texto a ser exibido no botão.
     * @return Um novo objeto StdButton configurado com os padrões especificados.
     */
	public static StdButton stdBtnConfig(String text) {
    	return new StdButton(text).setUpFont(STD_BOLD_FONT.deriveFont(12f))
				   .setColors(STD_WHITE_COLOR, STD_BLUE_COLOR);
	}
	
    /**
     * Configura um botão padrão invertido com texto especificado, fonte negrito de tamanho 12,
     * cor de texto azul e cor de fundo branca.
     *
     * @param text Texto a ser exibido no botão.
     * @return Um novo objeto StdButton configurado com os padrões invertidos especificados.
     */
	public static StdButton stdBtnConfigInvert(String text) {
    	return new StdButton(text).setUpFont(STD_BOLD_FONT.deriveFont(12f))
				   .setColors(STD_BLUE_COLOR, Color.white);
	}
	
	
}
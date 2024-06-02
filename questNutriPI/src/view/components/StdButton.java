package view.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.border.LineBorder;

import view.components.utils.RoundedBorder;
import view.panels.components.GeneralJPanelSettings;

public class StdButton extends JButton {
	private static final long serialVersionUID = 1L;
	
	private Color btnColor;

	public interface Action {
		void execute();
	}	
	
	public StdButton() {
		this("", null);
	}
	
	public StdButton(String text) {
		super(text, null);
	}
	
	public StdButton(String text, Action action) {
		super(text);
		this.setAction(action);
	}
	
	public StdButton setRounded() {
		this.setBorder(new RoundedBorder(10));
		return this;
	}
	
	public StdButton setUpFont(Font font) {
		setFont(font);
		return this;
	}
	
	public StdButton setFontColor(Color color) {
		setForeground(color);
		return this;
	}
	
	public StdButton setBgColor(Color color) {
		setBackground(color);
		return this;
	}
	
	public StdButton setColors(Color font, Color background) {
		setForeground(font);
		setBackground(background);
		btnColor = background;
		return this;
	}
	
	public StdButton setUpSize(Dimension d) {
		setPreferredSize(d);
		setMinimumSize(d);
		setMaximumSize(d);
		setSize(d);
		return this;
	}
	
	public StdButton setNoBorder() {
		this.setBorder(null);
		return this;
	}
	
	public StdButton setBorderColor(Color color) {
		this.setBorder(new LineBorder(color));
		return this;
	}
	
	public StdButton setBorderColor() {
		if(btnColor != null) {
			this.setBorder(new LineBorder(btnColor));
		} else {
			setNoBorder();
		}
		
		return this;
	}
	
	/**
	 * Atribui a ação ao Botão.
	 */
	public StdButton setAction(Action action) {
		if(action != null) {
			this.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					action.execute();
				}
			});
		}
		return this;
	}
	
	public static StdButton stdBtnConfig(String text) {
    	return new StdButton(text).setUpFont(GeneralJPanelSettings.STD_BOLD_FONT.deriveFont(12f))
				   .setColors(Color.white, GeneralJPanelSettings.STD_BLUE_COLOR);
	}
	
	public static StdButton stdBtnConfigInvert(String text) {
    	return new StdButton(text).setUpFont(GeneralJPanelSettings.STD_BOLD_FONT.deriveFont(12f))
				   .setColors(GeneralJPanelSettings.STD_BLUE_COLOR, Color.white);
	}
	
}
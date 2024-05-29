package view.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import view.components.utils.RoundedBorder;

public class StdButton extends JButton {
	private static final long serialVersionUID = 1L;

	public interface Action {
		void execute();
	}
	
	private Action action;
	
	
	public StdButton() {
		this("", null);
	}
	
	public StdButton(String text) {
		super(text, null);
	}
	
	public StdButton(String text, Action action) {
		super(text);
		this.action = action;
		this.setAction();
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
		return this;
	}
	
	/**
	 * Atribui a ação ao Botão.
	 */
	private void setAction() {
		if(this.action != null) {
			this.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					action.execute();
				}
			});
		}
	}	
	
}
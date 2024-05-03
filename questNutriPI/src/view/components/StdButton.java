package view.components;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import view.components.utils.RoundedBorder;
import view.utils.VUtils;

public class StdButton extends JButton {
	private static final long serialVersionUID = 1L;

	public interface Action {
		void execute();
	}
	
	private Action action;
	
	
	public StdButton() {
		super();
		this.setBackground(Color.white);
		this.setBorder(new RoundedBorder(10));
	}
	
	public StdButton(String text) {
		super(text);
		this.setBackground(Color.white);
		this.setBorder(new RoundedBorder(10));
	}
	
	public StdButton(String text, Action action) {
		super(text);
		this.setBorder(new RoundedBorder(10));
		this.action = action;
		this.setAction();
		this.setFont(VUtils.loadFont("Montserrat-Regular").deriveFont(20f));

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
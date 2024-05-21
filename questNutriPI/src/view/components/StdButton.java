package view.components;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import view.components.utils.RoundedBorder;
import view.utils.VUtils;

public class StdButton extends JButton {
	private static final long serialVersionUID = 1L;
	protected Font STD_TEXT_FONT = VUtils.loadFont("Montserrat-Regular");

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
		this.setBorder(new RoundedBorder(10));
		this.action = action;
		this.setAction();
		this.setFont(this.STD_TEXT_FONT.deriveFont(20f));

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
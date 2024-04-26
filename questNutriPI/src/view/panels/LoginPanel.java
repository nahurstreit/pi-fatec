package view.panels;

import javax.swing.*;

import view.QuestNutri;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public LoginPanel() {
		super();
		this.add(new JTextField("Usuário"));
		this.add(new JTextField("Senha"));
		JButton button = new JButton("Logar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				QuestNutri.doLogin();
			}
		});
		this.add(button);
	}

}
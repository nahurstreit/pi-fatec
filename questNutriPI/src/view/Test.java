package view;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class Test extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Create the panel.
	 */
	public Test() {
		
		passwordField = new JPasswordField();
		add(passwordField);
		
		textField = new JTextField();
		add(textField);
		textField.setColumns(10);

	}

}

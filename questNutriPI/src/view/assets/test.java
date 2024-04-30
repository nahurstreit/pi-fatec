package view.assets;

import javax.swing.JPanel;
import javax.swing.JLayeredPane;

public class test extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public test() {
		
		JPanel panel = new JPanel();
		add(panel);
		
		JLayeredPane layeredPane = new JLayeredPane();
		add(layeredPane);

	}

}

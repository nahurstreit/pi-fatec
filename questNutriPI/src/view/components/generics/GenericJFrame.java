package view.components.generics;

import javax.swing.JFrame;

public class GenericJFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	protected String frameName;
	
	@Override
	public String toString() {
		return "Caller frame: "+frameName;
	}
}
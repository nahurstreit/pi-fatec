package view.frames;

import java.awt.Dimension;

import javax.swing.JFrame;

import view.components.generics.GenericJPanel;
import view.components.labels.BreakActionLbl;

public class TestFrame {
	public static void main(String[] args) {
		JFrame test = new JFrame();
		test.setSize(new Dimension(500, 500));
		
		GenericJPanel testPanel = new GenericJPanel();
		
		BreakActionLbl lbl = new BreakActionLbl();
		
		testPanel.add(lbl);
		
		test.setContentPane(testPanel);
		test.setVisible(true);
	}
}
package view.panels;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import view.QuestNutri;

public class LoginComponent extends JPanel {
	public LoginComponent() {
		this.setMinimumSize(new Dimension(500, 500));
		JPanel left = new JPanel();
		left.setBackground(Color.white);
		left.setOpaque(true);
		left.add(QuestNutri.questNutriSVG, JPanel.CENTER_ALIGNMENT);
		
		this.add(left);
		
		JPanel right = new JPanel();
		right.setBackground(Color.black);

		this.add(right);
	}
}
package view.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import view.QuestNutri;
import view.components.HintInputField;
import view.components.HintPasswordInputField;
import view.components.Make;
import view.components.StdButton;
import view.utils.VUtils;

public class LoginPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public LoginPanel() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
        JPanel inputLoginPanel = new JPanel();
        inputLoginPanel.setBackground(new Color(85, 183, 254));
        inputLoginPanel.setPreferredSize(new Dimension(500, 500));
        inputLoginPanel.setMinimumSize(new Dimension(500, 500));

        placeInputs(inputLoginPanel);
        placeBtn(inputLoginPanel);
        
        this.add(inputLoginPanel);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Image bgImg = Make.sizedImg("LoginPageBG", QuestNutri.app.getWidth(), QuestNutri.app.getHeight()).getImage();
		g.drawImage(bgImg, 0, 0, null);
	}
	
	private void placeInputs(JPanel panel) {
		
		HintInputField tfUser = new HintInputField(
					"Digite aqui...",
					new Dimension(100, 20),
					12f
				);
		
		panel.add(tfUser);
		
		HintPasswordInputField password = new HintPasswordInputField(
				"Digite aqui...",
				new Dimension(100, 20),
				12f);
		
		panel.add(password);
	}
	
	private void placeBtn(JPanel panel) {
        StdButton button = new StdButton("Entrar", () -> QuestNutri.doLogin());
        button.setPreferredSize(new Dimension(150, 25));
        button.setBackground(Color.white);
        button.setForeground(new Color(85, 183, 254));
        button.setFont(VUtils.loadFont("Montserrat-Bold").deriveFont(12f));

        
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ;
            }
        });
        
        panel.add(button);
	}
}
package view.panels;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JPanel;

import view.QuestNutri;
import view.components.HintInputField;
import view.components.HintPasswordInputField;
import view.components.StdButton;
import view.panels.components.GeneralJPanel;
import view.utils.VMakePicture;
import view.utils.VUtils;

/**
 * Classe que define o painel de Login do sistema.
 */
public class LoginPanel extends GeneralJPanel {
	private static final long serialVersionUID = 1L;
	
	public LoginPanel() {
		this.ltGridBag();
		gbc.fill = GridBagConstraints.BOTH;
		
		int prefSize = 400; //Variável que controla
		Dimension dSize = new Dimension(prefSize, prefSize);
		
		JPanel loginPanelHolder = new JPanel();
		loginPanelHolder.setLayout(new GridBagLayout());
		loginPanelHolder.setBackground(this.STD_NULL_COLOR);
		this.gbcInsets();
		
		JPanel logoLoginPanel = new JPanel();
		logoLoginPanel.setLayout(new GridBagLayout());
		logoLoginPanel.setBackground(Color.white);
		logoLoginPanel.setPreferredSize(dSize);
		logoLoginPanel.setMinimumSize(dSize);
		logoLoginPanel.add(QuestNutri.loadSVG());
		
        JPanel inputLoginPanel = new JPanel();
        inputLoginPanel.setLayout(new GridBagLayout());
        inputLoginPanel.setBackground(this.STD_BLUE_COLOR);
        inputLoginPanel.setPreferredSize(dSize);
        inputLoginPanel.setMinimumSize(dSize);
        
        placeInputs(inputLoginPanel);
        placeBtn(inputLoginPanel);
        
        gbc.gridx = 0;
        loginPanelHolder.add(logoLoginPanel, gbc);
        loginPanelHolder.add(inputLoginPanel, gbcXp());
        
        loginPanelHolder.revalidate();
        loginPanelHolder.repaint();
        this.add(loginPanelHolder);
	}
	
	/**
	 * Override do método de pintura do JPanel para colocar uma imagem no lugar de uma cor sólida.
	 * 	@Override
	 */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Image bgImg = VMakePicture.sizedImg("LoginPageBG", QuestNutri.app.getWidth(), QuestNutri.app.getHeight()).getImage();
		g.drawImage(bgImg, 0, 0, null);
	}
	
	/**
	 * Adiciona os componentes de Input de login ao painel indicado.
	 * @param panel -> JPanel que receberá os componentes de Login. Serão adicionados:
	 * <li><b>HintInputField</b> - Caixa de Texto para username</li> 
	 * <li><b>HintPasswordInputField</b> - Caixa de Texto com dica de preenchimento de senha</li>
	 * 
	 * @see view.components.HintInputField
	 */
	private void placeInputs(JPanel panel) {
		//Label de Página de Administrador
		gbcInsets(0,0,20,0);
		gbc.gridx = 0;
		gbc.gridy = 0;
		JLabel lblAdminPage = new JLabel("Página de Administrador", JLabel.CENTER);
		lblAdminPage.setFont(this.STD_BOLD_FONT.deriveFont(18f));
		lblAdminPage.setForeground(Color.white);
		panel.add(lblAdminPage, gbc);
		
		//Label de Username
		gbcInsets(0,0,2,0);
		JLabel lblUsername = new JLabel("Username:", JLabel.CENTER);
		lblUsername.setFont(this.STD_MEDIUM_FONT.deriveFont(15f));
		lblUsername.setForeground(Color.white);
		panel.add(lblUsername, gbcYp());
		

		//TextField de Username
		HintInputField tfUser = new HintInputField(
					"Digite aqui...",
					new Dimension(100, 20),
					12f
				);
		panel.add(tfUser, gbcYp());
		
		
		//Label de Senha
		gbcInsets(5,0,2,0);
		JLabel lblPassword = new JLabel("Senha:", JLabel.CENTER);
		lblPassword.setFont(this.STD_MEDIUM_FONT.deriveFont(15f));
		lblPassword.setForeground(Color.white);
		panel.add(lblPassword, gbcYp());
		
		
		//Input de senha
		gbcInsets(0,0,30,0);
		HintPasswordInputField password = new HintPasswordInputField(
				"Digite aqui...",
				new Dimension(100, 20),
				12f);
		
		panel.add(password, gbcYp());
	}
	
	/**
	 * Adiciona o componente de Botão de login ao painel indicado.
	 * @param panel -> JPanel que receberá o botão de Login. Será adicionado:
	 * <li><b>StdButton</b> - Botão para executar a função QuestNutri.doLogin()</li>
	 * @see QuestNutri#doLogin()
	 */
	private void placeBtn(JPanel panel) {
        StdButton button = new StdButton("Entrar", () -> QuestNutri.doLogin());
        button.setPreferredSize(new Dimension(75, 25));
        button.setBackground(Color.white);
        button.setForeground(this.STD_BLUE_COLOR);
        button.setFont(this.STD_BOLD_FONT.deriveFont(12f));
        
        gbcInsets(0,0,20,0);
        panel.add(button, gbcYp());
	}
}
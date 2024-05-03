package view.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.apache.batik.swing.JSVGCanvas;

import view.QuestNutri;
import view.components.HintInputField;
import view.components.HintPasswordInputField;
import view.components.StdButton;
import view.utils.VMakePicture;
import view.utils.VUtils;

/**
 * Classe que define o painel de Login do sistema.
 */
public class LoginPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private GridBagConstraints gbc = new GridBagConstraints();
	
	
	public LoginPanel() {
		this.setLayout(new GridBagLayout());
		gbc.fill = GridBagConstraints.BOTH;
		
		int prefSize = 400; //Variável que controla
		Dimension dSize = new Dimension(prefSize, prefSize);
		
		JPanel loginPanelHolder = new JPanel();
		loginPanelHolder.setLayout(new GridBagLayout());
		loginPanelHolder.setBackground(new Color(0, 0, 0, 0));
		gbc.insets = new Insets(0, 0, 0, 0);
		
		
		JPanel logoLoginPanel = new JPanel();
		logoLoginPanel.setLayout(new GridBagLayout());
		logoLoginPanel.setBackground(Color.white);
		logoLoginPanel.setPreferredSize(dSize);
		logoLoginPanel.setMinimumSize(dSize);
		logoLoginPanel.add(VMakePicture.svgIcon("QuestNutri"), gbc);
		
        JPanel inputLoginPanel = new JPanel();
        inputLoginPanel.setLayout(new GridBagLayout());
        inputLoginPanel.setBackground(new Color(85, 183, 254));
        inputLoginPanel.setPreferredSize(dSize);
        inputLoginPanel.setMinimumSize(dSize);
        
        placeInputs(inputLoginPanel);
        placeBtn(inputLoginPanel);
        
        gbc.gridx = 0;
        loginPanelHolder.add(logoLoginPanel, gbc);
        gbc.gridx++;
        loginPanelHolder.add(inputLoginPanel, gbc);
        
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
		gbc.insets = new Insets(0, 0, 20, 0);
		gbc.gridx = 0;
		gbc.gridy = 0;
		JLabel lblAdminPage = new JLabel("Página de Administrador", JLabel.CENTER);
		lblAdminPage.setFont(VUtils.loadFont("Montserrat-Bold", 18f));
		lblAdminPage.setForeground(Color.white);
		panel.add(lblAdminPage, gbc);
		
		
		//Label de Username
		gbc.insets = new Insets(0, 0, 2, 0);
		gbc.gridy++;
		JLabel lblUsername = new JLabel("Username:", JLabel.CENTER);
		lblUsername.setFont(VUtils.loadFont("Montserrat-Medium", 15f));
		lblUsername.setForeground(Color.white);
		panel.add(lblUsername, gbc);
		

		//TextField de Username
		gbc.gridy++;
		HintInputField tfUser = new HintInputField(
					"Digite aqui...",
					new Dimension(100, 20),
					12f
				);
		panel.add(tfUser, gbc);
		
		
		//Label de Senha
		gbc.insets = new Insets(5, 0, 2, 0);
		gbc.gridy++;
		JLabel lblPassword = new JLabel("Senha:", JLabel.CENTER);
		lblPassword.setFont(VUtils.loadFont("Montserrat-Medium", 15f));
		lblPassword.setForeground(Color.white);
		panel.add(lblPassword, gbc);
		
		
		//Input de senha
		gbc.insets = new Insets(0, 0, 30, 0);
		gbc.gridy++;
		HintPasswordInputField password = new HintPasswordInputField(
				"Digite aqui...",
				new Dimension(100, 20),
				12f);
		
		panel.add(password, gbc);
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
        button.setForeground(new Color(85, 183, 254));
        button.setFont(VUtils.loadFont("Montserrat-Bold").deriveFont(12f));
        
        gbc.insets = new Insets(0, 0, 20, 0);
        gbc.gridy++;
        panel.add(button, gbc);
	}
}
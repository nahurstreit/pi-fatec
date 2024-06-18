package view.states;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JPanel;

import utils.view.ImagesUtil;
import utils.view.LanguageUtil;
import view.QuestNutri;
import view.components.buttons.StdButton;
import view.components.generics.GenericJPanel;
import view.components.inputs.HintInputField;
import view.components.inputs.HintPasswordInputField;

/**
 * Classe que define o painel de Login do sistema.
 * Este painel contém campos para inserção de usuário e senha, além de um botão para realizar o login.
 */
public class LoginPanel extends GenericJPanel {
	private static final long serialVersionUID = 1L;
	
	private JPanel logoPanel = new JPanel();
	
	private HintInputField tfUser;
	private HintPasswordInputField password;
	private StdButton button;
	
	/**
	 * Construtor da classe LoginPanel.
	 * Configura o layout GridBagLayout para o painel principal e adiciona os componentes de login.
	 */
	public LoginPanel() {
		this.ltGridBag();
		gbc.fill = GridBagConstraints.BOTH;
		
		int prefSize = 400; //Variável que controla
		Dimension dSize = new Dimension(prefSize, prefSize);
		
		JPanel loginPanelHolder = new JPanel();
		loginPanelHolder.setLayout(new GridBagLayout());
		loginPanelHolder.setBackground(STD_NULL_COLOR);
		gbc.insets();
		
        JPanel inputLoginPanel = new JPanel();
        inputLoginPanel.setLayout(new GridBagLayout());
        inputLoginPanel.setBackground(STD_BLUE_COLOR);
        inputLoginPanel.setPreferredSize(dSize);
        inputLoginPanel.setMinimumSize(dSize);
        
        logoPanel.setLayout(new GridBagLayout());
        logoPanel.setBackground(Color.white);
        logoPanel.setPreferredSize(dSize);
        logoPanel.setMinimumSize(dSize);
        
        placeInputs(inputLoginPanel);
        placeBtn(inputLoginPanel);
        
        loginPanelHolder.add(logoPanel, gbc.gridX(0));
        loginPanelHolder.add(inputLoginPanel, gbc.gridX(1));
        
        this.add(loginPanelHolder);
	}
	
	/**
	 * Override do método paintComponent para desenhar uma imagem de fundo no painel de login.
	 * @param g Objeto Graphics usado para desenhar componentes no painel.
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Image bgImg = ImagesUtil.sizedImg("LoginPageBG", QuestNutri.app.getWidth(), QuestNutri.app.getHeight()).getImage();
		g.drawImage(bgImg, 0, 0, null);
	}
	
	/**
	 * Adiciona os componentes de Input de login ao painel indicado.
	 * @param panel - JPanel que receberá os componentes de Login. Serão adicionados:
	 * <ul>
	 * <li><b>HintInputField</b> - Caixa de Texto para username</li> 
	 * <li><b>HintPasswordInputField</b> - Caixa de Texto com dica de preenchimento de senha</li>
	 * </ul>
	 * 
	 * @see view.components.inputs.HintInputField
	 */
	private void placeInputs(JPanel panel) {
		//Label de Página de Administrador
		gbc.insets(0,0,20,0)
			.grid(0);
		JLabel lblAdminPage = new JLabel(new LanguageUtil("Página de Administrador", "Admin's Page").get(), JLabel.CENTER);
		lblAdminPage.setFont(STD_BOLD_FONT.deriveFont(18f));
		lblAdminPage.setForeground(Color.white);
		panel.add(lblAdminPage, gbc);
		
		//Label de Username
		JLabel lblUsername = new JLabel("Username:", JLabel.CENTER);
		lblUsername.setFont(STD_MEDIUM_FONT.deriveFont(15f));
		lblUsername.setForeground(Color.white);
		panel.add(lblUsername, gbc.insets(0,0,2,0).yP());
		

		//TextField de Username
		tfUser = new HintInputField(
					new LanguageUtil("Digite aqui...", "Type here...").get(),
					new Dimension(100, 20),
					12f
				);
		panel.add(tfUser, gbc.yP());
		
		
		//Label de Senha
		JLabel lblPassword = new JLabel(new LanguageUtil("Senha:", "Password:").get(), JLabel.CENTER);
		lblPassword.setFont(STD_MEDIUM_FONT.deriveFont(15f));
		lblPassword.setForeground(Color.white);
		panel.add(lblPassword, gbc.insets(5,0,2,0).yP());
		
		
		//Input de senha
		password = new HintPasswordInputField(
				new LanguageUtil("Digite aqui...", "Type here...").get(),
				new Dimension(100, 20),
				12f).setEnterAction(
						() -> {
							button.getInteactionAction().execute();
						}
				).init();
		
		panel.add(password, gbc.insets(0,0,30,0).yP());
	}
	
	/**
	 * Método privado para adicionar o botão de login ao painel indicado.
	 * @param panel JPanel que receberá o botão de login.
	 */
	private void placeBtn(JPanel panel) {
		button = new StdButton(new LanguageUtil("Entrar", "Login").get(), () -> QuestNutri.doLogin(tfUser.getText(), password.getRealText()));
        button.setPreferredSize(new Dimension(75, 25));
        button.setBackground(Color.white);
        button.setForeground(STD_BLUE_COLOR);
        button.setFont(STD_BOLD_FONT.deriveFont(12f));

        panel.add(button, gbc.insets(0,0,20,0).yP());
	}
	
	/**
	 * Método para adicionar o logotipo da aplicação ao painel de logotipo.
	 */
	public void placeLogo() {
		logoPanel.add(QuestNutri.loadSVG());
		this.revalidate();
		this.repaint();
	}
}
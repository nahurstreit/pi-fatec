package view.panels;

import java.awt.*;

import javax.swing.*;

import view.panels.pages.CustomersPage;
import view.panels.pages.DietPage;
import view.panels.pages.HomePage;

/**
 * Classe que define o painel logado do usuário.
 */
public class LoggedPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private String nutriName;
	
	/**
	 * Tela principal localizada à direita da aplicação e que será mudada conforme a página selecionada na barra de menu lateral.
	 */
	private JPanel mainPanel = new HomePage();
	
	/**
	 * Variável geral de controle do design dos GridBagLayout
	 */
	private GridBagConstraints gbcPanel;

	/**
	 * Método construtor da instância de painel logado.
	 */
	public LoggedPanel(String nutriName) {
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints gbcSideBar = new GridBagConstraints();
		gbcSideBar.gridx = 0; //número horizontal da célula no grid
		gbcSideBar.gridy = 1; //número vertical da célula no grid
		gbcSideBar.gridwidth = 1; //tamanho máximo de ocupação da célula nos grids
		gbcSideBar.weightx = 0.0; //redimensionamento horizontal
		gbcSideBar.weighty = 1.0; //redimensionamento vertical
		gbcSideBar.ipadx = 10; //distância horizontal conteúdo até as bordas do grid
		gbcSideBar.ipady = 10; //distância vertical conteúdo até as bordas do grid
		gbcSideBar.fill = GridBagConstraints.BOTH; //modo de preenchimento do grid
		
		//Cria as possíveis páginas do sistema e as coloca no menu lateral.
		//Ao clicar em um desses itens, o mesmo executará o método de troca da tela principal do painel logado.
		SideBarItem homePage = new SideBarItem("HOME", () -> swapLoggedMainPanel(new HomePage()), true);
		SideBarItem customersPage = new SideBarItem("CLIENTES", () -> swapLoggedMainPanel(new CustomersPage(this)));
		SideBarItem dietPage = new SideBarItem("DIETA TESTE", () -> swapLoggedMainPanel(new DietPage()));
		
		SideBar fullSideBar = new SideBar(nutriName, homePage, customersPage, dietPage);
		fullSideBar.setMinimumSize(new Dimension(250, this.getHeight()));
		fullSideBar.setPreferredSize(new Dimension(250, this.getHeight()));
		
		this.add(fullSideBar, gbcSideBar);
		
		gbcPanel = new GridBagConstraints();
		gbcPanel.gridx = 1;
		gbcPanel.gridy = 1;
		gbcPanel.gridwidth = GridBagConstraints.REMAINDER; //preenche todo o resto disponível
		gbcPanel.weightx = 1.0;
		gbcPanel.weighty = 1.0;
		gbcPanel.ipadx = 10;
		gbcPanel.ipady = 10;
		gbcPanel.fill = GridBagConstraints.BOTH;
		this.add(mainPanel, gbcPanel);
	}
	
	/**
	 * Método genérico de criação do painel logado.
	 */
	public LoggedPanel() {
		this("{nutri}");
	}
	
	/**
	 * Método que troca a tela principal de exibição de um painel Logado.
	 * @param panel
	 */
	public void swapLoggedMainPanel(JPanel panel) {
		this.remove(mainPanel);
		mainPanel = panel;
		this.add(mainPanel, gbcPanel);
		this.revalidate();
		this.repaint();
	}

}
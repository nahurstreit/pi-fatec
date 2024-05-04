package view.panels;

import java.awt.*;

import javax.swing.*;

import view.panels.components.GeneralJPanel;
import view.panels.components.SideBar;
import view.panels.components.SideBarItem;
import view.panels.pages.CustomersPage;
import view.panels.pages.DietPage;
import view.panels.pages.HomePage;

/**
 * Classe que define o painel logado do usuário.
 */
public class LoggedPanel extends GeneralJPanel {
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unused")
	private String nutriName;
	
	/**
	 * Tela principal localizada à direita da aplicação e que será mudada conforme a página selecionada na barra de menu lateral.
	 */
	private JPanel mainPanel = new HomePage();

	/**
	 * Método construtor da instância de painel logado.
	 */
	public LoggedPanel(String nutriName) {
		this.ltGridBag();
		
		gbcYp();
		gbcGridY(1);
		gbcWgXY(0, 1.0);
		gbcApple(10);
		gbc.gridwidth = 1; //tamanho máximo de ocupação da célula nos grids
		gbcFill("BOTH");
		
		//Cria as possíveis páginas do sistema e as coloca no menu lateral.
		//Ao clicar em um desses itens, o mesmo executará o método de troca da tela principal do painel logado.
		SideBarItem homePage = new SideBarItem("HOME", () -> swapLoggedMainPanel(new HomePage()), true);
		SideBarItem customersPage = new SideBarItem("CLIENTES", () -> swapLoggedMainPanel(new CustomersPage(this)));
		SideBarItem dietPage = new SideBarItem("DIETA TESTE", () -> swapLoggedMainPanel(new DietPage()));
		
		SideBar fullSideBar = new SideBar(nutriName, homePage, customersPage, dietPage);
		fullSideBar.setMinimumSize(new Dimension(250, this.getHeight()));
		fullSideBar.setPreferredSize(new Dimension(250, this.getHeight()));
		this.add(fullSideBar, gbc);
		
		resetGbc();
		gbcGridXY(1);
		gbcWgXY(1.0);
		gbcApple(10);
		gbc.gridwidth = GridBagConstraints.REMAINDER; //preenche todo o resto disponível
		gbcFill("BOTH");
		this.add(mainPanel, gbc);
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
		this.add(mainPanel, gbc);
		this.revalidate();
		this.repaint();
	}

}
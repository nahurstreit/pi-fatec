package view.panels;

import java.awt.*;

import javax.swing.*;

import view.panels.pages.CustomersPage;
import view.panels.pages.HomePage;

public class LoggedPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private String nutriName;
	private JPanel mainPanel = new HomePage();
	private GridBagConstraints gbcPanel;

	/**
	 * Create the panel.
	 */
	public LoggedPanel(String nutriName) {
		super();
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints gbcSideBar = new GridBagConstraints();
		gbcSideBar.gridx = 0; //número horizontal da célula no grid
		gbcSideBar.gridy = 1; //número vertical da célula no grid
		gbcSideBar.gridwidth = 1; //tamanho máximo de ocupação da célula nos grids
		gbcSideBar.weightx = 0.0; //redimensionamento horizontal
		gbcSideBar.weighty = 1.0; //redimensionamento vertical
		gbcSideBar.ipadx = 10; //distância horizontal conteúdo até as bordas do grid
		gbcSideBar.ipady = 10; //distância vertical conteúdo até as bordas do grid
		gbcSideBar.fill = GridBagConstraints.VERTICAL; //modo de preenchimento do grid
		
		SideBarItem homePage = new SideBarItem("HOME", () -> swapLoggedMainPanel(new HomePage()), true);
		SideBarItem customersPage = new SideBarItem("CLIENTES", () -> swapLoggedMainPanel(new CustomersPage()));
		
		SideBar fullSideBar = new SideBar(nutriName, homePage, customersPage);
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
	
	public void swapLoggedMainPanel(JPanel panel) {
		this.remove(mainPanel);
		mainPanel = panel;
		this.add(mainPanel, gbcPanel);
		this.revalidate();
		this.repaint();
	}

}
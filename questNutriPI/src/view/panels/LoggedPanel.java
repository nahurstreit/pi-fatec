package view.panels;

import java.awt.Dimension;

import javax.swing.JLabel;

import org.apache.batik.swing.JSVGCanvas;

import view.QuestNutri;
import view.panels.components.GenericJPanel;
import view.panels.components.SideBar;
import view.panels.components.SideBarComponent;
import view.panels.components.SideBarItem;
import view.panels.components.SideBarMenu;
import view.panels.pages.AlimentsPage;
import view.panels.pages.CustomersPage;

/**
 * Classe que define o painel logado do usuário.
 */
public class LoggedPanel extends GenericJPanel {
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unused")
	private String nutriName;
	
	/**
	 * Tela principal localizada à direita da aplicação e que será mudada conforme a página selecionada na barra de menu lateral.
	 */
	private GenericJPanel mainPanel;

	/**
	 * Método construtor da instância de painel logado.
	 */
	public LoggedPanel(String nutriName) {
		this.ltGridBag();
		
		JLabel greetings = new JLabel("Olá, " + nutriName + "!", JLabel.CENTER);
		greetings.setFont(STD_BOLD_FONT.deriveFont(25f));
		
		SideBarComponent<JLabel> headComponent = new SideBarComponent<JLabel>(greetings);
		headComponent.gbc
			.grid(0,0)
			.fill("BOTH")
			.insets(20, 40)
			.wgt(1.0, 0);
		
		//Cria as possíveis páginas do sistema e as coloca no menu lateral.
		//Ao clicar em um desses itens, o mesmo executará o método de troca da tela principal do painel logado.
		SideBarItem customersPage = new SideBarItem("CLIENTES", () -> swapLoggedMainPanel(new CustomersPage(this)), true);
		SideBarItem dietPage = new SideBarItem("ALIMENTOS", () -> swapLoggedMainPanel(new AlimentsPage(this)));
		
		SideBarMenu menu = new SideBarMenu(customersPage, dietPage);
		menu.gbc
			.wgt(0, 1.0)
			.fill("NONE")
			.insets(15, 40)
			.anchor("NORTHWEST") //Sobe os itens do menu
			.grid(0,1);
		menu.setFirstPanel();
		
		
		SideBarComponent<JSVGCanvas> bottonComponent = new SideBarComponent<JSVGCanvas>(QuestNutri.loadSVG());
		bottonComponent.gbc
			.grid(0, 3)
			.wgt(0.0)
			.insets(20, 40)
			.fill("NONE");
		
		SideBar fullSideBar = new SideBar(headComponent, menu, bottonComponent);
		fullSideBar.setMinimumSize(new Dimension(250, this.getHeight()));
		fullSideBar.setPreferredSize(new Dimension(250, this.getHeight()));
		
		this.add(fullSideBar, gbc.wgt(0, 1.0).apple(10).width(1).fill("BOTH"));

		this.add(mainPanel, 
				gbc.grid(1, 0).wgt(1.0).apple(10).width("REMAINDER").fill("BOTH"));
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
	public void swapLoggedMainPanel(GenericJPanel panel) {
		if(mainPanel != null) this.remove(mainPanel);
		mainPanel = panel;
		this.add(mainPanel, gbc);
		this.revalidate();
		this.repaint();
	}

}
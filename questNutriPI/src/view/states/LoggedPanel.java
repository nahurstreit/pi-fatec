/**
 * Package que contém as classes que controlam a visualização dos estados da aplicação.
 */
package view.states;

import java.awt.Dimension;

import javax.swing.JLabel;

import org.apache.batik.swing.JSVGCanvas;

import utils.view.LanguageUtil;
import view.QuestNutri;
import view.components.generics.GenericJPanel;
import view.components.sidebar.SideBar;
import view.components.sidebar.SideBarComponent;
import view.components.sidebar.SideBarItem;
import view.components.sidebar.SideBarMenu;
import view.pages.aliment.AlimentsPage;
import view.pages.customer.CustomersPage;
import view.pages.settings.SettingsPage;

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
	
	public SideBarMenu menu;
	public SideBarItem customersPage;
	public SideBarItem alimentsPage;
	public SideBarItem settingsPage;

    /**
     * Construtor da instância de painel logado.
     *
     * @param nutriName Nome do nutricionista logado para exibir na saudação.
     */
	public LoggedPanel(String nutriName) {
		this.ltGridBag();
		
		JLabel greetings = new JLabel(new LanguageUtil("Olá, ", "Hello, ").get() + nutriName + "!", JLabel.CENTER);
		greetings.setFont(STD_BOLD_FONT.deriveFont(25f));
		
		SideBarComponent<JLabel> headComponent = new SideBarComponent<JLabel>(greetings);
		headComponent.gbc
			.grid(0,0)
			.fill("BOTH")
			.insets(20, 40)
			.wgt(1.0, 0);
		
		//Cria as possíveis páginas do sistema e as coloca no menu lateral.
		//Ao clicar em um desses itens, o mesmo executará o método de troca da tela principal do painel logado.
		customersPage = new SideBarItem(new LanguageUtil("CLIENTES", "CUSTOMERS").get(), () -> swapLoggedMainPanel(new CustomersPage(this)), true);
		alimentsPage = new SideBarItem(new LanguageUtil("ALIMENTOS", "ALIMENTS").get(), () -> swapLoggedMainPanel(new AlimentsPage(this)));
		settingsPage = new SideBarItem(new LanguageUtil("CONFIGURAÇÕES", "SETTINGS").get(), () -> swapLoggedMainPanel(new SettingsPage(this)));

		if(QuestNutri.isEditAuth()) {
			menu = new SideBarMenu(customersPage, alimentsPage, settingsPage);
		} else {
			menu = new SideBarMenu(customersPage, settingsPage);
		}
		
		menu.gbc
			.wgt(0, 1.0)
			.fill("NONE")
			.insets(15, 35)
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
     * @param panel Novo painel a ser exibido no painel logado.
	 */
	public void swapLoggedMainPanel(GenericJPanel panel) {
		if(mainPanel != null) this.remove(mainPanel);
		mainPanel = panel;
		this.add(mainPanel, gbc);
		this.revalidate();
		this.repaint();
	}

}
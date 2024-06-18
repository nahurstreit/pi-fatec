package view.frames;

import java.awt.Dimension;

import controller.app.PdfGeneratorController;
import model.entities.Customer;
import utils.view.LanguageUtil;
import view.QuestNutri;
import view.components.buttons.StdButton;
import view.components.generics.GenericJPanel;
import view.components.sidebar.SideBar;
import view.components.sidebar.SideBarComponent;
import view.components.sidebar.SideBarItem;
import view.components.sidebar.SideBarMenu;
import view.components.utils.StdGBC;
import view.pages.customer.diet.DietPage;
import view.pages.customer.profile.CustomerFormPage;

/**
 * Frame para exibição e interação com informações de um cliente.
 * Extende SubFrameFromMain para manter a consistência visual e funcional do aplicativo.
 */
public class CustomerFrame extends SubFrameFromMain {
	private static final long serialVersionUID = 1L;
	
	private Customer customer;
	public GenericJPanel framePanel;
	private GenericJPanel mainPanel;

	private StdGBC gbc = new StdGBC();
	
	private SideBar sideBar;
	private SideBarMenu menu;
	
	private String stdFrameName = new LanguageUtil("Janela de Cliente", "Customer's Window").get();
	
	/**
	 * Páginas padrão do frame.
	 */
	private CustomerFormPage profilePage;
	private DietPage dietPage;
	
	
    /**
     * Construtor para CustomerFrame.
     * 
     * @param customer objeto Customer representando o cliente a ser exibido no frame
     */
	public CustomerFrame(Customer customer) {
		super();
		this.frameName = "Customer Frame";
		this.customer = customer;
		
		framePanel = new GenericJPanel().ltGridBag().setCallerFrame(this);
		this.add(framePanel);
		mainPanel = new GenericJPanel(framePanel);
		
		int x = QuestNutri.app.getX() + QuestNutri.app.getWidth()/10;
		int y = QuestNutri.app.getY() + QuestNutri.app.getHeight()/10;
		
		int w = QuestNutri.app.getWidth() - (2*x);
		int h = QuestNutri.app.getHeight() - (2*y);
		
		this.setBounds(x, y, w, h);

		setFrameTitle(customer.name != null? new LanguageUtil("Cliente", "Customer").get() + " - " + customer.name: stdFrameName);
	}
	
    /**
     * Método para trocar o painel principal exibido no frame.
     * 
     * @param panel novo painel a ser exibido como painel principal
     */
	public void swapMainPanel(GenericJPanel panel) {
		try {
			framePanel.remove(mainPanel);
			mainPanel = panel.setOwner(framePanel);
			framePanel.add(panel, gbc.wgt(1.0).fill("BOTH"));
			framePanel.revalidate();
			framePanel.repaint();
		} catch (Exception e) {

		}
	}
	
    /**
     * Obtém o objeto Customer associado a este frame.
     * 
     * @return objeto Customer associado a este frame
     */
	public Customer getCustomer() {
		return customer;
	}
	
    /**
     * Obtém o painel principal do frame.
     * 
     * @return painel principal do frame
     */
	public GenericJPanel getMainPanel() {
		return mainPanel;
	}
	
	/**
	 * Método para definir a sideBar do frame.
	 * @param items - SideBarItem que representam os itens do menu
	 * @return -> Retorna o próprio CustomerFrame para implementar Fluent Interface
	 */
	public CustomerFrame setSideBarMenu(SideBarItem ...items) {
		menu = new SideBarMenu(items);
		sideBar = new SideBar(menu, sbPrintDiet());
		
		sideBar.setMinimumSize(new Dimension(250, this.getHeight()));
		sideBar.setPreferredSize(new Dimension(250, this.getHeight()));
		return this;
	}
	
	
	/**
	 * Método para definir a primeira página do frame, quando o frame não tiver uma sideBar.
	 * @param page - GenericJPanel página a ser exibida.
	 * @return -> Retorna o próprio CustomerFrame para implementar Fluent Interface
	 */
	public CustomerFrame setFirstPage(GenericJPanel page) {
		if(!(sideBar != null)) {
			mainPanel = page;
		}
		return this;
	}
	
	/**
	 * Método para definir o título do frame.
	 * @param text - Texto do título.
	 * @return -> Retorna o próprio CustomerFrame para implementar Fluent Interface
	 */
	public CustomerFrame setFrameTitle(String text) {
		this.setTitle(text);
		return this;
	}
	
	/**
	 * Método para inicializar a view do frame, adicionando o framePanel e a SideBar se for definida.
	 * @return -> Retorna o próprio CustomerFrame para implementar Fluent Interface
	 */
	public CustomerFrame init() {
		if(sideBar != null) {
			framePanel.add(sideBar, framePanel.gbc.fill("BOTH").wgt(0, 1.0));
			menu.setFirstPanel();
		}
		framePanel.add(mainPanel, framePanel.gbc.wgt(1.0));
		try {
			QuestNutri.heraldOfDarkness();
			QuestNutri.followYouIntoTheDark();
		} catch (Exception e) {
		}

		this.setVisible(true);
		return this;
	}
	
	/**
     * Cria um SideBarItem para a página de perfil do cliente.
     * 
     * @return SideBarItem para a página de perfil do cliente
     */
	public SideBarItem sbProfilePage() {
		profilePage = new CustomerFormPage(this.getMainPanel(), customer);
		return new SideBarItem(new LanguageUtil("PERFIL", "PROFILE").get(), () -> this.swapMainPanel(profilePage), true);
	}
	
	/**
     * Cria um SideBarItem para a página de dieta do cliente.
     * 
     * @return SideBarItem para a página de dieta do cliente
     */
	public SideBarItem sbDietPage() {
		dietPage = new DietPage(this.getMainPanel(), customer);
		return new SideBarItem(new LanguageUtil("DIETA", "DIET").get(), () -> this.swapMainPanel(dietPage));
	}
	
    /**
     * Cria um componente de barra lateral para o botão de exportar dieta.
     * 
     * @return componente de barra lateral para o botão de exportar dieta
     */
	public SideBarComponent<StdButton> sbPrintDiet() {
		StdButton printDietBtn = StdButton.stdBtnConfig(new LanguageUtil("Exportar Dieta", "Export Diet").get());
		printDietBtn.setAction(() -> {
			PdfGeneratorController.generate(customer);
		});
		SideBarComponent<StdButton> sideBarButton = new SideBarComponent<StdButton>(printDietBtn);
		sideBarButton.gbc.grid(0, 1).insets(300, 0, 0, 0);
	    return sideBarButton;
	}

}
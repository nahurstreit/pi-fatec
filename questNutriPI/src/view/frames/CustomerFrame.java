package view.frames;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import model.entities.Customer;
import view.QuestNutri;
import view.components.utils.StdGBC;
import view.panels.components.GenericJPanel;
import view.panels.components.SideBar;
import view.panels.components.SideBarItem;
import view.panels.components.SideBarMenu;
import view.panels.pages.DietPage;
import view.panels.pages.subpages.CustomerFormPage;

public class CustomerFrame extends SubFrame {
	private static final long serialVersionUID = 1L;
	
	private Customer customer;
	public GenericJPanel framePanel;
	private GenericJPanel mainPanel = new GenericJPanel();

	private StdGBC gbc = new StdGBC();
	
	private SideBar sideBar;
	private SideBarMenu menu;
	
	private String stdFrameName = "Janela de Cliente";
	
	/**
	 * Páginas padrão do frame.
	 */
	private CustomerFormPage profilePage;
	private DietPage dietPage;
	
	
	public CustomerFrame(Customer customer) {
		super();
		this.customer = customer;
		
		framePanel = new GenericJPanel().ltGridBag();
		this.add(framePanel);
		
		int x = QuestNutri.app.getX() + QuestNutri.app.getWidth()/10;
		int y = QuestNutri.app.getY() + QuestNutri.app.getHeight()/10;
		
		int w = QuestNutri.app.getWidth() - (2*x);
		int h = QuestNutri.app.getHeight() - (2*y);
		
		this.setBounds(x, y, w, h);

		setFrameTitle(customer.name != null? "Cliente - " + customer.name: stdFrameName);
	}
	
	public void swapMainPanel(GenericJPanel panel) {
		try {
			framePanel.remove(mainPanel);
			mainPanel = panel;
			framePanel.add(panel, gbc.wgt(1.0).fill("BOTH"));
			framePanel.revalidate();
			framePanel.repaint();
		} catch (Exception e) {

		}
	}
	
	public Customer getCustomer() {
		return customer;
	}
	
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
		
		sideBar = new SideBar(menu);
		
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
		this.setVisible(true);
		return this;
	}
	
	public SideBarItem sbProfilePage() {
		profilePage = new CustomerFormPage(this.getMainPanel(), customer);
		return new SideBarItem("Perfil", () -> this.swapMainPanel(profilePage), true);
	}
	
	public SideBarItem sbDietPage() {
		dietPage = new DietPage(this.getMainPanel());
		return new SideBarItem("Dieta", () -> this.swapMainPanel(dietPage));
	}

}
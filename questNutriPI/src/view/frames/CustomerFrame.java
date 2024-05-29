package view.frames;

import java.awt.Dimension;
import java.awt.GridBagLayout;
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

public class CustomerFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private Customer customer;
	public GenericJPanel framePanel = new GenericJPanel().ltGridBag();
	private GenericJPanel mainPanel = new GenericJPanel();
	private SideBar sideBar;
	private StdGBC gbc = new StdGBC();
	
	public CustomerFrame(Customer customer) {
		this.customer = customer;
		this.add(framePanel);
		
		//Definindo as subp�ginas do frame.
		SideBarMenu menu = new SideBarMenu(
				new SideBarItem("Perfil", () -> this.swapMainPanel(new CustomerFormPage(this.getMainPanel(), customer)), true),
				new SideBarItem("Dieta", () -> this.swapMainPanel(new DietPage(this.getMainPanel())))
		);
		menu.setFirstPanel();
		
		SideBar customerSideBar = new SideBar(menu);
		customerSideBar.setMinimumSize(new Dimension(250, this.getHeight()));
		customerSideBar.setPreferredSize(new Dimension(250, this.getHeight()));
		
		framePanel.add(customerSideBar, framePanel.gbc.fill("BOTH").wgt(0, 1.0));
		framePanel.add(mainPanel, framePanel.gbc.wgt(1.0));
		this.add(framePanel);
		
		int x = QuestNutri.app.getX() + QuestNutri.app.getWidth()/10;
		int y = QuestNutri.app.getY() + QuestNutri.app.getHeight()/10;
		
		int w = QuestNutri.app.getWidth() - (2*x);
		int h = QuestNutri.app.getHeight() - (2*y);
		
		this.setBounds(x, y, w, h);
		//customerFrame.setUndecorated(true);
		this.setTitle("Cliente - " + this.getCustomerName());
		
		QuestNutri.followYouIntoTheDark();
		
		this.addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent e) {
		        QuestNutri.heraldOfDarkness();;
		    }
		});
	}
	
	public CustomerFrame setSideBar(SideBar sideBar) {
		this.sideBar = sideBar;
		masterPanelSetup();
		return this;
	}
	
	private void masterPanelSetup() {
		framePanel.setLayout(new GridBagLayout());
		framePanel.add(sideBar, gbc.grid(0).wgt(0, 1.0).insets(0).anchor("WEST").width(1));
		framePanel.add(mainPanel, gbc.wgt(1.0).fill("BOTH").xP());
	}
	
	public void swapMainPanel(GenericJPanel panel) {
		framePanel.remove(mainPanel);
		mainPanel = panel;
		framePanel.add(panel, gbc.wgt(1.0).fill("BOTH"));
		framePanel.revalidate();
		framePanel.repaint();
	}
	
	public String getCustomerName() {
		return customer.name;
	}
	
	public GenericJPanel getMainPanel() {
		return mainPanel;
	}

}
package view.frames;

import java.awt.GridBagLayout;

import javax.swing.JFrame;

import models.Customer;
import view.components.utils.StdGBC;
import view.panels.components.GenericJPanel;
import view.panels.components.SideBar;

public class CustomerFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private Customer customer;
	public GenericJPanel framePanel = new GenericJPanel();
	private GenericJPanel mainPanel = new GenericJPanel();
	private SideBar sideBar;
	private StdGBC gbc = new StdGBC();
	
	public CustomerFrame(Customer customer) {
		this.customer = customer;
		this.add(framePanel);
	}
	
	public CustomerFrame setSideBar(SideBar sideBar) {
		this.sideBar = sideBar;
		masterPanelSetup();
		return this;
	}
	
	private void masterPanelSetup() {
		framePanel.setLayout(new GridBagLayout());
		framePanel.add(sideBar, gbc.grid(0).wgt(0, 1.0).insets(0).anchor("WEST").width(1));
		framePanel.add(mainPanel, gbc.wgt(1.0).fill("BOTH").xP().insets("1", 100, 20));
	}
	
	public void swapMainPanel(GenericJPanel panel) {
		framePanel.remove(mainPanel);
		mainPanel = panel;
		framePanel.add(panel, gbc);
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
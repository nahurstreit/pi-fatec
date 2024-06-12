package view.components.sidebar;

import java.awt.Component;

import view.components.generics.GenericComponent;
import view.components.generics.GenericJPanel;

public class SideBar extends GenericJPanel {
	private static final long serialVersionUID = 1L;
	
	public int stdPadding = 0;
	
	/**
	 * Cria o painel de menu lateral (SideBar)
	 * @param <T>
	 */
	public SideBar(SideBarComponent<?> ...components) {
		this.setBackground(STD_LIGHT_GRAY);
		this.ltGridBag();
		
		if(components.length > 0) {
			for(SideBarComponent<?> component: components) {
				GenericComponent holderPanel = new GenericComponent();
				holderPanel.ltGridBag();
				holderPanel.setBackground(STD_LIGHT_GRAY);
				holderPanel.add((Component) component.getComponent());
				this.add(holderPanel, component.gbc);
			}
		}
	}
  
}
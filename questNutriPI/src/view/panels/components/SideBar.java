package view.panels.components;

import java.awt.Component;

public class SideBar extends GenericJPanel {
	private static final long serialVersionUID = 1L;
	
	public int stdPadding = 0;
	
	/**
	 * Cria o painel de menu lateral (SideBar)
	 * @param <T>
	 */
	public SideBar(SideBarComponent<?> ...components) {
		this.setBackground(this.STD_LIGHT_GRAY);
		this.ltGridBag();
		
		if(components.length > 0) {
			for(SideBarComponent<?> component: components) {
				GenericComp holderPanel = new GenericComp();
				holderPanel.ltGridBag();
				holderPanel.setBackground(STD_LIGHT_GRAY);
				holderPanel.add((Component) component.getComponent());
				this.add(holderPanel, component.gbc);
			}
		}
	}
  
}
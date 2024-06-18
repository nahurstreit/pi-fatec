/**
 * Package que contém as classes que controlam o componente de SideBar
 */
package view.components.sidebar;

import java.awt.Component;

import view.components.generics.GenericComponent;
import view.components.generics.GenericJPanel;

/**
 * Painel lateral que serve como menu.
 */
public class SideBar extends GenericJPanel {
	private static final long serialVersionUID = 1L;
	
    /**
     * Construtor que cria um painel lateral (SideBar) com os componentes fornecidos.
     *
     * @param components Componentes que serão adicionados à SideBar
     */
	@SafeVarargs
	public SideBar(SideBarComponent<?> ...components) {
		this.setBackground(STD_LIGHT_GRAY_COLOR);
		this.ltGridBag();
		
		if(components.length > 0) {
			for(SideBarComponent<?> component: components) {
				GenericComponent holderPanel = new GenericComponent();
				holderPanel.ltGridBag();
				holderPanel.setBackground(STD_LIGHT_GRAY_COLOR);
				holderPanel.add((Component) component.getComponent());
				this.add(holderPanel, component.gbc);
			}
		}
	}
  
}
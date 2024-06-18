package view.components.sidebar;

import javax.swing.JLabel;

import utils.interfaces.IDoAction;

/**
 * Representa um item de menu lateral (SideBarItem).
 */
public class SideBarItem {
	
	public String text;
	private Boolean selected = false;
	private IDoAction event;
	
	private JLabel attachLbl;
	private SideBarMenu menu;
	
    /**
     * Construtor para inicializar um SideBarItem com texto e ação associada.
     *
     * @param text    Texto exibido no item.
     * @param event   Ação a ser executada quando o item é selecionado.
     * @param selected Indica se o item deve iniciar selecionado.
     */
	public SideBarItem(String text, IDoAction event, Boolean ...selected) {
		this.text = text;
		this.event = event;
		if(selected.length > 0) {
			this.selected = selected[0];
		}
	}
	
    /**
     * Define o JLabel associado a este item.
     *
     * @param lbl JLabel a ser associado.
     * @return o próprio objeto para implementar fluent interface.
     */
	public SideBarItem setLbl(JLabel lbl) {
		this.attachLbl = lbl;
		return this;
	}
	
    /**
     * Define o SideBarMenu ao qual este item pertence.
     *
     * @param menu SideBarMenu ao qual o item pertence.
     * @return o próprio objeto para implementar fluent interface.
     */
	public SideBarItem setBarMenu(SideBarMenu menu) {
		this.menu = menu;
		return this;
	}
	
    /**
     * Executa a ação associada a este item.
     * Se houver um evento associado, ele será executado. Além disso, se houver um JLabel associado
     * e um SideBarMenu associado, o método swapUnderline do menu será chamado para destacar o item.
     */
	public void performEvent() {
        if(event != null) {
            event.execute();
        }
        if(attachLbl != null && menu != null) {
        	menu.swapUnderline(attachLbl);
        }
	}
	
    /**
     * Verifica se este item de menu está selecionado.
     *
     * @return true se o item estiver selecionado, false caso contrário.
     */
	public boolean isSelected() {
		return this.selected;
	}
}
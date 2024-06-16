package view.components.sidebar;

import javax.swing.JLabel;

public class SideBarItem {
	/**
	 * Interface para declar o tipo genérico de execução. Possibilitando uma alta escalabilidade de SideBarItem e SideBar,
	 * uma vez que suas instâncias dependem unicamente de sua inicialização, não da estrutura da classe.
	 */
	public interface Action {
		void execute();
	}
	
	public String text;
	private Boolean selected = false;
	private Action event;
	
	private JLabel attachLbl;
	private SideBarMenu menu;
	
	public SideBarItem(String text, Action event, Boolean ...selected) {
		this.text = text;
		this.event = event;
		if(selected.length > 0) {
			this.selected = selected[0];
		}
	}
	
	public SideBarItem setLbl(JLabel lbl) {
		this.attachLbl = lbl;
		return this;
	}
	
	public SideBarItem setBarMenu(SideBarMenu menu) {
		this.menu = menu;
		return this;
	}
	
	/**
	 * Método que executa o evento indicado na criação do SideItem
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
	 * Método que retorna um valor booleano indicando se aquele item de SideBar começa com o estado selecionado na inicialização da instância.
	 * @return <b>boolean</b> -> Valor booleano que indica se aquela label deve começar selecionada no SideBar
	 */
	public boolean isSelected() {
		return this.selected;
	}
}
package view.panels;

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
	
	public SideBarItem(String text, Action event, Boolean ...selected) {
		this.text = text;
		this.event = event;
		if(selected.length > 0) {
			this.selected = selected[0];
		}
	}
	
	public void performEvent() {
        if (event != null) {
            event.execute();
        }
	}
	
	public boolean isSelected() {
		return this.selected;
	}
}
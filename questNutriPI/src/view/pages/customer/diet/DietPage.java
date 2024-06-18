package view.pages.customer.diet;

import model.entities.Customer;
import view.components.generics.GenericJPanel;
import view.pages.generics.GenericPage;

/**
 * Página para exibição da dieta semanal de um cliente.
 */
public class DietPage extends GenericPage {
	private static final long serialVersionUID = 1L;
	
	private GenericJPanel weekPanel = new GenericJPanel().ltGridBag();
	
	private int pagePadding = 10;
	
    /**
     * Construtor da página de dieta.
     *
     * @param ownerPanel Painel proprietário onde esta página será exibida.
     * @param customer   Cliente cuja dieta será exibida.
     */
	public DietPage(GenericJPanel ownerPanel, Customer customer) {
		super(ownerPanel);
		this.ltGridBag();
	
		weekPanel.add(new DietWeekPanel(this, customer), weekPanel.gbc.fill("BOTH").wgt(1.0));
		
		this.add(weekPanel, gbc.fill("BOTH").wgt(1.0).insets(pagePadding));
	}

}
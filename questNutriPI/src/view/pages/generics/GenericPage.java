package view.pages.generics;

import view.components.generics.GenericJPanel;

/**
 * Página genérica que estende GenericJPanel e define uma cor de fundo branca padrão.
 */
public class GenericPage extends GenericJPanel {
	private static final long serialVersionUID = 1L;
	
    /**
     * Construtor da página genérica.
     *
     * @param ownerPanel Painel proprietário onde esta página será exibida.
     */
	public GenericPage(GenericJPanel ownerPanel) {
		super(ownerPanel);
		this.setBackground(STD_WHITE_COLOR);
	}
}
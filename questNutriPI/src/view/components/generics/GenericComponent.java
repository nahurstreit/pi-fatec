/**
 * Package que contém os componentes genéricos da view.
 */
package view.components.generics;

/**
 * Classe que define um componente genérico separado de todos os outros componentes, para caso seja preciso implementar alguma lógica a parte.
 */
public class GenericComponent extends GenericJPanel {
	private static final long serialVersionUID = 1L;

	/**
	 * Painel dono deste componente.
	 */
	public GenericJPanel ownerPanel;
	
	/**
	 * Constructor que define o painel que é dono deste componente
	 * @param ownerPanel - GenericJPanel que é dono deste componente.
	 */
	public GenericComponent(GenericJPanel ownerPanel) {
		super(ownerPanel);
	}
	
	/**
	 * Constructor padrão.
	 */
	public GenericComponent() {
		this(null);
	}
}
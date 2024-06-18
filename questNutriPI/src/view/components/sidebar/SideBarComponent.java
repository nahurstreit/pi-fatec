package view.components.sidebar;

import java.awt.Dimension;

import utils.interfaces.GeneralVisualSettings;
import view.components.utils.StdGBC;

/**
 * Componente genérico para ser inserido em um sidebar.
 *
 * @param <T> Tipo do componente que será inserido no sidebar.
 */
public class SideBarComponent<T> implements GeneralVisualSettings {
	protected T component;
	private Dimension size;
	private Dimension maxSize;
	private Dimension minSize;
	
	public final int MAX_SIZE = 1;
	public final int MIN_SIZE = 2;
	public final int BOTH = 3;
	
	public StdGBC gbc = new StdGBC();
	
    /**
     * Construtor que inicializa o SideBarComponent com um componente especificado.
     *
     * @param component Componente a ser inserido no sidebar.
     */
	public SideBarComponent(T component) {
		this.component = component;
	}
	
    /**
     * Construtor padrão que inicializa o SideBarComponent sem um componente.
     */
	public SideBarComponent() {
		this(null);
	}
	
    /**
     * Obtém o componente associado a este SideBarComponent.
     *
     * @return Componente associado.
     */
	public T getComponent() {
		return this.component;
	}
	
    /**
     * Obtém o tamanho do componente de acordo com o método especificado.
     *
     * @param method Método para obter o tamanho: MAX_SIZE para tamanho máximo, MIN_SIZE para tamanho mínimo, BOTH para ambos.
     * @return Tamanho do componente.
     */
	public Dimension getSize(int method) {
		Dimension choosedSize = new Dimension();
		switch (method) {
		case MAX_SIZE:
			choosedSize = this.maxSize;
			break;
		case MIN_SIZE:
			choosedSize = this.minSize;
			break;
		case BOTH:
			choosedSize = this.size;
			break;
		}
		
		return choosedSize;
	}
	
    /**
     * Obtém o tamanho completo do componente (tamanho máximo, mínimo e padrão).
     *
     * @return Tamanho completo do componente.
     */
	public Dimension getSize() {
		return getSize(this.BOTH);
	}
	
    /**
     * Define um tamanho fixo para o componente.
     *
     * @param size Tamanho a ser definido.
     * @return Tamanho definido.
     */
	public Dimension setFixSize(Dimension size) {
		this.maxSize = size;
		this.minSize = size;
		this.size = size;
		return this.size;
	}
}
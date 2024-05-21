package view.panels.components;

import java.awt.Dimension;

import view.components.utils.StdGBC;

/**
 * Classe de componente genérico para inserção em um sidebar
 * @param <T> -> Tipo com componente
 */
public class SideBarComponent<T> {
	protected T component;
	private Dimension size;
	private Dimension maxSize;
	private Dimension minSize;
	
	public final int MAX_SIZE = 1;
	public final int MIN_SIZE = 2;
	public final int BOTH = 3;
	
	public StdGBC gbc = new StdGBC();
	
	public SideBarComponent(T component) {
		this.component = component;
	}
	
	public SideBarComponent() {
		this(null);
	}
	
	public T getComponent() {
		return this.component;
	}
	
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
	
	public Dimension getSize() {
		return getSize(this.BOTH);
	}
	
	public Dimension setFixSize(Dimension size) {
		this.maxSize = size;
		this.minSize = size;
		this.size = size;
		return this.size;
	}
}
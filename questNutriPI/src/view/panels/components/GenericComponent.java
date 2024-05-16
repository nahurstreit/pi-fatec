package view.panels.components;

public class GenericComponent extends GenericJPanel {
	private static final long serialVersionUID = 1L;

	public GenericJPanel ownerPanel;
	
	public GenericComponent(GenericJPanel ownerPanel) {
		super(ownerPanel);
	}
	
	public GenericComponent() {
		this(null);
	}
}
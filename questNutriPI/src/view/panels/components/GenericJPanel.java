package view.panels.components;

import java.awt.GridBagLayout;
import java.util.ArrayList;

public class GenericJPanel extends GeneralJPanelSettings {	
	private static final long serialVersionUID = 1L;
	
	public GenericJPanel ownerPanel;
	
	public GenericJPanel(GenericJPanel ownerPanel) {
		this.ownerPanel = ownerPanel;
	}
	
	public GenericJPanel() {
		this(null);
	}
	
	public GenericJPanel getOwner() {
		return this.ownerPanel;
	}
	
	public ArrayList<GenericJPanel> getFamilyOwners() {
		ArrayList<GenericJPanel> family = new ArrayList<GenericJPanel>();
		GenericJPanel current = this;
		
		while(current.getOwner() != null) {
			current = current.getOwner();
			family.add(current);
		}
		
		return family;
	}
	
	/**
	 * Método para rápida definição de layout como gridBagLayout;
	 */
	public GenericJPanel ltGridBag() {
		this.setLayout(new GridBagLayout());
		return this;
	}
	
	public void refresh() {
		this.revalidate();
		this.repaint();
	}
	
	public String toString() {
		String nameClass = this.getClass().toString().replaceAll(".*\\.", "");
		ArrayList<GenericJPanel> family = this.getFamilyOwners();
		String familyStr = "";
		for(int i = family.size() - 1; i >= 0; i--) {
			familyStr += "\n";
			int controllerSpaces = family.size() - i - 1;
			if(controllerSpaces != 0) {
				familyStr += giveTabSpace(controllerSpaces, (2 * (controllerSpaces)) - 1) + "|\n";
				familyStr += giveTabSpace(controllerSpaces, (2 * (controllerSpaces)) - 1) + "+-->";
			}

			familyStr += "("+ (family.size() - i) +") "+family.get(i).getClass().toString().replaceAll(".*\\.", "");
		}
		
		return "\nClass name: " + nameClass
				+"\nFamily Owners Tree: " + familyStr
				+"\n";
	}
	
	private String giveTabSpace(int quantity, int ...spaces) {
		int space = 1;
		if(spaces.length > 0) {
			if(spaces[0] >= 1) space = spaces[0];
		}
		String response = "";
		
		for(int i = 0; i < space; i++) {
			for(int j = 0; j < quantity; j++) {
				response += " ";
			}
		}
		
		return response;
	}
}
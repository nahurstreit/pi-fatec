package view.panels.components;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;

import view.frames.GenericJFrame;

public class GenericJPanel extends GeneralJPanelSettings {	
	private static final long serialVersionUID = 1L;
	
	public GenericJPanel ownerPanel;
	private GenericJFrame callerFrame;
	
	public GenericJPanel(GenericJPanel ownerPanel) {
		this.ownerPanel = ownerPanel;
	}
	
	public GenericJPanel() {
		this(null);
	}
	
	public GenericJPanel setCallerFrame(GenericJFrame frame) {
		if(ownerPanel == null) {
			this.callerFrame = frame;
		}
		return this;
	}
	
	public GenericJPanel setOwner(GenericJPanel ownerPanel) {
		this.ownerPanel = ownerPanel;
		return this;
	}
	
	public GenericJPanel getOwner() {
		return this.ownerPanel;
	}
	
	public GenericJFrame getCallerFrame() {
		return this.callerFrame;
	}
	
	public GenericJPanel setBGColor(Color color) {
		setBackground(color);
		return this;
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
	
	public GenericJPanel setBorder() {
		setBorder(BorderFactory.createLineBorder(Color.black));
		return this;
	}
	
	public GenericJPanel setBorder(Color color) {
		setBorder(BorderFactory.createLineBorder(color));
		return this;
	}
	
	public void refresh() {
		this.revalidate();
		this.repaint();
	}
	
	public String toString() {
		String res = "";
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
		
		try {
			res = family.get(family.size() - 1).getCallerFrame()
					+ "\nClass name: " + nameClass
					+ "\nFamily Owners Tree: " + familyStr
					+ "\n";
		} catch (Exception e) {
		}
		
		return res;
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
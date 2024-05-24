package view.panels.components;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import view.panels.pages.subpages.components.FormBoxInput;

public class FormComponent extends GenericComponent {
	private static final long serialVersionUID = 1L;
	
	private List<List<FormBoxInput>> rows = new ArrayList<List<FormBoxInput>>();
	
	public FormComponent(GenericJPanel ownerPanel) {
		super(ownerPanel);
		ltGridBag();
	}
	
	public FormComponent addRow(FormBoxInput ...formBoxInputs) {
		List<FormBoxInput> holderList = new ArrayList<FormBoxInput>();
		for(FormBoxInput box: formBoxInputs) {
			holderList.add(box);
		}
		
		this.rows.add(holderList);
		return this;
	}
	
	public FormComponent init() {
		gbc.fill("BOTH").wgt(1.0, 0).grid(0).anchor("NORTHWEST"); //Layout inicial DO FORM
		for(int i = 0; i < rows.size(); i++) {
			GenericJPanel rowPanel = new GenericJPanel().ltGridBag();
			rowPanel.setBackground(Color.red);
			rowPanel.gbc.grid(0).fill("BOTH").wgt(1.0).anchor("WEST"); //Layout inicial DA LINHA
			rowPanel.setPreferredSize(new Dimension(50, 50));
			for(FormBoxInput box: rows.get(i)) {
				rowPanel.add(box, rowPanel.gbc);
				rowPanel.gbc.xP();
			}
			add(rowPanel, gbc);
			gbc.yP();
		}
		return this;
	}
	
}
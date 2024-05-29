package view.panels.components;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

import view.panels.pages.subpages.components.FormBoxInput;

public class FormComponent extends GenericComponent {
	private static final long serialVersionUID = 1L;
	
	private List<List<FormBoxInput>> rows = new ArrayList<List<FormBoxInput>>();
	private String sessionName;
	
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
	
	public FormComponent setUpName(String name) {
		this.sessionName = name;
		return this;
	}
	
	public FormComponent init() {
		GenericJPanel panel = new GenericJPanel().ltGridBag();
		
		panel.gbc.fill("BOTH").wgt(1.0, 0).grid(0).anchor("NORTHWEST");  //Layout inicial DO FORM
		if(sessionName != null) {
			JLabel upperName = new JLabel(sessionName, JLabel.CENTER);
			upperName.setFont(STD_BOLD_FONT.deriveFont(20f));
			panel.add(upperName, panel.gbc);
			panel.gbc.yP();
		}
		for(int i = 0; i < rows.size(); i++) {
			GenericJPanel rowPanel = new GenericJPanel().ltGridBag();
			rowPanel.setBackground(Color.red);
			rowPanel.gbc.grid(0).fill("BOTH").wgt(1.0).anchor("WEST"); //Layout inicial DA LINHA
//			rowPanel.setPreferredSize(new Dimension(50, 75));
			for(FormBoxInput box: rows.get(i)) {
				rowPanel.add(box, rowPanel.gbc);
				rowPanel.gbc.xP();
			}
			panel.add(rowPanel, panel.gbc);
			panel.gbc.yP();
		}
		add(panel, gbc.fill("BOTH").wgt(1.0).insets("3", 25, 0));
		return this;
	}
	
}
package view.panels.pages.subpages.components;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;

import view.components.HintInputField;
import view.panels.components.GenericComponent;
import view.panels.components.GenericJPanel;

public class FormBoxInput extends GenericComponent {
	private static final long serialVersionUID = 1L;
	
	private JLabel label;
	private HintInputField input;

	public FormBoxInput(GenericJPanel ownerPanel) {
		super(ownerPanel);
		setBackground(Color.green);
		ltGridBag();
		
		label = new JLabel("");
		label.setFont(STD_BOLD_FONT.deriveFont(15f));
		this.add(label, gbc.fill("BOTH").grid(0).insets("3", 0, 15).anchor("WEST"));
		
		input = new HintInputField("Digite aqui...", new Dimension(10, 30), 12f);
		input.setMinimumSize(new Dimension(100, 20));
		this.add(input, gbc.yP().insets("1", 5, 15).wgt(1.0, 0));
	}
	
	public FormBoxInput setLbl(String text) {
		label.setText(text);
		return this;
	}
	
	public FormBoxInput setHint(String text) {
		input.setHint(text);
		return this;
	}
	
	public FormBoxInput setSize() {
		return this;
	}
	
	public FormBoxInput setMask(String mask) {
		input.setMask(mask);
		return this;
	}
	
	public FormBoxInput setValue(String text) {
		input.setInitialValue(text);
		return this;
	}
	
	public FormBoxInput clearMaskOnSelect(boolean clear) {
		input.clearMaskOnSelect(clear);
		return this;
	}
	
	public String getValue() {
		return input.getText();
	}
	

	
}
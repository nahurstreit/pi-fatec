package view.panels.pages.subpages.components;

import java.awt.Color;
import java.awt.Dimension;
import java.util.function.Function;

import javax.swing.JLabel;

import view.components.FormInputField;
import view.components.FormInputField.ValidationRule;
import view.panels.components.GenericComponent;
import view.panels.components.GenericJPanel;

public class FormBoxInput extends GenericComponent {
	private static final long serialVersionUID = 1L;
	
	private JLabel label;
	private JLabel errorLabel;
	private FormInputField input;
	
	private Function<String, Object> resMiddleware;

	public FormBoxInput(GenericJPanel ownerPanel) {
		super(ownerPanel);
		setBackground(Color.green);
		ltGridBag();
		
		label = new JLabel("");
		label.setFont(STD_BOLD_FONT.deriveFont(15f));
		this.add(label, gbc.fill("BOTH").grid(0).insets("3", 0, 15).anchor("WEST"));
		
		input = new FormInputField("Digite aqui...", new Dimension(10, 30), 12f).setFormBoxInput(this);
		input.setMinimumSize(new Dimension(100, 20));
		this.add(input, gbc.yP().insets(5, 15, 0, 15).wgt(1.0, 0));
		
		errorLabel = new JLabel("");
		errorLabel.setFont(STD_BOLD_FONT.deriveFont(12f));
		this.add(errorLabel, gbc.yP().insets(0, 15, 10, 15));
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
	
	public FormBoxInput setValidation(ValidationRule rule) {
		input.setValidationRule(rule);
		return this;
	}
	
	public FormBoxInput setValidation(ValidationRule rule, String errorMsg) {
		input.setValidationRule(rule, errorMsg);
		return this;
	}
	
	public FormBoxInput setResponseMiddleware(Function<String, Object> middleware) {
		resMiddleware = middleware;
		return this;
	}
	
	public void setErrorLbl(String text) {
		errorLabel.setText(text);
		this.setForeground(Color.red);
	}
	
	public Object getValue() {
		Object response = input.getValue();
		if(resMiddleware != null) {
			try {
				response = resMiddleware.apply(response.toString());
			} catch (Exception e) {
				response = null;
			}
		}
		return response;
	}
		
}
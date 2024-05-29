package view.components;

import java.awt.Dimension;

import view.panels.pages.subpages.components.FormBoxInput;

public class FormInputField extends HintInputField {
	private static final long serialVersionUID = 1L;

	public interface ValidationRule {
		boolean isValid(String text);
	}
	
	private ValidationRule rule;
	private FormBoxInput ownerBox;
	private String errorMsg = "Error!";
	
	public FormInputField(String hint, Dimension size, float fontSize) {
		super(hint, size, fontSize);
	}
	
	public FormInputField(String hint) {
		super(hint);
	}
	
	public FormInputField setValidationRule(ValidationRule rule) {
		this.rule = rule;
		return this;
	}
	
	public FormInputField setValidationRule(ValidationRule rule, String errorMsg) {
		this.rule = rule;
		this.errorMsg = errorMsg;
		return this;
	}
	
	public FormInputField setFormBoxInput(FormBoxInput ownerBox) {
		this.ownerBox = ownerBox;
		return this;
	}
	
	@Override
	public Object getValue() {
		Object result = super.getValue();
		if(rule != null) {
			if(!rule.isValid(result.toString()) && showHint == false) {
				ownerBox.setErrorLbl(errorMsg);
			} else {
				ownerBox.setErrorLbl("");
			}
		}
		return result;
	}
	
}
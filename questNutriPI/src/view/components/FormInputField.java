package view.components;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import validate.ValidationRule;

public class FormInputField extends HintInputField {
	private static final long serialVersionUID = 1L;
	
	private List<ValidationRule> validationRules = new ArrayList<>();
	private FormBoxInput ownerBox;
	
	public FormInputField(String hint, Dimension size, float fontSize) {
		super(hint, size, fontSize);
	}
	
	public FormInputField(String hint) {
		super(hint);
	}
	
	public FormInputField addValidationRule(ValidationRule ...rules) {
		for(ValidationRule rule: rules) validationRules.add(rule);
		return this;
	}
	
	public FormInputField setFormBoxInput(FormBoxInput ownerBox) {
		this.ownerBox = ownerBox;
		return this;
	}
	
	 @Override
    public Object getValue() {
        Object result = super.getValue();
        if (!showHint) {
        	if(ownerBox != null && validationRules != null && validationRules.size() > 0) {
                for(ValidationRule rule: validationRules) {
                    if (!rule.isValid(result.toString())) {
                        ownerBox.setErrorLbl(rule.getErrorMessage());
                        return result;
                    }
                }
                ownerBox.setErrorLbl(""); //Se não tiver erro, limpa a label de erro
        	}
        }
        return result;
    }
	
}
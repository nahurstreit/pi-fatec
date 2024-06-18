package view.components.forms;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import utils.validations.ValidationRule;
import view.components.inputs.HintInputField;

/**
 * Componente personalizado para definir um campo de TextField do componente VS-FlexFormUI.
 */
public class FormInputField extends HintInputField {
	private static final long serialVersionUID = 1L;
	
	private List<ValidationRule> validationRules = new ArrayList<>();
	private FormBoxInput ownerBox;
	
	/**
	 * Constructor do InputField
	 * @param hint - Dica a ser exibida no campo quando não tiver valores digitados
	 * @param size - tamanho da caixa de texto
	 * @param fontSize - tamanho da fonte.
	 */
	public FormInputField(String hint, Dimension size, float fontSize) {
		super(hint, size, fontSize);
	}
	
	/**
	 * Constructor que define apenas a dica a ser exibida no campo quando não tiver valores digitados.
	 * @param hint - Dica a ser exibida.
	 */
	public FormInputField(String hint) {
		super(hint);
	}
	
	/**
	 * Método que adiciona validações ao text.
	 * @param rules varargs do tipo ValidationRule
     * @return O próprio objeto para implementar fluent interface.
	 */
	public FormInputField addValidationRule(ValidationRule ...rules) {
		for(ValidationRule rule: rules) validationRules.add(rule);
		return this;
	}
	
	/**
	 * Atribui ao textfield o FormBoxInput que é dono desse campo de texto. Isso é feito para poder definir as validações e os erros do campo.
	 * @param ownerBox objeto do tipo FormBoxInput que possui esse campo
     * @return O próprio objeto para implementar fluent interface.
	 */
	public FormInputField setFormBoxInput(FormBoxInput ownerBox) {
		this.ownerBox = ownerBox;
		return this;
	}
	
	/**
	 * Método que retorna a String que está sendo digitada no textfield.
	 */
	@Override
    public String getValue() {
		String result = super.getValue();
        if(!isShowingHint()) {
        	if(!result.isBlank() && ownerBox != null && validationRules != null && validationRules.size() > 0) {
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
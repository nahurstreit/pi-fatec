package validate;

public class ValidationRule {
	
	public interface Validate {
		boolean isValid(String text);
	}
	
	private Validate rule;
    private String errorMessage;
    
    public ValidationRule(Validate rule, String errorMessage) {
    	this.rule = rule;
    	this.errorMessage = errorMessage;
    }
    
    public ValidationRule(Validate rule) {
    	this(rule, "Erro de validação.");
    }
    
    public String getErrorMessage() {
		return errorMessage;
	}
    
    public boolean isValid(String text) {
        return rule.isValid(text);
    }

}
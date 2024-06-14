package view.components.inputs;

import utils.interfaces.Condition;

public class ConditionalMask {
	private final String mask;
	public final Condition condition;
	
	public ConditionalMask(final String mask, final Condition condition) {
		this.mask = mask;
		this.condition = condition;
	}
	
	public ConditionalMask(final String mask) {
		this.mask = mask;
		this.condition = (text) -> {return true;};
	}

	public String getMask() {
		return mask;
	}
	
	public boolean isValid(String text) {
		return condition.isValid(text);
	}
}
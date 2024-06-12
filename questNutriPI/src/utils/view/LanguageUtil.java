package utils.view;

import java.util.ArrayList;
import java.util.List;

import view.QuestNutri;

public final class LanguageUtil {
	private List<String> options = new ArrayList<String>();
	
	public LanguageUtil(String portuguese, String english) {
		options.add(portuguese);
		options.add(english);
	}
	
	public String get() {
		return options.get(QuestNutri.language());
	}

}
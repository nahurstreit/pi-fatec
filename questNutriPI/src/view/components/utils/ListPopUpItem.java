package view.components.utils;

import java.util.function.Function;

public class ListPopUpItem<T> {
	public String text;
	public Function<T, Runnable> action;
	
	public ListPopUpItem() {
		// TODO Auto-generated constructor stub
	}
}
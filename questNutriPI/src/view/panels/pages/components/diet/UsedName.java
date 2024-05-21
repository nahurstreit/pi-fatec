package view.panels.pages.components.diet;

public class UsedName {
	public String name;
	public int currentChar = (int) 'A';
	
	public UsedName(String name) {
		this.name = name;
	}
	
	public UsedName add() {
		currentChar++;
		return this;
	}
	
	public char get() {
		return (char) this.currentChar;
	}
}
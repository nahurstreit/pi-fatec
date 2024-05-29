package validate;

public class Validate {
	public static boolean hasNumber(String str) {
        return str != null && str.matches(".*\\d.*");
	}
	
	public static boolean sizeBetween(String str, int min, int max) {
		return str.length() >= min && str.length() <= max;
	}
}
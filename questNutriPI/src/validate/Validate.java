package validate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class Validate {
	public static boolean hasNumber(String str) {
        return str != null && str.matches(".*\\d.*");
	}
	
	public static boolean sizeBetween(String str, int min, int max) {
		return str.length() >= min && str.length() <= max;
	}
	
	/**
	 * Método que retorna verdadeiro se uma string tem letras.
	 * @param str - String verificada
	 * @param allowed - caracteres permitidos pela validação.
	 * @return 
	 * <li><b>true</b> - se a String tiver letras.
	 * <li><b>false</b> - se a String não tiver letras.
	 */
	public static boolean hasChar(String str, Character ...allowed) {
	    if (str == null) {
	        return false;
	    }
	    
	    String allowedChars = "";
	    for (Character c : allowed) {
	        allowedChars += c;
	    }
	    
	    String regex = ".*[^\\d\\s" + allowedChars + "].*";
	    str = str.replaceAll("\\s", "");
	    return str.matches(regex);
	}
	
	public static boolean haveSpecifChar(String str, char searchChar) {
	    if (str == null) {
	        return false;
	    }
	    
	    String regex = Pattern.quote(String.valueOf(searchChar));
	    
	    return str.matches(".*" + regex + ".*");
	}
	
	public static boolean isDate(String date) {
		try {
			LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));			
		} catch (Exception e) {
			return false;
		}
		return true;		
	}

}
package utils;

public class ResponseUtil {
	private final boolean status;
	private final String message;
	
	public ResponseUtil(final boolean status, final String message) {
		this.status = status;
		this.message = message;
	}
	
	public boolean status() {
		return this.status;
	}
	
	public String message() {
		return message;
	}
}
package project;

public class AuthenticationException extends Exception{
	public AuthenticationException() {
		super("Authentication failed");
	}
	public AuthenticationException(String message) {
		super(message);
	}
}

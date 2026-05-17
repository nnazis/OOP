
public class NotResearcherException extends Exception{
	public NotResearcherException() {
		super("User is not a researcher");
	}
	public NotResearcherException(String message) {
		super(message);
	}
}

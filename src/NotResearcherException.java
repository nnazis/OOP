public class NotResearcherException extends Exception {
    public NotResearcherException() {
        super("Not a researcher");
    }
    public NotResearcherException(String message) {
        super(message);
    }
}


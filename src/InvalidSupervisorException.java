public class InvalidSupervisorException extends Exception {
    public InvalidSupervisorException() {
        super("Invalid supervisor");
    }
    public InvalidSupervisorException(String message) {
        super(message);
    }
}


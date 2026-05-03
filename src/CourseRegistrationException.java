
public class CourseRegistrationException extends Exception{
	public CourseRegistrationException() {
		super("Course registration failed");
	}
	public CourseRegistrationException(String message) {
		super(message);
	}
}

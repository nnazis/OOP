package project;

public class RegistrationService {
	private University university;
	
	public RegistrationService(University university) {
		this.university = university;
	}
	public void registerStudentToCourse(Student student, Course course)
		throws CourseRegistrationException {
		
		if (course.getStatus() != CourseStatus.OPEN) {
			throw new CourseRegistrationException("Course is not opened for registration");
		}
		if (student.getTotalCredits() + course.getCredits() > 21) {
			throw new CourseRegistrationException("Student cannot register for more than 21 credits");
		}
		
		student.registerForCourse(course);
		course.addStudent(student);
	}
	
	public void dropStudentFromCourse(Student student, Course course) {
		student.dropCourse(course);
		course.removeStudent(student);
	}
	
	public void approveRegistration(Student student, Course course, Manager manager) {
		System.out.println("Registration approved by " + manager.getFullName());
	}
	public void rejectRegistration(Student student, Course course, Manager manager) {
		System.out.println("Registration rejected by " + manager.getFullName());
	}
}

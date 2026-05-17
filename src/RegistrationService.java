public class RegistrationService {
    public University university;

    public RegistrationService(University university) {
        this.university = university;
    }

    public void registerStudentToCourse(Student student, Course course) throws CourseRegistrationException {
        student.registerForCourse(course);
    }

    public void dropStudentFromCourse(Student student, Course course, Manager manager) {
        student.dropCourse(course);
    }

    public void rejectRegistration(Student student, Course course, Manager manager) {
        manager.rejectRegistration(student, course);
    }
}


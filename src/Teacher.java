
import java.util.List;

public class Teacher extends User {
    public Teacher(String id, String name, String email) {
        super(id, name, email);
    }

    public List<Student> viewStudents(Course course) {
        return course.getStudents();
    }

    public void putMark(Student student, Course course, double att1, double att2, double finalExam) {
        if (!course.getTeachers().contains(this) && course.getTeacher() != this) {
            throw new IllegalArgumentException("Teacher is not assigned to this course!");
        }
        course.setMark(student, att1, att2, finalExam);
    }
}

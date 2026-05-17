
import java.util.ArrayList;
import java.util.List;

public class Student extends User {
    private double gpa;
    private int totalCredits;
    private Transcript transcript;
    private List<Course> registeredCourses;

    public Student(String id, String name, String email) {
        super(id, name, email);
        this.gpa = 0.0;
        this.totalCredits = 0;
        this.transcript = new Transcript();
        this.registeredCourses = new ArrayList<>();
    }

    public void registerCourse(Course course) {
        if (!registeredCourses.contains(course)) {
            registeredCourses.add(course);
            calculateTotalCredits();
        }
    }

    public void dropCourse(Course course) {
        registeredCourses.remove(course);
        calculateTotalCredits();
    }

    public List<Course> viewCourses() {
        return registeredCourses;
    }

    public Mark viewMark(Course course) {
        return transcript.getCourseMarks().get(course);
    }

    public double calculateGPA() {
        this.gpa = transcript.calculateGPA();
        return gpa;
    }

    private void calculateTotalCredits() {
        int credits = 0;
        for (Course course : registeredCourses) {
            credits += course.getCredits();
        }
        this.totalCredits = credits;
    }

    public double getGpa() {
        return gpa;
    }

    public int getTotalCredits() {
        return totalCredits;
    }

    public Transcript getTranscript() {
        return transcript;
    }

    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }
}

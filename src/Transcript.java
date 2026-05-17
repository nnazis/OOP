import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Transcript {
    private Student student;
    private Map<Course, Mark> courseMarks;
    private double gpa;

    public Transcript(Student student) {
        this.student = student;
        this.courseMarks = new HashMap<>();
        this.gpa = 0.0;
    }

    public double getGpa(){
        return gpa;
    }
    public Student getStudent(){
        return student;
    }

    public void addMark(Course course, Mark mark){
        courseMarks.put(course, mark);
        calculateGpa();
    }

    public Map<Course, Mark> getCourseMarks(){
        return courseMarks;
    }

    public Mark getMark(Course course){
        return courseMarks.get(course);
    }

    public double calculateGpa() {
        if (courseMarks.isEmpty()) {
            gpa = 0.0;
            return 0.0;
        }
        double totalPoints = 0.0;
        int totalCredits = 0;
        for (Map.Entry<Course, Mark> entry : courseMarks.entrySet()) {
            int credits = entry.getKey().getCredits();
            String grade = entry.getValue().getLetterGrade();
            double point = 0.0;
            if(grade.equals("A")){
                point = 4.0;
            }
            else if(grade.equals("B")){
                point = 3.0;
            }
            else if(grade.equals("C")){
                point = 2.0;
            }
            else if(grade.equals("D")){
                point = 1.0;
            }
            totalPoints += point * credits;
            totalCredits += credits;
        }
        if (totalCredits > 0) gpa = totalPoints / totalCredits;
        return gpa;
    }

    public void printTranscript() {
        System.out.println("=== Transcript: " + student.getFullName() + " ===");
        for (Map.Entry<Course, Mark> entry : courseMarks.entrySet()) {
            System.out.println(entry.getKey().getName() + " -> " + entry.getValue().getLetterGrade() + " (total: " + entry.getValue().getTotal() + ")");
        }
        System.out.println("GPA: " + gpa);
    }

    public String toString() {
        return "Transcript[student=" + student.getFullName() + ", gpa=" + gpa + "]";
    }
}


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Transcript implements Serializable {
    private Map<Course, Mark> courseMarks;

    public Transcript() {
        this.courseMarks = new HashMap<>();
    }

    public void addCourseMark(Course course, Mark mark) {
        courseMarks.put(course, mark);
    }

    public Map<Course, Mark> getCourseMarks() {
        return courseMarks;
    }

    public double calculateGPA() {
        if (courseMarks.isEmpty()) {
            return 0.0;
        }
        double totalPoints = 0.0;
        int totalCredits = 0;

        for (Map.Entry<Course, Mark> entry : courseMarks.entrySet()) {
            int credits = entry.getKey().getCredits();
            totalPoints += convertToGPA(entry.getValue().getTotal()) * credits;
            totalCredits += credits;
        }

        if (totalCredits == 0) {
            return 0.0;
        }
        return totalPoints / totalCredits;
    }

    private double convertToGPA(double total) {
        if (total >= 94.5) return 4.0;
        if (total >= 89.5) return 3.67;
        if (total >= 84.5) return 3.33;
        if (total >= 79.5) return 3.0;
        if (total >= 74.5) return 2.67;
        if (total >= 69.5) return 2.33;
        if (total >= 64.5) return 2.0;
        if (total >= 59.5) return 1.67;
        if (total >= 54.5) return 1.33;
        if (total >= 49.5) return 1.0;
        return 0.0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Transcript:\n");

        for (Map.Entry<Course, Mark> entry : courseMarks.entrySet()) {
            sb.append(entry.getKey().getName())
              .append(" - ")
              .append(entry.getValue().getTotal())
              .append(" ")
              .append(entry.getValue().getLetterGrade())
              .append("\n");
        }
        sb.append("GPA: ").append(calculateGPA());
        return sb.toString();
    }
}

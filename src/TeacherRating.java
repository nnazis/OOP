import java.io.Serializable;

public class TeacherRating implements Serializable {
    private Student student;
    private Teacher teacher;
    private int value;
    private String comment;

    public TeacherRating(Student student, Teacher teacher, int value, String comment) {
        this.student = student;
        this.teacher = teacher;
        this.value = value;
        this.comment = comment;
    }

    public Student getStudent() {
        return student;
    }
    public Teacher getTeacher() {
        return teacher;
    }
    public int getValue(){
        return value;
    }
    public String getComment(){
        return comment;
    }

    @Override
    public String toString() {
        return "Rating[" + teacher.getFullName() + " = " + value + "/5: " + comment + "]";
    }
}

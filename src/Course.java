import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Course implements Serializable {
    private String code;
    private String name;
    private int credits;
    private Teacher teacher;
    private List<Student> students;
    private List<Teacher> teachers;
    private List<Lesson> lessons;
    private List<Assessment> assessments;
    private GradingPolicy gradingPolicy;
    private Map<Student, Mark> marks;

    public Course(String code, String name, int credits) {
        this(code, name, credits, null, new GradingPolicy(30, 30));
    }

    public Course(String code, String name, int credits, Teacher teacher) {
        this(code, name, credits, teacher, new GradingPolicy(30, 30));
    }

    public Course(String code, String name, int credits, Teacher teacher, GradingPolicy gradingPolicy) {
        this.code = code;
        this.name = name;
        this.credits = credits;
        this.teacher = teacher;
        this.gradingPolicy = gradingPolicy;
        this.students = new ArrayList<>();
        this.teachers = new ArrayList<>();
        if (teacher != null) {
            this.teachers.add(teacher);
        }
        this.lessons = new ArrayList<>();
        this.assessments = new ArrayList<>();
        this.marks = new HashMap<>();
    }

    public void addStudent(Student student) {
        if (!students.contains(student)) {
            students.add(student);
            marks.put(student, new Mark());
            student.registerCourse(this);
        }
    }

    public void removeStudent(Student student) {
        students.remove(student);
        marks.remove(student);
        student.dropCourse(this);
    }

    public void addTeacher(Teacher teacher) {
        if (!teachers.contains(teacher)) {
            teachers.add(teacher);
        }
    }

    public void addLesson(Lesson lesson) {
        lessons.add(lesson);
    }

    public void addAssessment(Assessment assessment) {
        assessments.add(assessment);
    }

    public void putMark(Student student, Assessment assessment, double score) {
        if (!students.contains(student)) {
            throw new IllegalArgumentException("Student is not enrolled in this course!");
        }
        if (!assessments.contains(assessment)) {
            throw new IllegalArgumentException("Assessment does not belong to this course!");
        }
        if (score < 0 || score > assessment.getMaxScore()) {
            throw new IllegalArgumentException("Invalid score!");
        }
        throw new UnsupportedOperationException("Use setMark(student, att1, att2, finalExam) for course total marks!");
    }

    public void setMark(Student student, Mark mark) {
        if (!students.contains(student)) {
            throw new IllegalArgumentException("Student is not enrolled in this course!");
        }
        marks.put(student, mark);
        student.getTranscript().addCourseMark(this, mark);
        student.calculateGPA();
    }

    public void setMark(Student student, double attestation1, double attestation2, double finalExam) {
        setMark(student, new Mark(attestation1, attestation2, finalExam));
    }

    public Mark getMark(Student student) {
        return marks.get(student);
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getCredits() {
        return credits;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public List<Assessment> getAssessments() {
        return assessments;
    }

    public GradingPolicy getGradingPolicy() {
        return gradingPolicy;
    }

    public Map<Student, Mark> getMarks() {
        return marks;
    }

    @Override
    public String toString() {
        return code + " - " + name + " (" + credits + " credits)";
    }
}

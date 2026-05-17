import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Course {
    private String courseId;
    private String name;
    private String description;
    private int credits;
    private String major;
    private int yearOfStudy;
    private SemesterType semester;
    private CourseStatus status;
    private List<Teacher> instructions;
    private List<Student> students;
    private List<Lesson> lessons;
    private Map<Student, Mark> marks;

    public Course(String courseId, String name, String description, int credits, String major, int yearOfStudy, SemesterType semester) {
        this.courseId = courseId;
        this.name = name;
        this.description = description;
        this.credits = credits;
        this.major = major;
        this.yearOfStudy = yearOfStudy;
        this.semester = semester;
        this.status = CourseStatus.OPEN;
        this.instructions = new ArrayList<>();
        this.students = new ArrayList<>();
        this.lessons = new ArrayList<>();
        this.marks = new HashMap<>();
    }

    public String getCourseId(){
        return courseId;
    }
    public String getName(){
        return name;
    }
    public String getDescription(){
        return description;
    }
    public int getCredits(){
        return credits; 
    }
    public String getMajor(){ 
        return major;
    }
    public int getYearOfStudy(){
        return yearOfStudy;
    }
    public SemesterType getSemester(){ 
        return semester; 
    }
    public CourseStatus getStatus(){
        return status;
    }
    public List<Teacher> getInstructions(){
        return instructions;
    }
    public List<Student> getStudents(){
        return students;
    }
    public List<Lesson> getLessons(){
        return lessons;
    }

    public void setStatus(CourseStatus status) {
        this.status = status;
     }

    public void addInstructor(Teacher teacher) {
        if (!instructions.contains(teacher)) {
            instructions.add(teacher);
        }
    }

    public void removeInstructor(Teacher teacher) {
        instructions.remove(teacher);
    }

    public void addLesson(Lesson lesson) {
        lessons.add(lesson);
    }


    public String getCode() {
        return courseId;
    }

    public List<Teacher> getTeachers() {
        return instructions; 
    }

    public Teacher getTeacher() {
         return instructions.isEmpty() ? null : instructions.get(0); 
        }

    public void addTeacher(Teacher teacher) { 
        addInstructor(teacher); 
    }

    public void removeTeacher(Teacher teacher) { 
        removeInstructor(teacher);
     }

    public void addStudent(Student student) {
        if (!students.contains(student)) {
            students.add(student);
            if (!student.getRegisteredCourses().contains(this)){
                student.registerCourse(this);
            }
        }
    }

    public void removeStudent(Student student) { 
        students.remove(student); 
    }

    public void setMark(Student student, Mark mark) {
        marks.put(student, mark);
    }

    public Mark getMark(Student student) {
        return marks.get(student);
    }

    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (!(obj instanceof Course)){
            return false;
        }
        return courseId.equals(((Course) obj).courseId);
    }

    public int hashCode() {
        return courseId.hashCode();
    }

    public String toString() {
        return "Course[id=" + courseId + ", name=" + name + ", credits=" + credits + "]";
    }
}

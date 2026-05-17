import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Teacher extends Employee implements Researcher {
    private TeacherTitle title;
    private List<Course> courses;
    private double rating;
    private int hIndex;
    private List<ResearchPaper> researchPapers;
    private List<ResearchProject> researchProjects;

    public Teacher(String id, String username, String password, String firstName, String lastName, String email, double salary, String hireDate, TeacherTitle title) {
        super(id, username, password, firstName, lastName, email, UserRole.TEACHER, salary, hireDate);
        this.title = title;
        this.courses = new ArrayList<>();
        this.rating = 0.0;
        this.hIndex = 0;
        this.researchPapers = new ArrayList<>();
        this.researchProjects = new ArrayList<>();
    }

    public TeacherTitle getTitle(){
        return title;
    }
    public List<Course> getCourses(){
        return courses;
    }
    public double getRating(){
        return rating;
    }

    public boolean isProfessor() {
        return title == TeacherTitle.PROFESSOR;
    }

    public List<Course> viewCourses() {
        return courses;
    }

    public void manageCourse(Course course) {
        System.out.println("Managing: " + course.getName());
    }

    public List<Student> viewStudents(Course course) {
        return course.getStudents();
    }

    public void putMark(Student student, Course course, double attestation1, double attestation2, double finalExam) {
        Mark mark = new Mark(attestation1, attestation2, finalExam);
        course.setMark(student, mark);
        student.getTranscript().addMark(course, mark);
        student.calculateGPA();
    }

    public void updateRating(int newRating) {
        if (rating == 0.0) {
            rating = newRating;
        } else {
            rating = (rating + newRating) / 2.0;
        }
    }

    public void sendComplaint(String text, Admin admin) {
        System.out.println("Complaint sent to admin: " + text);
    }

    public void viewMenu() {
        System.out.println("Teacher menu");
    }

    public int getHIndex(){
        return hIndex;
    }
    public List<ResearchPaper> getResearchPapers(){
        return researchPapers;
    }
    public List<ResearchProject> getResearchProjects(){
        return researchProjects;
    }

    public void addResearchPaper(ResearchPaper paper) {
        if (!researchPapers.contains(paper)) {
            researchPapers.add(paper);
        }
    }

    public void addResearchProject(ResearchProject project) {
        if (!researchProjects.contains(project)) {
            researchProjects.add(project);
        }
    }

    public void joinProject(ResearchProject project) throws NotResearcherException {
        addResearchProject(project);
    }

    public int calculateTotalCitations() {
        int total = 0;
        for (ResearchPaper p : researchPapers) {
            total += p.getCitations();
        }
        return total;
    }

    public void printPapers(Comparator<ResearchPaper> comparator) {
        List<ResearchPaper> sorted = new ArrayList<>(researchPapers);
        Collections.sort(sorted, comparator);
        for (ResearchPaper p : sorted) {
            System.out.println(p);
        }
    }

    public String toString() {
        return "Teacher[id=" + getId() + ", name=" + getFullName() + ", title=" + title + "]";
    }
}

import java.util.ArrayList;
import java.util.List;

public class Teacher extends Employee implements Researcher{
	private TeacherTitle title;
	private List<Course> courses = new ArrayList<>();
	private double rating = 0.0;
	private int hIndex = 0;
	private List<ResearchPaper> researchPapers = new ArrayList<>();
	private List<ResearchProject>  researchProjects = new ArrayList<>();
	
	public Teacher(String id, String username, String password, String firstName, String lastName,
			String email, double salary, String hireDate, TeacherTitle title) {
		super(id, username, password, firstName, lastName, email, salary, hireDate, UserRole.TEACHER);
		this.title = title;
	}
	public TeacherTitle getTitle() {
		return title;
	}
	public List<Course> getCourses() {
		return courses;
	}
	public double getRating() {
		return rating;
	}
	public boolean isProfessor() {
		return title == TeacherTitle.PROFESSOR;
	}
	public void putMark(Student student, Course course, double att1, double att2, double finalExam) {
		Mark mark = new Mark(att1, att2, finalExam);
		student.getTranscript().addMark(course, mark);
	}
	public void uodateRating(int newRating) {
		this.rating = (this.rating * 0/7 + newRating) / 1.3;
	}
	@Override
	public int getHIndex() {
		return hIndex;
	}
	@Override
	public List<ResearchPaper> getResearchPapers() {
		return researchPapers;
	}
	@Override
	public List<ResearchProject> getResearchProjects() {
		return researchProjects;
	}
	@Override
	
	public void addResearchPaper(ResearchPaper paper) {
		researchPapers.add(paper);
	}
	@Override
	public void addResearchProject(ResearchProject project) {
		researchProjects.add(project);
	}
	@Override
	public void joinProject(ResearchProject project) throws NotResearcherException {
		researchProjects.add(project);
		project.addParticipant(this);
	}
	@Override
	public void viewMenu() {
		super.viewMenu();
		System.out.println("3. Manage Courses");
		System.out.println("4. Put Marks");
	}
}

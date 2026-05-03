package project;

import java.util.ArrayList;
import java.util.List;

public class Student extends User implements Researcher{
	private DegreeType degreeType = DegreeType.BACHELOR;
	private String major;
	private int yearOfStudy;
	private int totalCredits = 0;
	private Transcipt transcript = new Transcript(this);
	private List<Course> registeredCourses = new ArrayList<>();
	private List<TeacherRating> teacherRtaings = new ArrayList<>();
	private Researcher supervisor;
	private int hIndex = 0;
	private List<ResearchPaper> researchPapers = new ArrayList<>();
	private List<ResearchProject> researchProjects = new ArrayList<>();
	
	public Student(String id, String username, String password, String firstName, String lastName,
			String email, String major, int yearOfStudy) {
		super(id, username, password, firstName,lastName, email, UserRole.STUDENT);
		this.major = major;
		this.yearOfStudy = yearOfStudy;
	}
	public DegreeType getDegreeType() {
		return degreeType;
	}
	public String getMajor() {
		return major;
	}
	public int getYearOfStudy() {
		return yearOfStudy;
	}
	public double getGpa() {
		return transcript.getGpa();
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
	public List<TeacherRating> getTeacherRatings() {
		return teacherRatings;
	}
	public Researcher getSupervisor() {
		return supervisor;
	}
	
	public void registerForCourse(Course course) throws CourseRegistrationException {
		if (totalCredits + 3 > 21) throw new CourseRegistrationException("Credit limit exceeded");
		registeredCourses.add(course);
		totalCredits += 3;
		course.addStudent(this);
	}
	public void dropCourse(Course course) {
		registeredCourses.remove(course);
		totalCredits -= 3;
	}
	public void viewMark(Course course) {
		transcript.getCourseMarks().get(course);
	}
	public void rateTeacher(Teacher teacher, int rating, String comment) {
		teacherRatings.add(new TeacherRating(teacher, rating, comment));
		teacher.updateRating(rating);
	}
	public void setSupervisor(Researcher supervisor) {
		if (supervisor == null || !(supervisor instanceof Researcher)) {
			throw new InvalidSupervisorException();
		}
		this.supervisor = supervisor;
	}
	public boolean isFourthYear() {
		return yearOfStudy == 4;
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
	public int calculateTotalCitations() {
		return researchPapers.stream().mapToInt(ResearchPaper::getCitations).sum();
	}
	@Override
	public void printPpaers(Comparator<ResearchPaper> comparator) {
		researchPapers.stream().sorted(comparator).forEach(System.out::println);
	}
	@Override
	public void viewMenu() {
		super.viewMenu();
		System.out.println("1. View Courses");
		System.out.println("2. Register Course");
		System.out.println("3. View Transcript");
		System.out.println("4. Rate Teacher"); 
	}
}


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResearchEmployee extends Employee implements Researcher {
	private String position;
	private int hIndex;
	private List<ResearchPaper> researchPapers = new ArrayList<>();
	private List<ResearchProject> researchProjects = new ArrayList<>();
	private Map<Student, ResearchProject> supervisedFourthYearStudents = new HashMap<>();
	
	public ResearchEmployee(String id, String username, String password, String firstName, String lastName,
			String email, double salary, String hireDate, String position, int hIndex) {
		super(id, username, password, firstName, lastName, email, salary, hireDate, UserRole.RESEARCH_EMPLOYEE);
		this.position = position;
		this.hIndex = hIndex;
	}
	public String getPosition() {
		return position;
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
		if (paper == null) {
			throw new IllegalArgumentException("Paper cannot be null");
		}
		if (!researchPapers.contains(paper)) {
			researchPapers.add(paper);
		}
		University.getInstance().addResearchPaper(paper);
		updateHIndex();
	}

	@Override
	public void addResearchProject(ResearchProject project) {
		if (project == null) {
			throw new IllegalArgumentException("Project cannot be null");
		}
		if (!researchProjects.contains(project)) {
			researchProjects.add(project);
		}
		University.getInstance().addResearchProject(project);
	}

	public ResearchPaper publishResearchPaper(String title, LocalDate publicationDate, String journal, String doi,
			String abstractText, List<String> keywords) {
		ResearchPaper paper = new ResearchPaper(title, this, publicationDate, journal, doi, abstractText, keywords, 0);
		addResearchPaper(paper);
		University.getInstance().addLogRecord(
				new LogRecord("PUBLISH_RESEARCH_PAPER", this, this, "Published paper: " + title));
		return paper;
	}

	public void publishPaperForProject(ResearchProject project, ResearchPaper paper) {
		if (project == null || paper == null) {
			throw new IllegalArgumentException("Project and paper are required");
		}
		addResearchProject(project);
		addResearchPaper(paper);
		project.addPublishedPaper(paper);
		University.getInstance().addLogRecord(
				new LogRecord("PUBLISH_PROJECT_PAPER", this, this,
						"Published paper " + paper.getTitle() + " for project " + project.getTitle()));
	}

	public ResearchPaper publishResearchPaperForProject(ResearchProject project, String title,
			LocalDate publicationDate, String journal, String doi, String abstractText, List<String> keywords) {
		ResearchPaper paper = publishResearchPaper(title, publicationDate, journal, doi, abstractText, keywords);
		project.addPublishedPaper(paper);
		addResearchProject(project);
		return paper;
	}

	@Override
	public void joinProject(ResearchProject project) throws NotResearcherException {
		if (project == null) {
			throw new IllegalArgumentException("Project cannot be null");
		}
		addResearchProject(project);
		project.addParticipantAsResearcher(this);
		University.getInstance().addLogRecord(
				new LogRecord("JOIN_RESEARCH_PROJECT", this, this, "Joined project: " + project.getTitle()));
	}

	public void manageResearchProject(ResearchProject project, ResearchProjectStatus status) {
		if (project == null || status == null) {
			throw new IllegalArgumentException("Project and status are required");
		}
		addResearchProject(project);
		if (status == ResearchProjectStatus.ACTIVE) {
			project.startProject();
		} else if (status == ResearchProjectStatus.COMPLETED) {
			project.completeProject();
		} else if (status == ResearchProjectStatus.SUSPENDED) {
			project.suspendProject();
		}
		University.getInstance().addLogRecord(
				new LogRecord("MANAGE_RESEARCH_PROJECT", this, this,
						"Set project " + project.getTitle() + " to " + status));
	}

	@Override
	public int calculateTotalCitations() {
		int total = 0;
		for (ResearchPaper paper : researchPapers) {
			total += paper.getCitations();
		}
		return total;
	}

	@Override
	public void printPapers(Comparator<ResearchPaper> comparator) {
		List<ResearchPaper> sortedPapers = new ArrayList<>(researchPapers);
		if (comparator != null) {
			sortedPapers.sort(comparator);
		}
		for (ResearchPaper paper : sortedPapers) {
			System.out.println(paper);
		}
	}

	public void printPapersByTitle() {
		printPapers(Comparator.comparing(ResearchPaper::getTitle));
	}

	public void printPapersByPublicationDate() {
		printPapers(Comparator.comparing(ResearchPaper::getPublicationDate).reversed());
	}

	public void printPapersByCitations() {
		printPapers(Comparator.comparingInt(ResearchPaper::getCitations).reversed());
	}

	public List<Researcher> viewTopCitedResearchers(List<Researcher> researchers, int limit) {
		List<Researcher> result = new ArrayList<>();
		if (researchers == null) {
			throw new IllegalArgumentException("Researchers list cannot be null");
		}
		result.addAll(researchers);
		result.sort(new Comparator<Researcher>() {
			@Override
			public int compare(Researcher r1, Researcher r2) {
				return r2.calculateTotalCitations() - r1.calculateTotalCitations();
			}
		});
		if (limit < result.size()) {
			return new ArrayList<>(result.subList(0, limit));
		}
		return result;
	}

	public List<Researcher> viewTopCitedResearchers(int limit) {
		List<Researcher> researchers = new ArrayList<>();
		for (User user : University.getInstance().getUsers()) {
			if (user instanceof Researcher) {
				researchers.add((Researcher) user);
			}
		}
		return viewTopCitedResearchers(researchers, limit);
	}

	public void superviseFourthYearStudent(Student student, ResearchProject project) {
		if (student == null || project == null) {
			throw new IllegalArgumentException("Student and project are required");
		}
		addResearchProject(project);
		project.addParticipant(student);
		supervisedFourthYearStudents.put(student, project);
		University.getInstance().addLogRecord(
				new LogRecord("SUPERVISE_FOURTH_YEAR_STUDENT", this, student,
						"Supervising " + student.getFullName() + " in project " + project.getTitle()));
	}

	public Map<Student, ResearchProject> getSupervisedFourthYearStudents() {
		return supervisedFourthYearStudents;
	}

	@Override
	public void viewMenu() {
		super.viewMenu();
		System.out.println("3. Publish research paper");
		System.out.println("4. Publish paper for project");
		System.out.println("5. Print papers sorted");
		System.out.println("6. Join research project");
		System.out.println("7. Manage research project");
		System.out.println("8. View top cited researchers");
		System.out.println("9. Supervise fourth year student");
	}

	@Override
	public String toString() {
		return "ResearchEmployee " + getFullName() + " (" + getId() + "), position=" + position +
				", hIndex=" + getHIndex() + ", citations=" + calculateTotalCitations();
	}

	private void updateHIndex() {
		int newHIndex = 0;
		for (int h = 1; h <= researchPapers.size(); h++) {
			int count = 0;
			for (ResearchPaper paper : researchPapers) {
				if (paper.getCitations() >= h) {
					count++;
				}
			}
			if (count >= h) {
				newHIndex = h;
			}
		}
		hIndex = newHIndex;
	}
}

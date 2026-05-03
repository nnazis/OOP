package project;

import java.util.ArrayList;
import java.util.List;

public class ResearchEmployee extends Employee implements Researcher{
	private String position;
	private int hIndex;
	private List<ResearchPaper> researchPapers = new ArrayList<>();
	private List<ResearchProject> researchProjects = new ArrayList<>();
	
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
	public void printPapers(Comparator<ResearchPaper> comparator) {
		researchPapers.stream().sorted(comparator).forEach(System.out::println);
	}
}

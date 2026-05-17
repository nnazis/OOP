package project;

import java.util.Comparator;

public class ResearchService {
	private University university;
	
	public ResearchService(University university) {
		this.university = university;
	}
	
	public void assignSupervisor(Student student, Researcher supervisor)
			throws InvalidSupervisorException {
		if (supervisor.getHIndex() < 3) {
			throw new InvalidSupervisorException("Supervisor h-index must be at least 3");
		}
		
		student.setSupervisor(supervisor);
	}
	
	public void addResearcherToProject(Researcher researcher, ResearchProject project) {
		try {
			researcher.joinProject(project);
		} catch (NotResearcherException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
	
	public void printAllResearchPapers(Comparator<ResearchPaper> comparator) {
		university.printAllResearchPapers(comparator);
	}
	public Researcher findTopCitedResrarcherBySchool(String schoolName) {
		return university.getTopCitedResearcherBySchool(schoolName)l
	}
	public Researcher findTopCitedResearcherOfYear(int year) {
		return university.getTopCitedResearcherOfYear(year);
	}
}

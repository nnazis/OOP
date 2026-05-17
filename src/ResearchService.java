import java.util.Comparator;

public class ResearchService {
    private University university;

    public ResearchService(University university) {
        this.university = university;
    }

    public void assignSupervisor(Student student, Researcher supervisor)
            throws InvalidSupervisorException {
        student.setSupervisor(supervisor);
    }

    public void addResearcherToProject(Researcher researcher, ResearchProject project) {
        try {
            researcher.joinProject(project);
        } catch (NotResearcherException e) {
            System.out.println(e.getMessage());
        }
    }

    public void printAllResearchPapers(Comparator<ResearchPaper> comparator) {
        university.printAllResearchPapers(comparator);
    }

    public Researcher findTopCitedResearcherBySchool(String schoolName) {
        return university.getTopCitedResearcherBySchool(schoolName);
    }

    public Researcher findTopCitedResearcherOfYear(int year) {
        return university.getTopCitedResearcherOfYear(year);
    }
}


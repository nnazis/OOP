package project;
import java.util.List;

public interface Researcher {
	int getHIndex();
	List<ResearchPaper> getResearchPapers();
	List<ResearchProject> getResearchProjects();
	void addResearchPaper(ResearchPaper paper);
	void addResearchProject(ResearchProject project);
	void joinProject(ResearchProject project) throws NotResearcherException;
	int calculateTotalCitations();
	void printPapers(Comparator<ResearchPaper> comparator);
}

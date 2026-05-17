import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ResearchService {
    private University university;

    public ResearchService(University university) {
        this.university = university;
    }

    public void addResearcherToProject(Researcher researcher, ResearchProject project) throws NotResearcherException {
        if (researcher == null || project == null) {
            throw new IllegalArgumentException("Researcher and project are required");
        }
        researcher.joinProject(project);
    }

    public void printAllResearchPapers(Comparator<ResearchPaper> comparator) {
        List<ResearchPaper> papers = new ArrayList<>(university.getResearchPapers());
        if (comparator != null) {
            papers.sort(comparator);
        }
        for (ResearchPaper paper : papers) {
            System.out.println(paper);
        }
    }

    public Researcher findTopCitedResearcherOfYear(int year) {
        Researcher topResearcher = null;
        int topCitations = -1;
        for (Researcher researcher : getAllResearchers()) {
            int citations = 0;
            for (ResearchPaper paper : researcher.getResearchPapers()) {
                if (paper.getPublicationDate().getYear() == year) {
                    citations += paper.getCitations();
                }
            }
            if (citations > topCitations) {
                topCitations = citations;
                topResearcher = researcher;
            }
        }
        return topResearcher;
    }

    public Researcher findTopCitedResearcherBySchool(String schoolName) {
        Researcher topResearcher = null;
        int topCitations = -1;
        for (Researcher researcher : getAllResearchers()) {
            if (schoolName == null || schoolName.length() == 0 || researcher.toString().contains(schoolName)) {
                int citations = researcher.calculateTotalCitations();
                if (citations > topCitations) {
                    topCitations = citations;
                    topResearcher = researcher;
                }
            }
        }
        return topResearcher;
    }

    private List<Researcher> getAllResearchers() {
        List<Researcher> researchers = new ArrayList<>();
        for (User user : university.getUsers()) {
            if (user instanceof Researcher) {
                researchers.add((Researcher) user);
            }
        }
        return researchers;
    }
}

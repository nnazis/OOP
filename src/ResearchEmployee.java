import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ResearchEmployee extends Employee implements Researcher {
    private String position;
    private int hIndex;
    private List<ResearchPaper> researchPapers;
    private List<ResearchProject> researchProjects;

    public ResearchEmployee(String id, String username, String password, String firstName, String lastName, String email, double salary, String hireDate, String position, int hIndex) {
        super(id, username, password, firstName, lastName, email, UserRole.RESEARCH_EMPLOYEE, salary, hireDate);
        this.position = position;
        this.hIndex = hIndex;
        this.researchPapers = new ArrayList<>();
        this.researchProjects = new ArrayList<>();
    }

    public String getPosition(){
        return position;
    }

    public void viewMenu() {
        System.out.println("ResearchEmployee menu");
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
        if (!researchPapers.contains(paper)){
            researchPapers.add(paper);
        }
    }

    public void addResearchProject(ResearchProject project) {
        if (!researchProjects.contains(project)){
            researchProjects.add(project);
        }
    }

    public void joinProject(ResearchProject project) throws NotResearcherException {
        addResearchProject(project);
    }

    public int calculateTotalCitations() {
        int total = 0;
        for (ResearchPaper p : researchPapers){
            total += p.getCitations();
        }
        return total;
    }

    public void printPapers(Comparator<ResearchPaper> comparator) {
        List<ResearchPaper> sorted = new ArrayList<>(researchPapers);
        Collections.sort(sorted, comparator);
        for (ResearchPaper p : sorted){
            System.out.println(p);
        }
    }

    public String toString() {
        return "ResearchEmployee[id=" + getId() + ", position=" + position + "]";
    }
}

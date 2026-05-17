import java.util.Comparator;

public class ResearchPaperByCitationsComparator implements Comparator<ResearchPaper> {
    public int compare(ResearchPaper p1, ResearchPaper p2) {
        return p2.getCitations() - p1.getCitations(); 
    }
}


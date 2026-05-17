import java.util.Comparator;

public class ResearchPaperByDateComparator implements Comparator<ResearchPaper> {
    public int compare(ResearchPaper p1, ResearchPaper p2) {
        return p1.getPublishedDate().compareTo(p2.getPublishedDate());
    }
}


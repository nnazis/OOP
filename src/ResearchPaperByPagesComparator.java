import java.util.Comparator;

public class ResearchPaperByPagesComparator implements Comparator<ResearchPaper> {
    public int compare(ResearchPaper p1, ResearchPaper p2) {
        return p1.getPages() - p2.getPages();
    }
}


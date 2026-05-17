import java.util.Comparator;

public class ResearchPaperByPagesComparator implements Comparator<ResearchPaper> {
    @Override
    public int compare(ResearchPaper first, ResearchPaper second) {
        return Integer.compare(second.getPages(), first.getPages());
    }
}

import java.util.Comparator;

public class ResearchPaperByCitationsComparator implements Comparator<ResearchPaper> {
    @Override
    public int compare(ResearchPaper first, ResearchPaper second) {
        return Integer.compare(second.getCitations(), first.getCitations());
    }
}

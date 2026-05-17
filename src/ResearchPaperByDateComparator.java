import java.util.Comparator;

public class ResearchPaperByDateComparator implements Comparator<ResearchPaper> {
    @Override
    public int compare(ResearchPaper first, ResearchPaper second) {
        return second.getPublicationDate().compareTo(first.getPublicationDate());
    }
}

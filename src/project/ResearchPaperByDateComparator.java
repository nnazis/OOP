package project;

import java.util.Comparator;

public class ResearchPaperByDateComparator implements Comparator<ResearchPaper>{
	
	@Override
	public int compare(ResearchPaper p1, ResearchPaper p2) {
		return p1.getPublicationDate().compareTo(p2.getPublicationDate());
	}
}

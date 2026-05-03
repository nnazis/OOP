package project;

import java.util.Objects;

public class ResearchPaper {
	private String doi;
	private String title;
	private int pages;
	private String journal;
	private int citations;
	private String abstractText;
	private String publicationDate;
	private String keywords;
	
	public ResearchPaper(String doi, String title, String journal, int pages,
			String publicationDate, int citations, String abstractText, String keywords) {
		this.doi = doi;
		this.title = title;
		this.journal = journal;
		this.pages = pages;
		this.publicationDate = publicationDate;
		this.citations = citations;
		this.abstractText = abstractText;
		this.keywords = keywords;
	}
	public String getDoi() {
		return doi;
	}
	public String getTitle() {
		return title;
	}
	public String getJournal() {
		return journal;
	}
	public int getPages() {
		return pages;
	}
	public String getPublicationDate() {
		return publicationDate;
	}
	public int getCitations() {
		return citations; 
	}
	public String getAbstractText() {
		return abstractText;
	}
	public String getKeywords() {
		return keywords;
	}
	
	public void setCitations(int citations) {
		this.citations = citations;
	}
	
	@Override
	public String toString() {
		return String.format("ResearchPaper[DOI=%s, Title='%s', Journal=%s, Pages=%d, Date=%s, Ciatations=%d]",
				doi, title, journal, pages, publicationDate, citations);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		ResearchPaper that = (ResearchPaper) obj;
		return doi.equals(that.doi);
	}
	@Override
	public int hashCode() {
		return Objects.hash(doi);
	}
}

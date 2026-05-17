
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ResearchPaper implements Serializable {
    private String id;
    private String title;
    private User author;
    private LocalDate publicationDate;
    private String journal;
    private String doi;
    private String abstractText;
    private List<String> keywords;
    private int pages;
    private int citations;

    public ResearchPaper(String title, User author, LocalDate publicationDate) {
        this(title, author, publicationDate, "", "", "", new ArrayList<>(), 0, 0);
    }

    public ResearchPaper(String title, User author, LocalDate publicationDate, int citations) {
        this(title, author, publicationDate, "", "", "", new ArrayList<>(), citations, 0);
    }

    public ResearchPaper(String doi, String title, String journal, int pages, String publicationDate,
            int citations, String abstractText, String keywords) {
        this(title, new User("UNKNOWN", "Unknown Author", ""), LocalDate.parse(publicationDate), journal, doi,
                abstractText, parseKeywords(keywords), citations, pages);
    }

    public ResearchPaper(String title, User author, LocalDate publicationDate, String journal, String doi,
            String abstractText, List<String> keywords, int citations) {
        this(title, author, publicationDate, journal, doi, abstractText, keywords, citations, 0);
    }

    public ResearchPaper(String title, User author, LocalDate publicationDate, String journal, String doi,
            String abstractText, List<String> keywords, int citations, int pages) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        if (author == null) {
            throw new IllegalArgumentException("Author cannot be null");
        }
        if (publicationDate == null) {
            throw new IllegalArgumentException("Publication date cannot be null");
        }
        if (citations < 0) {
            throw new IllegalArgumentException("Citations cannot be negative");
        }
        if (pages < 0) {
            throw new IllegalArgumentException("Pages cannot be negative");
        }
        this.title = title;
        this.author = author;
        this.publicationDate = publicationDate;
        this.journal = journal == null ? "" : journal;
        this.doi = doi == null ? "" : doi;
        this.abstractText = abstractText == null ? "" : abstractText;
        this.keywords = keywords == null ? new ArrayList<>() : new ArrayList<>(keywords);
        this.pages = pages;
        this.citations = citations;
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public User getAuthor() {
        return author;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public String getPublicationDateAsString() {
        return publicationDate.toString();
    }

    public String getJournal() {
        return journal;
    }

    public String getDoi() {
        return doi;
    }

    public String getAbstractText() {
        return abstractText;
    }

    public List<String> getKeywords() {
        return new ArrayList<>(keywords);
    }

    public String getKeywordsAsString() {
        return String.join(", ", keywords);
    }

    public int getPages() {
        return pages;
    }

    public int getCitations() {
        return citations;
    }

    public void setCitations(int citations) {
        if (citations < 0) {
            throw new IllegalArgumentException("Citations cannot be negative");
        }
        this.citations = citations;
    }

    public void addCitation() {
        citations++;
    }

    public void addCitations(int count) {
        if (count < 0) {
            throw new IllegalArgumentException("Citation count cannot be negative");
        }
        citations += count;
    }

    private static List<String> parseKeywords(String keywords) {
        List<String> result = new ArrayList<>();
        if (keywords == null || keywords.length() == 0) {
            return result;
        }
        for (String keyword : keywords.split(",")) {
            String trimmed = keyword.trim();
            if (!trimmed.isEmpty()) {
                result.add(trimmed);
            }
        }
        return result;
    }

    @Override
    public String toString() {
        String source = journal.length() == 0 ? "Unpublished venue" : journal;
        return title + " by " + author.getFullName() + " | " + source + " | " + publicationDate +
                " | pages: " + pages + " | citations: " + citations;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ResearchPaper)) {
            return false;
        }
        ResearchPaper other = (ResearchPaper) obj;
        if (doi != null && other.doi != null && doi.length() > 0 && other.doi.length() > 0) {
            return doi.equals(other.doi);
        }
        return id.equals(other.id);
    }

    @Override
    public int hashCode() {
        if (doi != null && doi.length() > 0) {
            return doi.hashCode();
        }
        return id.hashCode();
    }
}

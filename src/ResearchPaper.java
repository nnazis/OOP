
import java.io.Serializable;
import java.time.LocalDate;

public class ResearchPaper implements Serializable {
    private String title;
    private User author;
    private LocalDate publicationDate;
    private int citations;

    public ResearchPaper(String title, User author, LocalDate publicationDate) {
        this.title = title;
        this.author = author;
        this.publicationDate = publicationDate;
        this.citations = 0;
    }

    public ResearchPaper(String title, User author, LocalDate publicationDate, int citations) {
        this.title = title;
        this.author = author;
        this.publicationDate = publicationDate;
        this.citations = citations;
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

    public int getCitations() {
        return citations;
    }
}

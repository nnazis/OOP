public class ResearchPaper {
    private String paperId;
    private String title;
    private int pages;
    private int citations;
    private String publishedDate;
    private String author;

    public ResearchPaper(String paperId, String title, int pages, int citations, String publishedDate, String author) {
        this.paperId =paperId;
        this.title= title;
        this.pages =pages;
        this.citations = citations;
        this.publishedDate = publishedDate;
        this.author= author;
    }

    public String getPaperId(){
        return paperId;
    }
    public String getTitle(){
        return title;
    }
    public int getPages(){
        return pages;
    }
    public int getCitations(){
        return citations;
    }
    public String getPublishedDate(){
        return publishedDate;
    }
    public String getAuthor(){
        return author;
    }

    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (!(obj instanceof ResearchPaper)){
            return false;
        }
        return paperId.equals(((ResearchPaper) obj).paperId);
    }

    public int hashCode() {
        return paperId.hashCode();
    }

    public String toString() {
        return "ResearchPaper[title=" + title + ", citations=" + citations + "]";
    }
}

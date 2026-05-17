import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class News implements Serializable {

    private String newsId;
    private String title;
    private String content;
    private NewsCategory category;
    private String publishedAt;
    private String author;

    public News(String title, String content, NewsCategory category, String author) {
        this.newsId = UUID.randomUUID().toString();
        this.title = title;
        this.content = content;
        this.category = category;
        this.author = author;
        this.publishedAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    public News(String newsId, String title, String content, NewsCategory category, String publishedAt, String author) {
        this.newsId = newsId;
        this.title = title;
        this.content = content;
        this.category = category;
        this.publishedAt = publishedAt;
        this.author = author;
    }

    public String getNewsId(){ 
        return newsId;
    }
    public String getTitle(){ 
        return title;
    }
    public String getContent(){ 
        return content;
    }
    public NewsCategory getCategory(){ 
        return category;
    }
    public String getPublishedAt(){
        return publishedAt;
    }
    public String getAuthor(){ 
        return author; 
    }

    @Override
    public String toString() {
        return "\n"+ "[" + category + "] " + title + "\n" + "By: " + author + "  |  " + publishedAt + "\n"+ "──────────────────────────────────────────\n" + content + "\n";
    }
}

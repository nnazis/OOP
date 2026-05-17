public class News {
    private String newsId;
    private String title;
    private String content;
    private NewsCategory category;
    private String publishedAt;
    private Manager author;

    public News(String newsId, String title, String content, NewsCategory category, String publishedAt, Manager author) {
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
    public Manager getAuthor(){
        return author;
    }

    public String toString() {
        return "News[title=" + title + ", category=" + category + "]";
    }
}

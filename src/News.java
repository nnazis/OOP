
import java.io.Serializable;
import java.time.LocalDate;

public class News implements Serializable {
    private String title;
    private String text;
    private LocalDate date;

    public News(String title, String text) {
        this.title = title;
        this.text = text;
        this.date = LocalDate.now();
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public LocalDate getDate() {
        return date;
    }
}

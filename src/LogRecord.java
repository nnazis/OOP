
import java.io.Serializable;
import java.time.LocalDateTime;

public class LogRecord implements Serializable {
    private String action;
    private User user;
    private String description;
    private LocalDateTime createdAt;

    public LogRecord(String action, User user, String description) {
        this.action = action;
        this.user = user;
        this.description = description;
        this.createdAt = LocalDateTime.now();
    }

    public String getAction() {
        return action;
    }

    public User getUser() {
        return user;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return createdAt + " " + action + " by " + user + ": " + description;
    }
}

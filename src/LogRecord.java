
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class LogRecord implements Serializable {
    private String logId;
    private String action;
    private User user;
    private User targetUser;
    private String description;
    private LocalDateTime createdAt;

    public LogRecord(String action, User user, String description) {
        this(action, user, (User) null, description);
    }

    public LogRecord(String action, User user, User targetUser, String description) {
        this.logId = UUID.randomUUID().toString();
        this.action = action;
        this.user = user;
        this.targetUser = targetUser;
        this.description = description;
        this.createdAt = LocalDateTime.now();
    }

    public LogRecord(String logId, User user, String action, String timestamp) {
        this.logId = logId;
        this.user = user;
        this.action = action;
        this.description = action;
        this.createdAt = parseTimestamp(timestamp);
    }

    public String getLogId() {
        return logId;
    }

    public String getAction() {
        return action;
    }

    public User getUser() {
        return user;
    }

    public User getTargetUser() {
        return targetUser;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getTimestamp() {
        return createdAt.toString();
    }

    @Override
    public String toString() {
        String target = targetUser == null ? "" : " target=" + targetUser;
        return logId + " | " + createdAt + " " + action + " by " + user + target + ": " + description;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LogRecord)) {
            return false;
        }
        LogRecord other = (LogRecord) obj;
        return logId.equals(other.logId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(logId);
    }

    private LocalDateTime parseTimestamp(String timestamp) {
        if (timestamp == null || timestamp.isBlank()) {
            return LocalDateTime.now();
        }
        try {
            return LocalDateTime.parse(timestamp);
        } catch (Exception exception) {
            return LocalDateTime.now();
        }
    }
}

public class LogRecord {
    private String logId;
    private User user;
    private String action;
    private String timestamp;

    public LogRecord(String logId, User user, String action, String timestamp) {
        this.logId = logId;
        this.user = user;
        this.action = action;
        this.timestamp = timestamp;
    }

    public LogRecord(String logId, User user, String action) {
        this(logId, user, action, "");
    }

    public String getLogId(){
        return logId;
    }
    public User getUser(){
        return user;
    }
    public String getAction(){
        return action;
    }
    public String getTimeStamp(){
        return timestamp;
    }

    public String toString() {
        return "LogRecord[user=" + user.getFullName() + ", action=" + action + "]";
    }
}

import java.io.Serializable;

public class Complaint implements Serializable {
    private String complaintId;
    private Teacher sender;
    private Admin receiver;
    private String text;
    private String createdAt;

    public Complaint(String complaintId, Teacher sender, Admin receiver, String text, String createdAt) {
        if (sender == null || receiver == null) {
            throw new IllegalArgumentException("Sender and receiver are required");
        }
        if (text == null || text.isBlank()) {
            throw new IllegalArgumentException("Complaint text cannot be empty");
        }
        this.complaintId = complaintId;
        this.sender = sender;
        this.receiver = receiver;
        this.text = text;
        this.createdAt = createdAt;
    }

    public String getComplaintId() {
        return complaintId;
    }

    public Teacher getSender() {
        return sender;
    }

    public Admin getReceiver() {
        return receiver;
    }

    public String getText() {
        return text;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return complaintId + " | " + sender + " -> " + receiver + " | " + createdAt + " | " + text;
    }
}

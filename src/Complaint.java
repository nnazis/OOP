public class Complaint {
    private String complaintId;
    private Teacher sender;
    private Admin receiver;
    private String text;
    private String createdAt;

    public Complaint(String complaintId, Teacher sender, Admin receiver, String text, String createdAt) {
        this.complaintId = complaintId;
        this.sender = sender;
        this.receiver = receiver;
        this.text = text;
        this.createdAt = createdAt;
    }

    public String getComplaintId() { 
        return complaintId;
    }
    public Teacher getSender(){ 
        return sender;
    }
    public Admin getReceiver() {
        return receiver;
    }
    public String getText(){ 
        return text;
    }
    public String getCreatedAt(){
        return createdAt;
    }

    public String toString() {
        return "Complaint[from=" + sender.getFullName() + ", text=" + text + "]";
    }
}


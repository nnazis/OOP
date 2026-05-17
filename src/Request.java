public class Request {
    private String requestId;
    private Employee sender;
    private String title;
    private String text;
    private RequestStatus status;
    private Manager signedBy;
    private String createdDate;

    public Request(String requestId, Employee sender, String title, String text) {
        this.requestId = requestId;
        this.sender = sender;
        this.title = title;
        this.text = text;
        this.status = RequestStatus.NEW;
        this.createdDate = "2026-01-01";
    }

    public String getRequestId(){
        return requestId;
    }
    public Employee getSender(){
        return sender;
    }
    public String getTitle(){
        return title;
    }
    public String getText(){
        return text;
    }
    public void setStatus(RequestStatus status) { this.status = status; }

    public RequestStatus getStatus(){
        return status;
    }
    public Manager getSignedBy(){
        return signedBy;
    }
    public String getCreatedDate(){
        return createdDate;
    }

    public void sign(Manager manager) {
        this.signedBy = manager;
    }

    public void approve() {
        this.status = RequestStatus.APPROVED;
    }

    public void reject() {
        this.status = RequestStatus.REJECTED;
    }

    public String toString() {
        return "Request[id=" + requestId + ", title=" + title + ", status=" + status + "]";
    }
}

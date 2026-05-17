
import java.io.Serializable;

public class Request implements Serializable {
    private String text;
    private RequestStatus status;

    public Request(String text) {
        this.text = text;
        this.status = RequestStatus.NEW;
    }

    public String getText() {
        return text;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }
}

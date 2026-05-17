
import java.io.Serializable;

public class Message implements Serializable {
    private String id;
    private Employee sender;
    private Employee receiver;
    private String text;

    public Message(String id, Employee sender, Employee receiver, String text) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public Employee getSender() {
        return sender;
    }

    public Employee getReceiver() {
        return receiver;
    }

    public String getText() {
        return text;
    }
}

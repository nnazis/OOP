public class Message {
    private String messageId;
    private Employee sender;
    private Employee receiver;
    private String text;
    private String sentAt;
    private boolean isRead;

    public Message(String messageId, Employee sender, Employee receiver, String text, String sentAt) {
        this.messageId = messageId;
        this.sender = sender;
        this.receiver = receiver;
        this.text = text;
        this.sentAt = sentAt;
        this.isRead = false;
    }

    public String getMessageId(){
        return messageId; 
    }
    public Employee getSender(){
        return sender; 
    }
    public Employee getReceiver(){
        return receiver;
    }
    public String getText(){
        return text; 
    }
    public String getSentAt()   
      { 
        return sentAt;
     }
    public boolean isRead(){
        return isRead;
       }

    public void markAsRead() {
        isRead = true;
    }

    public String toString() {
        return "Message[from=" + sender.getFullName() + ", to=" + receiver.getFullName() + "]";
    }
}

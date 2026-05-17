import java.util.ArrayList;
import java.util.List;

public class Employee extends User {
    private double salary;
    private String hireDate;
    private List<Message> messages;
    private List<Request> requests;

    public Employee(String id, String username, String password, String firstName, String lastName, String email, UserRole role, double salary, String hireDate) {
        super(id, username, password, firstName, lastName, email, role);
        this.salary = salary;
        this.hireDate = hireDate;
        this.messages = new ArrayList<>();
        this.requests = new ArrayList<>();
    }

    public double getSalary() {
        return salary;
    }
    public String getHireDate(){
        return hireDate; 
    }
    public List<Message> getMessages(){
        return messages;
    }
    public List<Request> getRequests(){
        return requests; 
    }

    public void sendMessage(Employee receiver, String text) {
        Message msg = new Message("msg-" + System.currentTimeMillis(), this, receiver, text, "2026-01-01");
        messages.add(msg);
        receiver.getMessages().add(msg);
    }

    public void submitRequest(Request request) {
        requests.add(request);
    }

    public void viewMenu() {
        System.out.println("Employee menu");
    }

    public String toString() {
        return "Employee[id=" + getId() + ", name=" + getFullName() + "]";
    }
}

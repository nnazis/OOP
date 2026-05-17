package project;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Employee extends User {
	protected double salary;
	protected String hireDate;
	protected List<Message> messages = new ArrayList<>();
	protected List<Request> requests = new ArrayList<>();
	
	public Employee(String id, String username, String password, String firstName, String lastName, 
			String email, double salary, String hireDate, UserRole role) {
		super(id, username, password, firstName, lastName, email, role);
		this.salary = salary;
		this.hireDate = hireDate;
	}
	public double getSalary() {
		return salary;
	}
	public String getHireDate() {
		return hireDate;
	}
	public List<Message> getMessages() {
		return messages;
	}
	public List<Request> getRequests() {
		return requests;
	}
	public void sendMessages(Employee receiver, String text) {
		Message msg = new Message(UUID.randomUUID().toString(), this, receiver, text);
		messages.add(msg);
		receiver.messages.add(msg);
	}
	public void submitRequest(Request request) {
		requests.add(request);
	}
	@Override
	public void viewMenu() {
		super.viewMenu();
		System.out.println("1. View Message");
		System.out.println("2. Send Message");
	}
}

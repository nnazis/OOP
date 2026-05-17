package project;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializationManager {
	private String filePath;
	
	public SerializationManager(String filePath) {
		this.filePath = filePath;
	}
	
	public void saveUniversity(University university) {
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath))) {
			out.writeObject(university);
			System.out.println("University saved successfully");
		} catch (IOException e) {
			System.out.println("Save error: " + e.getMessage());
		}
	}
	
	public University loadUniversity() {
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath))) {
			return (University) in.readObject();
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Load error: " + e.getMessage());
			return null;
		}
	}
}

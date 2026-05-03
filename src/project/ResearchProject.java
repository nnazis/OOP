package project;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ResearchProject {
	private String projectId;
	private String topic;
	private String description;
	private String startDate;
	private String endDate;
	private List<Researcher> participants = new ArrayList<>();
	
	public ResearchProject(String projectId, String topic, String description,
			String startDate, String endDate) {
		this.projectId = projectId;
		this.topic = topic;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public String getProjectId() {
		return projectId;
	}
	public String getTopic() {
		return topic;
	}
	public String getDescription() {
		return description;
	}
	public String getStartDate() {
		return startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	
	public void addParticipant(Researcher participant) {
		participants.add(participant);
	}
	public List<Researcher> getParticipants() {
		return new ArrayList<>(participants);
	}
	@Override
	public String toString() {
		return "ResearchProject[" + projectId + "] " + topic + " (" + startDate + " - " + endDate + ")";
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		ResearchProject that = (ResearchProject) obj;
		return projectId.equals(that.projectId);
	}
	@Override
	public int hashCode() {
		return Objects.hash(projectId);
	}
}

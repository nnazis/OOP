
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ResearchProject implements Serializable {
    private String id;
    private String title;
    private String description;
    private User supervisor;
    private ResearchProjectStatus status;
    private LocalDate startDate;
    private LocalDate endDate;
    private double budget;
    private List<User> participants;
    private List<ResearchPaper> publishedPapers;

    public ResearchProject(String title) {
        this(title, "", null, LocalDate.now(), null, 0);
    }

    public ResearchProject(String projectId, String topic, String description, String startDate, String endDate) {
        this(topic, description, null, LocalDate.parse(startDate),
                endDate == null || endDate.isBlank() ? null : LocalDate.parse(endDate), 0);
        this.id = projectId;
    }

    public ResearchProject(String title, String description, User supervisor, LocalDate startDate,
            LocalDate endDate, double budget) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Project topic cannot be empty");
        }
        if (budget < 0) {
            throw new IllegalArgumentException("Budget cannot be negative");
        }
        this.title = title;
        this.description = description == null ? "" : description;
        this.supervisor = supervisor;
        this.startDate = startDate;
        this.endDate = endDate;
        this.budget = budget;
        this.status = ResearchProjectStatus.PLANNED;
        this.participants = new ArrayList<>();
        this.publishedPapers = new ArrayList<>();
        this.id = UUID.randomUUID().toString();
        if (supervisor != null) {
            addParticipant(supervisor);
        }
    }

    public String getId() {
        return id;
    }

    public String getProjectId() {
        return id;
    }

    public void addParticipant(User user) {
        if (user != null && !participants.contains(user)) {
            participants.add(user);
        }
    }

    public void addResearcher(Researcher researcher) throws NotResearcherException {
        if (researcher == null) {
            throw new NotResearcherException();
        }
        if (researcher instanceof User) {
            addParticipant((User) researcher);
        }
    }

    public void addParticipantAsResearcher(User user) throws NotResearcherException {
        if (!(user instanceof Researcher)) {
            throw new NotResearcherException();
        }
        addParticipant(user);
    }

    public void removeParticipant(User user) {
        participants.remove(user);
    }

    public boolean hasParticipant(User user) {
        return participants.contains(user);
    }

    public void startProject() {
        this.status = ResearchProjectStatus.ACTIVE;
        if (startDate == null) {
            startDate = LocalDate.now();
        }
    }

    public void completeProject() {
        this.status = ResearchProjectStatus.COMPLETED;
        this.endDate = LocalDate.now();
    }

    public void suspendProject() {
        this.status = ResearchProjectStatus.SUSPENDED;
    }

    public void updateDetails(String description, double budget, LocalDate endDate) {
        if (budget < 0) {
            throw new IllegalArgumentException("Budget cannot be negative");
        }
        this.description = description == null ? "" : description;
        this.budget = budget;
        this.endDate = endDate;
    }

    public void addPublishedPaper(ResearchPaper paper) {
        if (paper == null) {
            throw new IllegalArgumentException("Paper cannot be null");
        }
        if (!publishedPapers.contains(paper)) {
            publishedPapers.add(paper);
        }
    }

    public String getTitle() {
        return title;
    }

    public String getTopic() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public User getSupervisor() {
        return supervisor;
    }

    public ResearchProjectStatus getStatus() {
        return status;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public double getBudget() {
        return budget;
    }

    public List<User> getParticipants() {
        return participants;
    }

    public List<ResearchPaper> getPublishedPapers() {
        return publishedPapers;
    }

    public void printParticipants() {
        System.out.println("Participants of project: " + title);
        for (User user : participants) {
            System.out.println(user);
        }
    }

    public void printPublishedPapers() {
        System.out.println("Published papers of project: " + title);
        for (ResearchPaper paper : publishedPapers) {
            System.out.println(paper);
        }
    }

    @Override
    public String toString() {
        return id + " | " + title + " | " + status + " | participants: " + participants.size()
                + " | papers: " + publishedPapers.size();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ResearchProject)) {
            return false;
        }
        ResearchProject other = (ResearchProject) obj;
        return id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}

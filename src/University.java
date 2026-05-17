
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class University implements Serializable {
    private static University instance;

    private List<User> users;
    private List<Course> courses;
    private List<News> news;
    private List<ResearchProject> researchProjects;
    private List<ResearchPaper> researchPapers;
    private List<LogRecord> logRecords;
    private SystemSettings systemSettings;

    public University() {
        this.users = new ArrayList<>();
        this.courses = new ArrayList<>();
        this.news = new ArrayList<>();
        this.researchProjects = new ArrayList<>();
        this.researchPapers = new ArrayList<>();
        this.logRecords = new ArrayList<>();
        this.systemSettings = new SystemSettings();
    }

    public static University getInstance() {
        if (instance == null) {
            instance = new University();
        }
        return instance;
    }

    public static void setInstance(University university) {
        if (university == null) {
            throw new IllegalArgumentException("University cannot be null");
        }
        instance = university;
    }

    public void addUser(User user) {
        if (!users.contains(user)) {
            users.add(user);
        }
    }

    public void removeUser(User user) {
        users.remove(user);
    }

    public void addCourse(Course course) {
        if (!courses.contains(course)) {
            courses.add(course);
        }
    }

    public void addNews(News newsItem) {
        if (!news.contains(newsItem)) {
            news.add(newsItem);
        }
    }

    public void addResearchProject(ResearchProject researchProject) {
        if (!researchProjects.contains(researchProject)) {
            researchProjects.add(researchProject);
        }
    }

    public void addResearchPaper(ResearchPaper researchPaper) {
        if (!researchPapers.contains(researchPaper)) {
            researchPapers.add(researchPaper);
        }
    }

    public void addLogRecord(LogRecord logRecord) {
        logRecords.add(logRecord);
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public List<News> getNews() {
        return news;
    }

    public List<ResearchProject> getResearchProjects() {
        return researchProjects;
    }

    public List<ResearchPaper> getResearchPapers() {
        return researchPapers;
    }

    public List<LogRecord> getLogRecords() {
        return logRecords;
    }

    public SystemSettings getSystemSettings() {
        return systemSettings;
    }

    public List<Researcher> getAllResearchers() {
        List<Researcher> researchers = new ArrayList<>();
        for (User user : users) {
            if (user instanceof Researcher) {
                researchers.add((Researcher) user);
            }
        }
        return researchers;
    }

    public void printAllResearchPapers(Comparator<ResearchPaper> comparator) {
        Comparator<ResearchPaper> sorting = comparator == null
                ? new ResearchPaperByDateComparator()
                : comparator;
        researchPapers.stream().sorted(sorting).forEach(System.out::println);
    }

    public Researcher getTopCitedResearcherOfYear(int year) {
        return new ResearchService(this).findTopCitedResearcherOfYear(year);
    }

    public Researcher getTopCitedResearcherBySchool(String schoolName) {
        return new ResearchService(this).findTopCitedResearcherBySchool(schoolName);
    }
}

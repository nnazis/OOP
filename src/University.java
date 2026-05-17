
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class University implements Serializable {
    private static University instance;

    private List<User> users;
    private List<Course> courses;
    private List<News> news;
    private List<ResearchProject> researchProjects;
    private List<ResearchPaper> researchPapers;

    public University() {
        this.users = new ArrayList<>();
        this.courses = new ArrayList<>();
        this.news = new ArrayList<>();
        this.researchProjects = new ArrayList<>();
        this.researchPapers = new ArrayList<>();
    }

    public static University getInstance() {
        if (instance == null) {
            instance = new University();
        }
        return instance;
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
}

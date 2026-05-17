import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class University {
    private static University instance;

    private String name;
    private List<User> users;
    private List<Course> courses;
    private List<News> news;
    private List<ResearchProject> researchProjects;
    private List<ResearchPaper> researchPapers;
    private List<LogRecord> logs;
    private SystemSettings systemSettings;

    public University(String name) {
        this.name = name;
        this.users = new ArrayList<>();
        this.courses = new ArrayList<>();
        this.news = new ArrayList<>();
        this.researchProjects = new ArrayList<>();
        this.researchPapers = new ArrayList<>();
        this.logs = new ArrayList<>();
        this.systemSettings = new SystemSettings();
    }

    public static University getInstance() {
        if (instance == null) instance = new University("University");
        return instance;
    }

    public String getName() {
        return name;
    }
    public List<User> getUsers() {
        return users;
    }
    public List<Course> getCourses(){
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
    public List<LogRecord> getLogs() {
        return logs; 
    }
    public SystemSettings getSystemSettings() {
        return systemSettings;
    }

    public void addUser(User user) {
        if (!users.contains(user)){
            users.add(user);}
         }
    public void removeUser(User user) { 
        users.remove(user);
    }
    public void addCourse(Course course) {
        if (!courses.contains(course)){
            courses.add(course); }
    }
    public void addNews(News n) {
        news.add(n);
    }
    public void addResearchProject(ResearchProject p) {
        if (!researchProjects.contains(p)){
            researchProjects.add(p);
        }
    }
    public void addResearchPaper(ResearchPaper p) {
        {
            if (!researchPapers.contains(p)) researchPapers.add(p);
        }
    }
    public void addLogRecord(LogRecord r) { 
        logs.add(r); 
    }

    public List<Researcher> getAllResearchers() {
        List<Researcher> list = new ArrayList<>();
        for (User u : users){
            if (u instanceof Researcher){
                list.add((Researcher) u);
            }
        }
        return list;
    }

    public void printAllResearchPapers(Comparator<ResearchPaper> comparator) {
        List<ResearchPaper> sorted = new ArrayList<>(researchPapers);
        Collections.sort(sorted, comparator);
        for (ResearchPaper p : sorted){
            System.out.println(p);
        }
    }

    public Researcher getTopCitedResearcherBySchool(String schoolName) {
        Researcher top = null; int max = -1;
        for (Researcher r : getAllResearchers()) {
            int c = r.calculateTotalCitations();
            if (c > max) { 
                max = c; top = r; 
            }
        }
        return top;
    }

    public Researcher getTopCitedResearcherOfYear(int year) {
        return getTopCitedResearcherBySchool(null);
    }

    public User authenticate(String username, String password) throws AuthenticationException {
        for (User u : users) {
            if (u.getUsername().equals(username)) {
                if (u.getPassword().equals(password)) {
                     u.login(username, password); return u; 
                    }
                throw new AuthenticationException("Wrong password");
            }
        }
        throw new AuthenticationException("User not found: " + username);
    }

    public String toString() { return "University[name=" + name + ", users=" + users.size() + "]"; }
}

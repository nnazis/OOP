import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Student extends User implements Researcher {

    private DegreeType degreeType;
    private String major;
    private int yearOfStudy;
    private double gpa;
    private int totalCredits;
    private Transcript transcript;
    private List<Course> registeredCourses;
    private List<TeacherRating> teacherRatings;
    private Researcher supervisor;

    private int hIndex;
    private List<ResearchPaper> researchPapers;
    private List<ResearchProject> researchProjects;

    public Student(String id, String username, String password, String firstName, String lastName, String email, String major, int yearOfStudy) {
        super(id, username, password, firstName, lastName, email, UserRole.STUDENT);
        this.major = major;
        this.yearOfStudy = yearOfStudy;
        this.degreeType = DegreeType.BACHELOR;
        this.gpa = 0.0;
        this.totalCredits = 0;
        this.transcript = new Transcript(this);
        this.registeredCourses = new ArrayList<>();
        this.teacherRatings = new ArrayList<>();
        this.hIndex = 0;
        this.researchPapers = new ArrayList<>();
        this.researchProjects = new ArrayList<>();
    }

    public DegreeType getDegreeType(){
        return degreeType;
    }
    public String getMajor(){
        return major;
    }
    public int getYearOfStudy()
    { return yearOfStudy;

    }
    public double getGpa()
    { return gpa;

    }
    public int getTotalCredits() {
        return totalCredits;
    }
    public Transcript getTranscript()
    { return transcript;

    }
    public List<Course> getRegisteredCourses()
    { return registeredCourses; 

    }
    public List<TeacherRating> getTeacherRatings()
    { return teacherRatings;

    }
    public Researcher getSupervisor() 
    { return supervisor; 

    }

    public void setDegreeType(DegreeType degreeType) 
    { this.degreeType  = degreeType;

    }
    public void setMajor(String major)
    { this.major       = major; 

    }
    public void setYearOfStudy(int yearOfStudy)
    { this.yearOfStudy = yearOfStudy; 

    }

   
    public void registerCourse(Course course) {
        if (!registeredCourses.contains(course)) {
            registeredCourses.add(course);
            recalculateTotalCredits();
        }
    }

    
    public void registerForCourse(Course course) throws CourseRegistrationException {
        if (course == null) {
            throw new CourseRegistrationException("Course cannot be null");
        }
        if (registeredCourses.contains(course)) {
            throw new CourseRegistrationException("Already registered for: " + course.getName());
        }
        SystemSettings s = University.getInstance().getSystemSettings();
        if (!s.isRegistrationOpen()) {
            throw new CourseRegistrationException("Registration is currently closed");
        }
        if (totalCredits + course.getCredits() > s.getMaxCreditsPerSemester()) {
            throw new CourseRegistrationException(
                "Would exceed max credits (" + s.getMaxCreditsPerSemester() + ")");
        }
        course.addStudent(this); 
        System.out.println(getFullName() + " registered for: " + course.getName());
    }

   
    public void dropCourse(Course course) {
        if (!registeredCourses.contains(course)) {
            return; 
        }
        registeredCourses.remove(course);
        recalculateTotalCredits();
        if (course.getStudents().contains(this)) {
            course.removeStudent(this);
        }
        System.out.println(getFullName() + " dropped: " + course.getName());
    }

    public List<Course> viewCourses() {
        return registeredCourses;
    }

    public Mark viewMark(Course course) {
        return transcript.getCourseMarks().get(course);
    }

    public void rateTeacher(Teacher teacher, int rating, String comment) {
        if (teacher == null) {
            System.out.println("Teacher cannot be null.");
            return;
        }
        if (rating < 1 || rating > 5) {
            System.out.println("Rating must be between 1 and 5.");
            return;
        }

        boolean isMyTeacher = false;
        for (Course course : registeredCourses) {
            if (course.getTeachers().contains(teacher) || teacher.equals(course.getTeacher())) {
                isMyTeacher = true;
                break;
            }
        }
        if (!isMyTeacher) {
            System.out.println("You can only rate teachers of your own courses. " + teacher.getFullName() + " does not teach your courses.");
            return;
        }

        for (TeacherRating tr : teacherRatings) {
            if (tr.getTeacher().equals(teacher)) {
                System.out.println("You already rated " + teacher.getFullName() + ".");
                return;
            }
        }

        teacherRatings.add(new TeacherRating(this, teacher, rating, comment));
        System.out.println("Rated " + teacher.getFullName() + ": " + rating + "/5");
    }

    public void setSupervisor(Researcher supervisor) throws InvalidSupervisorException {
        if (!isFourthYear()) {
            throw new InvalidSupervisorException("Only 4th year students can have a research supervisor. Your year: " + yearOfStudy);
        }
        this.supervisor = supervisor;
        System.out.println("Supervisor set: " + ((User) supervisor).getFullName());
    }

    public boolean isFourthYear() {
        return yearOfStudy == 4;
    }

    public double calculateGPA() {
        this.gpa = transcript.calculateGpa();
        return gpa;
    }

    private void recalculateTotalCredits() {
        int credits = 0;
        for (Course course : registeredCourses) {
            credits += course.getCredits();
        }
        this.totalCredits = credits;
    }

    @Override
    public void viewMenu() {
        System.out.println("=== STUDENT MENU: " + getFullName() + " ===");
        System.out.println("1. View courses");
        System.out.println("2. Register for course");
        System.out.println("3. Drop course");
        System.out.println("4. View marks");
        System.out.println("5. Rate teacher");
        System.out.println("6. View GPA");
        System.out.println("7. Research papers");
        System.out.println("8. Set supervisor");
        System.out.println("0. Logout");
    }

    @Override
    public int getHIndex() {
        return hIndex;
    }

    @Override
    public List<ResearchPaper> getResearchPapers() {
        return researchPapers;
    }

    @Override
    public List<ResearchProject> getResearchProjects() {
        return researchProjects;
    }

    @Override
    public void addResearchPaper(ResearchPaper paper) {
        if (paper == null){
            throw new IllegalArgumentException("Paper cannot be null");
        }
        if (!researchPapers.contains(paper)){
            researchPapers.add(paper);
        }
        University.getInstance().addResearchPaper(paper);
        updateHIndex();
    }

    @Override
    public void addResearchProject(ResearchProject project) {
        if (project == null){
            throw new IllegalArgumentException("Project cannot be null");
        }
        if (!researchProjects.contains(project)){
            researchProjects.add(project);
        }
        University.getInstance().addResearchProject(project);
    }


    @Override
    public void joinProject(ResearchProject project) throws NotResearcherException {
        if (project == null){
            throw new IllegalArgumentException("Project cannot be null");
        }
        boolean canJoin = (degreeType == DegreeType.MASTER || degreeType == DegreeType.PHD) || yearOfStudy >= 3;
        if (!canJoin) {
            throw new NotResearcherException("Only 3rd/4th year or graduate students can join. Year: " + yearOfStudy);
        }
        addResearchProject(project);
        project.addParticipant(this);
        University.getInstance().addLogRecord(new LogRecord("JOIN_RESEARCH_PROJECT", this, "Joined project: " + project.getTitle()));
    }

    @Override
    public int calculateTotalCitations() {
        int total = 0;
        for (ResearchPaper p : researchPapers){
            total += p.getCitations();
        }
        return total;
    }

    @Override
    public void printPapers(Comparator<ResearchPaper> comparator) {
        List<ResearchPaper> sorted = new ArrayList<>(researchPapers);
        if (comparator != null){
            sorted.sort(comparator);
        }
        System.out.println("--- Papers of " + getFullName() + " ---");
        for (ResearchPaper p : sorted){
            System.out.println(p);
        }
    }

    private void updateHIndex() {
        int newH = 0;
        for (int h = 1; h <= researchPapers.size(); h++) {
            int count = 0;
            for (ResearchPaper p : researchPapers) {
                if (p.getCitations() >= h)
                    {
                        count++;
                    }
            }
            if (count >= h){
                newH = h;
            }
        }
        this.hIndex = newH;
    }

    @Override
    public String toString() {
        return "Student " + getFullName() + " (" + getId() + ")" + " year=" + yearOfStudy + " GPA=" + String.format("%.2f", gpa);
    }
}

import java.util.ArrayList;
import java.util.List;

public class Manager extends Employee implements ReportGenerator {

    private ManagerType managerType;

    public Manager(String id, String username, String password, String firstName, String lastName, String email, double salary, String hireDate, ManagerType managerType) {
        super(id, username, password, firstName, lastName, email, UserRole.MANAGER, salary, hireDate);
        this.managerType = managerType;
    }

    public ManagerType getManagerType() { return managerType; }

    public void approveRegistration(Student student, Course course) {
        if (!course.getStudents().contains(student)) {
            course.addStudent(student);
        }
        System.out.println("Approved: " + student.getFullName() + " -> " + course.getName());
        University.getInstance().addLogRecord(new LogRecord("APPROVE_REGISTRATION", this,"Approved registration for " + student.getFullName() + " in " + course.getName()));
    }

    public void rejectRegistration(Student student, Course course) {
        if (course.getStudents().contains(student)) {
            course.removeStudent(student);
        }
        System.out.println("Rejected: " + student.getFullName() + " -> " + course.getName());
        University.getInstance().addLogRecord( new LogRecord("REJECT_REGISTRATION", this, "Rejected registration for " + student.getFullName() + " in " + course.getName()));
    }

    public void addCourseForRegistration(Course course) {
        if (course == null){
            throw new IllegalArgumentException("Course cannot be null");
        }
        University.getInstance().addCourse(course);
        System.out.println("Course added for registration: " + course.getName());
        University.getInstance().addLogRecord(new LogRecord("ADD_COURSE", this, "Added course: " + course.getName()));
    }

    public void assignTeacherToCourse(Teacher teacher, Course course) {
        if (teacher == null || course == null) {
            throw new IllegalArgumentException("Teacher and course cannot be null");
        }
        course.addTeacher(teacher);
        System.out.println("Assigned " + teacher.getFullName() + " to " + course.getName());
        University.getInstance().addLogRecord(new LogRecord("ASSIGN_TEACHER", this,"Assigned " + teacher.getFullName() + " to " + course.getName()));
    }

    public void createNews(News news) {
        if (news == null){
            throw new IllegalArgumentException("News cannot be null");
        }
        University.getInstance().addNews(news);
        System.out.println("News created: " + news.getTitle());
    }

    public void removeNews(News news) {
        if (news == null){
            throw new IllegalArgumentException("News cannot be null");
        }
        University.getInstance().getNews().remove(news);
        System.out.println("News removed: " + news.getTitle());
    }

    public List<Student> viewStudentsSortedByGpa() {
        List<Student> students = getAllStudents();
        for (int i = 0; i < students.size() - 1; i++) {
            for (int j = 0; j < students.size() - 1 - i; j++) {
                if (students.get(j).getGpa() < students.get(j + 1).getGpa()) {
                    Student tmp = students.get(j);
                    students.set(j, students.get(j + 1));
                    students.set(j + 1, tmp);
                }
            }
        }
        return students;
    }

    public List<Student> viewStudentsSortedAlphabetically() {
        List<Student> students = getAllStudents();
        for (int i = 0; i < students.size() - 1; i++) {
            for (int j = 0; j < students.size() - 1 - i; j++) {
                if (students.get(j).getFullName().compareTo(students.get(j + 1).getFullName()) > 0) {
                    Student tmp = students.get(j);
                    students.set(j, students.get(j + 1));
                    students.set(j + 1, tmp);
                }
            }
        }
        return students;
    }

    public List<Teacher> viewTeachersSortedAlphabetically() {
        List<Teacher> teachers = getAllTeachers();
        for (int i = 0; i < teachers.size() - 1; i++) {
            for (int j = 0; j < teachers.size() - 1 - i; j++) {
                if (teachers.get(j).getFullName().compareTo(teachers.get(j + 1).getFullName()) > 0) {
                    Teacher tmp = teachers.get(j);
                    teachers.set(j, teachers.get(j + 1));
                    teachers.set(j + 1, tmp);
                }
            }
        }
        return teachers;
    }

    public List<Request> viewEmployeeRequests() {
        List<Request> result = new ArrayList<>();
        for (User u : University.getInstance().getUsers()) {
            if (u instanceof Employee) {
                for (Request req : ((Employee) u).getRequests()) {
                    if (req.getStatus() == RequestStatus.NEW) {
                        result.add(req);
                    }
                }
            }
        }
        return result;
    }

    public void signRequest(Request request) {
        if (request == null){
            throw new IllegalArgumentException("Request cannot be null");
        }
        if (request.getStatus() != RequestStatus.NEW) {
            System.out.println("Request is already " + request.getStatus());
            return;
        }
        request.setStatus(RequestStatus.SIGNED);
        System.out.println(getFullName() + " signed request: " + request.getText());
        University.getInstance().addLogRecord(new LogRecord("SIGN_REQUEST", this, "Signed request: " + request.getText()));
    }

    @Override
    public void viewMenu() {
        super.viewMenu();
        System.out.println("3. Approve registration");
        System.out.println("4. Reject registration");
        System.out.println("5. Add course for registration");
        System.out.println("6. Assign teacher to course");
        System.out.println("7. Create news");
        System.out.println("8. Remove news");
        System.out.println("9. View students by GPA");
        System.out.println("10. View students A-Z");
        System.out.println("11. View teachers A-Z");
        System.out.println("12. View employee requests");
        System.out.println("13. Sign request");
        System.out.println("14. Generate student report");
        System.out.println("15. Generate course report");
        System.out.println("16. Generate teacher report");
        System.out.println("17. Generate research report");
    }

    
    @Override
    public Report generateStudentPerformanceReport(List<Student> students) {
        StringBuilder sb = new StringBuilder();
        sb.append("Generated by: ").append(getFullName()).append("\n\n");
        for (Student s : students) {
            sb.append(s.getFullName()).append(" | GPA: ").append(String.format("%.2f", s.getGpa())).append(" | Credits: ").append(s.getTotalCredits()).append("\n");
            for (Course c : s.getRegisteredCourses()) {
                Mark m = s.viewMark(c);
                String grade = (m != null) ? m.getLetterGrade() + " (" + m.getTotal() + ")" : "N/A";
                sb.append("  ").append(c.getName()).append(": ").append(grade).append("\n");
            }
            sb.append("\n");
        }
        return new Report(ReportType.STUDENT_MARK_STATISTICS,
            "Student Performance Report", sb.toString());
    }

    @Override
    public Report generateCourseStatisticsReport(Course course) {
        StringBuilder sb = new StringBuilder();
        sb.append("Course: ").append(course.getName()).append(" | Code: ").append(course.getCode()).append(" | Credits: ").append(course.getCredits()).append("\n");
        sb.append("Teachers:\n");
        for (Teacher t : course.getTeachers()) {
            sb.append("  ").append(t.getFullName()).append("\n");
        }
        List<Student> students = course.getStudents();
        sb.append("Students enrolled: ").append(students.size()).append("\n");
        if (!students.isEmpty()) {
            double total = 0;
            int passed = 0;
            for (Student s : students) {
                Mark m = course.getMark(s);
                if (m != null) {
                    total += m.getTotal();
                    if (m.getTotal() >= 50) passed++;
                }
            }
            sb.append("Average score: ").append(String.format("%.2f", total / students.size())).append("\n");
            sb.append("Pass rate: ").append(passed).append("/").append(students.size()).append("\n");
        }
        return new Report(ReportType.COURSE_STATISTICS,
            "Course Statistics: " + course.getName(), sb.toString());
    }

    @Override
    public Report generateTeacherStatisticsReport(Teacher teacher) {
        StringBuilder sb = new StringBuilder();
        sb.append("Teacher: ").append(teacher.getFullName()).append("\n");
        sb.append("Courses:\n");
        int totalStudents = 0;
        for (Course c : University.getInstance().getCourses()) {
            if (c.getTeachers().contains(teacher) || teacher.equals(c.getTeacher())) {
                sb.append("  ").append(c.getName()).append(" (").append(c.getStudents().size()).append(" students)\n");
                totalStudents += c.getStudents().size();
            }
        }
        sb.append("Total students taught: ").append(totalStudents).append("\n");
        if (teacher instanceof Researcher) {
            Researcher r = (Researcher) teacher;
            sb.append("h-index: ").append(r.getHIndex()).append("\n");
            sb.append("Total citations: ").append(r.calculateTotalCitations()).append("\n");
            sb.append("Research papers: ").append(r.getResearchPapers().size()).append("\n");
        }
        return new Report(ReportType.TEACHER_STATISTICS,"Teacher Statistics: " + teacher.getFullName(), sb.toString());
    }

    @Override
    public Report generateResearchStatisticsReport(List<Researcher> researchers) {
        StringBuilder sb = new StringBuilder();
        sb.append("Total researchers: ").append(researchers.size()).append("\n\n");
        int totalPapers = 0;
        int totalCitations = 0;
        Researcher top = null;
        int topCit = -1;
        for (Researcher r : researchers) {
            int cit = r.calculateTotalCitations();
            totalPapers += r.getResearchPapers().size();
            totalCitations += cit;
            if (cit > topCit) { topCit = cit; top = r; }
            String name = (r instanceof User) ? ((User) r).getFullName() : r.toString();
            sb.append(name).append(" | papers=").append(r.getResearchPapers().size()).append(" | citations=").append(cit).append(" | h=").append(r.getHIndex()).append("\n");
        }
        sb.append("\nTotal papers: ").append(totalPapers).append("\n");
        sb.append("Total citations: ").append(totalCitations).append("\n");
        if (top != null) {
            sb.append("Top researcher: ").append(top instanceof User ? ((User) top).getFullName() : top.toString()).append(" (").append(topCit).append(" citations)\n");
        }
        return new Report(ReportType.RESEARCH_STATISTICS,"Research Statistics Report", sb.toString());
    }

    private List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        for (User u : University.getInstance().getUsers()) {
            if (u instanceof Student){
                students.add((Student) u);
            }
        }
        return students;
    }

    private List<Teacher> getAllTeachers() {
        List<Teacher> teachers = new ArrayList<>();
        for (User u : University.getInstance().getUsers()) {
            if (u instanceof Teacher){
                teachers.add((Teacher) u);
            }
        }
        return teachers;
    }

    @Override
    public String toString() {
        return "Manager " + getFullName() + " (" + getId() + ") type=" + managerType;
    }

    public List<Student> viewStudentsSortedByGpa(List<Student> students) {
        List<Student> result = new ArrayList<>(students);
        for (int i = 0; i < result.size()-1; i++)
            for (int j = 0; j < result.size()-1-i; j++)
                if (result.get(j).getGpa() < result.get(j+1).getGpa()) {
                    Student t = result.get(j); result.set(j,result.get(j+1)); result.set(j+1,t);
                }
        return result;
    }

    public List<Student> viewStudentsSortedAlphabetically(List<Student> students) {
        List<Student> result = new ArrayList<>(students);
        for (int i = 0; i < result.size()-1; i++)
            for (int j = 0; j < result.size()-1-i; j++)
                if (result.get(j).getFullName().compareTo(result.get(j+1).getFullName()) > 0) {
                    Student t = result.get(j); result.set(j,result.get(j+1)); result.set(j+1,t);
                }
        return result;
    }

    public List<Request> viewEmployeeRequests(List<Employee> employees) {
        List<Request> result = new ArrayList<>();
        for (Employee e : employees)
            for (Request req : e.getRequests())
                if (req.getStatus() == RequestStatus.NEW) result.add(req);
        return result;
    }

}

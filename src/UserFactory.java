public class UserFactory {
   
    public static User createUser(UserRole role, String id, String username, String password, String firstName, String lastName, String email) {
        
        if (role == UserRole.STUDENT) {
            return new Student(id, username, password, firstName, lastName, email, "Undeclared", 1);
        } 
        else if (role == UserRole.TEACHER) {
            return new Teacher(id, username, password, firstName, lastName, email, 0, "N/A", TeacherTitle.ASSISTANT);
        } 
        else if (role == UserRole.MANAGER) {
            return new Manager(id, username, password, firstName, lastName, email, 0, "N/A", ManagerType.DEPARTMENT_HEAD);
        } 
        else if (role == UserRole.ADMIN) {
            return new Admin(id, username, password, firstName, lastName, email, 0, "N/A");
        } 
        else {
            return new ResearchEmployee(id, username, password, firstName, lastName, email, 0, "N/A", "Researcher", 0);
        }
    }
}

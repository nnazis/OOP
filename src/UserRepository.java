
public class UserRepository extends DataRepository<User> {
    public User findById(String id) {
        for (User user : items) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }
}

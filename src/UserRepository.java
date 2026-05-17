import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository implements DataRepository<User> {
    private List<User> users;

    public UserRepository()
    { 
        users = new ArrayList<>();
    }

    public void save(User obj)
    { 
        if (!users.contains(obj)){
            users.add(obj);
        } 
    }
    public void delete(User obj){
        users.remove(obj);
    }
    public List<User> findAll(){
        return users;
    }

    public Optional<User> findById(String id) {
        for (User u : users) {
            if (u.getId().equals(id)){
                return Optional.of(u);
            }
        }
        return Optional.empty();
    }
}

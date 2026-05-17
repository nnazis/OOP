import java.util.List;
import java.util.Optional;

public interface DataRepository<T> {
    void save(T obj);
    void delete(T obj);
    List<T> findAll();
    Optional<T> findById(String id);
}


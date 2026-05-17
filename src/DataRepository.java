
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DataRepository<T> implements Serializable {
    protected List<T> items;

    public DataRepository() {
        this.items = new ArrayList<>();
    }

    public void add(T item) {
        if (!items.contains(item)) {
            items.add(item);
        }
    }

    public void remove(T item) {
        items.remove(item);
    }

    public List<T> getAll() {
        return items;
    }
}

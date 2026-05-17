import java.util.ArrayList;
import java.util.List;

public class NewsPublisher {
    private List<Observer> observers;

    public NewsPublisher() {
        this.observers = new ArrayList<>();
    }

    public void attach(Observer observer) {
        if (!observers.contains(observer)){
            observers.add(observer);
        }
    }

    public void detach(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers(News news) {
        for (Observer o : observers) {
            o.update(news);
        }
    }
}

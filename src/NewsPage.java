import java.util.ArrayList;
import java.util.List;

public class NewsPage {

    private List<News> newsList;

    public NewsPage() {
        this.newsList = new ArrayList<>();
    }

    public void addNews(News news) {
        newsList.add(news);
        System.out.println("News published: \"" + news.getTitle() + "\"");
    }

    public void removeNews(News news) {
        if (newsList.remove(news)) {
            System.out.println("News removed: \"" + news.getTitle() + "\"");
        } else {
            System.out.println("News not found.");
        }
    }

    public List<News> getAllNews() {
        return newsList;
    }

    public List<News> getNewsByCategory(NewsCategory category) {
        List<News> result = new ArrayList<>();
        for (News n : newsList) {
            if (n.getCategory() == category) {
                result.add(n);
            }
        }
        return result;
    }

    public News findById(String newsId) {
        for (News n : newsList) {
            if (n.getNewsId().equals(newsId)) return n;
        }
        return null;
    }

    public void printAll() {
        if (newsList.isEmpty()) {
            System.out.println("No news available.");
            return;
        }
        System.out.println("\n===== ALL NEWS =====");
        for (int i = 0; i < newsList.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + newsList.get(i));
        }
    }

    public void printByCategory(NewsCategory category) {
        List<News> filtered = getNewsByCategory(category);
        if (filtered.isEmpty()) {
            System.out.println("No news in category: " + category);
            return;
        }
        System.out.println("\n===== NEWS: " + category + " =====");
        for (int i = 0; i < filtered.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + filtered.get(i));
        }
    }
}

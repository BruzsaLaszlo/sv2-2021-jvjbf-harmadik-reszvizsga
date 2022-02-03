package potvizsga.contentsite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Podcast implements Content {

    private final String title;
    private final List<String> speakers;
    private final List<User> users = new ArrayList<>();

    public Podcast(String title, List<String> speakers) {
        this.title = title;
        this.speakers = speakers;
    }

    @Override
    public boolean isPremiumContent() {
        return false;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public List<User> clickedBy() {
        return new ArrayList<>(users);
    }

    @Override
    public void click(User user) {
        users.add(user);
    }

    public List<String> getSpeakers() {
        return Collections.unmodifiableList(speakers);
    }
}

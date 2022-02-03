package potvizsga.contentsite;

import java.util.ArrayList;
import java.util.List;

public class Video implements Content {

    private static final int PREMIUM_LENTH = 15;

    private int length;
    private String title;
    private List<User> users = new ArrayList<>();

    public Video(String title, int length) {
        this.length = length;
        this.title = title;
    }

    @Override
    public boolean isPremiumContent() {
        return length > PREMIUM_LENTH;
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

}

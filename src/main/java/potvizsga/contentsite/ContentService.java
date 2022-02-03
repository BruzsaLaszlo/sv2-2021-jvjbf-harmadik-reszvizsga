package potvizsga.contentsite;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ContentService {

    private final Set<User> users = new HashSet<>();
    private final Set<Content> contents = new HashSet<>();


    public void registerUser(String userName, String password) {
        if (!users.add(new User(userName, password)))
            throw new IllegalArgumentException("Username is already taken: " + userName);
    }

    public void addContent(Content content) {
        if (contents.stream().noneMatch(c -> c.getTitle().equals(content.getTitle())))
            contents.add(content);
        else throw new IllegalArgumentException("Content name is already taken: " + content.getTitle());
    }

    public Set<User> getAllUsers() {
        return Collections.unmodifiableSet(users);
    }

    public Set<Content> getAllContent() {
        return Collections.unmodifiableSet(contents);
    }

    public void logIn(String userName, String password) {
        User found = users.stream()
                .filter(user -> user.getUserName().equals(userName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Username is wrong!"));
        if (found.getPassword() == (userName + password).hashCode())
            found.setLogIn(true);
        else throw new IllegalArgumentException("Password is Invalid!");
    }

    public void clickOnContent(User user, Content content) {
        if (!user.isLogIn())
            throw new IllegalStateException("Log in to watch this content!");
        if (content.isPremiumContent() && !user.isPremiumMember())
            throw new IllegalStateException("Upgrade for Premium to watch this content!");
        content.click(user);
    }
}

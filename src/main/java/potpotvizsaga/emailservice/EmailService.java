package potpotvizsaga.emailservice;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailService {

    private Set<User> users = new HashSet<>();

    public void registerUser(String emailAddress) {
        validateEmail(emailAddress);
        users.add(new User(emailAddress));
    }

    private void validateEmail(String emailAddress) {
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        Matcher matcher = Pattern.compile(regexPattern).matcher(emailAddress);
        if (!matcher.matches() || !emailAddress.equals(emailAddress.toLowerCase())) {
            throw new IllegalArgumentException("Email address is not valid: " + emailAddress);
        }
        if (users.stream().anyMatch(user -> user.getEmailAddress().equals(emailAddress)))
            throw new IllegalArgumentException("Email address is already taken!");
    }

    public Set<User> getUsers() {
        return Collections.unmodifiableSet(users);
    }

    public void sendEmail(String from, String to, String subject, String content) {
        User toUser = users.stream()
                .filter(user -> user.getEmailAddress().equals(to))
                .findFirst()
                .orElseThrow(IllegalStateException::new);
        users.stream()
                .filter(user -> user.getEmailAddress().equals(from))
                .findFirst()
                .ifPresent(user -> user.sendEmail(subject, content, toUser));
    }

    public void sendSpam(String subject, String content) {
        users.forEach(user -> user.getEmail(new Spam(user, subject, content)));
    }
}

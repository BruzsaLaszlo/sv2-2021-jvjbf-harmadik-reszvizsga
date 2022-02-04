package potpotvizsaga.emailservice;

import java.util.*;

public class User {

    private String emailAddress;
    private List<Email> incoming = new ArrayList<>();
    private List<Email> sent = new ArrayList<>();
    private boolean hasSpamFilter;

    public User(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void getEmail(Email email) {
        if (hasSpamFilter && email.getSubject().contains("spam")) {
            throw new IllegalStateException("Be careful, tis is a spam!");
        }
        incoming.add(email);
    }

    public void sendEmail(String subject, String content, User to) {
        Email email = new Normal(this, to, subject, content);
        to.getEmail(email);
        sent.add(email);
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public List<Email> getIncoming() {
        incoming.sort(Comparator.comparing(Email::isImportant).reversed());
        return Collections.unmodifiableList(incoming);
    }

    public List<Email> getSent() {
        return Collections.unmodifiableList(sent);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(emailAddress, user.emailAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emailAddress);
    }

    public boolean hasSpamFilter() {
        return hasSpamFilter;
    }

    public void spamFilterChange() {
        hasSpamFilter = !hasSpamFilter;
    }
}

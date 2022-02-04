package potpotvizsaga.emailservice;

public class Spam implements Email {

    private User from = new User("spam@spam.com");
    private User to;
    private String subject;
    private String content;

    public Spam(User to, String subject, String content) {
        this.to = to;
        this.subject = subject;
        this.content = content;
    }

    @Override
    public User getFrom() {
        return from;
    }

    @Override
    public User getTo() {
        return to;
    }

    @Override
    public String getSubject() {
        return subject;
    }

    @Override
    public String getContent() {
        return content;
    }

}

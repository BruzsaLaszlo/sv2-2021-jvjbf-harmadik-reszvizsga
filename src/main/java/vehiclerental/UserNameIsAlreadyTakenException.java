package vehiclerental;

public class UserNameIsAlreadyTakenException extends RuntimeException {

    UserNameIsAlreadyTakenException(String message) {
        super(message);
    }

}

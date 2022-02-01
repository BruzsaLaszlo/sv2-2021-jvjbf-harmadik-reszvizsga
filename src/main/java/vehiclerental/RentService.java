package vehiclerental;

import java.time.LocalTime;
import java.util.*;

public class RentService {

    private static final int MAX_RENTING_MINUTES = 180;
    private Set<Rentable> rentables = new HashSet<>();
    private Set<User> users = new HashSet<>();
    private Map<Rentable, User> actualRenting = new TreeMap<>();


    public void registerUser(User user) {
        if (!users.add(user)) {
            throw new UserNameIsAlreadyTakenException("Username is taken!");
        }
    }

    public void addRentable(Rentable rentable) {
        rentables.add(rentable);
    }

    public void rent(User user, Rentable rentable, LocalTime time) {
        validate(user, rentable);
        rentable.rent(time);
        actualRenting.put(rentable, user);
    }

    public void closeRent(Rentable rentable, int minutes) {
        actualRenting.get(rentable).minusBalance(rentable.calculateSumPrice(minutes));
        actualRenting.remove(rentable);
        rentable.closeRent();
    }

    private void validate(User user, Rentable rentable) {
        if (rentable.getRentingTime() != null)
            throw new IllegalStateException("rentable is taken");
        if (user.getBalance() < rentable.calculateSumPrice(MAX_RENTING_MINUTES))
            throw new IllegalStateException("not enough money");
    }

    public Set<User> getUsers() {
        return Collections.unmodifiableSet(users);
    }

    public Set<Rentable> getRentables() {
        return Collections.unmodifiableSet(rentables);
    }

    public Map<Rentable, User> getActualRenting() {
        return Collections.unmodifiableMap(actualRenting);
    }
}

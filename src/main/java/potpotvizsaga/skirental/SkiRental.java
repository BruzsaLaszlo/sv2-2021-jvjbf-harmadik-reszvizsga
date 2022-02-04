package potpotvizsaga.skirental;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class SkiRental {

    private Map<String, Equipment> rentals = new TreeMap<>();


    public Map<String, Equipment> getRentals() {
        return Collections.unmodifiableMap(rentals);
    }

    public void loadFromFile(Path path) {
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            reader.readLine();
            while (reader.ready()) {
                var data = reader.readLine().split(";");
                var dataEq = data[1].split(" ");
                rentals.put(data[0], new Equipment(Integer.parseInt(dataEq[0]), Integer.parseInt(dataEq[1])));
            }
        } catch (IOException e) {
            throw new IllegalStateException("Cannot read file: " + path);
        }
    }


    public List<String> listChildren() {
        return rentals.entrySet().stream()
                .filter(entry -> entry.getValue().getSizeOfBoot() <= 37
                        && entry.getValue().getSizeOfBoot() != 0
                        && entry.getValue().getSizeOfSkis() <= 120
                        && entry.getValue().getSizeOfSkis() != 0)
                .map(Map.Entry::getKey)
                .toList();
    }

    public String getNameOfPeopleWithBiggestFoot() {
        return rentals.entrySet().stream()
                .filter(entry -> entry.getValue().getSizeOfSkis() != 0)
                .max(Comparator.comparing(entry -> entry.getValue().getSizeOfBoot()))
                .map(Map.Entry::getKey)
                .orElseThrow();
    }
}

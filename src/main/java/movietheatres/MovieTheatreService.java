package movietheatres;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalTime;
import java.util.*;

public class MovieTheatreService {

    private Map<String, List<Movie>> shows = new LinkedHashMap<>();

    public void readFromFile(Path path) {
        for (String line : getLinesFromFile(path)) {
            var data = line.split("[-;]");
            List<Movie> movies = shows.computeIfAbsent(data[0], s -> new ArrayList<>());
            movies.add(new Movie(data[1], LocalTime.parse(data[2])));
        }
    }

    public Map<String, List<Movie>> getShows() {
        shows.values()
                .forEach(movies -> movies.sort(Comparator.comparing(Movie::getStartTime)));
        return Collections.unmodifiableMap(shows);
    }

    public List<String> findMovie(String title) {
        return shows.entrySet().stream()
                .filter(entry -> entry.getValue().stream()
                        .anyMatch(movie -> movie.getTitle().equals(title)))
                .map(Map.Entry::getKey)
                .toList();
    }

    public LocalTime findLatestShow(String title) {
        return shows.values().stream()
                .flatMap(Collection::stream)
                .filter(movie -> movie.getTitle().equals(title))
                .max(Comparator.comparing(Movie::getStartTime))
                .map(Movie::getStartTime)
                .orElseThrow(IllegalArgumentException::new);
    }

    private List<String> getLinesFromFile(Path path) {
        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            throw new IllegalArgumentException("can not read: " + path, e);
        }
    }
}

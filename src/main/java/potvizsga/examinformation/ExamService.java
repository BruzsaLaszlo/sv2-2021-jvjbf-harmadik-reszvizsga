package potvizsga.examinformation;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class ExamService {

    private int theoryMax;
    private int practiceMax;
    private Map<String, ExamResult> results = new TreeMap<>();

    public void readFromFIle(Path path) {
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            var data = reader.readLine().split(" ");
            theoryMax = Integer.parseInt(data[0]);
            practiceMax = Integer.parseInt(data[1]);
            while (reader.ready()) {
                data = reader.readLine().split("[;]");
                var dataMarks = data[1].split(" ");
                results.put(data[0], new ExamResult(dataMarks[0], dataMarks[1]));
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("Cannot read file: " + path);
        }
    }

    public List<String> findPeopleFailed() {
        return results.entrySet().stream()
                .filter(this::isFailed)
                .map(Map.Entry::getKey)
                .toList();
    }


    public String findBestPerson() {
        return results.entrySet().stream()
                .filter(entry -> !isFailed(entry))
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElseThrow(NoSuchElementException::new);
    }

    private boolean isFailed(Map.Entry<String, ExamResult> result) {
        return result.getValue().getPractice() < practiceMax * 0.51
                || result.getValue().getTheory() < theoryMax * 0.51;
    }

    public int getTheoryMax() {
        return theoryMax;
    }

    public int getPracticeMax() {
        return practiceMax;
    }

    public Map<String, ExamResult> getResults() {
        return Collections.unmodifiableMap(results);
    }
}

package potvizsga.examinformation;

import java.util.Objects;

public class ExamResult implements Comparable<ExamResult> {
    private final int theory;
    private final int practice;

    public ExamResult(String theory, String practice) {
        this.theory = Integer.parseInt(theory);
        this.practice = Integer.parseInt(practice);
    }

    public ExamResult(int theory, int practice) {
        this.theory = theory;
        this.practice = practice;
    }

    @Override
    public int compareTo(ExamResult o) {
        return Integer.compare(theory + practice, o.theory + o.practice);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExamResult that = (ExamResult) o;
        return theory == that.theory && practice == that.practice;
    }

    @Override
    public int hashCode() {
        return Objects.hash(theory, practice);
    }

    public int getTheory() {
        return theory;
    }

    public int getPractice() {
        return practice;
    }
}

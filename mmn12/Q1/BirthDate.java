import java.time.LocalDate;

public class BirthDate {
    private final LocalDate birthDate;

    public BirthDate(int year, int month, int day) {
        if (year < 0) {
            throw new IllegalArgumentException("year must be >= 0");
        }
        if (month < 0) {
            throw new IllegalArgumentException("month must be >= 0");
        }
        if (day < 0) {
            throw new IllegalArgumentException("year must be >= 0");
        }

        LocalDate date = LocalDate.of(year, month, day);
        if (date.compareTo(LocalDate.now()) > 0) {
            throw new IllegalArgumentException("Birthdate can't be in the future");
        }
        this.birthDate = date;
    }

    public LocalDate getDate() {
        return birthDate;
    }

    public int getMonth() {
        return birthDate.getMonth().getValue();
    }
}

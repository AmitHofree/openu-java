import java.time.LocalDate;

public class Student implements Comparable<Student> {
    private final String firstName, lastName;
    private final int id;
    private final LocalDate birthDate;

    public Student(String firstName, String lastName, int id, LocalDate birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.birthDate = birthDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getId() {
        return id;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    @Override
    public int compareTo(Student o) {
        return Integer.compare(id, o.getId());
    }
}

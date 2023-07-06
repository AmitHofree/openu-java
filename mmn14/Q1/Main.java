import java.time.LocalDate;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) throws IllegalArgumentException {
        Student s1 = new Student("Chuck", "Norris", 1, LocalDate.of(1940, 3, 10));
        Student s2 = new Student("Jackie", "Chan", 2, LocalDate.of(1954, 4, 7));
        Student s3 = new Student("Bruce", "Lee", 3, LocalDate.of(1940, 11, 27));
        Student s4 = new Student("Jean-Claude", "Van Damme", 4, LocalDate.of(1960, 10, 18));
        String[] phoneNumberArray = new String[]{"0501114128", "0501114127", "0501114126"};

        AssociationTable<Student, String> table = new AssociationTable<>(new Student[]{s3, s2, s1}, phoneNumberArray);
        table.add(s4, "0528714393");
        table.add(s1, "0521337133");
        table.remove(s3);

        for (Iterator<Student> it = table.keyIterator(); it.hasNext(); ) {
            Student s = it.next();
            System.out.printf("ID: %d, Name: %s %s, Date of Birth: %s, Phone number: %s\n", s.getId(),
                    s.getFirstName(), s.getLastName(), s.getBirthDate().toString(), table.get(s));
        }
    }
}

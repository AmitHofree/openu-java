import java.util.ArrayList;

class Main {
    public static void main(String[] args) {
        ArrayList<Employee> employees = new ArrayList<Employee>();
        employees.add(new SalariedEmployee("John", "Smith", "111-11-1111",
                new BirthDate(1986, 11, 20), 800.00));
        employees.add(new HourlyEmployee("Karen", "Price", "222-22-2222",
                new BirthDate(1974, 7, 26), 16.75, 40));
        employees.add(new CommissionEmployee("Sue", "Jones", "333-33-3333",
                new BirthDate(1998, 1, 5), 10000, .06));
        employees.add(new BasePlusCommissionEmployee("Bob", "Lewis", "444-44-4444",
                new BirthDate(1986, 4, 7), 5000, .04, 300));
        employees.add(new PieceWorker("James", "Hetfield", "555-55-5555",
                new BirthDate(1963, 8, 3), 200000, 10));

        for (int i = 0; i < employees.size(); i++) {
            System.out.printf("Employee #%d:\n", i);
            Employee employee = employees.get(i);
            System.out.println(employee.toString());
            if (employee.isBirthdayMonth()) {
                System.out.println("Happy birthday month! Employee receives bonus 200$!");
            }
            System.out.printf("Monthly pay: %,.2f$\n", employee.earnings());
            System.out.println();
        }
    }
}

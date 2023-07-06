import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] argv) throws InterruptedException {
        Random random = new Random();
        List<Flight> flightsList = new ArrayList<>();
        Airport sydneyAirport = new Airport("Sydney", 3);
        Airport newYorkAirport = new Airport("New York", 3);
        for (int i = 0; i < 10; i++) {
            Flight flight = random.nextBoolean() ? new Flight(i, sydneyAirport, newYorkAirport)
                    : new Flight(i, newYorkAirport, sydneyAirport);
            flightsList.add(flight);
            flight.start();
        }

        // Wait for flights to finish
        for (Flight flight : flightsList) {
            flight.join();
        }
    }
}

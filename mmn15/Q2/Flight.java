import java.util.Random;

/**
 * Flight for simulation
 */
public class Flight extends Thread {
    public final int flightNumber;
    public final Airport departAirport, landAirport;

    /**
     * @param flightNumber A flight number, identifying the flight
     * @param departAirport An airport to begin the flight from
     * @param landAirport An airport to end the flight in
     */
    public Flight(int flightNumber, Airport departAirport, Airport landAirport) {
        this.flightNumber = flightNumber;
        this.departAirport = departAirport;
        this.landAirport = landAirport;
    }

    /**
     * Simulates a flight from departAirport and landAirport
     * Sleeping for random seconds at each step to imitate
     * the time it takes to perform these actions.
     */
    @Override
    public void run() {
        int departRunwayNumber = departAirport.depart(flightNumber);
        sleepRandomTime(2, 5);
        departAirport.freeRunway(flightNumber, departRunwayNumber);
        sleepRandomTime(5, 10);
        int landRunwayNumber = landAirport.land(flightNumber);
        sleepRandomTime(2, 5);
        landAirport.freeRunway(flightNumber, landRunwayNumber);
    }

    private void sleepRandomTime(int minSeconds, int maxSeconds) {
        try {
            Random random = new Random();
            int secondsToSleep = random.nextInt(maxSeconds - minSeconds) + minSeconds;
            Thread.sleep(secondsToSleep * 1000L);
        } catch (InterruptedException e) {}
    }
}

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Airport for simulation
 */
public class Airport {
    private final String name;
    private final Queue<Integer> runwayQueue;
    private final Lock lock;
    private final Condition availableRunway;

    /**
     * @param name Name of airport
     * @param numberOfRunways Number of runways used for landing and departing
     */
    public Airport(String name, int numberOfRunways) {
        this.name = name;
        this.runwayQueue = new ArrayDeque<>();
        for (int i = 0; i < numberOfRunways; i++) {
            runwayQueue.add(i);
        }
        this.lock = new ReentrantLock(true);
        this.availableRunway = lock.newCondition();
    }

    /**
     * Simulate a request for departure.
     * Waits until a runway is available, and then returns it.
     * @param flightNumber The number of the flight requesting to depart
     * @return Runway number that is available for departing
     */
    public int depart(int flightNumber) {
        System.out.printf("Airport %s - Flight %d requesting to depart\n", name, flightNumber);
        int runwayNumber = getFreeRunway(String.format("Airport %s - Flight %d may depart from runway", name, flightNumber));
        return runwayNumber;
    }

    /**
     * Simulate a request for landing.
     * Waits until a runway is available, and then returns it.
     * @param flightNumber The number of the flight requesting to land
     * @return Runway number that is available for landing
     */
    public int land(int flightNumber) {
        System.out.printf("Airport %s - Flight %d requesting to land\n", name, flightNumber);
        int runwayNumber = getFreeRunway(String.format("Airport %s - Flight %d may land on runway", name, flightNumber));
        return runwayNumber;
    }

    private int getFreeRunway(String logLine) {
        while (true) {
            lock.lock();
            try {
                while (runwayQueue.size() == 0) availableRunway.await();
                int runwayNumber = runwayQueue.remove();
                System.out.printf("%s %d\n", logLine, runwayNumber);
                return runwayNumber;
            } catch (InterruptedException e) {
            } finally {
                lock.unlock();
            }
        }
    }

    /**
     * Free a runway for other flights to use it
     * @param flightNumber The flight number that finished using the runway
     * @param runwayNumber The runway number that is now free
     */
    public void freeRunway(int flightNumber, int runwayNumber) {
        System.out.printf("Airport %s - Flight %d is done with runway %d\n", name, flightNumber, runwayNumber);
        lock.lock();
        try {
            runwayQueue.add(runwayNumber);
            availableRunway.signal();
        } finally {
            lock.unlock();
        }
    }

}

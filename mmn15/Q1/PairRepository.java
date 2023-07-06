import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Repository implementation that returns work in pairs.
 * Safe for use in multithreaded programs.
 * @param <T> Object type of the repository entities
 */
public class PairRepository<T> {
    private final Queue<T> state;
    private int workersCount;

    /**
     * @param initialList Initial data to initialize the repository with
     */
    public PairRepository(List<T> initialList) {
        state = new ArrayDeque<>();
        state.addAll(initialList);
        workersCount = 0;
    }

    /**
     * Retrieves the result of the computation stored by the repository.
     * @return An entity that is the result of the computation
     */
    public synchronized T getResult() {
        while (state.size() != 1 || workersCount > 0) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        return state.peek();
    }

    /**
     * Returns a pair of item to process in a worker.
     * @return A list containing two elements to process
     */
    public synchronized List<T> getWork() {
        List<T> returnList = new ArrayList<>();
        while (state.size() < 2 && workersCount > 0) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        if (workersCount <= 0 && state.size() == 1) return null;
        returnList.add(state.remove());
        returnList.add(state.remove());
        workersCount++;
        return returnList;
    }

    /**
     * Stores a new entity in the repository
     * @param itemToInsert Entity to be stored in the repository
     */
    public synchronized void insert(T itemToInsert) {
        workersCount--;
        state.add(itemToInsert);
        notifyAll();
    }
}







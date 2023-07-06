import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Implements a merge sort that runs in parallel using a pool of workers
 * and a central repository that aggregates and divides the work done by the workers.
 */
public class ParallelMergeSort {
    private final PairRepository<List<Integer>> repository;
    private final int countWorkers;
    private boolean hasResult;

    /**
     * @param array Array to be sorted using the parallel merge sort algorithm
     * @param countWorkers Number of workers that perform the sorting concurrently.
     */
    public ParallelMergeSort(List<Integer> array, int countWorkers) {
        this.hasResult = false;
        this.countWorkers = countWorkers;
        List<List<Integer>> initialList = new ArrayList<>();
        for (int number : array) {
            initialList.add(Collections.singletonList(number));
        }
        this.repository = new PairRepository<>(initialList);
    }

    /**
     * Sorts the array the object was initialized with.
     * Subsequent calls are permitted and do not perform extra work.
     * @return  The sorted array
     */
    public List<Integer> sort() {
        if (!hasResult) {
            for (int i = 0; i < countWorkers; i++) {
                MergeSortWorker worker = new MergeSortWorker(repository);
                worker.start();
            }
        }
        List<Integer> result = repository.getResult();
        hasResult = true;
        return result;
    }
}

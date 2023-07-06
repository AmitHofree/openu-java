import java.util.ArrayList;
import java.util.List;

/**
 * Worker that performs merge sort on a given PairRepository.
 */
public class MergeSortWorker extends Thread{
    private final PairRepository<List<Integer>> repository;

    /**
     * @param repository A PairRepository to pull lists for sorting from
     */
    public MergeSortWorker(PairRepository<List<Integer>> repository) {
        this.repository = repository;
    }

    /**
     * Performs the sorting work.
     */
    @Override
    public void run() {
        List<List<Integer>> list = repository.getWork();
        while (list != null) {
            List<Integer> sorted = sortAndMergeLists(list);
            repository.insert(sorted);
            list = repository.getWork();
        }
    }

    /**
     * Sorts and merges a pair of lists according to the merge sort algorithm.
     * @param list A list containing two sorted integer lists
     * @return A sorted list that is the result of merging the two input lists
     */
    private List<Integer> sortAndMergeLists(List<List<Integer>> list) {
        List<Integer> arrayOne = list.get(0);
        List<Integer> arrayTwo = list.get(1);
        List<Integer> merged = new ArrayList<>();
        int indexOfFirstArray = 0;
        int indexOfSecondArray = 0;


        while (indexOfFirstArray < arrayOne.size() && indexOfSecondArray < arrayTwo.size()) {
            if (arrayOne.get(indexOfFirstArray) <= arrayTwo.get(indexOfSecondArray)) {
                merged.add(arrayOne.get(indexOfFirstArray));
                indexOfFirstArray++;
            }
            else {
                merged.add(arrayTwo.get(indexOfSecondArray));
                indexOfSecondArray++;
            }
        }

        while (indexOfFirstArray < arrayOne.size()) {
            merged.add(arrayOne.get(indexOfFirstArray));
            indexOfFirstArray++;
        }

        while (indexOfSecondArray < arrayTwo.size()) {
            merged.add(arrayTwo.get(indexOfSecondArray));
            indexOfSecondArray++;
        }

        return merged;
    }
}

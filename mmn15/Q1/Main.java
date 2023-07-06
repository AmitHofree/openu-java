import java.util.*;

public class Main {
    public static void main(String[] argv) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> listToSort = new ArrayList<>();
        Random random = new Random();

        System.out.println("Please enter values for n (List size) and m (Number of workers).");
        System.out.print("Enter a value for list size - ");
        int n = scanner.nextInt();
        System.out.print("Enter a value for workers count - ");
        int m = scanner.nextInt();

        for (int i = 0; i < n; i++) {
            listToSort.add(random.nextInt(100));
        }

        System.out.println("Printing the list before sorting it - ");
        System.out.printf("%s\n", listToSort);
        ParallelMergeSort mergeSort = new ParallelMergeSort(listToSort, m);
        System.out.print("Printing a sorted list - ");
        System.out.printf("%s\n", mergeSort.sort());
    }
}

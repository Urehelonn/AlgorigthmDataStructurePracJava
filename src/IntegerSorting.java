import java.util.ArrayList;

public class IntegerSorting {

    public static void main(String[] args) {
        int[] ary = new int[]{3, 15, 6, 5, 2, 5, 13, 23, 26, 45};
        int[] sort = insertionSort(ary);
        for (int num : sort) {
            System.out.println(num);
        }
    }

    // insertion sort, with Greedy Approach
    // O(n^2)
    public static int[] insertionSort(int[] ary) {
        // create empty array list
        ArrayList<Integer> sorted = new ArrayList<Integer>();
        for (int i = 0; i < ary.length; i++) {
            // loop through sorted list to see where to insert the number
            boolean inserted = false;
            for (int j = 0; j < sorted.size(); j++) {
                // if find a number bigger than current number, insert into sorted, before j
                if (ary[i] < sorted.get(j)) {
                    sorted.add(j, ary[i]);
                    inserted = true;
                    break;
                }
            }
            // in case if the number checking is the biggest one of sorted array
            if(!inserted) sorted.add(ary[i]);
        }
        int[] res = sorted.stream()
                .mapToInt(Integer::intValue)
                .toArray();
        return res;
    }
}

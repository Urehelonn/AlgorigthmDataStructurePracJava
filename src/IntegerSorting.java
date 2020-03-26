import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class IntegerSorting {

    public static void main(String[] args) {
        double[] ary = new double[]{38, 27, 43, 3, 9, 82, 10};
        double[] sort1 = insertionSort(ary);
        double[] sort2 = mergeSort(ary);
        for (double num : sort2) {
            System.out.println(num);
        }
        // binary search for the target number
        System.out.println("Enter the number you want to search: ");
        Scanner in = new Scanner(System.in);
        double target = in.nextInt();
        int ind = binarySearch(sort2, target, 0, sort2.length - 1);
        System.out.println(
                ind == -1 ? "Sorry the number you are looking for isn't in the array."
                        : "The number you are looking for is located at: " + ind
        );


    }

    // insertion sort, with Greedy Approach
    // O(n^2)
    public static double[] insertionSort(double[] ary) {
        if (ary.length <= 1) return ary;
        // create empty array list
        ArrayList<Double> sorted = new ArrayList<Double>();
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
            if (!inserted) sorted.add(ary[i]);
        }
        double[] res = sorted.stream()
                .mapToDouble(Double::doubleValue)
                .toArray();
        return res;
    }

    // binary search, using divide and conquer algorithm
    // O(nlogn)
    public static int binarySearch(double[] ary, double target, int start, int end) {
        int res = -1;
        if (end >= start) {
            int mid = (int) Math.floor((end + start) / 2);
            if (ary[mid] == target) return mid;
            else if (ary[mid] > target) return binarySearch(ary, target, start, mid - 1);
            else {
                return binarySearch(ary, target, mid + 1, end);
            }
        }
        return res;
    }

    // merge sort, using divide and conquer algorithm
    // O(nlogn)
    public static double[] mergeSort(double[] ary) {
        // create empty array list
//        ArrayList<Double> sorted = new ArrayList<Double>();
        if (ary.length <= 1) return ary;
        double[] res = ary.clone();
        mergeSortHelper(res, 0, ary.length - 1);
        // convert double arrayList to double
//        double[] res = sorted.stream()
//                .mapToDouble(Double::doubleValue)
//                .toArray();
        return res;
    }

    private static void mergeSortHelper(double[] ary, int start, int end) {
        if (start < end) {
            int mid = (end + start) / 2;
            mergeSortHelper(ary, start, mid);
            mergeSortHelper(ary, mid + 1, end);
            merge(ary, start, mid, end);
        }
    }

    private static void merge(double[] ary, int start, int mid, int end) {
        double[] left = Arrays.copyOfRange(ary, start, mid+1);
        double[] right = Arrays.copyOfRange(ary, mid + 1, end+1);
        int i = 0, j = 0;
        int k = start;
        while (i < left.length && j < right.length) {
            if (left[i] < right[j]) {
                ary[k++] = left[i++];
            } else {
                ary[k++] = right[j++];
            }
        }
        while (i < left.length) {
            ary[k++] = left[i++];
        }
        while (j < right.length) {
            ary[k++] = right[j++];
        }
    }
}

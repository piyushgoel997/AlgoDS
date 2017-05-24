package Sorts;

import java.util.Comparator;

/**
 * Created by piyus on 06-04-2017.
 */
public class QuickSort {
    //Basic Implementation without any shuffling or improvements.
    // start = starting index
    // end = ending index
    public static <Item> void quickSort(Item[] items, int start, int end, Comparator<Item> comparator) {
        if (end - start < 1) {
            return;
        }
        int i = start + 1;
        int j = end;
        while (i < j) {
            while (i <= end && comparator.compare(items[i], items[start]) == -1) {
                i++;
            }
            while (j > start && comparator.compare(items[j], items[start]) == 1) {
                j--;
            }
            if (i < j) {
                exch(items, i, j);
                i++;
                j--;
            }
        }
        exch(items, start, j);
        quickSort(items, start, j - 1, comparator);
        quickSort(items, j + 1, end, comparator);
    }

    private static <Item> void exch(Item[] items, int i, int j) {
        Item temp = items[i];
        items[i] = items[j];
        items[j] = temp;
    }

    public static void main(String[] args) {
        Integer[] arr = {5, 6, 8, 1, 4, 3, 9, 2, 4, 1, 9, 7};
        quickSort(arr, 0, arr.length - 1, Integer::compareTo);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}

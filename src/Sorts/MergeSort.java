package Sorts;

import java.util.Comparator;

/**
 * Created by piyus on 06-04-2017.
 */
public class MergeSort {

    public static <Item> Item[] mergeSort(Item[] items, Comparator<Item> comparator) {
        if (items.length <= 1) {
            return items;
        }

        Item[] left = (Item[]) new Object[items.length / 2];
        Item[] right = (Item[]) new Object[items.length - items.length / 2];
        for (int i = 0; i < left.length; i++) {
            left[i] = items[i];
        }
        for (int i = 0; i < right.length; i++) {
            right[i] = items[left.length + i];
        }
        left = mergeSort(left, comparator);
        right = mergeSort(right, comparator);
        return merge(left, right, comparator);
    }

    private static <Item> Item[] merge(Item[] left, Item[] right, Comparator<Item> comparator) {
        int i = 0;
        int j = 0;
        Item[] items = (Item[]) new Object[left.length + right.length];

        while ((i < left.length) && (j < right.length)) {
            switch (comparator.compare(left[i], right[j])) {
                case 1:
                    items[i + j] = right[j];
                    j++;
                    break;
                case -1:
                    items[i + j] = left[i];
                    i++;
                    break;
                case 0:
                    items[i + j] = left[i];
                    i++;
                    items[i + j] = right[j];
                    j++;
                    break;
            }
        }

        while (i < left.length) {
            items[i + j] = left[i];
            i++;
        }

        while (j < right.length) {
            items[i + j] = right[j];
            j++;
        }

        return items;
    }

    public static void main(String[] args) {
        Integer[] arr = {5, 6, 8, 1, 4, 3, 9, 2, 4, 1, 9, 7};
        Object[] a = mergeSort(arr, Integer::compareTo);
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }

}

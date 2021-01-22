package de.nikos410.fpr.employeemanagement.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BubbleSortHelper<T extends Comparable<T>> {
    private final List<T> data;
    private boolean sorted = false;

    public BubbleSortHelper(Collection<T> data) {
        this.data = new ArrayList<>(data);
    }

    public Collection<T> sorted() {
        while (!sorted) {
            sorted = true;

            for (int i = 1; i < data.size(); i++) {
                if (data.get(i-1).compareTo(data.get(i)) > 0) {
                    swap(i-1, i);
                    sorted = false;
                }
            }
        }

        return data;
    }

    private void swap(int a, int b) {
        final T temp = data.get(a);
        data.set(a, data.get(b));
        data.set(b, temp);
    }
}

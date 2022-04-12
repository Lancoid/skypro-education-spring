package com.company.skyproeducationspring.utils;

import java.util.Arrays;

public class IntListImpl implements IntList {

    private static final int DEFAULT_CAPACITY = 10;
    /**
     * The array buffer into which the elements of the IntListImpl are stored.
     */
    private int[] data;

    /**
     * The size of the StringListImpl.
     */
    private int listSize = 0;

    private int listCapacity;

    public IntListImpl() {
        listCapacity = DEFAULT_CAPACITY;
        this.data = new int[listCapacity];
    }

    public IntListImpl(int capacity) {
        listCapacity = capacity;

        if (capacity > 0) {
            data = new int[listCapacity];
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + capacity);
        }
    }

    public IntListImpl(int[] inputData) {
        data = inputData;
        listSize = inputData.length;
        listCapacity = inputData.length;
    }

    @Override
    public int add(int item) {

        if (listSize == listCapacity) {
            expand();
        }

        data[listSize++] = item;

        return data[listSize - 1];
    }

    @Override
    public int add(int index, int item) {
        if (index < 0 || index >= listCapacity) {
            throw new ArrayIndexOutOfBoundsException();
        }

        if (listSize == listCapacity) {
            expand();
        }

        System.arraycopy(data, index, data, index + 1, listSize - index);

        data[index] = item;
        listSize++;

        return data[index];
    }

    @Override
    public int set(int index, int item) {
        if (index < 0 || index >= listCapacity) {
            throw new ArrayIndexOutOfBoundsException();
        }

        data[index] = item;

        return data[index];
    }

    @Override
    public int remove(int index) {
        int value = get(index);

        System.arraycopy(data, index + 1, data, index, listCapacity - index - 1);
        listSize--;

        return value;
    }

    @Override
    public int removeByValue(int item) {
        return remove(indexOf(item));
    }

    @Override
    public boolean contains(int item) {
        IntListImpl list = new IntListImpl(this.toArray());
        list.sort();

        int min = 0;
        int max = list.size() - 1;

        while (min <= max) {
            int mid = (min + max) / 2;

            if (item == list.get(mid)) {
                return true;
            }

            if (item < list.get(mid)) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }

        return false;
    }

    @Override
    public int indexOf(int item) {
        for (int i = 0; i < listSize; i++) {
            if (item == this.get(i)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(int item) {
        for (int i = listSize - 1; i >= 0; i--) {
            if (item == this.get(i)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int get(int index) {
        if (index < 0 || index >= listCapacity) {
            throw new ArrayIndexOutOfBoundsException();
        }

        return data[index];
    }

    @Override
    public boolean equals(IntList otherList) {
        if (otherList == this) {
            return true;
        }

        if (this.listSize != otherList.size()) {
            return false;
        }

        for (int i = 0; i < listSize; i++) {
            if (this.get(i) != otherList.get(i)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int size() {
        return listSize;
    }

    @Override
    public boolean isEmpty() {
        return listSize == 0;
    }

    @Override
    public void clear() {
        this.data = new int[DEFAULT_CAPACITY];
        this.listCapacity = DEFAULT_CAPACITY;
        this.listSize = 0;
    }

    @Override
    public int[] toArray() {
        return Arrays.copyOf(data, listSize);
    }

    public void sort() {
        quickRecursionSort(0, listCapacity - 1);
    }

    private void quickRecursionSort(int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(begin, end);

            quickRecursionSort(begin, partitionIndex - 1);
            quickRecursionSort(partitionIndex + 1, end);
        }
    }

    private void expand() {
        listCapacity = (int) (listCapacity * 1.5) + 1;

        int[] newArray = new int[listCapacity];
        System.arraycopy(data, 0, newArray, 0, listSize);

        data = newArray;
    }

    private int partition(int begin, int end) {
        int pivot = data[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (data[j] <= pivot) {
                i++;

                swapElements(i, j);
            }
        }

        swapElements(i + 1, end);

        return i + 1;
    }

    private void swapElements(int indexA, int indexB) {
        int tmp = data[indexA];

        data[indexA] = data[indexB];
        data[indexB] = tmp;
    }
}

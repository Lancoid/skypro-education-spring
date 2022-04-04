package com.company.skyproeducationspring.utils;

import java.util.Arrays;

public class StringListImpl implements StringList {

    private static final int DEFAULT_CAPACITY = 10;
    /**
     * The array buffer into which the elements of the StringListImpl are stored.
     */
    private String[] data;

    /**
     * The size of the StringListImpl.
     */
    private int listSize = 0;

    private int listCapacity = 0;

    public StringListImpl() {
        listCapacity = DEFAULT_CAPACITY;
        this.data = new String[listCapacity];
    }

    public StringListImpl(int capacity) {
        listCapacity = capacity;

        if (capacity >= 0) {
            data = new String[listCapacity];
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + capacity);
        }
    }

    public StringListImpl(String[] inputData) {
        data = inputData;
        listSize = inputData.length;
        listCapacity = inputData.length;
    }

    @Override
    public String add(String item) {

        if (listSize == listCapacity) {
            expand();
        }

        data[listSize] = item;
        ++listSize;

        return data[listSize - 1];
    }

    @Override
    public String add(int index, String item) {
        if (index < 0 || index >= listCapacity) {
            throw new ArrayIndexOutOfBoundsException();
        }

        data[index] = item;

        return data[index];
    }

    @Override
    public String set(int index, String item) {
        if (index < 0 || index >= listCapacity) {
            throw new ArrayIndexOutOfBoundsException();
        }

        data[index] = item;

        return data[index];
    }

    @Override
    public String remove(String item) {
        return remove(indexOf(item));
    }

    @Override
    public String remove(int index) {
        String string = get(index);
        if (listSize - index >= 0) {
            System.arraycopy(data, index + 1, data, index, listSize - index);
        }

        data[listSize] = null;
        listSize--;

        return string;
    }

    @Override
    public boolean contains(String item) {
        return indexOf(item) >= 0;
    }

    @Override
    public int indexOf(String item) {
        for (int i = 0; i < listSize; i++) {
            if (item.equals(this.get(i))) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        for (int i = listSize - 1; i >= 0; i--) {
            if (item.equals(this.get(i))) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public String get(int index) {
        if (index < 0 || index >= listCapacity) {
            throw new ArrayIndexOutOfBoundsException();
        }

        return data[index];
    }

    @Override
    public boolean equals(StringList otherList) {
        if (otherList == this) {
            return true;
        }

        if (this.listSize != otherList.size()) {
            return false;
        }

        for (int i = 0; i < listSize; i++) {
            if (!this.get(i).equals(otherList.get(i))) {
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
        this.data = new String[DEFAULT_CAPACITY];
    }

    @Override
    public String[] toArray() {
        return Arrays.copyOf(data, listSize);
    }

    private void expand() {
        listCapacity += 1;

        String[] newArray = new String[listCapacity];
        System.arraycopy(data, 0, newArray, 0, listSize);

        data = newArray;
    }
}

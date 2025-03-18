package com.aston.homework3;

import java.util.Arrays;

public class MyArrayList<T extends Comparable<T>> {

    private int capacity = 10;
    private Object[] array;
    private int size = 0;

    public MyArrayList() {
        array = new Object[capacity];
    }

    public MyArrayList(int capacity) {
        this.capacity = capacity;
        array = new Object[capacity];
    }

    public void add(T element) {

        if (size == capacity){
            array = Arrays.copyOf(array, capacity*2);
            capacity = capacity*2;
        }
        array[size] = element;
        size++;
    }

    public void add(int index, T element) {

        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        Object[] arrayBuffer = array.clone();
        if (size == capacity){
            array = new Object[capacity*2];
        } else {
            array = new Object[capacity];
        }

        for (int i = 0, j = 0; i < size + 1; i++, j++) {
            if (j == index) {
                array[j] = element;
                j++;
            }
            array[j] = arrayBuffer[i];
        }
        size++;
    }

    @SuppressWarnings("unchecked")
    public T get(int index){
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        return (T) array[index];
    }

    public void delete(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        Object[] arrayBuffer = array.clone();
        for (int i = 0, j = 0; i < size + 1; i++, j++) {
            if (j == index) {
                i++;
            }
            array[j] = arrayBuffer[i];
        }
        size--;
    }

    @Override
    public String toString() {
        if (array == null) {
            return "[]";
        }
        StringBuilder arr = new StringBuilder();
        arr.append("[");
        for (int i = 0; i < size; i++) {
            arr.append(array[i]);
            if (i < size - 1) {
                arr.append(", ");
            }
        }
        return arr.append("]").toString();
    }

    //Сортировка слиянием
    private void mergeSort(int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(left, mid);
            mergeSort(mid + 1, right);
            mergeSort(left, mid, right);
        }
    }

    @SuppressWarnings("unchecked")
    private void mergeSort(int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        Object[] leftArray = new Object[n1];
        Object[] rightArray = new Object[n2];
        System.arraycopy(array, left, leftArray, 0, n1);
        System.arraycopy(array, mid+1, rightArray, 0, n2);
        int i = 0;
        int j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            if (((T)leftArray[i]).compareTo((T)rightArray[j]) <= 0) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }
        while (i < n1) {
            array[k] = leftArray[i];
            i++;
            k++;
        }
        while (j < n2) {
            array[k] = rightArray[j];
            j++;
            k++;
        }
    }

    public void mergeSort(){
        if (size>1){
            mergeSort(0, size - 1);
        }
    }

    public int getCapacity() {
        return capacity;
    }

    public int getSize() {
        return size;
    }
}
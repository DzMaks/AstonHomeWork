package com.aston.homework3;

import java.util.Arrays;

public class MyArrayList<T> {

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

    public int getCapacity() {
        return capacity;
    }

    public int getSize() {
        return size;
    }
}
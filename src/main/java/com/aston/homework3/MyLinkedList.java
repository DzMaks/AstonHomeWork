package com.aston.homework3;

import java.util.NoSuchElementException;

public class MyLinkedList<E extends  Comparable<E>>{

   private int size = 0;
   private Node<E> first;
   private Node<E> last;

    public MyLinkedList(){
    }

    public void add(E e){
        Node<E> newNode = new Node<>(null, e, null);
        if (size == 0){
            first = newNode;
        } else {
            last.next = newNode;
            newNode.prev = last;
        }
        last = newNode;
        size++;
    }

    public void add(E e, int index){
        if (index == 0) {
            addFirst(e);
        }
        else if(index == size - 1) {
            add(e);
        } else {
            Node<E> newNode = new Node<>(null, e, null);
            indexValidate(index);
            Node<E> current = first;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            if (current != null) {
                newNode.next = current;
                newNode.prev = current.prev;
                current.prev.next = newNode;
                current.prev = newNode;
            }
            size++;
        }
    }

    public void addFirst(E e){
        Node<E> newNode = new Node<E>(null, e, first);
        if (first != null){
            first.prev = newNode;
        }
        first = newNode;
        size++;
    }

    public void removeFirst(){
        if (first == null){
            throw new NoSuchElementException();
        }
        first = first.next;
        first.prev = null;
        size--;
    }

    public void removeLast(){
        if (last == null){
            throw new NoSuchElementException();
        }
        last = last.prev;
        last.next = null;
        size--;
    }

    public void remove(int index){
        indexValidate(index);
        if (index == 0) {
            removeFirst();
        } else if (index == size - 1) {
            removeLast();
        } else {
            Node<E> current = first;
            for(int i = 0; i < index; i++) {
                current = current.next;
            }
            if (current.prev != null) {
                current.prev.next = current.next;
            }
            if (current.next != null) {
                current.next.prev = current.prev;
            }
            size--;
        }
    }

    public E get(int index){
          indexValidate(index);
        Node<E> current = first;
        for(int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    private static class Node<E> {

        E data;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E data, Node<E> next) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }

    }
    @Override
    public String toString() {
        if (size == 0){
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<E> current = first;
        while (current != null) {
            sb.append(current.data);
            current = current.next;
            if (current != null) {
                sb.append(", ");
            }
        }
        return sb.append("]").toString();
    }

    private void indexValidate(int index){
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }
    }

    //Быстаря сортировка
    private void quickSort(Node<E> start, Node<E> end){
        if (start != null && end != null && size > 1){
            E pivotData = end.data;
            Node<E> smallerElement = start;
            for(Node<E> current = start; current != end; current = current.next){
                if (current.data != null && current.data.compareTo(pivotData) < 0){
                    E temp = smallerElement.data;
                    smallerElement.data = current.data;
                    current.data = temp;
                    smallerElement = smallerElement.next;
                }
            }
            E temp = smallerElement.data;
            smallerElement.data = end.data;
            end.data = temp;
            if (smallerElement.prev != null && smallerElement != start){
                quickSort(start, smallerElement.prev);
            }
            if (smallerElement.next != null && smallerElement != end){
                quickSort(smallerElement.next, end);
            }
        }
    }

    public void quickSort(){
        if (first != null && last != null && size > 1){
            quickSort(first, last);
        }
    }

    public int getSize() {
        return size;
    }

}
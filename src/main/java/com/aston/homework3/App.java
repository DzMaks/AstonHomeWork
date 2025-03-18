package com.aston.homework3;

public class App {

    public static void main(String[] args) {

      MyLinkedList<Integer> list = new MyLinkedList<>();
      list.add(43);
      list.add(98);
      list.add(25);
      list.add(45);
      list.add(102);
      list.add(15);
      list.add(7);
      list.add(65);
      list.add(17);
     System.out.println(list);
     list.quickSort();
     System.out.println(list);

    }

}

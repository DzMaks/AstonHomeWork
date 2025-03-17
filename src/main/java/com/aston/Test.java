package com.astonhomework;

public class Test {

    public void ifNegative (int a) throws MyException {
        if (a < 0) {
            throw new MyException();
        }
    }

    public void catchingException (int a) {
        try {
            ifNegative(a);
        } catch (MyException e) {
            System.out.println("Поймали наше исключение");
        }
    }

}

package com.app.generics;

public class GenericExample {

    public static void main(String[] args) {
        Generic<String> generic = new Generic<>("Priti");
        generic.printItem();
        Generic<Integer> genericInt = new Generic<>(1);
        genericInt.printItem();

    }

}


record Generic<T> (T item){

    void printItem(){
        System.out.println(item);
    }
}

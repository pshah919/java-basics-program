package com.app.generics;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

public class InheritanceGenerics {

 public static void main(String[] args) {
    List<Cat> catList= Arrays.asList(new Cat("catA"),new Cat("catB"));
    List<Animal<String>> dogList= Arrays.asList(new Dog("dogA"),new Cat("dogB"));

    addDog(dogList);
    printAnimalChild(catList);
 }

 static void printAnimalChild(List<? extends Animal<String>> list){
  for(Animal<String> animal : list){
   System.out.println(animal);
  }
 }

 static void addDog(List<? super Animal<String>> list){
  Puppy d= new Puppy("Tom");
  Dog d1= new Dog("xyz");
  list.add(d);
  list.add(d1);

 }
}

@Getter
@Setter
class Animal<T> {
 T type;

 public Animal(T type) {
  this.type=type;
 }
}

class Dog extends Animal<String> {

 public Dog(String type){
  super(type);
 }
}

class Puppy extends Dog {

 public Puppy(String type){
  super(type);
 }
}

class Cat extends Animal<String> {
 public Cat(String type) {
  super(type);
 }
}



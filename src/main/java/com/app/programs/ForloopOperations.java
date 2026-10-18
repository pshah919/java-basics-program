package com.app.programs;

import lombok.AllArgsConstructor;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ForloopOperations {

    public static void main(String[] args) {
        Transaction t= new Transaction("Success",1,"Success","abc");
        Transaction t1= new Transaction("Fail",2,"Fail","xyz");
        Transaction t2= new Transaction("Success",3,"Success","xyz");
        Transaction t3= new Transaction("Fail",4,"Success","abc");
        Transaction t4= new Transaction("Success",5,"Success","abc");
        Transaction t5= new Transaction("Fail",6,"Fail","abc");
        //Filter out failed transactions.

        //Group valid transactions by category.

               // Calculate the total spent per category and return the top 3 highest-spending categories
        List<Transaction> list = List.of(t,t1,t2,t3,t4,t5);

        Map<String, Double> map = list.stream().filter(e-> e.status().equals("Success")).collect(Collectors.groupingBy(Transaction :: category,
                Collectors.summingDouble(Transaction::amount))).entrySet().stream().
                sorted(Map.Entry.<String, Double> comparingByValue().reversed()).limit(3).collect(
                        Collectors.toMap(Map.Entry :: getKey, Map.Entry :: getValue, (e1,e2)->e1, LinkedHashMap::new));


    }
}

record Transaction(String id, double amount, String status, String category) {}

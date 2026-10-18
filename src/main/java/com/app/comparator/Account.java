package com.app.comparator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@AllArgsConstructor
@Setter
@Getter
public class Account implements Comparable<Account>{

    private int balance;
    private String accountId;

    @Override
    public boolean equals(Object account){
        if(account == null){
            return false;
        } else if (account instanceof Account) {

            if((((Account) account).getAccountId()).equals(this.accountId)){
                return true;
            }
        }
        return false;
    }

    @Override
    public int compareTo(Account o) {
        if(o == null){
            return 1;
        }

            int nameComparison = this.accountId.compareTo(o.getAccountId());

            System.out.println(nameComparison);
            if(nameComparison !=0){
                return nameComparison;
            }

        return Integer.compare(balance, o.getBalance());
    }

    public static void main(String[] args) {
        Account account = new Account(20,"A");
        Account account1 = new Account(40,"B");
        Account account2 = new Account(30,"AA");
        List<Account> list = Arrays.asList(account,account1,account2);

        Comparator<Account> balanceComparator= (a,b)->(Integer.compare(b.getBalance(),a.getBalance()));
        list.sort(balanceComparator);
        list.forEach(item -> System.out.println(item.getAccountId()+","+item.getBalance()));


        Collections.sort(list); //will use compareTo method for comaprison
        list.forEach(item -> System.out.println(item.getAccountId()+","+item.getBalance()));


    }
}

package com.app.designpatterns;

import java.util.ArrayList;
import java.util.List;

public class PrototypeExample implements Cloneable{

    private List<String> ipList= new ArrayList<>();

    public PrototypeExample(){
        ipList.add("hi");
    }

    public PrototypeExample clone(){
        try {
            PrototypeExample ex =(PrototypeExample) super.clone();
            ex.ipList= new ArrayList<>(this.ipList);
            return ex;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

}

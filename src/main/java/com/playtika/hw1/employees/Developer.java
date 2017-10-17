package com.playtika.hw1.employees;

import java.math.BigDecimal;

public class Developer extends Employee {
    public Developer(String lastName, String firstName, Positions position, BigDecimal salary) {
        super(lastName, firstName, position, salary);
    }

    public void fixBag(String jiraNamber){
        System.out.println( getLastName() +  ", FIX " + jiraNamber + " IMMEDIATELY!!!");
    }

}

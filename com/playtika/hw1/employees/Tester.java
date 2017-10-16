package com.playtika.hw1.employees;

import java.math.BigDecimal;

public class Tester extends Employee {
    public Tester(String lastName, String firstName,Positions position, BigDecimal salary) {
        super(lastName, firstName, position, salary);
    }

    @Override
    public void working(String jiraNumber) {
        System.out.println("QA will test " + jiraNumber);
    }
}

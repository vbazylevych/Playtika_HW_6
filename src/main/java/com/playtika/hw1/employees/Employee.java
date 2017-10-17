package com.playtika.hw1.employees;

import java.math.BigDecimal;
import java.util.ArrayList;

public abstract class Employee {
    private final String lastName;
    private final String firstName;
    public Positions position;
    private BigDecimal salary;
    public ArrayList<String> tasksDone = new ArrayList<>();

    public Employee(String lastName, String firstName, Positions position, BigDecimal salary) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.position = position;
        this.salary = salary;
    }

    public void working(String jiraNumber) {
        System.out.println(firstName + " " + lastName + " done " + jiraNumber);
        tasksDone.add(jiraNumber);
    }

    public void increaseOfSalary(double percentage) {
        calculateNewSalary(percentage);
        System.out.println("Salary for " + firstName + " " + lastName + " was increased!!!!!");
    }

    private void calculateNewSalary(double percentage) {
        this.salary = BigDecimal.valueOf(percentage * salary.doubleValue() / 100);
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getTaskDone() {
        return tasksDone.get(0);
    }

    public void moveToPosition(Positions newPosition) {
        position = newPosition;
        System.out.println(firstName + " " + lastName + " was moved to position " + newPosition.toString());


    }
}

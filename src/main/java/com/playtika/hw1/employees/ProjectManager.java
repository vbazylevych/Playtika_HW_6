package com.playtika.hw1.employees;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Random;

public class ProjectManager extends Employee {

    public ProjectManager(String lastName, String firstName, BigDecimal salary) {
        super(lastName, firstName, Positions.PM, salary);
    }

    public void working() {
        System.out.println("HRs, hire a lot of Devs and several QAs"+ "\n");
    }

    public void createTeam(ArrayList<Employee> employers, String idea) {

        System.out.println("Our goal is " + idea.toUpperCase()+ "\n");
        System.out.println("Our team is:");
        for (Employee employee : employers) {
            System.out.println(employee.getLastName());
        }
        System.out.println();
    }

    public String giveTask(Employee employee) {
        String jiraNumber = new Random().toString().replace("java.util.Random@", "");
        return jiraNumber;
    }

}


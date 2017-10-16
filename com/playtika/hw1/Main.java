package com.playtika.hw1;

import com.playtika.hw1.customers.Customer;
import com.playtika.hw1.employees.*;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        Customer.setIdea("Create new Casino");


        ProjectManager projectManager = new ProjectManager("Big", "Boss", BigDecimal.valueOf(100501));

        Developer juniorDeveloper = new Developer("Kot", "Igor", Positions.JuniorDeveloper, BigDecimal.valueOf(1005));
        Developer seniorDeveloper = new Developer("Artemenko", "Artem", Positions.SeniorDeveloper, BigDecimal.valueOf(100500));

        Tester tester = new Tester("Koshkina","Koshkva", Positions.JuniorQA, BigDecimal.valueOf(500));

        DevOps devops = new DevOps("Nash", "Jenia", BigDecimal.valueOf(100499));

        projectManager.working();
        ArrayList<Employee> employees = new ArrayList();
        employees.add(seniorDeveloper);
        employees.add(juniorDeveloper);
        employees.add(tester);

        projectManager.createTeam(employees, Customer.getIdea());

        juniorDeveloper.working(projectManager.giveTask(juniorDeveloper));
        seniorDeveloper.working(projectManager.giveTask(juniorDeveloper));

        tester.working(juniorDeveloper.getTaskDone());
        tester.working(seniorDeveloper.getTaskDone());

        juniorDeveloper.fixBag(juniorDeveloper.getTaskDone());
        juniorDeveloper.working(juniorDeveloper.getTaskDone());

        tester.working(juniorDeveloper.getTaskDone());

        tester.moveToPosition(Positions.SeniorQA);
        tester.increaseOfSalary(15);

        devops.working(seniorDeveloper.getTaskDone());
        devops.working(juniorDeveloper.getTaskDone());


    }
}

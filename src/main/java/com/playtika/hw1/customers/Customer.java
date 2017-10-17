package com.playtika.hw1.customers;

public   class Customer {
    private static String idea ;

    public static String getIdea() {
        return "Bla Bla "+idea + "Bla Bla " ;
    }

    public static void setIdea(String idea) {
        Customer.idea = idea;
    }
}

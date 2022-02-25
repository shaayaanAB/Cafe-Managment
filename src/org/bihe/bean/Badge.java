package org.bihe.bean;

public class Badge {

    private String name;
    private int offPercent;

    Badge(String name, int offPercent) {
        this.name = name;
        this.offPercent = offPercent;
    }

    public String getName() {
        return name;
    }

    void print(Customer customer) {
        System.out.println(customer.getName() + " have a " + this.name + "!");
    }

    double submitBadge(double cost) {
        return cost * this.offPercent / 100;
    }

}

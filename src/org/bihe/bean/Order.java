package org.bihe.bean;

import java.time.LocalDateTime;
import java.util.ArrayList;

class Order {

    private int id = 0;  // set Automatically
    private double costBeforeOff;
    private double costAfterOff;
    private ArrayList<String> stuff;
    private LocalDateTime date;

    Order(ArrayList<String> stuff, double costBeforeOff, double costAfterOff, LocalDateTime date) {
        this.id = setId();
        this.date = date;
        this.costBeforeOff = costBeforeOff;
        this.costAfterOff = costAfterOff;
        this.stuff = stuff;
    }

    double getCostAfterOff() {
        return costAfterOff;
    }

    private int setId() {
        this.id ++;
        return id;
    }

    static double calcOrderXp(double costAfterOff) {
        return costAfterOff / 10_000;
    }

}
package org.bihe.bean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

class Customer {

    private String name;
    private String address;
    private String nationalId;
    private String tel;
    private LocalDateTime birthDate;
    private int xp;   // 5 xp will be given as gift for joining our club!
    private double totalBought;
    private ArrayList<Badge> badges;
    private ArrayList<Order> orders;

    private Customer(String name, String address, String nationalId, String tel, LocalDateTime birthDate) {
        this.name = name;
        this.address = address;
        this.nationalId = nationalId;
        this.tel = tel;
        this.birthDate = birthDate;
        this.xp = 5;
        this.totalBought = 0;
        this.badges = new ArrayList<>();
        this.orders = new ArrayList<>();
    }

    double getTotalBought() {
        return totalBought;
    }
    void setTotalBought(double totalBought) {
        this.totalBought = totalBought;
    }
    String getName() {
        return name;
    }
    void setAddress(String address) {
        this.address = address;
    }
    String getNationalId() {
        return nationalId;
    }
    void setTel(String tel) {
        this.tel = tel;
    }
    ArrayList<Badge> getBadges() {
        return badges;
    }
    ArrayList<Order> getOrders() {
        return orders;
    }

    static boolean createCustomer(CustomerManager customerManager,String name,String address,String nationalId,String tel,int monthOfBirth,int dayOfBirth) {
        // set birth date
        LocalDateTime birthDate = LocalDateTime.of(0,monthOfBirth,dayOfBirth,0,0,0);
        // create and add the new customer
        Customer newCustomer = new Customer(name, address, nationalId, tel, birthDate);
        if (customerManager.addNewCustomerToList(newCustomer)) {
            return true;
        } else {
            return false;
        }
    }

    void print() {
        System.out.print(this.name + " with ID number : " + this.nationalId + ", telephone number : " + this.tel);
        System.out.println(" lives in " + this.address + " has " + this.xp + " XPs !");
    }

    private void addXpToCustomerAccount(Double costAfterOff) {
        this.xp += Order.calcOrderXp(costAfterOff);
    }

    private void addCostToCustomerTotalBought(double costAfterOff) {
        this.totalBought += costAfterOff;
    }

    // add XP and stuffs and total cost to customer's account
    void saveOrderInCustomerAccount(ArrayList<String> stuff, double cost, double costAfterOff, BadgeManager badgeManager) {
        Order newOrder = new Order(stuff, cost, costAfterOff, today());
        this.getOrders().add(newOrder);
        addXpToCustomerAccount(costAfterOff);
        achieveAnyBadge(badgeManager);
        addCostToCustomerTotalBought(costAfterOff);
    }

    double checkForDiscount(double cost) {
        cost = usingBadges(cost);
        cost -= checkingAndSubmitBirthdayOff(cost);
        return cost;
    }

    private double checkingAndSubmitBirthdayOff(double cost) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd");
        LocalDateTime now = LocalDateTime.now();
        dateTimeFormatter.format(now);
        if (dateTimeFormatter.format(now).equals(dateTimeFormatter.format(this.birthDate))) {
            birthdayCongrats();
            return cost * 20 / 100;
        }
        return cost;
    }

    private double usingBadges(double cost) {
        for (Badge badge : this.getBadges()) {
           cost -= badge.submitBadge(cost);
        }
        return cost;
    }

    private boolean checkXpForAddingBadge(BadgeManager badgeManager) {
        if (this.xp > 100 && this.xp < 500 && ! hasBadge("Bronze Badge")) {
            Badge bronze = badgeManager.findBadge("Bronze Badge");
            addBadgeToCustomer(bronze);
        } else if (this.xp > 500 && this.xp < 900 && ! hasBadge("Silver Badge")) {
            Badge silver = badgeManager.findBadge("Bronze Badge");
            addBadgeToCustomer(silver);
        } else if (this.xp > 900 && this.badges.size() == 2) {
            Badge gold = badgeManager.findBadge("Bronze Badge");
            addBadgeToCustomer(gold);
        }
        return false;
    }

    private void achieveAnyBadge(BadgeManager badgeManager) {
        if (checkXpForAddingBadge(badgeManager)) {
            System.out.println("* * * * * * * * * * * * * * * * * * *");
            System.out.println("Congratulations!!!");
            System.out.println("New badge has been achieved!");
            System.out.println("* * * * * * * * * * * * * * * * * * *");
        }
    }

    private static LocalDateTime today() {
        return LocalDateTime.now();
    }

    void addBadgeToCustomer(Badge badge) {
        switch (badge.getName()) {
            case "Bronze Badge" :
                if (hasBadge("Bronze Badge")) {
                    break;
                }
                this.badges.add(badge);
                break;

            case "Silver Badge" :
                if (hasBadge("Silver Badge")) {
                    break;
                }
                this.badges.add(badge);

            case "Gold Badge" :
                if (hasBadge("Gold Badge")) {
                    break;
                }
                this.badges.add(badge);
        }
    }

    // search if customer has the badge, system wont add same again.
    private boolean hasBadge(String badgeName) {
        for (Badge badge : this.badges) {
            if (badge.getName().equals(badgeName)) {
                return true;
            }
        }
        return false;
    }

    private void birthdayCongrats() {
        System.out.println("\nHappy Birth day dear " + this.name);
        System.out.println("Today you will have a additional 20% discount from us!");
    }

}
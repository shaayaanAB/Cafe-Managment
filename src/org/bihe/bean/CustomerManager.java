package org.bihe.bean;

import java.util.ArrayList;

public class CustomerManager {

    private ArrayList<Customer> customers;

    public CustomerManager() {
        this.customers = new ArrayList<>();
    }

    boolean addNewCustomerToList(Customer newCustomer) {
        for (Customer customer : this.customers) {
            if (customer.getNationalId().equals(newCustomer.getNationalId())) {
                return false;
            }
        }
        customers.add(newCustomer);
        return true;
    }

    void sortingCustomersByTotalBought() {
        Customer temp;
        for (int i = 0; i < this.customers.size() - 1; i++) {
            for (int j = 0; j < this.customers.size(); j++) {
                if (this.customers.get(i).getTotalBought() < this.customers.get(j).getTotalBought()) {
                    temp = this.customers.get(i);
                    this.customers.set(i, this.customers.get(j));
                    this.customers.set(j, temp);
                }
            }
        }
    }

    void printCustomers() {
        for (Customer customer : this.customers) {
            customer.print();
        }
        Main.printSeprateLine();
    }

    double calcTotalSells() {
        double totalSells = 0;
        for (Customer customer : this.customers) {
            for (Order order : customer.getOrders()) {
                totalSells += order.getCostAfterOff();
            }
        }
        return totalSells;
    }

    Customer findCustomerByNationalId(String nationalId) {
        for (Customer customer : this.customers) {
            if (customer.getNationalId().equals(nationalId)) {
                return customer;
            }
        }
        return null;
    }

    void showAllCustomers() {
        if (this.customers.size() <= 0) {       // see if any customers exist to show.
            System.err.println("No customer has been registered.");
            Main.printSeprateLine();
        } else {
            for (Customer customer : this.customers) {
                customer.print();
            }
            Main.printSeprateLine();
        }
    }

    boolean resetCustomersBought() {
        if (this.customers.size() == 0) {
            return false;
        } else {
            for (Customer customer : this.customers) {
                customer.setTotalBought(0);
            }
        }
        return true;
    }

}

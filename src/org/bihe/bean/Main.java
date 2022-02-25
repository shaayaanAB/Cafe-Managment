package org.bihe.bean;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        AdminManager adminManager = new AdminManager();
        CustomerManager customerManager = new CustomerManager();
        BadgeManager badgeManager = new BadgeManager();

        badgeManager.createBadge("Bronze Badge", 5);
        badgeManager.createBadge("Silver Badge", 10);
        badgeManager.createBadge("Gold Badge", 15);

        adminManager.createAndAddNewAdmin("Test Admin", "aaa", "111");
        adminManager.createAndAddNewAdmin("Shayan Afghan Badie", "shayan_a_b", "shaYan78");
        adminManager.createAndAddNewAdmin("Mohammad Mohammadi", "mohammad", "mamad1234");

//        Customer.createCustomer(customerManager, "Shahla Badie", "Pardis", "006123452", "09121020370", 8, 26);

        badgeManager.createBadge("Bronze Badge", 5);
        badgeManager.createBadge("Silver Badge", 10);
        badgeManager.createBadge("Gold Badge", 15);

        Scanner keyboard = new Scanner(System.in);

        generateLoginMenu(adminManager, keyboard, customerManager, badgeManager);

    }

    private static void generateLoginMenu(AdminManager adminManager, Scanner keyboard, CustomerManager customerManager, BadgeManager badgeManager) {
        System.out.println("<< Hello and welcome >>\n");
        System.out.println("Please login to your account.\n");
        do {
            System.out.print("Username : ");
            String username = keyboard.next();
            System.out.print("Password : ");
            String password = keyboard.next();

            if (!adminManager.Login(username, password)) {
                System.err.println("WRONG USERNAME / PASSWORD");
                printSeprateLine();
            } else {
                System.out.println("\nYou have logged in successfully.");
                printSeprateLine();
                generateMainMenu(keyboard, adminManager, customerManager, badgeManager);
            }
        } while (true);
    }

    private static void generateMainMenu(Scanner keyboard, AdminManager adminManager, CustomerManager customerManager, BadgeManager badgeManager) {
        int userChoice;
        do {
            System.out.println("1. Take Order\n2. Financial Management\n3. Customer Management\n4. Admin Management\n5. Exit\n");
            System.out.print("Please choose your action : ");
            userChoice = keyboard.nextInt();
            printSeprateLine();
            switch (userChoice) {
                case 1:       // Take order
                    takeOrder(keyboard, customerManager, badgeManager);
                    break;

                case 2:       // Financial management
                    financialManagementMenu(keyboard, customerManager);
                    break;

                case 3:       // Customer management
                    customerManagementMenu(keyboard, customerManager, badgeManager);
                    break;

                case 4:       // Admin management
                    adminManagementMenu(keyboard, adminManager);
                    break;

                case 5:      // Exit
                    System.out.println("HAVE A GOOD TIME.");
                    System.exit(0);

                default:
                    System.err.println("\nYour choice is wrong.");
                    printSeprateLine();
            }
        } while (true);
    }

    private static void financialManagementMenu(Scanner keyboard, CustomerManager customerManager) {
        int userChoice;
        do {
            System.out.println("1. Total sells.\n2. A customer total bought.\n3. List of customers by total bought.");
            System.out.println("4. Reset all the sells and orders.\n5. Back\n");
            System.out.print("Please choose your action : ");
            userChoice = keyboard.nextInt();
            printSeprateLine();

            switch (userChoice) {
                case 1:    // Total sells.
                    showTotalSells(customerManager);
                    break;

                case 2:    // A customer total bought
                    showCustomerTotalBought(keyboard, customerManager);
                    break;

                case 3:    // List of customers by total bought
                    sortAndShowCustomersByTotalBought(customerManager);
                    break;

                case 4:    // Reset
                    resetCustomersBought(customerManager);
                    break;

                case 5:    // Back to main menu
                    break;

                default:
                    System.err.println("\nYour choice is wrong.");
                    printSeprateLine();
            }
        } while (userChoice != 5);
    }

    private static void customerManagementMenu(Scanner keyboard, CustomerManager customerManager, BadgeManager badgeManager) {
        int userChoice;
        do {
            System.out.println("1. List of all customers.\n2. Change customer telephone number.\n3. Change customer address");
            System.out.println("4. Create new customer account.\n5. Add a badge to a customer.\n6. Show a customer badges.\n7. Back\n");
            System.out.print("Please choose your action : ");
            userChoice = keyboard.nextInt();
            printSeprateLine();

            switch (userChoice) {
                case 1:    // Showing list of customers
                    customerManager.showAllCustomers();
                    break;

                case 2:    // Change customer telephone number
                    changeCustomerTelephoneNumber(keyboard, customerManager);
                    break;

                case 3:    // Change customer address
                    changeAddress(keyboard, customerManager);
                    break;

                case 4:    // Create new customer account
                    createCustomer(keyboard, customerManager);
                    break;

                case 5 :   // adding badge manually
                    addingBadgeManually(keyboard, customerManager, badgeManager);
                    break;

                case 6 :   // Show a selected customer badges
                    showCustomerBadges(keyboard, customerManager);
                    break;

                case 7:    // Back to main menu
                    break;

                default:
                    System.err.println("\nYour choice is wrong.");
                    printSeprateLine();
            }
        } while (userChoice != 7);
    }

    private static void adminManagementMenu(Scanner keyboard, AdminManager adminManager) {
        int userChoice;
        do {
            System.out.println("1. List of all admins.\n2. Create new admin.\n3. Change your password\n4. Back\n");
            System.out.print("Please choose your action : ");
            userChoice = keyboard.nextInt();
            printSeprateLine();

            switch (userChoice) {
                case 1:    // Showing list of admins
                    adminManager.showAllAdmins();
                    break;

                case 2:    // Create new admin
                    createAdmin(keyboard, adminManager);
                    break;

                case 3:    // Changing password
                    changePassword(keyboard, adminManager);
                    break;

                case 4:    // Back to main menu
                    break;

                default:
                    System.err.println("\nYour choice is wrong.");
                    printSeprateLine();
            }
        } while (userChoice != 4);
    }

    private static void createAdmin(Scanner keyboard, AdminManager adminManager) {
        System.out.println("<< Creating New Admin >>");
        System.out.println("Enter your name : ");
        String name = keyboard.next();

        System.out.print("Choose a username : ");
        String username = keyboard.next();

        System.out.print("Choose a password : ");
        String password = keyboard.next();

        if (adminManager.createAndAddNewAdmin(name, username, password)) {
            System.out.println("\nThe new admin has successfully added to system.");
            printSeprateLine();
        } else {
            System.err.println("\nAn admin is exist in system with chosen username.");
            printSeprateLine();
        }
    }

    private static void createCustomer(Scanner keyboard, CustomerManager customerManager) {
        System.out.println("<< Creating New Customer >>\n");
        System.out.print("Enter name : ");
        String name = keyboard.next();
        System.out.print("Enter telephone number : ");
        String tel = keyboard.next();
        System.out.print("Enter national ID number : ");
        String nationalId = keyboard.next();
        System.out.print("Enter address : ");
        String address = keyboard.next();
        System.out.print("Enter month of birth : ");
        int month = keyboard.nextInt();
        System.out.print("Enter day of birth : ");
        int day = keyboard.nextInt();

        if (Customer.createCustomer(customerManager, name, address, nationalId, tel, month, day)) {
            System.out.println("\nNew customer has successfully added.");
            printSeprateLine();
        } else {
            System.out.println("\nThis customer is already registered.");
            printSeprateLine();
        }
    }

    private static void changeAddress(Scanner keyboard, CustomerManager customerManager) {
        System.out.println("<< Changing Address >>\n");
        Customer customerAccount = getIdAndFindCustomer(keyboard, customerManager);

        if (customerAccount == null) {
            System.err.println("No customer with this ID were found.");
            printSeprateLine();
        } else {
            System.out.print("Please enter new address for " + customerAccount.getName() + " : ");
            String newAddress = keyboard.next();

            customerAccount.setAddress(newAddress);
            printSeprateLine();
        }
    }

    private static void changeCustomerTelephoneNumber(Scanner keyboard, CustomerManager customerManager) {
        System.out.println("<< Changing Telephone Number >>\n");
        Customer customerAccount = getIdAndFindCustomer(keyboard, customerManager);

        if (customerAccount == null) {
            System.err.println("No customer with this ID were found.");
            printSeprateLine();
        } else {
            System.out.print("Please enter new telephone number for " + customerAccount.getName() + " : ");
            String newTelephone = keyboard.next();

            customerAccount.setTel(newTelephone);
            printSeprateLine();
        }
    }

    private static void changePassword(Scanner keyboard, AdminManager adminManager) {
        System.out.println("<< Changing Password >>\n");
        System.out.print("Enter your username : ");
        String username = keyboard.next();

        System.out.print("Enter your current password : ");
        String currentPass = keyboard.next();

        System.out.print("Enter new password : ");
        String newPass = keyboard.next();

        System.out.print("Repeat new password : ");
        String repeatedPass = keyboard.next();

        Admin userAccount = adminManager.findAdminByUsername(username); // find admin's account by username

        if (!userAccount.checkAndChangingPassword(currentPass, newPass, repeatedPass)) {
            System.err.println("Wrong username/password");
            printSeprateLine();
        } else {
            System.out.println("Password successfully changed.");
            printSeprateLine();
        }
    }

    private static void showTotalSells(CustomerManager customerManager) {
        double totalSells = customerManager.calcTotalSells();
        System.out.println("Company's total sells is " + totalSells + " Rials.");
        printSeprateLine();
    }

    private static void resetCustomersBought(CustomerManager customerManager) {
        if (customerManager.resetCustomersBought()) {
            System.out.println("All customers total bought was successfully reset.");
            printSeprateLine();
        } else {
            System.err.println("No customer were found.");
            printSeprateLine();
        }
    }

    private static void sortAndShowCustomersByTotalBought(CustomerManager customerManager) {
        customerManager.sortingCustomersByTotalBought();
        customerManager.printCustomers();
        printSeprateLine();
    }

    private static void takeOrder(Scanner keyboard, CustomerManager customerManager, BadgeManager badgeManager) {
        System.out.println("<< Taking Order >>\n");
        Customer customerAccount = getIdAndFindCustomer(keyboard, customerManager);

        if (customerAccount == null) {
            System.err.println("No customer with this ID were found.");
            printSeprateLine();
        } else {
            double totalCost = 0;
            ArrayList<String> stuff = new ArrayList<>();
            do {
                System.out.print("Please type the order or enter 0 to finish ordering : ");
                String thingOrdered = keyboard.next();

                if (thingOrdered.equals("0")) {  // Finish the order
                    finishTakingOrder(customerAccount, stuff, totalCost, badgeManager);
                    break;
                }
                System.out.println("Please enter the price : ");
                double cost = keyboard.nextInt();

                totalCost += cost;
                stuff.add(thingOrdered);
            } while (true);
        }
    }

    private static void finishTakingOrder(Customer customerAccount, ArrayList<String> stuff, double totalCost, BadgeManager badgeManager) {
        double costAfterOff = customerAccount.checkForDiscount(totalCost);  // calc cost with discount
        customerAccount.saveOrderInCustomerAccount(stuff, totalCost, costAfterOff, badgeManager);  // Saving order
        System.out.println("\nThe receipt is " + totalCost + " Rials.");
        System.out.println("You should get " + costAfterOff + " Rials from " + customerAccount.getName());
        printSeprateLine();
    }

    private static Customer getIdAndFindCustomer(Scanner keyboard, CustomerManager customerManager) {
        System.out.print("Please enter customer's ID : ");
        String id = keyboard.next();
        return customerManager.findCustomerByNationalId(id);
    }

    public static void addingBadgeManually(Scanner keyboard, CustomerManager customerManager, BadgeManager badgeManager) {
        Customer customerAccount = getIdAndFindCustomer(keyboard, customerManager);
        if (customerAccount == null) {
            System.err.println("No customer with this ID were found.");
            printSeprateLine();
        } else {
            System.out.println("Which badge you want to give " + customerAccount.getName() + " ?");
            System.out.println("1. Bronze Badge.\n2. Silver Badge.\n3. Gold Badge");
            System.out.println("Please choose your acion : ");
            int userChoice = keyboard.nextInt();
            if (userChoice == 1) {
                customerAccount.addBadgeToCustomer(badgeManager.findBadge("Bronze Badge"));
            } else if (userChoice == 2) {
                customerAccount.addBadgeToCustomer(badgeManager.findBadge("Silver Badge"));
            } else if (userChoice == 3) {
                customerAccount.addBadgeToCustomer(badgeManager.findBadge("Gold Badge"));
            } else {
                System.err.println("\nYour choice is wrong.");
                printSeprateLine();
            }
        }
    }

    private static void showCustomerBadges(Scanner keyboard, CustomerManager customerManager) {
        Customer customer = getIdAndFindCustomer(keyboard, customerManager);
        if (customer == null) {
            System.err.println("\nNo customer with this ID were found.");
            printSeprateLine();
        } else if (customer.getBadges().size()==0) {
            System.err.println("\n" + customer.getName() + " has no badge.");
            printSeprateLine();
        } else {
            System.out.println();
            for (Badge badge : customer.getBadges()) {
                badge.print(customer);
            }
            printSeprateLine();
        }
    }

    private static void showCustomerTotalBought(Scanner keyboard, CustomerManager customerManager) {
        Customer customer = getIdAndFindCustomer(keyboard, customerManager);
        System.out.println("\n" + customer.getName() + " has a total bought of " + customer.getTotalBought() + " Rials from us.\n");
        printSeprateLine();

    }

    public static void printSeprateLine() {
        System.out.println("\n-------------------------\n");
    }

}
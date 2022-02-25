package org.bihe.bean;

import java.util.ArrayList;

class AdminManager {

    private ArrayList<Admin> admins;

    AdminManager() {
        this.admins = new ArrayList<>();
    }

    boolean Login(String username, String password) {
        for (Admin admin : this.admins) {
            if (username.equals(admin.getUsername()) && password.equals(admin.getPassword())) {
                return true;
            }
        }
        return false;
    }

    boolean createAndAddNewAdmin(String name, String username, String password) {
        Admin newAdmin = new Admin(name, username, password);
        if (isUsernameUnique(username)) {
            this.admins.add(newAdmin);
            return true;
        }
        return false;
    }

    void showAllAdmins() {
        for (Admin admin : this.admins) {
            System.out.println(admin.getName());
        }
        Main.printSeprateLine();
    }

    Admin findAdminByUsername(String username) {
        for (Admin admin : this.admins) {
            if (admin.getUsername().equals(username)) {
                return admin;
            }
        }
        return null;
    }

    private boolean isUsernameUnique(String username) {
        for (Admin admin : this.admins) {
            if (admin.getUsername().equals(username)) {
                return false;
            }
        }
        return true;
    }

}
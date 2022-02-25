package org.bihe.bean;

class Admin {

    // constructors, getters and setters and methods are all package - private in this code!

    private String name;
    private String username;
    private String password;

    Admin(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }

    String getUsername() {
        return username;
    }
    String getName() {
        return name;
    }
    String getPassword() {
        return password;
    }

    boolean checkAndChangingPassword(String currentPass, String newPass, String repeatedPass) {
        if (!this.password.equals(currentPass)) {
            return false;
        } else if ( ! newPass.equals(repeatedPass)) {
            return false;
        } else {
            this.password = newPass;
        }
        return true;
    }

}
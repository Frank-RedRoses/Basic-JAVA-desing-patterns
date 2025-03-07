package com.caveofprogramming.designpattern.logindemo.view;

/**
 * This class is used to create an instance that stores data from
 * the login form and transmit it when a login event is triggered.
 * This class is another example of a Bean.
 */
public class CreateUserEvent {
    private String name;
    private String password;

    public CreateUserEvent(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

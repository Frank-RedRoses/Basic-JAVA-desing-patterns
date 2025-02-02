package com.caveofprogramming.designpattern.logindemo.view;

/**
 * This interface must be implemented by the CreateUser Observer class.
 * It declares the onUserCreated() method that is listen to a user creation event.
 */
public interface CreateUserListener {
    public void onUserCreated(CreateUserEvent event);
}

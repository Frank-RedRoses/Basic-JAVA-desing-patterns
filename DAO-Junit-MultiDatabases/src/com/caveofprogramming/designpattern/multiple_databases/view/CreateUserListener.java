package com.caveofprogramming.designpattern.multiple_databases.view;

/**
 * This interface must be implemented by the Login Observer class.
 * It declares the loginPerform() method.
 */
public interface CreateUserListener {
    public void userCreated(CreateUserEvent event);
}

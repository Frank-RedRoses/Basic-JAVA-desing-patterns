package com.caveofprogramming.designpattern.dao_factory.view;

/**
 * This interface must be implemented by the Login Observer class.
 * It declares the loginPerform() method.
 */
public interface CreateUserListener {
    public void userCreated(CreateUserEvent event);
}

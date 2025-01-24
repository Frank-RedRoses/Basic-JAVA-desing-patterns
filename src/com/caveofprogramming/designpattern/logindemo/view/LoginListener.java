package com.caveofprogramming.designpattern.logindemo.view;

/**
 * This interface must be implemented by the Login Observer class.
 * It declares the loginPerform() method.
 */
public interface LoginListener {
    public void loginPerform(LoginFormEvent event);
}

package com.caveofprogramming.designpattern.logindemo.view;

/**
 * Interface that declares methods for listening to events associated
 * with the application windows, such as open or closing the application.
 */
public interface AppListener {
    /**
     * Invoke when the application window is open.
     */
    public void onOpen();

    /**
     * Invoke when the application window is close.
     */
    public void onClose();
}

package com.caveofprogramming.designpattern.logindemo.view;

/**
 * Listener interface for handling save events triggered from the "Save"
 * option in the "File" menu of the application.
 * <p>
 * This interface helps to decouple the View from the Controller by
 * allowing the Controller to implement the {@code onSave()} method.
 * The Controller handles saving new {@code Person} entries from a
 * list to the database.
 * </p>
 */
public interface SaveListener {

    /**
     * Invoked when a save action is triggered.
     */
    public void onSave();
}

package com.caveofprogramming.designpattern.logindemo.controller;

import com.caveofprogramming.designpattern.logindemo.model.*;
import com.caveofprogramming.designpattern.logindemo.view.*;

import java.sql.SQLException;

/**
 * This class handles the business logic of the application.
 * The business logic refers to the code that is responsible for processing
 * the data, but not directly interacting with either the data or the user interface.
 * <br>
 * As with the other classes and packages, this class and the root package
 * may not necessarily be named {@code Controller}.<br>
 * The {@code Controller} sends commands to both the View and the Model. It is
 * almost certainly listening to the View, but may or may not listen to the Model.
 */
public class Controller implements CreateUserListener, SaveListener, AppListener {
    private final Model model;
    private final View view;

    /**
     * The {@code Controller} constructor receives references to the {@code view} and
     * the {@code model}. This allows the {@code Controller} to interact with both components.
     *
     * @param view  a reference to the {@code view}
     * @param model a reference to the {@code model}
     */
    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
    }

    /**
     * This is the implementation of the method defined in
     * the CreateUserListener interface.
     * <p>
     * {@code onUserCreated()} is called when an user creation event
     *  is triggered by the {@code createUserButton}; and temporarily
     *  adds a new {@code Person} to list in the GUI.
     * </p>
     *
     * @param event user create event triggered on pressing a {@code JButton}
     */
    @Override
    public void onUserCreated(CreateUserEvent event) {
        // The validation and verification of the name and password
        // should be performed in the `View`.
        model.addPerson(new Person(event.getName(), event.getPassword()));
    }

    /**
     * Persists new or updated {@code Person} instances from the GUI's
     * list of people to the {@code Database}.
     */
    @Override
    public void onSave() {
        try {
            model.save();
        } catch (Exception e) {
            view.showError("Error saving to the database");
        }
    }

    /* **************** Singleton pattern *********************** */
    /* Database db = new Database();  "new" keyword cannot be used by
     * external classes to create instances.
     * This is how to access an instance when using the Singleton pattern:
     * Database db = Database.getInstance();   // But, adds Global variable disadvantage
     * Other option is to call static methods using the database instance:
     */

    /**
     * Connects to the database.
     * Implements singleton pattern static methods.
     */
    @Override
    public void onOpen() {
        try {
            Database.getInstance().connect();
        } catch (Exception e) {
            view.showError("Unable to connect to the database");
        }

        try {
            model.load();
        } catch (Exception e) {
            view.showError("Error loading data from database");
        }
    }

    /**
     * Disconnects from the database.
     * Implements singleton pattern static methods.
     */
    @Override
    public void onClose() {
        Database.getInstance().disconnect();
    }
    /* Notes on the Singleton:
     * Whether you retrieve your database instance each timer or maintain
     * single upfront reference depends on how often you need to use it.
     * If you only use it a few times, it is better to rely on static methods.
     * However, if you need to use your singleton in several places throughout
     * your code, it is better to maintain an upfront reference.
     *
     * Another possibility is to use a connection pool, where you simply grab
     * a connection from the pool,use it, and then release it back to the pool.
     * The connection remains available in the pool for future use. This is an
     * excellent way to manage connections if you are willing to handle the
     * setup.
     */
    /* ************ End of Singleton pattern ******************* */
}

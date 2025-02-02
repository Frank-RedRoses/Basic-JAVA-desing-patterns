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
     * @param view a reference to the {@code view}
     * @param model a reference to the {@code model}
     */
    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
    }

    /**
     * This is the implementation of the method defined in the LoginListener interface
     */
    @Override
    public void onUserCreated(CreateUserEvent event) {

        // Here the specific database DAO factory is obtained
        DAOFactory factory = DAOFactory.getFactory(DAOFactory.MYSQL);

        PersonDAO personDAO = factory.getPersonDAO(); // personDAO with MySQL implementation.

        System.out.println("Login event received: " + event.getName() + "; " + event.getPassword());

        // The validation and verification of the name and password
        // should be performed in the `View`.
        try {
            personDAO.addPerson(new Person(event.getName(), event.getPassword()));
        } catch (SQLException e) {
            // Here, I could add code to send the error to the View
            // and implement a method in the View that displays the error.
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onSave() {
        // To Do: implement logic that pass the data of new people from a
        // list of people that holds the Person data of the database.
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

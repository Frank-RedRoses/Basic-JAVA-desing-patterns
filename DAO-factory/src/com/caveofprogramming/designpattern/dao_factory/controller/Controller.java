package com.caveofprogramming.designpattern.dao_factory.controller;

import com.caveofprogramming.designpattern.dao_factory.model.DAOFactory;
import com.caveofprogramming.designpattern.dao_factory.model.Model;
import com.caveofprogramming.designpattern.dao_factory.model.Person;
import com.caveofprogramming.designpattern.dao_factory.model.PersonDAO;
import com.caveofprogramming.designpattern.dao_factory.view.CreateUserEvent;
import com.caveofprogramming.designpattern.dao_factory.view.CreateUserListener;
import com.caveofprogramming.designpattern.dao_factory.view.View;

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
public class Controller implements CreateUserListener {
    private final Model model;
    private final View view;

    // For the convenience of this example, a PersonDAO is instantiated here.
    // In the future, a DAOFactory will handle with DAOs.
    private PersonDAO personDAO = DAOFactory.getPersonDAO();

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
    public void userCreated(CreateUserEvent event) {
        System.out.println("Login event received: " + event.getName() + "; " + event.getPassword());

        // The validation and verification of the name and the password should be performed in the `View`.
        try {
            personDAO.addPerson(new Person(event.getName(), event.getPassword()));
        } catch (SQLException e) {
            // Here, I could add code to send the error to the View
            // and implement a method in the View to displays the error.
            throw new RuntimeException(e);
        }
    }
}

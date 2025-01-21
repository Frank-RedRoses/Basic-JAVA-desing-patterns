package com.caveofprogramming.designpattern.demo1.controller;

import com.caveofprogramming.designpattern.demo1.model.Model;
import com.caveofprogramming.designpattern.demo1.view.LoginFormEvent;
import com.caveofprogramming.designpattern.demo1.view.LoginListener;
import com.caveofprogramming.designpattern.demo1.view.View;

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
public class Controller implements LoginListener {
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
    public void loginPerform(LoginFormEvent event) {
        System.out.println("Login event received: " + event.getName() + "; " + event.getPassword());
    }
}

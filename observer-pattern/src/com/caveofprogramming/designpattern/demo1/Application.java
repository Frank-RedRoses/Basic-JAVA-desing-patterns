package com.caveofprogramming.designpattern.demo1;

import com.caveofprogramming.designpattern.demo1.controller.Controller;
import com.caveofprogramming.designpattern.demo1.model.Model;
import com.caveofprogramming.designpattern.demo1.view.View;

import javax.swing.*;

/**
 * This application is an example of the Model-View-Controller (MVC) design pattern.
 * It is intended to demonstrate how the MVC pattern is valuable for large applications.
 * <br>
 * For smaller applications, this pattern may not be always necessary. In some cases,
 * the combination of just the View and the Model might suffice, and the Controller may
 * not be needed. However,separating the front end (View) from the data in the back end
 * (Model) remains essential.<br>
 * For this application let's suppose we have a Swing application that has some complexity;
 * most games would fall into this category.
 */
public class Application {

    public static void main(String[] args) {
        // This block of code from here is specific to Swing and not MVC pattern related.
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                runApp();  // This is the entry point of our application
            }
        });
    }

    // Inside this method we implement the MVC pattern. (This method's name does not matter).
    private static void runApp() {
        Model model = new Model();
        View view = new View(model);    // The model will listen to the view
        Controller controller = new Controller(view, model);
        /* The controller send commands to the View and the Model, and it is almost certainly
         * listening to the View, but may or may not listen to the Model. So, this code will
         * probably be written as it listen to the model.
         */

        // To do: Implement the observer pattern to make the Controller listen to the View
    }
}

package com.caveofprogramming.designpattern.logindemo;

import com.caveofprogramming.designpattern.logindemo.controller.Controller;
import com.caveofprogramming.designpattern.logindemo.model.Model;
import com.caveofprogramming.designpattern.logindemo.view.View;

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

    // To Do:
    // Update the list of people in the View to display new added person.
    // The logic on the Model to save new data on the list to the database
    // when the save button is click.


    public static void main(String[] args) {
        // This block of code from here is specific to Swing and not MVC pattern related.
        /*
         * This is the recommended way to create a Swing
         * event dispatch thread -- i.e. to run a Swing
         * program.
         */
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                runApp();  // This is the entry point of our application
            }
        });
    }

    /**
     * This method utilizes the MVC pattern. The name of the method is not significant.</br>
     * This code also implements the Observer pattern where the controller acts as the Observer,
     * while the View serves as the Subject.
     * When the User fills out the form and clicks the "OK" button, we want the View
     * to trigger a login event to indicate the user is attempting to log in. The Controller
     * is notified of this event and receives some information with this notification,
     * which, in this particular case, includes the data entered into a form.
      */
    private static void runApp() {
        /*
         * Create the model. It might seem artificial to have just
         * one class named 'Model.' In some cases, you might not even
         * need such a class. What's most important is having a package
         * named 'model' (or something similar), where all your model-related
         * code can reside, regardless of how many classes you include.
         */
        Model model = new Model();

        /*
         * Once again, you'll likely have a 'view' package, rather than just
         * a single 'View' class.
         *
         * The view typically listens to the model, though it's worth noting
         * that some MVC implementations restrict the view from directly
         * interacting with the model. In such cases, only the controller
         * classes are allowed to interact with the model classes.
         */
        View view = new View(model);    // The Model listens to the View.

        /*
         * Finally, the controller should be a package as well, and you
         * may have multiple controllers, a single controller with many
         * sub-controllers, or other variations. For many applications,
         * there will be just one controller that handles routing all messages.
         * It listens to both the view and model, and instructs them on
         * what actions to take.
         */
        Controller controller = new Controller(view, model);
        /* Something to point out is that the Controller almost certainly
         * listens to the View, but may or may not listen to the Model.
         * In this app, it sends commands to both the View and the Model.
         * */

        // Implementation of the Observer pattern
        view.setCreateUserListener(controller);
        view.setSaveListener(controller);
        view.setAppListener(controller);
        /* The controller is abstracted behind an interface, ensuring that
         * the View and the Controller are not tightly coupled.
         */

        /*
         * In this implementation of MVC, the view listens to the model
         * and updates itself when the model changes. However, some
         * argue that the view should not directly interact with the
         * model. Instead, the controller should listen to the model
         * and instruct the view to update by calling methods within
         * the view package.
         */
        model.setPeopleUpdatedListener(view);
    }
}

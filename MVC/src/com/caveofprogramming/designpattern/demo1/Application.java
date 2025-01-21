package com.caveofprogramming.designpattern.demo1;

import com.caveofprogramming.designpattern.demo1.controller.Controller;
import com.caveofprogramming.designpattern.demo1.model.Model;
import com.caveofprogramming.designpattern.demo1.view.View;

import javax.swing.SwingUtilities;

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
        Model model = new Model();
        View view = new View(model);    // The Model listens to the View.
        /* The controller send commands to both the View and the Model. It almost certainly
         * listens to the View, but may or may not listen to the Model. In this case, the code is
         * likely to listen to the Model. */
        Controller controller = new Controller(view, model);

        // Implementation of the Observer pattern
        view.setLoginListener(controller);
        /* The controller is abstracted behind an interface, ensuring that the View and the Controller
         * are not tightly coupled. */

    }
}

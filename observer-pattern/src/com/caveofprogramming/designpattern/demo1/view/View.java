package com.caveofprogramming.designpattern.demo1.view;

import com.caveofprogramming.designpattern.demo1.model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class represent the view of our application. You would not necessarily call
 * this class {@code View} nor would the root package necessarily be called {@code view}.
 * It is often more appropriate to give this class a name such as {@code MainFrame}.
 * The important concept is that this is the part of the code responsible for interacting
 * with the user.
 */
public class View extends JFrame implements ActionListener {

    private final Model model;
    private final JButton helloButton;

    /**
     * The {@code View} constructor receives a reference to the {@code Model}
     * and that allows the {@code Model} to listen to the {@code View}.
     * @param model a reference to the model.
     */
    public View(Model model) {
        super("MVC Demo");  // The parent constructor (JFrame) accepts a title for the app.
        this.model = model;

        // Create new Buttons here
        helloButton = new JButton("Click me!");
        JButton goodbyeButton = new JButton("Goodbye!");

        ////////////////////////////////////////////////////////////////////////////////////
        // This block of code leverages Swing to set up the application's windows Layout. //
        setLayout(new GridBagLayout());

        // Sets the layout parameters for the button
        GridBagConstraints gc = new GridBagConstraints();
        gc.anchor = GridBagConstraints.CENTER;
        gc.gridx = 1;
        gc.gridy = 1;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.fill = GridBagConstraints.NONE;

        add(helloButton, gc); // Adds a "hello" button to the UI.

        gc.anchor = GridBagConstraints.CENTER;
        gc.gridx = 1;
        gc.gridy = 2;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.fill = GridBagConstraints.NONE;

        add(goodbyeButton, gc); // Adds a "goodbye" button to the UI.

        //// Implementation of the Observer Pattern: an example involving buttons ////
        helloButton.addActionListener(this);    // Pass an instance that implements ActionListener interface.
        goodbyeButton.addActionListener(this);
        /*
        * - The verb "add" implies the existence of a collection, in this case, a list
        * of listeners for each button. In contrast, the verb "set" suggest that only a
        * single listener is assigned.
        * - These methods are unaware that "this" refers to the view. They only know that
        * the provided reference implements the actionPerformed(ActionEvent e) method.
        */

        /* This is another example where an anonymous class syntax is used to define
        *  the action performed by the goodbye button. */
        goodbyeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Sorry to see you go.");
            }
        });
        ///// End of the Observer Pattern /////

        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        /////////////////// End of Swing code ///////////////////
    }

    /**
     * This method is defined in the ActionListener Interface and implemented here.
     * This method is called by the {@code JButton} when the user clicks the {@code helloButton}
     * or the {@code goodbyeButton}.
     *
     * @param e the event to be processed. This Object is used solely to contain event-related data.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // When called by the JButton, this method receives the event as "ActionEvent e".
        // The reference "e" can then be used to access data associated with the button that triggered the event.
        JButton source = (JButton) e.getSource();
        if (source == helloButton)
            System.out.println("Hello there!");
        else
            System.out.println("Some other button.");
    }
}

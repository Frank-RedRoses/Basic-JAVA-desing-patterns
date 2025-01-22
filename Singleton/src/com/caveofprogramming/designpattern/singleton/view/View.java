package com.caveofprogramming.designpattern.singleton.view;

import com.caveofprogramming.designpattern.singleton.model.Database;
import com.caveofprogramming.designpattern.singleton.model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * This class represent the view of our application. You would not necessarily call
 * this class {@code View} nor would the root package necessarily be called {@code view}.
 * It is often more appropriate to give this class a name such as {@code MainFrame}.
 * The important concept is that this is the part of the code responsible for interacting
 * with the user.
 */
public class View extends JFrame implements ActionListener {

    private final Model model;
    private final JTextField nameField;
    private final JPasswordField passField;
    private final JButton okButton;

    private LoginListener loginListener;

    /**
     * The {@code View} constructor receives a reference to the {@code Model}
     * and that allows the {@code Model} to listen to the {@code View}.
     * @param model a reference to the model.
     */
    public View(Model model) {
        super("MVC Demo");  // The parent constructor (JFrame) accepts a title for the app.
        this.model = model;
        nameField = new JTextField(10);
        passField = new JPasswordField(10);
        okButton = new JButton("OK");

        ////////////////////////////////////////////////////////////////////////////////////
        // This block of code leverages Swing to set up the application's windows Layout. //
        setLayout(new GridBagLayout());

        // Sets the layout parameters for the window view
        GridBagConstraints gc = new GridBagConstraints();
        gc.anchor = GridBagConstraints.LAST_LINE_END;
        gc.gridx = 1;
        gc.gridy = 1;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.insets = new Insets(100, 0, 0, 10);
        gc.fill = GridBagConstraints.NONE;

        add(new JLabel("Name: "), gc);

        gc.anchor = GridBagConstraints.LAST_LINE_START;
        gc.gridx = 2;
        gc.gridy = 1;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.insets = new Insets(100, 0, 0, 0);
        gc.fill = GridBagConstraints.NONE;

        add(nameField, gc);

        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 1;
        gc.gridy = 2;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.insets = new Insets(0, 0, 0, 10);
        gc.fill = GridBagConstraints.NONE;

        add(new JLabel("Password: "), gc);

        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 2;
        gc.gridy = 2;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.fill = GridBagConstraints.NONE;

        add(passField, gc);

        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.gridx = 2;
        gc.gridy = 3;
        gc.weightx = 1;
        gc.weighty = 100;
        gc.fill = GridBagConstraints.NONE;

        add(okButton, gc);

        okButton.addActionListener(this); // The View itself listens to the okButton

        /* **************** Singleton pattern ************************ */
        // Database db = new Database();  "new" keyword cannot be used by external classes to create instances.
        // This is how to access an instance when using the Singleton pattern:
        Database db = Database.getInstance();   // Global variable disadvantage

        // Other option is to use static methods:
        addWindowListener(new WindowAdapter() {
            // At opening and closing events from the window application
            @Override
            public void windowOpened(WindowEvent e) {
                Database.getInstance().connect();
            }

            @Override
            public void windowClosing(WindowEvent e) {
                Database.getInstance().disconnect();
            }
        });
        /*
         * Whether you retrieve your database instance each timer or maintain
         * single upfront reference depends on how often you need to use it.
         * If you only use it a few times, it is better to rely on static methods.
         * However, if you need to use your singleton in several places throughout
         * your code, it is better to maintain an upfront reference.
         */
        /* ************ End of Singleton pattern ******************* */

        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * This method is invoked by JButton when the okButton is pressed.
     *
     * @param e the event to be processed. A click, in this case.
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        String password = new String(passField.getPassword());
        String name = nameField.getText();

        fireLoginEvent(new LoginFormEvent(name, password));
    }

    // Verifies the loginListener reference is good and performs the login.
    private void fireLoginEvent(LoginFormEvent event) {
        if (loginListener != null) {
            loginListener.loginPerform(event);
        }
    }

    /**
     * This setter method accepts any class that implements the {@code LoginListener} interface
     * and sets the {@code loginListener} field to point to the given instance of that class.
     * The View is unaware of the specific type of the instance passed to this method; it only
     * requires that the type implements the {@code loginPerform()} method.
     *
     * @param loginListener A data type that implements the loginPerform() method.
     */
    public void setLoginListener(LoginListener loginListener) {
        this.loginListener = loginListener;
    }
}

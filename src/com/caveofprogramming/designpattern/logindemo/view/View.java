package com.caveofprogramming.designpattern.logindemo.view;

import com.caveofprogramming.designpattern.logindemo.model.Model;
import com.caveofprogramming.designpattern.logindemo.model.Person;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

/**
 * This class represent the view of our application. You would not necessarily call
 * this class {@code View} nor would the root package necessarily be called {@code view}.
 * It is often more appropriate to give this class a name such as {@code MainFrame}.
 * The important concept is that this is the part of the code responsible for interacting
 * with the user.
 */
public class View extends JFrame implements ActionListener, PeopleChangedListener {

    private final Model model;
    private final JTextField nameField;
    private final JPasswordField passField;
    private final JPasswordField repeatPassField;
    private final JButton okButton;
    private final JList<Person> userList;
    private final DefaultListModel<Person> listModel;

    private CreateUserListener createUserListener;
    private SaveListener saveListener;
    private AppListener appListener;

    /**
     * The {@code View} constructor receives a reference to the {@code Model}
     * and that allows the {@code Model} to listen to the {@code View}.
     *
     * @param model a reference to the model.
     */
    public View(Model model) {
        super("MVC Demo");  // The parent constructor (JFrame) accepts a title for the app.
        this.model = model;
        nameField = new JTextField(10);
        passField = new JPasswordField(10);
        repeatPassField = new JPasswordField(10);
        okButton = new JButton("Create user");
        listModel = new DefaultListModel<Person>();
        userList = new JList<Person>(listModel);
        int margin = 15;
        Border outerBorder = BorderFactory.createEmptyBorder(margin, margin, margin, margin);
        Border innerBorder = BorderFactory.createEtchedBorder();

        ////////////////////////////////////////////////////////////////////////////////////
        // This block of code leverages Swing to set up the application's windows Layout. //
        userList.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
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

        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 1;
        gc.gridy = 3;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.insets = new Insets(0, 0, 0, 10);
        gc.fill = GridBagConstraints.NONE;

        add(new JLabel("Repeat password: "), gc);

        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 2;
        gc.gridy = 3;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.fill = GridBagConstraints.NONE;

        add(repeatPassField, gc);

        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.gridx = 2;
        gc.gridy = 4;
        gc.weightx = 1;
        gc.weighty = 100;
        gc.fill = GridBagConstraints.NONE;

        add(okButton, gc);

        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.gridx = 1;
        gc.gridy = 5;
        gc.weightx = 1;
        gc.weighty = 100;
        gc.gridwidth = 2;
        gc.fill = GridBagConstraints.HORIZONTAL;

        // add to the GUI the JScrollPane that will display the userList element
        add(new JScrollPane(userList), gc);

        //// Implementation of the Observer Pattern: an example involving buttons ////
        okButton.addActionListener(this); // Assign the View itself to listen to the okButton
        /* Notes:
        * - Pass an instance that implements ActionListener interface.
        * - The verb "add" implies the existence of a collection, in this case, a list
        * of listeners for each button. In contrast, the verb "set" suggest that only a
        * single listener is assigned.
        * - These methods are unaware that "this" refers to the view. They only know that
        * the provided reference implements the actionPerformed(ActionEvent e) method.
        */

        // This part of the code start the first interaction with the database
        addWindowListener(new WindowAdapter() {
            // At opening and closing events from the window application do:
            @Override
            public void windowOpened(WindowEvent e) {
                appListener.onOpen();
            }

            @Override
            public void windowClosing(WindowEvent e) {
                appListener.onClose();
            }
        });

        // Adds the Menu Bar with the "Save" item to the Windows
        JMenuBar menu = createMenu();
        setJMenuBar(menu);

        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        /////////////////// End of Swing code ///////////////////
    }


    /**
     * This method is defined in the ActionListener Interface and implemented here.
     * It is invoked by {@code JButton} when the user clicks the {@code okButton}.
     *
     * @param e the event to be processed. A click, in this case.
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        String password = new String(passField.getPassword());
        String repeatPass = new String(repeatPassField.getPassword());

        if (password.equals(repeatPass)) {
            String name = nameField.getText();
            fireCreateUserEvent(new CreateUserEvent(name, password));
        } else {
            showError("Password does not match");
        }

    }

    /**
     * Shows given error on the View window as a warning message.
     * @param error a string error message.
     */
    public void showError(String error) {
        JOptionPane.showMessageDialog(
                this,
                error,
                "Error",
                JOptionPane.WARNING_MESSAGE);
    }

    /**
     *  Creates a menu bar on the view that shows a "File" menu with a "Save" item.
     *
     * @return a JMenuBar instance containing a "Save" item.
     */
    public JMenuBar createMenu() {

        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenuItem saveItem = new JMenuItem("Save");
        saveItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_S,
                KeyEvent.CTRL_DOWN_MASK
        ));
        fileMenu.add(saveItem);
        menuBar.add(fileMenu);
        saveItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireSaveEvent();
            }
        });
        return menuBar;
    }

    /**
     * This setter method accepts any instance that implements the {@code CreateUserListener}
     * interface and set it to {@code createUserListener} field in this class.
     * <p>
     * This allow the View to notify the listener when a user creation event occurs,
     * without needing to know the specific implementation details of the listener.
     * The View is unaware of the specific type of the instance passed to this method;
     * it only requires that the instance implements the {@code onUserCreated()} method.
     * </p>
     *
     * @param createUserListener an instance of a class that implements the {@code onUserCreated()}
     *                           method that handles the user creation on the application.
     */
    public void setCreateUserListener(CreateUserListener createUserListener) {
        this.createUserListener = createUserListener;
    }

    /**
     * Sets the {@code saveListener} to the given instance of a class that
     * implements tha {@code SaveListener} interface.
     * <p>
     * This allows the view to notify the listener, in this example the {@code controller},
     * when a save to database event occurs, without needing to know the specific details
     * of the implementation
     * </p>
     * @param saveListener an instance of a class implementing {@code SaveListener}
     *                     which handle the writing of new user to the database.
     */
    public void setSaveListener(SaveListener saveListener) {
        this.saveListener = saveListener;
    }

    public void setAppListener(AppListener appListener) {
        this.appListener = appListener;
    }

    // Verifies the createUserListener reference is not null.
    // Calls the method that handles the user creation on the database.
    private void fireCreateUserEvent(CreateUserEvent event) {
        if (createUserListener != null) {
            createUserListener.onUserCreated(event);
        }
    }

    // Calls the method that handles writing new users data to the database.
    private void fireSaveEvent() {
        if (saveListener != null) {
            saveListener.onSave();
        }
    }

    @Override
    public void onPeopleChanged() {
        /*
         * Some interpretation of the MVC would force the view
         * to be updated only through the controller, which would
         * contact the database, get the data and tell to the view
         * to update itself (by calling a view method directly).
         * Others, as here, have the view listening to the model
         * (but never telling it what to do)
         */
//        listModel.clear();

        List<Person> people = model.getPeople();

        for (Person person : people)
            listModel.addElement(person);
    }
}

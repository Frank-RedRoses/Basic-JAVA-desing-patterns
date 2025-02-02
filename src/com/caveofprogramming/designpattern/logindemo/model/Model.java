package com.caveofprogramming.designpattern.logindemo.model;

import com.caveofprogramming.designpattern.logindemo.view.PeopleChangedListener;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The Model class deals with the data on the back end.<br>
 * As with the {@code  View} class case, the names of the class and the root
 * package are used for illustrative purposes in this tutorial.
 * <p>
 * It is important to remember that the {@code model} should never import or
 * use anything from the {@code view} or {@code controller} packages. The
 * {@code model} should be a self-contained component, capable of being
 * tested independently.
 * </p>
 * <p>
 * This representation of a classic model class, though currently empty,
 * will be implemented in future steps. It is worth noting that some programs
 * may not implement a dedicated Model class at all, while others may use a
 * single Model class for an specific task or even multiple Models classes.
 * The structure of the MVC pattern varies based on the program's requirements.
 * A good approach is to think of the Model as a package or concept rather than
 * a specific class.
 * </p>
 * <p>
 * It's not always the case that the controller interacts with a single model class.
 * In this application, for example, the Controller works with multiple classes from
 * the Model package.
 * </p>
 */
public class Model {

    private final Set<Person> people = new HashSet<Person>();
    private PeopleChangedListener peopleChangedListener;

    // getter
    public List<Person> getPeople() {
        return new ArrayList<Person>(people);
    }

    // setter
    public void setPeopleChangedListener(PeopleChangedListener peopleChangedListener) {
        this.peopleChangedListener = peopleChangedListener;
    }


    /**
     * Get the all the {@code Person} entries from the database and
     * save them in the memory of the application as a list.
     *
     * @throws SQLException if a database access error occurs or this
     * method is called on a closed connection
     */
    public void load() throws SQLException {
        DAOFactory DAOfactory = DAOFactory.getFactory(DAOFactory.MYSQL);
        PersonDAO personDAO = DAOfactory.getPersonDAO();

//        people.clear();
        people.addAll(personDAO.getPeople());

        fireDataChanged();
    }

    // Updates the contends of the list of Person
    private void fireDataChanged() {
        if (peopleChangedListener != null) {
            peopleChangedListener.onPeopleChanged();
        }
    }


}

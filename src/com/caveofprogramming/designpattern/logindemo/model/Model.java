package com.caveofprogramming.designpattern.logindemo.model;

import com.caveofprogramming.designpattern.logindemo.view.PeopleUpdatedListener;

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
    // Could use a sorted set here -- LinkedHashSet or TreeSet
    private final Set<Person> people = new HashSet<Person>();
    private PeopleUpdatedListener peopleUpdatedListener;

    /**
     * Gets the temporal {@code List} that holds the people data from the
     * database and new created {@code Person}.
     *
     * @return a list holding {@code Person} entities.
     */
    public List<Person> getPeople() {
        return new ArrayList<Person>(people);
    }

    // Sets the listener that updates the temporal list of people
    public void setPeopleUpdatedListener(PeopleUpdatedListener peopleUpdatedListener) {
        this.peopleUpdatedListener = peopleUpdatedListener;
    }

    /**
     * Add a {@code Person} to the {@code people} set and
     * temporarily stores it as a list in the application's GUI.
     * <p>
     * If the application is stopped or reset, any {@code Person}
     * that has not been persisted to the database will be lost.
     * </p>
     *
     * @param person a {@code Person} instance.
     */
    public void addPerson(Person person) {
        people.add(person);
        firePeopleListUpdated();
    }

    /**
     * Deletes a person from the {@code people} set and updates
     * the temporal list holding {@code Person} entities in the application.
     *
     * @param person the {@code Person} to be deleted.
     */
    public void deletePerson(Person person) {
        people.remove(person);
        firePeopleListUpdated();
    }

    /**
     * Saves or updates the {@code Person} entities store in the {@code people} Set
     * to the {@code Database}.
     *
     * @throws SQLException if a database access error occurs or this
     *                      method is called on a closed connection
     */
    public void save() throws SQLException {
        /*
         * Note: this very simple implementation takes no account of what would
         * happen if multiple users were using this application. In this case,
         * we'd probably want to think of stuff like stored procedures,
         * transactions and so on, and how to handle the case where multiple
         * users try to modify the same person record at the same time.
         */
        // Here the specific database DAO factory is obtained
        DAOFactory factory = DAOFactory.getFactory(DAOFactory.MYSQL);
        PersonDAO personDAO = factory.getPersonDAO();   // personDAO with MySQL implementation.

        for (Person person : people) {
            // If the person has an ID, the record must
            // already exist in the database, because we
            // get the IDs from the database autoincrement
            // ID column.
            if (person.getId() == 0)
                personDAO.addPerson(new Person(
                        person.getName(),
                        person.getPassword()
                ));
            else
                personDAO.updatePerson(person);
        }
        load(); // Load new Person IDs added to the database to the GUI
    }

    /**
     * Get the all the {@code Person} entries from the database to
     * a {@code Set} and save them temporally as a list in the
     * GUI of the application.
     *
     * @throws SQLException if a database access error occurs or this
     *                      method is called on a closed connection
     */
    public void load() throws SQLException {
        DAOFactory factory = DAOFactory.getFactory(DAOFactory.MYSQL);
        PersonDAO personDAO = factory.getPersonDAO();

        people.clear();
        people.addAll(personDAO.getPeople());
        firePeopleListUpdated();
    }

    // Calls the method on PeopleUpdatedListener interface to update the list
    // of Person that is hold in application memory (not database);
    // the same list that is displayed on the GUI.
    private void firePeopleListUpdated() {
        if (peopleUpdatedListener != null) {
            peopleUpdatedListener.onPeopleListUpdated();
        }
    }


}

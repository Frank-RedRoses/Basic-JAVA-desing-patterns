package com.caveofprogramming.designpattern.logindemo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * <p>This class interacts with the database and manage the transfer of the data held by the
 * {@code Person} bean to and from the {@code People} table. It follows the DAO (Data Access
 * Object) pattern, which is designed to have one DAO class per table or view. When working
 * with a database view, a single DAO object can handle updates across multiple tables.</p>
 * <p>This class provides CRUD (create, retrieve, update, delete) methods to perform database
 * operations.</p>
 * <p>The purpose of this class is to abstract the complexities of interacting with the database.
 * In this example, SQL is used with JDBC connection, but the database can be swapped seamlessly
 * because the implementation relies on the {@code Person} transfer object and this DAO class.
 * Only the implementation details of the methods would need to be change based on the database
 * being used.</p>
 *
 * For multiple databases a DAO factory helps you to manage multiple DAOs.
 *
 */
public class PersonDAO {

    /**
     * This method receives a {@code Person} object and adds it to the
     * database.
     *
     * @param person {@code Person} object that contains the data of a person.
     */
    public void addPerson(Person person) throws SQLException {
        Connection conn = Database.getInstance().getConn(); // from the Singleton pattern.

        PreparedStatement p = conn.prepareStatement("INSERT INTO people (name, password) values (?, ?)");

        p.setString(1, person.getName());
        p.setString(2, person.getPassword());

        p.executeUpdate();

        p.close();
    }

    /**
     * Retrieves a {@code Person} data from the database using the given {@code id}.
     *
     * @param id The id of the {@code Person} in the database.
     * @return
     */
    public Person getPerson(int id) {
        return  null;
    }

    /**
     * Retrieves a list of all people from the database.
     * This approach is only suitable for very small databases. Typically, you will use
     * a {@code find()} method to search for specific objects in the database.
     *
     * @return
     */
    public List<Person> getPeople() {
        return null;
    }

    /**
     * Updates the record of the specified {@code Person} in the {@code People} table.
     * @param person The {@code Person} object holding the data to be updated.
     */
    public void updatePerson(Person person) {

    }

    /**
     * Deletes a person from the {@code People} table using the given id.
     *
     * @param id id of the person to be deleted.
     */
    public void deletePerson(int id) {

    }
}

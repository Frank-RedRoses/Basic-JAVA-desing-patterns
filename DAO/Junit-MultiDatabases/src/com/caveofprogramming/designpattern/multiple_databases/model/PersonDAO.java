package com.caveofprogramming.designpattern.multiple_databases.model;

import java.sql.SQLException;
import java.util.List;

/**
 * <p>The {@code PersonDAO} interface provides CRUD (create, retrieve, update, delete)
 * methods to perform database operations and abstracts the complexities of database
 * interaction.</p>
 * <p>This interface allows to implement different versions of DAO methods according
 * to the specific database, abstracting the representation of the {@code PersonDAO}.</p>
 * This ensures that the {@code Controller} is decoupled from the implementations details
 * and only interacts with the appropriate {@code PersonDAO} based on the required database.
 */
public interface PersonDAO {
    int addPerson(Person person) throws SQLException;

    Person getPerson(int id) throws SQLException;

    List<Person> getPeople() throws SQLException;

    int updatePerson(Person person) throws SQLException;

    int deletePerson(int id) throws SQLException;

    int deleteAll() throws SQLException;
}

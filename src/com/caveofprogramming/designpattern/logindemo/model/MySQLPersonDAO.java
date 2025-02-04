package com.caveofprogramming.designpattern.logindemo.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>This class interacts with a MySQL database and manage the transfer of data to and from
 * the {@code People} table using the {@code Person} bean. It follows the DAO (Data Access
 * Object) pattern, where typically one DAO class is created per table or view. For database
 * views, a single DAO object can handle updates across multiple tables.</p>
 * <p>This class implement CRUD (create, retrieve, update, delete) methods to perform database
 * operations.</p>
 * In this example, SQL is used with JDBC connection, but the database can be swapped seamlessly
 * because the implementation relies on the {@code Person} transfer object and the {@code PersonDAO}
 * interface. Only the method implementation details would need to be change for a different
 * the database to be used.</p>
 * <p> For multiple databases a DAO factory helps you to manage multiple DAOs.</p>
 */
public class MySQLPersonDAO implements PersonDAO {

    /**
     * This method receives a {@code Person} object and adds it to the
     * database.
     *
     * @param person {@code Person} object that contains the data of a person.
     * @return either (1) the row count for SQL Data Manipulation Language (DML)
     * statements or (2) 0 for SQL statements that return nothing.
     * @see PersonDAO#addPerson(Person)
     */
    @Override
    public int addPerson(Person person) throws SQLException {
        Connection conn = Database.getInstance().getConnection(); // from the Singleton pattern.

        PreparedStatement p = conn
                .prepareStatement("INSERT INTO people (name, password) values (?, ?)");

        p.setString(1, person.getName());
        p.setString(2, person.getPassword());

        int updated = p.executeUpdate();

        p.close();

        return updated;
    }

    /**
     * Retrieves a {@code Person} data from the database using the given {@code id}.
     *
     * @param id The id of the {@code Person} in the database.
     * @return a {@code Person} instance containing the data of database entry
     * with the given {@code id}.
     * @see PersonDAO#getPerson(int)
     */
    @Override
    public Person getPerson(int id) throws SQLException {
        Connection conn = Database.getInstance().getConnection();

        String sql = "SELECT id, name, password from people where id=? order by id";
        PreparedStatement selectStatement = conn.prepareStatement(sql);

        selectStatement.setInt(1, id);

        ResultSet results = selectStatement.executeQuery();

        Person person = null;

        if (results.next()) {
            person = new Person(
                    id,
                    results.getString("name"),
                    results.getString("password")
            );
        }

        results.close();
        selectStatement.close();
        return person;
    }

    /**
     * Retrieves a list of all people from the database, order by id.
     * This approach is only suitable for very small databases. Typically, you will use
     * a {@code find()} method to search for specific objects in the database.
     *
     * @return returns a list with all the people in the database order by id.
     * @throws SQLException if a database access error occurs or this method
     * is called on a closed connection
     * @see PersonDAO#getPeople()
     */
    @Override
    public List<Person> getPeople() throws SQLException {

        List<Person> people = new ArrayList<Person>();

        Connection conn = Database.getInstance().getConnection();

        String sql = "select id, name, password from people order by id";
        Statement selectStatement = conn.createStatement();

        ResultSet results = selectStatement.executeQuery(sql);

        while (results.next()) {
            int id = results.getInt("id");
            String name = results.getString("name");
            String password = results.getString("password");

            Person person = new Person(id, name, password);
            people.add(person);
        }

        results.close();
        selectStatement.close();
        return people;
    }

    /**
     * Updates the record of the specified {@code Person} in the {@code People} table.
     *
     * @param person The {@code Person} object holding the data to be updated in the database.
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements
     * or (2) 0 for SQL statements that return nothing
     * @throws SQLException if a database access error occurs or this method
     * is called on a closed connection
     * @see PersonDAO#updatePerson(Person)
     */
    @Override
    public int updatePerson(Person person) throws SQLException {
        Connection conn = Database.getInstance().getConnection();

        PreparedStatement prepStatement = conn
                .prepareStatement("UPDATE people SET name=?, password=? where id=?");

        prepStatement.setString(1, person.getName());
        prepStatement.setString(2, person.getPassword());
        prepStatement.setInt(3, person.getId());

        int updated = prepStatement.executeUpdate();
        prepStatement.close();
        return updated;
    }

    /**
     * Deletes a person from the {@code People} table using the given id.
     *
     * @param id id of the person to be deleted.
     * @throws SQLException if a database access error occurs or this method
     * is called on a closed connection
     * @see PersonDAO#
     */
    @Override
    public int deletePerson(int id) throws SQLException {
        Connection conn = Database.getInstance().getConnection();

        PreparedStatement prepStatement = conn
                .prepareStatement("DELETE from people WHERE id=?");

        prepStatement.setInt(1, id);

        int deleted = prepStatement.executeUpdate();
        prepStatement.close();
        return deleted;
    }

    /**
     * Deletes all entries on the database.
     *
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements
     * or (2) 0 for SQL statements that return nothing
     * @throws SQLException if a database access error occurs or this method
     * is called on a closed connection
     * @see PersonDAO#deleteAll()
     */
    @Override
    public int deleteAll() throws SQLException {
        Connection conn = Database.getInstance().getConnection();

        PreparedStatement prepStatement = conn
                .prepareStatement("DELETE from people");

        int deleted = prepStatement.executeUpdate();
        prepStatement.close();
        return deleted;
    }
}

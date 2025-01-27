package com.caveofprogramming.designpattern.logindemo.model;

/**
 * The {@code MySQLDAOFactory} class facilitates the creation and management
 * of multiple DAOs instances that operates with a MySQL database.
 */
public class MySQLDAOFactory extends DAOFactory {


    /**
     * Creates a new instance of {@code MySQLPersonDAO} and returns
     * it as an {@code PersonDAO} abstract class.
     *
     * @return new instance {@code MySQLPersonDAO} as a {@code PersonDAO}.
     */
    @Override
    public PersonDAO getPersonDAO() {
        return new MySQLPersonDAO();
    }

    /**
     * Creates a new instance of {@code getLogDAO} abstract class.
     *
     * @return new {@code getLogDAO} instance.
     */
    @Override
    public LogDAO getLogDAO() {
        return new MySQLLogDAO();
    }

}

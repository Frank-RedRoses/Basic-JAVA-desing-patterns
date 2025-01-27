package com.caveofprogramming.designpattern.logindemo.model;

/**
 * The DAOFactory class allow to create and manage the instantiation
 * of multiples DAOs.<br>
 * <p>The DAO pattern can be visualized as a pyramid with multiple layers. The DAOFactory
 * represents one of those layers, although many other layers are not present in this application.
 * </p>
 */
public class DAOFactory {

    /**
     * Creates a new instance of {@code PersonDAO} class.
     * @return new {@code PersonDAO} instance.
     */
    public static PersonDAO getPersonDAO() {
        return new PersonDAO();
    }

    /**
     * Creates a new instance of {@code getLogDAO} class.
     * @return new {@code getLogDAO} instance.
     */
    public static LogDAO getLogDAO() {
        return new LogDAO();
    }

}

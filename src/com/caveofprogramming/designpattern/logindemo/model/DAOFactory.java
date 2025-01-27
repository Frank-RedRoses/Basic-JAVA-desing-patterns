package com.caveofprogramming.designpattern.logindemo.model;

/**
 * The {@code DAOFactory} abstract class provide a structure for defining abstract
 * methods that must be implemented by its subclasses. This structure enables the
 * decoupling of database specific implementation details from the {@code Controller}
 * or any other class that requires a DAO instance.<br>
 * Additionally, it includes the {@code getFactory()} static method to return different
 * types of DAO factories according to the {@code type} of database. The DAO factory
 * subclass returned by this method contains only the details necessary to interact
 *  with the specific database.
 *
 * <p>The DAO pattern can be visualized as a pyramid with multiple layers. The DAOFactory
 * represents one of those layers, although many other layers are not present in
 * this application.</p>
 */
public abstract class DAOFactory {

    public static final int MYSQL = 0;
    public static final int ORACLE = 1;

    public abstract PersonDAO getPersonDAO();

    public abstract LogDAO getLogDAO();

    public static DAOFactory getFactory(int type) {
        switch(type) {
            case MYSQL:
                return new MySQLDAOFactory();
            case ORACLE:
                return new OracleDAOFactory();
            default:
                return null;
        }
    }
}

package com.caveofprogramming.designpattern.logindemo.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * <p>This class represents the database of our application and uses the Singleton pattern
 * to ensure that only one instance is created to access the database.</p>
 *
 * <p>The Singleton pattern, is a design pattern that restricts a class to only one instance.
 * This means only one object can be created from the class, and that object is shared
 * throughout your application.</p>
 *
 * <p>The Singleton pattern is somewhat controversial because some developers argue it introduces
 * global variables indirectly. Once the Singleton instance is created, it can be accessed globally,
 * which, if not managed carefully, may lead to unintended side effects or confusion.<br>
 *
 * As a result, it’s important to carefully consider where and how to use the Singleton pattern.
 * Some even argue that it shouldn’t be used at all due to these concerns.</p>
 *
 * <p>A classic use case for the Singleton pattern is a Database class. This ensures that
 * only one instance of the database connection exists, avoiding potential issues with
 * multiple connections being created and managed improperly.</p>
 */
public class Database {

////// This is the recommended implementation of the Singleton pattern //////////
    // This is the only statement that instantiates the database
    private final static Database instance = new Database();

    private Connection conn;

    /**
     * When a constructor is private, the new keyword cannot be used by external classes
     * to create instances.
     */
    private Database() {
    }

    /**
     * The single database instance is returned by this getter method.
      */
    public static Database getInstance() {
        return instance;
    }

    /* **************************************************************************
     * This portion of the code shows the old way the Singleton pattern was used.
     *
     * private static Database oldInstance;
     *
     * public static Database getOldInstance() {
     *      if (oldInstance == null)
     *          oldInstance = new Database();
     *      return oldInstance;
     * }
     *
     * This is called lazy instantiation, which is useful when you do not want
     * the class to be constructed at program startup. However, it is not thread-safe.
     ***************************************************************************** */

    /*
     * Add whatever methods you like to your singleton class.
     */

    /**
     *  Gets the database connection.
     * @return an object containing a connection to the database.
     */
    public Connection getConn() {
        return conn;
    }

    /**
     * Connects to the database.
     */
    public void connect() throws Exception {
        if (conn != null)
            return;

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new Exception("Driver not found");
        }

        String url = String.format("jdbc:mysql://localhost:%d/patterns", 3306);

        conn = DriverManager.getConnection(url, "squiffy", "LetMeIn01");
    }

    /**
     * Disconnects from the database
     */
    public void disconnect() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("Can't close connection");
            }
        }
        conn = null;
    }
    /* **************************************************************************
     * Two typical examples of using these methods are:
     * - Each time you perform a query, you can quickly connect and disconnect
     * just for the query. In android, when is using a lightweight database like
     * SQLite, this is a common scenario.
     * - When connecting to a remote database, you probably connect when your
     * program starts up and disconnect when it shuts down.
     *************************************************************************** */
}

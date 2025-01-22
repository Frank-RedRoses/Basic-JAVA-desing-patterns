package com.caveofprogramming.designpattern.singleton.model;

/**
 * <p>This class represents the database of our application and uses the Singleton pattern
 * to ensure that only one instance is created to access the database.</p>
 *
 * <p>The Singleton pattern, is a design pattern that restricts a class to only one instance.
 * This means only one object can be created from the class, and that object is shared
 * throughout your application.</p>
 *
 * <p>A classic use case for the Singleton pattern is a Database class. This ensures that
 * only one instance of the database connection exists, avoiding potential issues with
 * multiple connections being created and managed improperly.</p>
 */
public class Database {

////// This is the recommended implementation of the Singleton pattern //////////
    // This is the only statement that instantiates the database
    private final static Database instance = new Database();

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

    /**
     * Connects to the database
     */
    public void connect() {
        System.out.println("Connected to database");
    }

    /**
     * Disconnects from the database
     */
    public void disconnect() {
        System.out.println("Disconnected from database");
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

package com.caveofprogramming.designpattern.logindemo.model;

/**
 * <p>This Class represent a Transfer Object, also known as a Bean, and it is the first step
 * to implement the DAO pattern.
 * This transfer object contains the data that is need to manage the People table.</p>
 * A Bean class is a simple Java object that adheres to specific conventions, typically
 * used to encapsulate data. The key features are:
 * <ul>
 *     <li>Private fields: Data store in private members</li>
 *     <li>Public getters and setters: Access to these variable is provided through
 *     public methods.</li>
 *     <li>No-argument constructors: The class includes a public, no-argument
 *     constructor.</li>
 *     <li>Often implements the Serializable interface for easy object serialization</li>
 * </ul>
 * A typical use of a Bean is to store, manipulate, and transfer data across different layers
 * of an application.
 */
public class Person {

    private int id;
    private String name;
    private String password;
    /* Using the String data type for passwords is uncommon.
     * When getting the password in the view, you will likely apply a hashing algorithm to
     * create an encrypted version of the password, which you can then handle in subsequence
     * processes.
     * For demonstration purposes the password will be handled as a plain String.
     */

    public Person() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

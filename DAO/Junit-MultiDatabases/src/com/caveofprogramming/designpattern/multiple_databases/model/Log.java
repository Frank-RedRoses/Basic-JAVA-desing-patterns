package com.caveofprogramming.designpattern.multiple_databases.model;

import java.util.Date;

/**
 * The class {@code Log} represents an operation on the {@code people} table.
 * This class is a Bean class to transfer data to/from the database.
 */
public class Log {
    private int id;
    private Date date;
    private String message;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

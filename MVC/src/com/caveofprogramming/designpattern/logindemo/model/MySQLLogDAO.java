package com.caveofprogramming.designpattern.logindemo.model;

import java.util.List;

/**
 * Class that implements the {@code LogDAO} interface to work with a MySQL database.
 * It uses the {@code Log} bean to handle the data. While not implemented, it serves as an
 * example of how DAOs can be independently implemented for different databases and be
 * abstracted using the {@code LogDAO} interface.
 */
public class MySQLLogDAO implements LogDAO {
    /*
     *(non-Javadoc)
     * @see LogDAO#addEntry(String) 
     */
    @Override
    public void addEntry(String message) {
        // Not implemented
        // Get current time and log message to database
    }

    /*
     *(non-Javadoc)
     * @see LogDAO#getEntries(int)
     */
    @Override
    public List<Log> getEntries(int number) {
        // Not implemented. Get latest "number" log messages.
        return null;
    }

    // Maybe no need for update or delete in this case.
}

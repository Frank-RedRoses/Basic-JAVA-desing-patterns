package com.caveofprogramming.designpattern.multiple_databases.model;

import java.util.List;

/**
 * Class that implements the {@code LogDAO} interface to work with a MySQL database.
 * It uses the {@code Log} bean to handle the data. While not implemented, it serves as an
 * example of how DAOs can be independently implemented for different databases and be
 * abstracted using the {@code LogDAO} interface.
 */
public class MySQLLogDAO implements LogDAO {
    @Override
    public void addEntry(String message) {
        // Not implemented
        // Get current time and log message to database
    }

    @Override
    public List<Log> getEntries(int number) {
        // Not implemented. Get lastest "number" log messages.
        return null;
    }


}

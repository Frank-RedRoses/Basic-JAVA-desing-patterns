package com.caveofprogramming.designpattern.multiple_databases.model;

import java.util.List;

/**
 * <p>The {@code LogDAO} interface provides CRUD (create, retrieve) methods to perform
 * database operations.</p>
 * <p>This interface allows to implement different versions of DAO methods according
 * to the specific database, abstracting the representation of the {@code LogDAO}.</p>
 * This ensures that the {@code Controller} is decoupled from the implementations details
 * and only interacts with the appropriate {@code LogDAO} based on the required database.
 */
public interface LogDAO {
    void addEntry(String message);

    List<Log> getEntries(int number);

    // May not need update() or delete() in this example.
}

package com.caveofprogramming.designpattern.multiple_databases.model;

import java.util.List;

/**
 * This class interacts with an Oracle database and manage the transfer of the data to and from
 * a {@code log} table using the {@code Log} bean.While not implemented, it serves as an
 * example of how DAOs can be independently implemented for different databases and be
 * abstracted using the {@code LogDAO} interface.
 */
public class OracleLogDAO implements LogDAO{
    @Override
    public void addEntry(String message) {

    }

    @Override
    public List<Log> getEntries(int number) {
        return List.of();
    }
}

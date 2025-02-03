package com.caveofprogramming.designpattern.logindemo.model;

import java.sql.SQLException;
import java.util.List;

/**
 * This class interacts with an Oracle database and manage the transfer of the data to and from
 * a {@code People} table using the {@code Person} bean.While not implemented, this class serves
 * as an example of how DAOs can be implemented for different databases independently and be
 * abstracted using the {@code PersonDAO} interface.
 */
public class OraclePersonDAO implements PersonDAO{

    @Override
    public int addPerson(Person person) throws SQLException {
        return 0;
    }

    @Override
    public Person getPerson(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Person> getPeople() throws SQLException {
        return List.of();
    }

    @Override
    public int updatePerson(Person person) throws SQLException {
        return 0;
    }

    @Override
    public int deletePerson(int id) throws SQLException {
        return 0;
    }

    @Override
    public int deleteAll() throws SQLException {
        return 0;
    }
}

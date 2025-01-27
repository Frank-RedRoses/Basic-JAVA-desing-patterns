package com.caveofprogramming.designpattern.logindemo.model;

import org.junit.jupiter.api.*;
import java.sql.SQLException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Writing a DAO class without testing can lead to unreliable, hard-to-maintain
 * code. The DAO class directly interacts with the database, and testing it ensures
 * that data is correctly added, retrieved, updated, and deleted. Without proper
 * testing, issues such as incorrect queries or data inconsistencies might go unnoticed.
 * Testing a DAO is essential for several reasons:
 * <ul>
 *     <li>Ensures Data integrity</li>
 *     <li>Validate SQL queries</li>
 *     <li>Prevent future bugs</li>
 *     <li>Verifies connection handling</li>
 *     <li>Improves code reliability</li>
 *     <li>Facilitates refactoring</li>
 * </ul>
 */
class PersonDAOTest {

    @BeforeAll
    public static void setUpBeforeClass() throws Exception {
        System.out.println("Set up before class");
        Database.getInstance().connect();
    }

    @AfterAll
    public static void tearDownAfterClass() throws Exception {
        System.out.println("Tear down after class");
        Database.getInstance().disconnect();
    }

    @BeforeEach
     void setUp() throws Exception{
        System.out.println("set up before a test");
//         JDBC should be used here rather than a custom method I wrote,
//         as it provides a more reliable and standardized way to handle
//         database operations, reducing the risk of potential failures.
        PersonDAO dao = DAOFactory.getPersonDAO();  // Method wrote by myself
        dao.deleteAll();                            // Method wrote by myself
    }

    @AfterEach
    void tearDown() {
        System.out.println("tear down after a test");
    }


    @Test
    void testCreate() throws SQLException {
        System.out.println("testing");
        Person person1 = new Person("Bob", "letmein");
        Person person2 = new Person("Sue", "PassHello");
        PersonDAO dao = DAOFactory.getPersonDAO();
        /* Using a try-catch block to handle exceptions within the test and
         * explicitly failing the test when an exception is caught could be
         * a better approach. This is, of course, a personal preference and
         * depends on how you want to manage exceptions in your test cases.*/
        dao.addPerson(person1);
        dao.addPerson(person2);

        List<Person> people = dao.getPeople();  // Here again I should use JDBC instead of custom methods

        assertEquals(2, people.size(), "Should be two people in database.");

        assertEquals(person1, people.get(0), "These two people should be the same");
        assertEquals(person2, people.get(1), "These two people should be the same");
    }
}
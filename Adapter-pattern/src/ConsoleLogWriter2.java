/**
 * Adapter Pattern class using Inheritance.</br>
 * This class works as an intermediate class between two incompatible
 * interfaces.
 * <p>
 * It extends the class that provides the desired functionality
 * (writing to console),and implements the interface methods by
 * internally calling the corresponding methods from the parent
 * class.<br>
 * The adapter allows one interface to be used as if it were another, ensuring
 * compatibility between different components.
 * </p>
 */
public class ConsoleLogWriter2 extends ConsoleWriter implements LogWriter {

    @Override
    public void writeOutToConsole(String text) {
        writeToConsole(text);   // Extended method from the parent class
    }
}

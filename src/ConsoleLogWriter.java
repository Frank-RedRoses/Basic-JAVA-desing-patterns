/**
 * Adapter pattern class.</br>
 * This class works as an intermediate class between two incompatible
 * interfaces.
 * <p>
 * It instantiates the class that provides the desired functionality
 * (writing to console),and implements the interface methods by
 * internally calling the corresponding methods from the functionality
 * class.<br>
 * The adapter allows one interface to be used as if it were another, ensuring
 * compatibility between different components.
 * </p>
 */
public class ConsoleLogWriter implements LogWriter {

    ConsoleWriter otherInterfaceWriter = new ConsoleWriter();

    @Override
    public void writeOutToConsole(String text) {
        otherInterfaceWriter.writeToConsole(text);
    }
}

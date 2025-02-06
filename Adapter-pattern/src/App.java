public class App {

    public static void main(String[] args) {

        // There are two ways to implement the Adapter Pattern:

        //////////// Composition /////////
        LogWriter logWriter = new ConsoleLogWriter();

        Logger logger = new Logger(logWriter);
        logger.write("Hello there");

        /////////// Inheritance //////////
        LogWriter logWriter2 = new ConsoleLogWriter2();

        Logger logger2 = new Logger(logWriter2);
        logger2.write("Hello there 2");

    }
}

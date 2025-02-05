public class App {

    public static void main(String[] args) {

        // There are two ways to implement the Adapter Pattern:

        //////////// Composition /////////
        LogWriter logWriter = new ConsoleLogWriter();

        Logger logger = new Logger(logWriter);
        logger.write("Hello there");

    }
}

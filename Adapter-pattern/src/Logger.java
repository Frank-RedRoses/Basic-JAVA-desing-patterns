public class Logger {

    public LogWriter logWriter;

    public Logger(LogWriter logWriter) {
        this.logWriter = logWriter;
    }

    public void write(String text){
        logWriter.writeOutToConsole(text);
    }
}

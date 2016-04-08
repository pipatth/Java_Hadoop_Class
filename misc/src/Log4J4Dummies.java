import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.PropertyConfigurator;

public class Log4J4Dummies {
    static Logger logger = Logger.getLogger(Log4J4Dummies.class);

    static {
        Level theLevel = Level.INFO;
        String level = System.getProperty("LEVEL");
        if(level != null) {
            if(level.equalsIgnoreCase("trace")) {
                theLevel = Level.TRACE;
            }
            if(level.equalsIgnoreCase("debug")) {
                theLevel = Level.DEBUG;
            }
            if(level.equalsIgnoreCase("warn")) {
                theLevel = Level.WARN;
            }
            if(level.equalsIgnoreCase("error")) {
                theLevel = Level.ERROR;
            }
            if(level.equalsIgnoreCase("fatal")) {
                theLevel = Level.FATAL;
            }
            if(level.equalsIgnoreCase("off")) {
                theLevel = Level.OFF;
            }
            if(level.equalsIgnoreCase("all")) {
                theLevel = Level.ALL;
            }

        }
        logger.setLevel(theLevel);
    }

    public static void main(String args[]) {

        System.out.println("Logger name: " + logger.getName());

        // Note, %n is newline
        String pattern =  "Milliseconds since program start: %r %n";
        pattern += "Classname of caller: %C %n";
        pattern += "Date in ISO8601 format: %d{ISO8601} %n";
        pattern += "Location of log event: %l %n";
        pattern += "Message: %m %n";

        PatternLayout layout = new PatternLayout(pattern);
        ConsoleAppender appender = new ConsoleAppender(layout);

        //PropertyConfigurator.configure("log4j.properties");

        logger.addAppender(appender);


        logger.debug("Here is some DEBUG");
        logger.info("Here is some INFO");
        logger.warn("Here is some WARN");
        logger.error("Here is some ERROR");
        logger.fatal("Here is some FATAL");
    }
}

import java.io.IOException;

public class Main {

    public static void processOneFile(String filename, String logFilename, String title, Logger logger) throws IOException {
        logger.clear();
        FileContent fContent = IOHelper.readFile(filename, logger);
        logger.dumpToLogFile(logFilename);
        System.out.println(IOHelper.getOutputInfo(fContent, title));
    }

    public static void main(String[] args) throws IOException {
        Logger logger = new Logger();
        processOneFile("data_e1.txt", "error_log_e.txt", "Assignment 1.8.1", logger);
//        processOneFile("data_e2.txt", "error_log_e2.txt", "Assignment 1.8.2", logger);
//        processOneFile("data_e3.txt", "error_log_e3.txt", "Assignment 1.8.3", logger);

    }
}

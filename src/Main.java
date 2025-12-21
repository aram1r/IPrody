import logger.*;

import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        FileLoggerConfiguration config = new FileLoggerConfiguration(Level.DEBUG, new Pattern(), Paths.get("D:\\Programming\\IPrody\\"), "log", 15048L);

        FileLogger log = new FileLogger(config);
        for (int i = 0; i<1000; i++) {
            log.debug("Message");
        }
        FileLoggerConfiguration config2= FileLoggerConfigurationLoaderInterface.load("D:\\Programming\\IPrody\\IPrody\\config.txt");
        System.out.println("Программа выполнена");
    }
}
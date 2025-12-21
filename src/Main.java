import logger.*;

import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
//        FileLoggerConfiguration config = new FileLoggerConfiguration(Level.DEBUG, new Pattern(), Paths.get("D:\\Programming\\IPrody\\"), "log", 15048L);

//        FileLogger log = new FileLogger(config);
//        for (int i = 0; i<1000; i++) {
//            log.debug("Message");
//        }
        FileLoggerConfiguration config2= new FileLoggerConfigurationLoaderImpl().load("D:\\Programming\\IPrody\\IPrody\\config.txt");

        FileLogger log = new FileLogger(config2);
        for (int i = 0; i<1000; i++) {
            log.debug("Message");
        }
        System.out.println("Программа выполнена");
    }
}
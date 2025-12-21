package logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Pattern{

    public String record(Level level, String message) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) + " " + level.toString() + " Сообщение: " + message;
    }
}

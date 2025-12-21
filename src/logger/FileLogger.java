package logger;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileLogger implements Logger{

    FileLoggerConfiguration config;
    private int index = 0;

    public FileLogger(FileLoggerConfiguration config) {
        this.config = config;
    }

    @Override
    public void debug(String message) {
        writeToFile("[" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy-HH:mm:ss")) + "][DEBUG] " + message
        + "[" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy-HH:mm:ss")) + "][INFO] " + message  + "\n");
    }

    @Override
    public void info(String message) {
        writeToFile("[" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy-HH:mm:ss")) + "][INFO] " + message);
    }

    public void writeToFile (String message) {
        String fileName = "Log_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy-HH-mm")) + "_" + index;
        if (index == 0 ) {
            fileName = "Log_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy-HH-mm"));
        }

        Path baseDir = config.getPath();

        Path filePath = baseDir.resolve(fileName + ".log");

        File createdFile = filePath.toFile();

        boolean append = true;

        if (!createdFile.exists()) {
            createLogFile(createdFile.toPath());
            append = false;
        }
        long size = message.getBytes().length;
        try {
            long sizeInBytes = Files.size(createdFile.toPath());
            long maximumSize = config.getMaximumFileSize();
            if ((sizeInBytes + size) > maximumSize) {
                index++;
                fileName = "Log_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy-HH-mm")) + "_" + index;
                Path newFilePath = baseDir.resolve(fileName + ".log");
                createdFile = newFilePath.toFile();

                createLogFile(createdFile.toPath());
                append = false;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(createdFile.getAbsolutePath(), append))) {
            writer.write(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Boolean createLogFile(Path path) {
        try {

            if (path.getParent()!=null) {
                Files.createDirectories(path.getParent());
            }
           Files.createFile(path);
        } catch (FileAlreadyExistsException e) {
            System.out.println("Ошибка: Файл уже существует.");
        } catch (IOException e) {
            System.err.println("Произошла ошибка ввода/вывода: " + e.getMessage());
            e.printStackTrace();
        } catch (SecurityException e) {
            System.err.println("Ошибка безопасности: Отказано в доступе.");
        }
        return Files.exists(path);
    }
}

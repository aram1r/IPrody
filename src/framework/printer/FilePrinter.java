package framework.printer;

import framework.execution.Execution;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class FilePrinter implements Printer {

    private final File pathToWrite;

    public FilePrinter(String path) {
        this.pathToWrite = new File(path);
        // Проверка существования и создание директорий, если их нет
        if (!pathToWrite.exists()) {
            boolean created = pathToWrite.mkdirs();
            if (!created) {
                System.err.println("Не удалось создать директорию: " + path);
            }
        }
    }

    @Override
    public void write(List<Execution> executions) {
        // Формируем имя файла с временной меткой
        // Формат: 2026-01-12_23-15-00.txt
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
        String fileName = "report_" + timestamp + ".txt";

        // Создаем путь к конкретному файлу внутри директории pathToWrite
        Path file = pathToWrite.toPath().resolve(fileName);

        // Формируем содержимое файла
        StringBuilder content = new StringBuilder();
        content.append("Test Execution Report - ").append(timestamp).append("\n");
        content.append("==========================================\n\n");

        for (Execution execution : executions) {
            content.append("Class: ").append(execution.getTestClass().getName()).append("\n");
            content.append("Start: ").append(execution.getStartTime()).append("\n");
            content.append("End:   ").append(execution.getEndTime()).append("\n");
            content.append("Results:\n");

            execution.getExecutionItems().forEach(item ->
                    content.append("  - Method: ").append(item.getMethod().getName())
                            .append(" | Success: ").append(item.getAssertResult().isSuccess())
                            .append(" | Details: ").append(item.getAssertResult().toString())
                            .append("\n")
            );
            content.append("------------------------------------------\n");
        }

        // Записываем в файл
        try {
            Files.writeString(file, content.toString(), StandardOpenOption.CREATE);
            System.out.println("Отчет успешно сохранен: " + file.toAbsolutePath());
        } catch (IOException e) {
            System.err.println("Ошибка при записи файла: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

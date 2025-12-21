package logger;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileLoggerConfigurationLoaderImpl implements FileLoggerConfigurationLoaderInterface{
    public FileLoggerConfiguration load(String stringPath) {
        FileLoggerConfiguration config = new FileLoggerConfiguration();
        try {
            Path path = Paths.get(stringPath);
            Files.lines(path).forEach(e -> {
                String setting = e.substring(0, e.indexOf("="));

                //Level level, Pattern pattern, Path path, String fileName, Long maximumFileSize
                switch (setting) {
                    case "maximumFileSize": config.setMaximumFileSize(Long.parseLong(e.substring(e.indexOf("=") + 1))); break;
                    case "level": config.setLevel(Level.valueOf(e.substring(e.indexOf("=") + 1))); break;
                    case "path": config.setPath(Paths.get(e.substring(e.indexOf("=") + 1))); break;
                    case "fileName": config.setFileName(e.substring(e.indexOf("=") + 1)); break;
                }
                config.setPattern(new Pattern());
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return config;
    };
}

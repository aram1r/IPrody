package logger;

import java.nio.file.Path;

public class FileLoggerConfiguration implements LoggerConfiguration {

    Level level;
    Pattern pattern;
    Path path;
    String fileName;
    Long maximumFileSize;

    public FileLoggerConfiguration() {

    }

    public FileLoggerConfiguration(Level level, Pattern pattern, Path path, String fileName, Long maximumFileSize) {
        this.level = level;
        this.pattern = pattern;
        this.path = path;
        this.fileName = fileName;
        this.maximumFileSize = maximumFileSize;
    }

    @Override
    public Level level() {
        return null;
    }

    @Override
    public Pattern pattern() {
        return null;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getMaximumFileSize() {
        return maximumFileSize;
    }

    public void setMaximumFileSize(Long maximumFileSize) {
        this.maximumFileSize = maximumFileSize;
    }
}

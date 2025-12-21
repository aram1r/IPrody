package storage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileStorageReader<T> implements ObjectStorageReader {
    FileStorage fileStorage;
    final Class<T> type;

    public FileStorageReader(FileStorage fileStorage) {
        this.fileStorage = fileStorage;
        this.type = fileStorage.getType();
    }


    @Override
    public byte[] read(String namespace, String name) {
        Path path = Paths.get(namespace, name);
        File file = path.toFile();
        if (file.exists()) {
            try {
                InputStream inputStream = Files.newInputStream(path);
                return inputStream.readAllBytes();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return new byte[0];
    }

    @Override
    public List<byte[]> read(String namespace, String name, int chuckSize) {
        Path path = Paths.get(namespace, name);
        File file = path.toFile();
        List<byte[]> bytes = new ArrayList<>();
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file), chuckSize);
            long size = file.length();
            while (size > 0) {
                bytes.add(bufferedInputStream.readNBytes(chuckSize));
                size -= chuckSize;
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return bytes;
    }
}

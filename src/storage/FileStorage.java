package storage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;

public class FileStorage<T> implements ObjectStorage{
    HashMap<String, ArrayList<String>>  storage;
    FileStorageReader  reader;
    final Class<T> type;

    public FileStorage(Class<T> type) {
        this.type = type;
        storage = new HashMap<>();
    }

    @Override
    public void put(String namespace, String name, Object object) {
        Path path = Paths.get(namespace, name);
        try {
            Files.createDirectories(path.getParent());
            Files.createFile(path);
            ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(path, StandardOpenOption.CREATE));
            out.writeObject(object);
            if (!storage.containsKey(namespace)) {
                storage.put(namespace, new ArrayList<>());
            } else {
                storage.get(namespace).add(name);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());;
        }
    }

    @Override
    public T get(String namespace, String name) throws IOException {
        Path path = Paths.get(namespace, name);
        if (Files.notExists(path)) {
            throw new FileNotFoundException("Файл не найден");
        }
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(path))) {
            Object obj = ois.readObject();
            return type.cast(obj);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public HashMap<String, ArrayList<String>> getStorage() {
        return storage;
    }

    public void setStorage(HashMap<String, ArrayList<String>> storage) {
        this.storage = storage;
    }

    public FileStorageReader getReader() {
        return reader;
    }

    public void setReader(FileStorageReader reader) {
        this.reader = reader;
    }

    public Class<T> getType() {
        return type;
    }
}

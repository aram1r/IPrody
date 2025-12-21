package storage;

import java.util.List;

public interface ObjectStorageReader {
    byte[] read (String namespace, String name);
    List<byte[]> read (String namespace, String name, int chuckSize);
}

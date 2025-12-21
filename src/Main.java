import storage.FileStorage;
import storage.FileStorageReader;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        FileStorage fileStorage = new FileStorage(Object.class);
        FileStorageReader fileStorageReader = new FileStorageReader<>(fileStorage);
        fileStorage.setReader(fileStorageReader);

        fileStorage.put("D:\\Programming\\IPrody\\IPrody\\", "File2.txt", "Объект для записи текст текст текст \n Ещё текст \n Ещё текст \n Ещё текст \n Ещё текст \n Ещё текст");

        System.out.println(Arrays.toString(fileStorageReader.read("D:\\Programming\\IPrody\\IPrody\\", "File2.txt")));
        System.out.println(Arrays.toString(new List[]{fileStorageReader.read("D:\\Programming\\IPrody\\IPrody\\", "File2.txt", 64)}));
    }
}
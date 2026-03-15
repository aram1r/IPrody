import com.iprody.library.controller.ReaderController;
import com.iprody.library.entity.Reader;

public class Main {
    public static void main(String[] args) {
        Reader reader = new Reader();
        reader.setEmail("new@email");
        reader.setName("John");
        reader.setPhone("1232154");

        ReaderController readerController = new ReaderController();

        readerController.createReader("John", "new@email", "1232154");
    }
}
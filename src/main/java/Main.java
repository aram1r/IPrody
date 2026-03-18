import com.example.AppConfig;
import com.example.dao.AuthorDao;
import com.example.dao.BookDao;
import com.example.model.Author;
import com.example.model.Book;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        var context = new AnnotationConfigApplicationContext(AppConfig.class);
//
//        Author author = new Author();
//        author.setName("Филипп Дик");
//        author.setCountry("США");



        AuthorDao authorDao = context.getBean(AuthorDao.class);
//
//        Author author = authorDao.findById(1);
//
        BookDao bookDao = context.getBean(BookDao.class);
//
//        Book book = new Book();
//        book.setAuthor(author);
//        book.setPublishedYear(1968);
//        book.setTitle("Человек в высоком замке");
//        book.setAuthorId(1);
//
//        bookDao.create(book);

        List<Book> book = bookDao.findByAuthorId(1);
        System.out.println(book);

        List<Book> books =  bookDao.findByAuthorId(1);
        for (Book book1 : books) {
            System.out.println(book1);
        }
    }
}
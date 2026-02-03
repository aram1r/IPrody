package libraryAPI.dao;

import libraryAPI.DatabaseHandler;
import libraryAPI.model.Book;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BookDAOImpl implements BookDAO {

    @Override
    public Book addBook(Book book) {
        Book bookResult = null;
        String sql = String.format("insert into \"Library\".books (title, author, published_year, genre) values ('%s', '%s', '%d', '%s')", book.getTitle(), book.getAuthor(), book.getPublished_year(), book.getGenre());
        return executeStatement(bookResult, sql);
    }

    @Override
    public Book updateBook(Book book) {
        Book bookResult = null;
        String sql = String.format("update \"Library\".books set title='%s', author='%s', published_year=%d, genre='%s' where book_id = %d", book.getTitle(), book.getAuthor(), book.getPublished_year(), book.getGenre(), book.getId());
        return executeStatement(bookResult, sql);
    }

    private Book executeStatement(Book bookResult, String sql) {
        System.out.println(sql);
        try (PreparedStatement preparedStatement = DatabaseHandler.getConnection().prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println(resultSet.getString("title") + " вставка прошла успешно");
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return bookResult;
    }
}

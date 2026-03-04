package libraryAPI.dao;

import libraryAPI.DatabaseHandler;
import libraryAPI.model.Book;

import java.sql.*;

public class BookDAOImpl implements BookDAO {

    @Override
    public Book addBook(Book book) {
        Book bookResult = null;
        String sql = "INSERT INTO \"Library\".books (title, author, published_year, genre) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseHandler.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setInt(3, book.getPublished_year());
            stmt.setString(4, book.getGenre());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                return book; // Возвращаем объект, если запись добавлена
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean bookExists(Integer bookId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM \"Library\".books WHERE \"Library\".books.book_id = ?";
        try (Connection conn = DatabaseHandler.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, bookId);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
        }
        return null;
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

    private Book mapResultSetToBook(ResultSet rs) throws SQLException {
        if (rs.next()) {
            Book book = new Book();
            book.setId(rs.getInt("id")); // Предполагаем, что в БД есть колонка id
            book.setTitle(rs.getString("title"));
            book.setAuthor(rs.getString("author"));
            book.setPublished_year(rs.getInt("published_year"));
            book.setGenre(rs.getString("genre"));
            return book;
        }
        return null;
    }
}

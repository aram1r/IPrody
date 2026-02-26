package libraryAPI.dao;

import libraryAPI.DatabaseHandler;
import libraryAPI.model.Reader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReaderDaoImpl implements ReaderDAO {

    @Override
    public Reader addReader(Reader reader) {
        String sql = String.format("insert into \"Library\".readers (name, email, phone) values ('%s', '%s', '%s')",
                reader.getName(), reader.getEmail(), reader.getPhone());

        return executeInsert(reader, sql);
    }

    @Override
    public Reader updateReader(Reader reader) {
        Reader reader1 = null;
        String sql = String.format("update \"Library\".readers set name = %s, email = %s, phone = %s where reader_id = %d", reader.getName(), reader.getEmail(), reader.getPhone(), reader.getId());
        return executeStatement(reader1, sql);
    }

    @Override
    public Boolean readerExists(Integer readerId) {
        String sql = "SELECT COUNT(*) FROM \"Library\".readers WHERE \"Library\".readers.reader_id = ?";
        try (Connection conn = DatabaseHandler.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, readerId);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1); // Берем первую (и единственную) колонку
                return count > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private Reader executeStatement(Reader reader1, String sql) {
        try (PreparedStatement preparedStatement = DatabaseHandler.getConnection().prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println(resultSet.getString("reader_name") + " вставка прошла успешно");
                reader1 = resultSet.getObject("reader_name", Reader.class);
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return reader1;
    }

    private Reader executeInsert(Reader reader, String sql) {
        try (Connection conn = DatabaseHandler.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            int affectedRows = stmt.executeUpdate(); // Выполняем вставку

            if (affectedRows > 0) {
                System.out.println("Вставка прошла успешно");
                return reader; // Возвращаем объект, чтобы сервис увидел, что он != null
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // Если вставка не удалась
    }
}

package com.iprody.library.repository;


import com.iprody.library.entity.Book;
import com.iprody.library.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class BookRepository {

    public Book save(Book book) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.persist(book); // Объект получает сгенерированный ID

            transaction.commit();
            return book; // Возвращаем сохраненный объект
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            // Можно вернуть null или пробросить исключение дальше
            throw new RuntimeException("Ошибка при сохранении книги: " + e.getMessage(), e);
        }
    }

    public Book findById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Book.class, id);
        }
    }

    public List<Book> findBorrowedBooksByReaderId(Long readerId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT b FROM BorrowedBook bb " +
                    "JOIN bb.book b " +
                    "WHERE bb.reader.id = :readerId " +
                    "AND bb.status = 'borrowed'";

            return session.createQuery(hql, Book.class)
                    .setParameter("readerId", readerId)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    public List<Book> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Book", Book.class).list();
        }
    }

    public void delete(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Book book = session.get(Book.class, id);
            if (book != null) {
                session.remove(book);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}
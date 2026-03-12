package com.iprody.library.repository;

import com.iprody.library.entity.Book;
import com.iprody.library.entity.BorrowedBook;
import com.iprody.library.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class BorrowedBookRepository {

    public void save(BorrowedBook borrowedBook) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(borrowedBook);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public BorrowedBook findById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(BorrowedBook.class, id);
        }
    }

    public List<BorrowedBook> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from BorrowedBook", BorrowedBook.class).list();
        }
    }

    public void delete(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            BorrowedBook borrowedBook = session.get(BorrowedBook.class, id);
            if (borrowedBook != null) {
                session.remove(borrowedBook);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}

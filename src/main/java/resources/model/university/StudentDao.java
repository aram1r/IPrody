package resources.model.university;

import org.hibernate.Session;
import org.hibernate.Transaction;
import resources.model.HibernateSession;
import java.util.List;
import java.util.function.Function;

public class StudentDao implements StudentDaoInterface {

    private Session getCurrentSession() {
        return HibernateSession.getSessionFactory().getCurrentSession();
    }

    private <T> T execute(Function<Session, T> action) {
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            try {
                T result = action.apply(session);
                tx.commit();
                return result;
            } catch (Exception e) {
                if (tx.getStatus().canRollback()) tx.rollback();
                throw e;
            }
        }
    }

    @Override
    public void add(Student entity) {
        execute(session -> { session.persist(entity); return null; });
    }

    @Override
    public void update(Student entity) {
        execute(session -> { session.merge(entity); return null; });
    }

    @Override
    public Student findById(String id) {
        return execute(session -> session.get(Student.class, id));
    }

    @Override
    public void delete(Student entity) {
        execute(session -> {
            session.remove(entity);
            return null;
        });
    }

    @Override
    public List<Student> findAll() {
        return execute(session -> session.createSelectionQuery("from Student", Student.class).list());
    }

    @Override
    public Student findByName(String name) {
        return execute(session -> session.createSelectionQuery(
                        "from Student where name = :name", Student.class)
                .setParameter("name", name)
                .uniqueResult()); // вернет один объект или null
    }

    @Override
    public void deleteAll() {
        execute(session -> session.createMutationQuery("delete from Student").executeUpdate());
    }
}
import resources.model.HibernateSession;
import resources.model.university.Student;
import resources.model.university.StudentDao;

public class Main {
    public static void main(String[] args) {
        StudentDao studentDao = new StudentDao();
//        Student student = new Student("John", "johnk@mail.ru");
//        studentDao.delete(student);
//        studentDao.add(student);
//        student.setEmail("john@mail.ru");
//        studentDao.update(student);
//        studentDao.delete(student);
//        Student student1 = new Student("Jackss", "jackss@mail.ru");
//        studentDao.add(student1);

        Student findByName = studentDao.findByName("Jackss");
        System.out.println(findByName);
//        Student student2 = new Student("Karl", "karl@mail.ru");
//        studentDao.add(student2);
        // Добавь это, чтобы убедиться, что всё сохранилось и закрылось корректно
        HibernateSession.getSessionFactory().close();


    }
}
package resources.model.university;

import java.util.List;

public interface StudentDaoInterface {
    void add(Student entity);

    void update(Student entity);

    Student findById(String id);

    void delete(Student entity);

    List<Student> findAll();

    Student findByName(String name);

    void deleteAll();
}

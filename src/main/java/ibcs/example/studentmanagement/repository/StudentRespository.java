package ibcs.example.studentmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ibcs.example.studentmanagement.entity.Student;

public interface StudentRespository extends JpaRepository<Student, Long> {
    List<Student> findByNameContaining(String name);

    List<Student> findByEmail(String email);

    List<Student> findByNameAndEmail(String name, String email);

    List<Student> findByCourses_NameContaining(String courseName);

    List<Student> findByNameContainingAndCourses_NameContaining(String name, String courseName);

}

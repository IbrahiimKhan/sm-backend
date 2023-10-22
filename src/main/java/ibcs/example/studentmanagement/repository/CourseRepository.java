package ibcs.example.studentmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ibcs.example.studentmanagement.entity.Course;
public interface CourseRepository extends JpaRepository<Course,Long> {
    
}

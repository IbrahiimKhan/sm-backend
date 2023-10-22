package ibcs.example.studentmanagement.entity;

import org.springframework.web.bind.annotation.CrossOrigin;

import jakarta.persistence.*;

@Entity
@Table(name = "Courses")
@CrossOrigin(origins = "*")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    // Add a default (no-argument) constructor
    public Course() {
    }

    public Course(String name, String description, Student student) {
        this.name = name;
        this.description = description;
        this.student = student;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}

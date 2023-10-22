package ibcs.example.studentmanagement.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Pageable;
import ibcs.example.studentmanagement.entity.Course;
import ibcs.example.studentmanagement.entity.Student;
import ibcs.example.studentmanagement.repository.CourseRepository;
import ibcs.example.studentmanagement.repository.StudentRespository;

@RestController
@CrossOrigin(origins = "*")
public class MainController {

	@Autowired
	private StudentRespository studentRepo;
	@Autowired
	private CourseRepository courseRepo;

	@GetMapping("/")
	public String hello() {
		return "Hellow from ibcs...";
	}

	@GetMapping("/student/all")
	public List<Student> filterStudents(
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "course", required = false) String courseName) {

		if (name != null && courseName != null) {
			// Filter students by name and course
			return studentRepo.findByNameContainingAndCourses_NameContaining(name, courseName);
		} else if (name != null) {
			// Filter students by name
			return studentRepo.findByNameContaining(name);
		} else if (courseName != null) {
			// Filter students by course
			return studentRepo.findByCourses_NameContaining(courseName);
		} else {
			// Return all students if no filters are specified
			return studentRepo.findAll();
		}
	}

	@GetMapping("/student/{id}")
	public Optional<Student> getStudent(@PathVariable("id") Long id) {
		return studentRepo.findById(id);
	}

	@GetMapping("/course/all")
	public List<Course> getCourses() {
		return courseRepo.findAll();
	}

	@GetMapping("/course/{id}")
	public Optional<Course> getCourse(@PathVariable("id") Long id) {
		return courseRepo.findById(id);
	}

	@PostMapping("/student/add")
	public Student addStudent(@RequestBody Student student) {
		return studentRepo.save(student);
	}

	@PostMapping("/course/add")
	public Course addCourse(@RequestBody Course course) {
		return courseRepo.save(course);
	}

	@PostMapping("/student/update")
	public Student updateStudent(@RequestBody Student student) {
		return studentRepo.save(student);
	}

	@GetMapping("/student/{id}/delete")
	public String deleteStudent(@PathVariable("id") Long id) {
		try {
			studentRepo.deleteById(id);
		} catch (Exception ex) {
			return "Failed";
		}

		return "Successful";
	}

	@GetMapping("/course/{id}/delete")
	public String deleteCourse(@PathVariable("id") Long id) {
		try {
			courseRepo.deleteById(id);
		} catch (Exception ex) {
			return "Failed";
		}

		return "Successful";
	}

	@PostMapping("/course/update")
	public Course updateCourse(@RequestBody Course course) {
		return courseRepo.save(course);
	}

	@GetMapping("/student/search")
	public List<Student> searchStudents(@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "email", required = false) String email) {
		if (name != null && email != null) {
			return studentRepo.findByNameContaining(name);
		} else if (name != null) {
			return studentRepo.findByNameContaining(name);
		} else if (email != null) {
			return studentRepo.findByEmail(email);
		} else {
			return studentRepo.findAll();
		}
	}

}

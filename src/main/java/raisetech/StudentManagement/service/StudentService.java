package raisetech.StudentManagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentsCourses;
import raisetech.StudentManagement.repository.StudentRepository;

@Service
public class StudentService {

  private StudentRepository repository;

  @Autowired
  public StudentService(StudentRepository repository) {
    this.repository = repository;
  }

  public List<Student> searchStudentList() {

    return repository.search().stream()
        .filter(student -> student.getYears() >= 30 && student.getYears() <= 39).collect(
            Collectors.toList());

  }

  public List<StudentsCourses> searchStudentsCoursesList() {
    return repository.searchStudentsCourses().stream()
        .filter(StudentsCourses -> StudentsCourses.getCourses().contains("JAVAコース"))
        .collect(Collectors.toList());
  }
}

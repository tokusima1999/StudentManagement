package raisetech.StudentManagement.domain;

import java.util.List;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentsCourses;

@Getter
@Setter

public class StudentDetail {

  private Student student;
  private List<StudentsCourses> studentsCourses;

  public Long getId() {
    return student != null ? student.getId() : null;
  }

}

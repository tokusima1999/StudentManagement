package raisetech.StudentManagement.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentCourse;

@Getter
@Setter
@JsonIgnoreProperties({"id"})  // id フィールドを除外

public class StudentResponseDTO {

  private Student student;
  private List<StudentCourse> studentCourseList;
}

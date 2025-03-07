package raisetech.StudentManagement.domain;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentCourse;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class StudentDetail {

  @Valid
  @NotNull(message = "情報が不足しています。再度入力してください。")
  private Student student;
  private List<StudentCourse> studentCourseList;

  public Long getId() {
    return student != null ? student.getId() : null;
  }

}

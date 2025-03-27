package raisetech.StudentManagement.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentCourse;

@Schema(description = "受講生詳細情報")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Validated
@Data
public class StudentDetail {

  @Valid
  @NotNull(message = "情報が不足しています。再度入力してください。")
  Student student;
  private List<StudentCourse> studentCourseList;

  @JsonIgnore
  public Long getId() {
    return student != null ? student.getId() : null;
  }

}

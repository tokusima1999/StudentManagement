package raisetech.StudentManagement.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 新規登録時に使用されるフォームです。
 */
@Getter
@Setter
public class StudentRegisterForm {

  private StudentForm student = new StudentForm();
  private StudentCourseForm studentCourse = new StudentCourseForm();
}
